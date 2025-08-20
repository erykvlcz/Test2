package pl.kurs.ex3.models;

import pl.kurs.ex3.models.enums.Group;

import java.math.BigDecimal;

public class Student extends Person{
    private Group group;
    private BigDecimal scholarship;

    public Student(String firstname, String lastname, String pesel, String city, Group group, BigDecimal scholarship) {
        super(firstname, lastname, pesel, city);
        this.group = group;
        this.scholarship = scholarship;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public BigDecimal getScholarship() {
        return scholarship;
    }

    public void setScholarship(BigDecimal scholarship) {
        this.scholarship = scholarship;
    }

    @Override
    public String toString() {
        return super.toString()  +
                " group='" + group + '\'' +
                ", scholarship=" + scholarship +
                '}';
    }

    @Override
    public BigDecimal getIncome() {
        return scholarship;
    }
}
