package pl.kurs.ex3.models;

import pl.kurs.ex3.models.enums.Sex;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;

public abstract class Person implements Serializable {
    public static final Comparator<Person> BY_GET_IN_COME_DESCENDING_COMPARATOR = new ByGetInComeDescendingComparator();
    private static final long serialVersionUID = 1L;

    private String firstname;
    private String lastname;
    private String pesel;
    private String city;

    public Person(String firstname, String lastname, String pesel, String city) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.pesel = pesel;
        this.city = city;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Sex getPlec(){
        int gender = Character.getNumericValue(pesel.charAt(9));
        if(gender % 2 == 0){
            return Sex.FEMALE;
        }else {
            return Sex.MALE;
        }
    }

    public abstract BigDecimal getIncome();

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", pesel='" + pesel + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    private static class ByGetInComeDescendingComparator implements Comparator<Person>{

        @Override
        public int compare(Person o1, Person o2) {
            return o2.getIncome().compareTo(o1.getIncome());
        }
    }
}
