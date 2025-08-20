package pl.kurs.ex2.models;

import java.time.LocalDate;

public class Patient extends Person{

    private Patient(int id, String lastname, String firstname, String pesel, LocalDate birthDate) {
        super(id, lastname, firstname, pesel, birthDate);
    }

    public static Patient createFromLine(String line){
        String[] values = line.trim().split("\t");
        String[] date = values[4].split("-");
        return new Patient(
                Integer.valueOf(values[0]),
                values[1],
                values[2],
                values[3],
                LocalDate.of(Integer.valueOf(date[0]),
                        Integer.valueOf(date[1]),
                        Integer.valueOf(date[2]))
        );
    }

    @Override
    public String toString() {
        return super.toString() + " {}";
    }
}
