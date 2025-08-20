package pl.kurs.ex2.models;

import java.time.LocalDate;

public class Visit {
    private Doctor doctor;
    private Patient patient;
    private LocalDate visitDate;

    private Visit(Doctor doctor, Patient patient, LocalDate visitDate) {
        this.doctor = doctor;
        this.patient = patient;
        this.visitDate = visitDate;
    }

    public static Visit createVisitFromLine(Doctor doctor, Patient patient, LocalDate visitDate){
        Visit visit = new Visit(doctor, patient, visitDate);
        doctor.getVisits().add(visit);
        patient.getVisits().add(visit);
        return visit;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    @Override
    public String toString() {
        return "Visit{" +
                " doctor=" + doctor +
                ", patient=" + patient +
                ", visitDate=" + visitDate +
                '}';
    }
}
