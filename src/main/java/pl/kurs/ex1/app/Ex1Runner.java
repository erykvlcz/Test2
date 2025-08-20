package pl.kurs.ex1.app;

import pl.kurs.ex1.models.enums.Sex;
import pl.kurs.ex1.models.MostPopularBirthDay;
import pl.kurs.ex1.models.Mother;
import pl.kurs.ex1.models.Newborn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.*;

public class Ex1Runner {
    public static void main(String[] args) {
        Map<Integer, Mother> mothers = readMothersFromFile();
        List<Newborn> newborns = readNewbornsFromFile(mothers);

        List<Newborn> tallestBoy = findTallestNewborn(newborns, Sex.MALE);
        List<Newborn> tallestGirl = findTallestNewborn(newborns, Sex.FEMALE);
        for (Newborn newbornB : tallestBoy) {
            System.out.println("Name of the tallest boy: " + newbornB.getFirstname() + ", height: " + newbornB .getHeightInCm() + "cm.");
        }
        for (Newborn newbornG : tallestGirl) {
            System.out.println("Name of the tallest girl: " + newbornG.getFirstname() + ", height: " + newbornG .getHeightInCm() + "cm.");
        }

        System.out.println();
        MostPopularBirthDay mostPopularBirthDay = MostPopularBirthDay.create(newborns);
        System.out.println(mostPopularBirthDay);

        System.out.println();
        List<Mother> mothersUnder25WithNewbornWightOver4000 = findMothersWithAgeUnderAndNewbornWeightOver(mothers,25,4000);
        System.out.println("Names of mothers under 25 years of age who gave birth to children over 4000g: ");
        for (Mother mother : mothersUnder25WithNewbornWightOver4000) {
            System.out.println(mother.getFirstname());
        }

        System.out.println();
        List<Newborn> daughtersWithMotherName = getDaughtersWithMotherName(mothers);
        System.out.println("Names and dates of birth of girls who inherited their mother's name: ");
        for (Newborn newborn : daughtersWithMotherName) {
            System.out.println("Firstname: " + newborn.getFirstname() + ", birthdate: " + newborn.getBirthDate());
        }

        System.out.println();
        List<Mother> mothersWhoHaveTwins = getMothersWhoHaveTwins(mothers);
        System.out.println("Mothers who have twins: ");
        for (Mother motherWhoHaveTwins : mothersWhoHaveTwins) {
            System.out.println(motherWhoHaveTwins);
        }
    }

    private static List<Mother> getMothersWhoHaveTwins(Map<Integer, Mother> mothers) {
        List<Mother> mothersWithTwins = new ArrayList<>();
        for (Mother mother : mothers.values()) {
            Set<LocalDate> uniqueBirthDates = new HashSet<>();
            boolean hasTwins = false;
            for (Newborn newborn : mother.getNewborns()) {
                if(!uniqueBirthDates.add(newborn.getBirthDate())){
                    hasTwins = true;
                    break;
                }
            }
            if(hasTwins){
                mothersWithTwins.add(mother);
            }
        }
        return mothersWithTwins;
    }

    private static List<Newborn> getDaughtersWithMotherName(Map<Integer, Mother> mothers) {
        List<Newborn> daughtersWithMotherName = new ArrayList<>();
        for (Mother mother : mothers.values()) {
            for (Newborn newborn : mother.getNewborns()) {
                if(newborn.getSex() == Sex.FEMALE){
                    if(newborn.getFirstname().equalsIgnoreCase(mother.getFirstname())){
                        daughtersWithMotherName.add(newborn);
                    }
                }
            }
        }
        return daughtersWithMotherName;
    }

    private static List<Mother> findMothersWithAgeUnderAndNewbornWeightOver(Map<Integer, Mother> mothers, int age, int weightGrams) {
        if(mothers.isEmpty()) throw new IllegalArgumentException("Mothers list is empty");
        if(age < 1) throw new IllegalArgumentException("Age can not be below 0");
        if(weightGrams < 1) throw new IllegalArgumentException("Weight can not be below 0");

        List<Mother> mothersUnderSelectedAge = getMothersUnderSelectedAge(mothers, age);
        List<Mother> mothersWhoChildrenWeightsOver = mothersWhoChildrenWeightOver(mothersUnderSelectedAge, weightGrams);
        return mothersWhoChildrenWeightsOver;
    }

    private static List<Mother> mothersWhoChildrenWeightOver(List<Mother> mothersUnderSelectedAge, int weightGrams) {
        List<Mother> selectedMothers = new ArrayList<>();
        for (Mother mother : mothersUnderSelectedAge) {
            boolean weightOver = false;
            for (Newborn newborn : mother.getNewborns()) {
                if(newborn.getWeightInGrams() > weightGrams){
                    weightOver = true;
                    break;
                }
            }
            if(weightOver){
                selectedMothers.add(mother);
            }
        }

        return selectedMothers;
    }

    private static List<Mother> getMothersUnderSelectedAge(Map<Integer, Mother> mothers, int age) {
        List<Mother> mothersUnderSelectedAge = new ArrayList<>();
        for (Mother mother : mothers.values()) {
            if(mother.getAge() < age){
                mothersUnderSelectedAge.add(mother);
            }
        }
        return mothersUnderSelectedAge;
    }

    private static List<Newborn> findTallestNewborn(List<Newborn> newborns, Sex sex) {
        List<Newborn> tallestNewborns = new ArrayList<>();
        if(newborns.isEmpty()) new ArrayList<>();
        Collections.sort(newborns, Newborn.HEIGHT_DESCENDING_COMPARATOR);
        int heightOfTallestNewborn = 0;

        for (Newborn newborn : newborns) {
            if(newborn.getSex() == sex){
                heightOfTallestNewborn = newborn.getHeightInCm();
                break;
            }
        }

        for (Newborn newborn : newborns) {
            if(newborn.getSex() == sex){
                if(newborn.getHeightInCm() == heightOfTallestNewborn){
                    tallestNewborns.add(newborn);
                }
            }
        }
        return tallestNewborns;
    }

    private static List<Newborn> readNewbornsFromFile(Map<Integer, Mother> mothers) {
        List<Newborn> newborns = new ArrayList<>();
        try (
                BufferedReader br = new BufferedReader(new FileReader("noworodki.txt"))
        ){
            String line = null;
            while ((line = br.readLine()) != null){
                newborns.add(Newborn.createFromLine(line, mothers));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return newborns;
    }

    private static Map<Integer, Mother> readMothersFromFile() {
        Map<Integer, Mother> mothers = new HashMap<>();
        try (
                BufferedReader br = new BufferedReader(new FileReader("mamy.txt"))
        ){
            String line = null;
            while ((line = br.readLine()) != null){
                Mother mother = Mother.createFromLine(line);
                mothers.put(mother.getId(), mother);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mothers;
    }





}
