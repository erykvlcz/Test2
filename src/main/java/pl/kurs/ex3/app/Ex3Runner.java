package pl.kurs.ex3.app;

import pl.kurs.ex3.models.*;
import pl.kurs.ex3.models.enums.Group;
import pl.kurs.ex3.models.enums.Job;
import pl.kurs.ex3.models.enums.Sex;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Ex3Runner {
    public static void main(String[] args) {
        Person[] persons = {
                new Student("Jan", "Kowalski", "85032650715", "Warsaw", Group.COMPUTER_SCIENCE, new BigDecimal("1500")),
                new Student("Anna", "Nowak", "91051413723", "Krakow", Group.LAW, new BigDecimal("8000")),
                new Student("Piotr", "Wiśniewski", "99051263997", "Gdansk", Group.ELECTRONICS, new BigDecimal("4999.10")),
                new Student("Katarzyna", "Wójcik", "87110327405", "Poznan", Group.PSYCHOLOGY, new BigDecimal("1400")),
                new Student("Marek", "Lewandowski", "96020843037", "Wroclaw", Group.COMPUTER_SCIENCE, new BigDecimal("1601.7")),
                new Employee("Tomasz", "Kaczmarek", "84041646775", "Warsaw", Job.MANAGER, new BigDecimal("2000.11")),
                new Employee("Monika", "Krawczyk", "02222841184", "Krakow", Job.ENGINEER, new BigDecimal("6000")),
                new Employee("Paweł", "Sikorski", "58060343799", "Gdansk", Job.ACCOUNTANT, new BigDecimal("5400.50")),
                new Employee("Joanna", "Zielińska", "75090754821", "Poznan", Job.PROGRAMMER, new BigDecimal("9650")),
                new Employee("Łukasz", "Kamiński", "01231584459", "Wroclaw", Job.MARKETING_SPECIALIST, new BigDecimal("5500"))
        };

        List<Person> personWithHighestIncome = getPersonWithHighestIncome(persons);
        System.out.println("Person with highest income: " + personWithHighestIncome);

        int womanNumberInArray = calculateWomanNumberInArray(persons);
        System.out.println("Number of women in array: " + womanNumberInArray);

        List<Person> personsList = new ArrayList<>(Arrays.asList(persons));
        writePersonListToFile(personsList, "peoplelist.person");

        List<Person> loadedPersonsList = loadPersonsList("peoplelist.person");

    }

    private static List<Person> loadPersonsList(String filename) {
        List<Person> loadedPersons = null;
        try (
                FileInputStream fis = new FileInputStream(filename);
                ObjectInputStream ois = new ObjectInputStream(fis);
        ){
            loadedPersons = (List<Person>) ois.readObject();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();

        }
        return loadedPersons;
    }

    private static void writePersonListToFile(List<Person> people, String filename) {
        try (
                FileOutputStream fos = new FileOutputStream(filename);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ){

            oos.writeObject(people);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static int calculateWomanNumberInArray(Person[] people) {
        int womanCounter = 0;
        for (int i = 0; i < people.length; i++) {
            if(people[i].getPlec() == Sex.FEMALE) womanCounter++;
        }
        return womanCounter;
    }

    private static List<Person> getPersonWithHighestIncome(Person[] persons) {
        if(persons.length == 0) Collections.emptyList();
        List<Person> people = new ArrayList<>();
        Arrays.sort(persons, Person.BY_GET_IN_COME_DESCENDING_COMPARATOR);
        BigDecimal maxSalary = persons[0].getIncome();
        for (Person person : persons) {
            if(person.getIncome().compareTo(maxSalary) == 0){
                people.add(person);
            }
        }
        return people;
    }

}
