package pl.kurs.ex2.models;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class Person {
    public static final Comparator<Person> BY_VISIT_NUMBER_COMPARATOR = new ByVisitNumberComparator();
    public static final Comparator<Person> BY_BIRTH_DATE_COMPARATOR = new ByBirthDateComparator();

    private int id;
    private String lastname;
    private String firstname;
    private String pesel;
    private LocalDate birthDate;
    private List<Visit> visits = new ArrayList<>();

    public Person(int id, String lastname, String firstname, String pesel, LocalDate birthDate) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.pesel = pesel;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " +
                " id=" + id +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", pesel='" + pesel + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    private static class ByVisitNumberComparator implements Comparator<Person>{
        @Override
        public int compare(Person o1, Person o2) {
            return Integer.compare(o2.getVisits().size(), o1.getVisits().size());
        }
    }

    private static class ByBirthDateComparator implements Comparator<Person>{
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getBirthDate().compareTo(o2.getBirthDate());
        }
    }
}
