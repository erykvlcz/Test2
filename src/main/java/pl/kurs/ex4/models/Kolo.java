package pl.kurs.ex4.models;

import java.util.Objects;

public class Kolo extends Figura{
    private int radius;

    public Kolo(int radius) {
        if(radius < 1) throw new IllegalArgumentException("Radius length can not be less than 1");
        this.radius = radius;
    }

    Kolo(int radius, boolean factoryConstructor) {
        super(factoryConstructor);
        if (radius < 1) throw new IllegalArgumentException("Radius length can not be less than 1");
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void calculateArea(){
         setArea(radius * radius * Math.PI);
    }

    @Override
    public void calculatePerimeter(){
        setPerimeter(2 * Math.PI * radius);
    }

    @Override
    public String toString() {
        return super.toString() +
                "Koło o promienu: " + radius;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Kolo kolo = (Kolo) o;
        return radius == kolo.radius;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(radius);
    }


}
