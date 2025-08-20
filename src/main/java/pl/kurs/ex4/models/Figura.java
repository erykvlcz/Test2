package pl.kurs.ex4.models;

import pl.kurs.ex4.models.interfaces.AreaPerimeterComputable;

import java.io.Serializable;
import java.util.Comparator;

public class Figura implements AreaPerimeterComputable, Serializable {
    private static final long serialVersionUID = 1L;

    public static final Comparator<Figura> BY_AREA_DESCENDING_COMPARATOR = new ByAreaDescendingComparator();
    public static final Comparator<Figura> BY_PERIMETER_DESCENDING_COMPARATOR = new ByPerimeterDescendingComparator();

    private static int figuraCounter = 0;
    private int figuraNumber;
    private double area;
    private double perimeter;

    protected Figura() {
        this.figuraNumber = 0;
    }

     Figura(boolean factoryConstructor) {
        if(factoryConstructor){
            figuraCounter++;
            this.figuraNumber = figuraCounter;
        }else {
            this.figuraNumber = 0;
        }
    }

    public static Kwadrat stworzKwadrat(int side){
        if(side < 1) throw new IllegalArgumentException("Side length can not be less than 1");
        return new Kwadrat(side, true);
    }

    public static Kolo stworzKolo(int radius){
        if(radius < 1) throw new IllegalArgumentException("Radius length can not be less than 1");
        return new Kolo(radius, true);
    }

    public static Prostokat stworzProstokat(int sideX, int sideY){
        if(sideX < 1 || sideY < 1) throw new IllegalArgumentException("Side length can not be less than 1");
        return new Prostokat(sideX, sideY, true);
    }

    public int getFiguraNumber() {
        return figuraNumber;
    }

    public void setFiguraNumber(int figuraNumber) {
        this.figuraNumber = figuraNumber;
    }

    public static int getFiguraCounter() {
        return figuraCounter;
    }

    public static void setFiguraCounter(int figuraCounter) {
        Figura.figuraCounter = figuraCounter;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    @Override
    public String toString() {
        return "Figura nr: " + figuraNumber + ": " ;
    }

    @Override
    public void calculateArea() {
    }

    @Override
    public void calculatePerimeter() {
    }

    private static class ByAreaDescendingComparator implements Comparator<Figura>{
        @Override
        public int compare(Figura o1, Figura o2) {
            return Double.compare(o2.getArea(), o1.getArea());
        }
    }

    private static class ByPerimeterDescendingComparator implements Comparator<Figura>{
        @Override
        public int compare(Figura o1, Figura o2) {
            return Double.compare(o2.getPerimeter(), o1.getPerimeter());
        }
    }

}
