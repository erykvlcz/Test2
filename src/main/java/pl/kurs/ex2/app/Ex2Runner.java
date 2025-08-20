package pl.kurs.ex2.app;

import pl.kurs.ex2.models.*;
import pl.kurs.ex2.models.enums.Specialization;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.Year;
import java.util.*;

public class Ex2Runner {
    public static void main(String[] args) {
        List<Patient> patients = loadPatientsFromFile();
        List<Doctor> doctors = loadDoctorsFromFile();
        List<Visit> visits = loadVisitsFromFile(doctors, patients);

        List<Doctor> doctorsWithLargestVisitsNumber = getDoctorWithLargestVisitsNumber(doctors);
        System.out.println("Doctor with biggest visits number:");
        for (Doctor doctor : doctorsWithLargestVisitsNumber) {
            System.out.println(doctor);
        }

        System.out.println();
        List<Patient> patientsWithLargestVisitsNumber = getPatientsWithLargestVisitsNumber(patients);
        System.out.println("Patient with biggest visits number:");
        for (Patient patient : patientsWithLargestVisitsNumber) {
            System.out.println(patient);
        }

        System.out.println();
        List<Specialization> mostPopularSpecialization = getMostPopularSpecialization(doctors);
        System.out.println("The most popular specialization");
        for (Specialization specialization : mostPopularSpecialization) {
            System.out.println(specialization);
        }

        System.out.println();
        List<Year> yearWithTheHighestVisitNumber = getYearWithTheHighestVisitNumber(visits);
        System.out.println("Year with the highest visit number:");
        for (Year year : yearWithTheHighestVisitNumber) {
            System.out.println(year);
        }

        System.out.println();
        System.out.println("Show Top 5 oldest doctors");
        showTop5OldestDoctors(doctors);

        System.out.println();
        System.out.println("Show Top 5 doctors with highest visits number");
        showTop5DoctorsWithHighestVisitsNumber(doctors);


        System.out.println();
        List<Patient> patientsWithMin5VisitedDoctors = getPatientsWithMin5VisitedDoctors(patients);
        System.out.println("Patient with minimum 5 visited doctors: ");
        for (Patient patientsWithMin5VisitedDoctor : patientsWithMin5VisitedDoctors) {
            System.out.println(patientsWithMin5VisitedDoctor);
        }

        System.out.println();
        List<Doctor> doctorsWhoHaveOnlyOnePatient = getDoctorsWhoHaveOnlyOnePatient(doctors);
        System.out.println("Doctor who has only one patient: ");
        for (Doctor doctor : doctorsWhoHaveOnlyOnePatient) {
            System.out.println(doctor);
        }
    }

