package pl.kurs.ex1.models;

import java.util.ArrayList;
import java.util.List;

public class Mother extends Person{
    private int age;
    private List<Newborn> newborns = new ArrayList<>();

    private Mother(int id, String name, int age) {
        super(id, name);
        this.age = age;
    }

    public static Mother createFromLine(String line){
        String values[] = line.trim().split(" ");
        return new Mother(
                Integer.valueOf(values[0]),
                values[1],
                Integer.valueOf(values[2])
        );
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Newborn> getNewborns() {
        return newborns;
    }

    public void setNewborns(List<Newborn> newborns) {
        this.newborns = newborns;
    }

    @Override
    public String toString() {
        return super.toString() +
                " age=" + age +
                '}';
    }
}
