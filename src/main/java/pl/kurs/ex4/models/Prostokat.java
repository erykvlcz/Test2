package pl.kurs.ex4.models;

import java.util.Objects;

public class Prostokat extends Figura{
    private int sideX;
    private int sideY;

    public Prostokat(int sideX, int sideY) {
        if(sideX < 1 || sideY < 1) throw new IllegalArgumentException("Side length can not be less than 1");
        this.sideX = sideX;
        this.sideY = sideY;
    }

     Prostokat(int sideX, int sideY, boolean factoryConstructor) {
        super(factoryConstructor);
        if(sideX < 1 || sideY < 1) throw new IllegalArgumentException("Side length can not be less than 1");
        this.sideX = sideX;
        this.sideY = sideY;
    }


    public double getSideX() {
        return sideX;
    }

    public void setSideX(int sideX) {
        this.sideX = sideX;
    }

    public double getSideY() {
        return sideY;
    }

    public void setSideY(int sideY) {
        this.sideY = sideY;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Prostokąt o bokach " + sideX + 'x' + sideY;
    }

    @Override
    public void calculateArea() {
        setArea(sideX * sideY);
    }

    @Override
    public void calculatePerimeter() {
        setPerimeter(2 * (sideX + sideY));
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Prostokat prostokat = (Prostokat) o;
        return sideX == prostokat.sideX && sideY == prostokat.sideY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sideX, sideY);
    }

}
