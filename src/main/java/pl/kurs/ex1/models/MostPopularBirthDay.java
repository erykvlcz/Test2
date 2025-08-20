package pl.kurs.ex1.models;

import java.time.DayOfWeek;
import java.util.List;

public class MostPopularBirthDay {
    private DayOfWeek mostPopularDayOfWeek;
    private int birthsNumber;
    private List<Newborn> newborns;

    private MostPopularBirthDay(List<Newborn> newborns) {
        this.newborns = newborns;
    }

    public static MostPopularBirthDay create(List<Newborn> newborns){
        MostPopularBirthDay mpbd = new MostPopularBirthDay(newborns);
        mpbd.findMostPopularDayOfWeek(newborns);
        return mpbd;
    }

    private void findMostPopularDayOfWeek(List<Newborn> newborns) {
        int[] daysCounter = new int[DayOfWeek.values().length];
        for (Newborn newborn : newborns) {
            switch (newborn.getBirthDate().getDayOfWeek()){
                case MONDAY -> daysCounter[0] ++;
                case TUESDAY -> daysCounter[1] ++;
                case WEDNESDAY -> daysCounter[2] ++;
                case THURSDAY -> daysCounter[3] ++;
                case FRIDAY -> daysCounter[4] ++;
                case SATURDAY -> daysCounter[5] ++;
                case SUNDAY -> daysCounter[6] ++;
            }
        }

        int biggestIndex = 0;
        for (int i = 1; i < daysCounter.length; i++) {
            if(daysCounter[i] > daysCounter[biggestIndex]){
                biggestIndex = i;
            }
        }

        mostPopularDayOfWeek = DayOfWeek.of(biggestIndex + 1);
        birthsNumber = daysCounter[biggestIndex];

    }

    public DayOfWeek getMostPopularDayOfWeek() {
        return mostPopularDayOfWeek;
    }

    public void setMostPopularDayOfWeek(DayOfWeek mostPopularDayOfWeek) {
        this.mostPopularDayOfWeek = mostPopularDayOfWeek;
    }

    public int getBirthsNumber() {
        return birthsNumber;
    }

    public void setBirthsNumber(int birthsNumber) {
        this.birthsNumber = birthsNumber;
    }

    public List<Newborn> getNewborns() {
        return newborns;
    }

    public void setNewborns(List<Newborn> newborns) {
        this.newborns = newborns;
    }

    @Override
    public String toString() {
        return "Most popular birthday = " +
                  mostPopularDayOfWeek.name() +
                ", births number = " + birthsNumber;
    }
}
