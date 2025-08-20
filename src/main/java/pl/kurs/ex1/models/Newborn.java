package pl.kurs.ex1.models;

import pl.kurs.ex1.models.enums.Sex;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Map;

public class Newborn extends Person{
    public static final Comparator<Newborn> HEIGHT_DESCENDING_COMPARATOR = new HeightComparator();

    private Sex sex;
    private LocalDate birthDate;
    private int weightInGrams;
    private int heightInCm;
    private Mother mother;

    private Newborn(int id, Sex sex, String name, LocalDate birthDate, int weightInGrams, int heightInCm, Mother mother) {
        super(id, name);
        this.sex = sex;
        this.birthDate = birthDate;
        this.weightInGrams = weightInGrams;
        this.heightInCm = heightInCm;
        this.mother = mother;
    }

    private static Newborn create(int id, Sex sex, String name, LocalDate birthDate, int weightInGrams, int heightInCm, Mother mother){
        Newborn newborn = new Newborn(id, sex, name, birthDate, weightInGrams, heightInCm, mother);
        mother.getNewborns().add(newborn);
        return newborn;
    }

    public static Newborn createFromLine(String line, Map<Integer, Mother> mothersMap){

        String values[] = line.trim().split(" ");

        return create(
                Integer.valueOf(values[0]),
                Sex.setSexFromString(values[1]),
                values[2],
                LocalDate.parse(values[3]),
                Integer.valueOf(values[4]),
                Integer.valueOf(values[5]),
                mothersMap.get(Integer.valueOf(values[6]))
        );
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getWeightInGrams() {
        return weightInGrams;
    }

    public void setWeightInGrams(int weightInGrams) {
        this.weightInGrams = weightInGrams;
    }

    public int getHeightInCm() {
        return heightInCm;
    }

    public void setHeightInCm(int heightInCm) {
        this.heightInCm = heightInCm;
    }

    public Mother getMother() {
        return mother;
    }

    public void setMother(Mother mother) {
        this.mother = mother;
    }

    @Override
    public String toString() {
        return super.toString() +
                "sex='" + sex + '\'' +
                ", birthDate=" + birthDate +
                ", weightInGrams=" + weightInGrams +
                ", heightInCm=" + heightInCm +
                ", mother=" + mother +
                '}';
    }

    private static class HeightComparator implements Comparator<Newborn>{
        @Override
        public int compare(Newborn o1, Newborn o2) {
            return Integer.compare(o2.getHeightInCm(), o1.getHeightInCm());
        }
    }
}