    private static List<Visit> loadVisitsFromFile(List<Doctor> doctors, List<Patient> patients) {
        List<Visit> visits = new ArrayList<>();
        try (
                BufferedReader br = new BufferedReader(new FileReader("wizyty.txt"))
        ){
            br.readLine();
            Set<Integer> doctorsId = addDoctorsId(doctors);
            Set<Integer> patientsId = addPatientsId(patients);

            String line = null;
            while ((line = br.readLine()) != null){
                if(checkAreVisitDataCorrect(line, doctorsId,patientsId)){
                    String[] values = line.trim().split("\t");
                    String[] date = values[2].split("-");

                    Doctor doctorFromVisit = getDoctor(Integer.valueOf(values[0]), doctors);
                    Patient patientFromVisit = getPatient(Integer.valueOf(values[1]), patients);
                    visits.add(Visit.createVisitFromLine(doctorFromVisit, patientFromVisit, LocalDate.of(Integer.valueOf(date[0]),
                            Integer.valueOf(date[1]),
                            Integer.valueOf(date[2])))
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return visits;
    }

    private static List<Doctor> loadDoctorsFromFile() {
        List<Doctor> doctors = new ArrayList<>();

        try (
                BufferedReader br = new BufferedReader(new FileReader("lekarze.txt"))
        ){
            br.readLine();
            String line = null;
            while ((line = br.readLine()) != null){
                Doctor newDoctor = Doctor.createFromLine(line);
                if(!checkMaliciousDoctorData(doctors, newDoctor)){
                    doctors.add(newDoctor);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctors;
    }

    private static List<Patient> loadPatientsFromFile() {
        List<Patient> patients = new ArrayList<>();
        try (
                BufferedReader br = new BufferedReader(new FileReader("pacjenci.txt"))
        ){
            br.readLine();
            String line = null;
            while ((line = br.readLine()) != null){
                Patient newPatient = Patient.createFromLine(line);
                if(!checkMaliciousPatientData(patients, newPatient)){
                    patients.add(newPatient);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }

    private static List<Doctor> getDoctorsWhoHaveOnlyOnePatient(List<Doctor> doctors) {
        List<Doctor> doctorsList = new ArrayList<>();
        for (Doctor doctor : doctors) {
            List<Patient> admittedPatient = new ArrayList<>();
            for (Visit visit : doctor.getVisits()) {
                admittedPatient.add(visit.getPatient());
            }
            if(admittedPatient.size() < 2 && admittedPatient.size() > 0){
                doctorsList.add(doctor);
            }
        }
        return doctorsList;
    }

    private static List<Patient> getPatientsWithMin5VisitedDoctors(List<Patient> patients) {
        List<Patient> patientsList = new ArrayList<>();
        for (Patient patient : patients) {
            Set<Doctor> differendDoctorVisited = new HashSet<>();
            for (Visit visit : patient.getVisits()) {
                differendDoctorVisited.add(visit.getDoctor());
            }
            if(differendDoctorVisited.size() >= 5){
                patientsList.add(patient);
            }
        }
        return patientsList;
    }

    private static void showTop5DoctorsWithHighestVisitsNumber(List<Doctor> doctors) {
        if(doctors.isEmpty()) new ArrayList<>();

        Collections.sort(doctors, Person.BY_VISIT_NUMBER_COMPARATOR);
        for (int i = 0; i < Math.min(5, doctors.size()); i++) {
            System.out.println(doctors.get(i) + ", visits number: " + doctors.get(i).getVisits().size());
        }
    }

    private static void showTop5OldestDoctors(List<Doctor> doctors) {
        Collections.sort(doctors, Person.BY_BIRTH_DATE_COMPARATOR);
        for (int i = 0; i < Math.min(5, doctors.size()); i++) {
            System.out.println(doctors.get(i));
        }
    }

    private static List<Year> getYearWithTheHighestVisitNumber(List<Visit> visits) {
        if(visits.isEmpty()) new ArrayList<>();
        Map<Year,Integer> yearCounter = new HashMap<>();
        for (Visit visit : visits) {
            yearCounter.put(Year.of(visit.getVisitDate().getYear()), yearCounter.getOrDefault(Year.of(visit.getVisitDate().getYear()), 0) + 1);
        }
        int biggestVisitsNumber = Collections.max(yearCounter.entrySet(), Map.Entry.comparingByValue()).getValue();
        return getMostVisitedYears(yearCounter, biggestVisitsNumber);
    }

    private static List<Year> getMostVisitedYears(Map<Year, Integer> yearCounter, int biggestVisitsNumber) {
        List<Year> years = new ArrayList<>();
        for (Map.Entry<Year, Integer> entry : yearCounter.entrySet()) {
            if (entry.getValue().equals(biggestVisitsNumber)) {
                years.add(entry.getKey());
            }
        }
        return years;
    }

    private static List<Specialization>  getMostPopularSpecialization(List<Doctor> doctors) {
        if(doctors.isEmpty()) new ArrayList<>();
        Map<Specialization, Integer> specializationCounter = new HashMap<>();
        for (Doctor doctor : doctors) {
            specializationCounter.put(doctor.getSpecialization(), specializationCounter.getOrDefault(doctor.getSpecialization(), 0) + 1);
        }

        int biggestVisitsNumber = Collections.max(specializationCounter.entrySet(), Map.Entry.comparingByValue()).getValue();
        return getSpecializations(specializationCounter, biggestVisitsNumber);
    }

    private static List<Specialization> getSpecializations(Map<Specialization, Integer> specializationCounter, int biggestVisitsNumber) {
        List<Specialization> specializationList = new ArrayList<>();
        for (Map.Entry<Specialization, Integer> entry : specializationCounter.entrySet()) {
            if (entry.getValue().equals(biggestVisitsNumber)) {
                specializationList.add(entry.getKey());
            }
        }
        return specializationList;
    }

    private static List<Patient> getPatientsWithLargestVisitsNumber(List<Patient> patients) {
        if(patients.isEmpty()) new ArrayList<>();
        Collections.sort(patients, Person.BY_VISIT_NUMBER_COMPARATOR);
        int biggestDoctorVisitsNumber = patients.get(0).getVisits().size();
        List<Patient> patientsList  = new ArrayList<>();
        for (Patient patient : patients) {
            if(patient.getVisits().size() == biggestDoctorVisitsNumber){
                patientsList.add(patient);
            }
        }
        return patientsList;
    }

    private static List<Doctor> getDoctorWithLargestVisitsNumber(List<Doctor> doctors) {
        if(doctors.isEmpty()) new ArrayList<>();
        Collections.sort(doctors, Person.BY_VISIT_NUMBER_COMPARATOR);
        int largestDoctorVisitsNumber = doctors.get(0).getVisits().size();
        List<Doctor> doctorsList = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if(doctor.getVisits().size() == largestDoctorVisitsNumber){
                doctorsList.add(doctor);
            }
        }
        return doctorsList;
    }

    private static Patient getPatient(Integer id, List<Patient> patients) {
        for (Patient patient : patients) {
            if(patient.getId() == id){
                return patient;
            }
        }
        return null;
    }

    private static Doctor getDoctor(Integer id, List<Doctor> doctors) {
        for (Doctor doc : doctors) {
            if(doc.getId() == id){
                return doc;
            }
        }
        return null;
    }

    private static Set<Integer> addDoctorsId(List<Doctor> doctors) {
        Set<Integer> set = new HashSet<>();
        for (Doctor d : doctors) {
            set.add(d.getId());
        }
        return set;
    }

    private static Set<Integer> addPatientsId(List<Patient> patients) {
        Set<Integer> set = new HashSet<>();
        for (Patient p : patients) {
            set.add(p.getId());
        }
        return set;
    }

    private static boolean checkAreVisitDataCorrect(String line, Set<Integer> doctorsId, Set<Integer> patientsId) {
        String[] data = line.trim().split("\t");
        int doctorId = Integer.parseInt(data[0]);
        int patientId = Integer.parseInt(data[1]);
        return doctorsId.contains(doctorId) && patientsId.contains(patientId);
    }

    private static boolean checkMaliciousPatientData(List<Patient> patients, Patient newPatient) {
        for (Patient patient: patients) {
            if(patient.getId() == newPatient.getId() || patient.getPesel().equals(newPatient.getPesel())){
                return true;
            }
        }
        return false;
    }

    private static boolean checkMaliciousDoctorData(List<Doctor> doctors, Doctor newDoctor) {
        for (Doctor doctor : doctors) {
            if(doctor.getId() == newDoctor.getId() || doctor.getNip().equals(newDoctor.getNip()) || doctor.getPesel().equals(newDoctor.getPesel())){
                return true;
            }
        }
        return false;
    }
}
