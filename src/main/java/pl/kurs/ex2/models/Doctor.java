package pl.kurs.ex2.models;

import pl.kurs.ex2.models.enums.Specialization;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Objects;

public class Doctor extends Person{
    private Specialization specialization;
    private String nip;

    private Doctor(int id, String lastname, String firstname, String pesel, LocalDate birthDate, Specialization specialization, String nip) {
        super(id, lastname, firstname, pesel, birthDate);
        this.specialization = specialization;
        this.nip = nip;
    }

    public static Doctor createFromLine(String line) {
        String[] values = line.split("\t");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return new Doctor(
                Integer.valueOf(values[0]),
                values[1],
                values[2],
                values[6],
                LocalDate.parse(values[4], dateFormat),
                addSpecialization(values[3]),
                values[5].replace("-", "")
        );
    }

    private static Specialization addSpecialization(String value) {
        for (Specialization specialization : Specialization.values()) {
            if (specialization.name().equalsIgnoreCase(value)) {
                return specialization;
            }
        }
        return null;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return getId() == doctor.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return super.toString() + " {" +
                " specialization=" + specialization +
                ", nip='" + nip + '\'' +
                '}';
    }

}
