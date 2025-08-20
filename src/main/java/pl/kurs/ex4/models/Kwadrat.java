package pl.kurs.ex4.models;

import java.util.Objects;

public class Kwadrat extends Figura{
    private int sideLength;

    public Kwadrat(int sideLength) {
        if(sideLength < 1) throw new IllegalArgumentException("Side length can not be less than 1");
        this.sideLength = sideLength;
    }

    public Kwadrat(int sideLength, boolean factoryConstructor) {
        super(factoryConstructor);
        if(sideLength < 1) throw new IllegalArgumentException("Side length can not be less than 1");
        this.sideLength = sideLength;
    }

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Kwadrat o boku " + sideLength;
    }

    @Override
    public void calculateArea(){
         setArea(sideLength * sideLength);
    }

    @Override
    public void calculatePerimeter(){
        setPerimeter(4 * sideLength);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Kwadrat kwadrat = (Kwadrat) o;
        return sideLength == kwadrat.sideLength;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sideLength);
    }
}
