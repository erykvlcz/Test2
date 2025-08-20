package pl.kurs.ex3.models;

import pl.kurs.ex3.models.enums.Job;

import java.math.BigDecimal;

public class Employee extends Person {
    private Job job;
    private BigDecimal salary;

    public Employee(String firstname, String lastname, String pesel, String city, Job job, BigDecimal salary) {
        super(firstname, lastname, pesel, city);
        this.job = job;
        this.salary = salary;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return  super.toString() +
                " job=" + job +
                ", salary=" + salary +
                '}';
    }

    @Override
    public BigDecimal getIncome() {
        return salary;
    }
}
