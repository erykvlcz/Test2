package pl.kurs.ex1.models;

import java.io.Serializable;

public abstract class Person implements Serializable {
    private int id;
    private String firstname;

    public Person(int id, String firstname) {
        this.id = id;
        this.firstname = firstname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                " id=" + id +
                ", firstname='" + firstname + '\'' +
                '}';
    }
}
