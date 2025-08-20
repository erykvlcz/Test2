package pl.kurs.ex4.app;

import pl.kurs.ex4.models.Figura;
import pl.kurs.ex4.models.Kwadrat;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Ex4Runner {
    public static void main(String[] args) {
        List<Figura> figury = Arrays.asList(Figura.stworzKwadrat(10) ,Figura.stworzKolo(20), Figura.stworzProstokat(10,20));
        for(Figura f : figury) {
            System.out.println(f);
        }

        System.out.println();
        List<Figura> figureWithLargestPerimeter = getFigureWithLargestPerimeter(figury);
        System.out.println("Figure with largest perimeter:");
        for (Figura figura : figureWithLargestPerimeter) {
            System.out.println(figura + " perimeter: " + figura.getPerimeter());
        }

        System.out.println();
        List<Figura> figureWithLargestArea = getFigureWithLargestArea(figury);
        System.out.println("Figure with largest perimeter:");
        for (Figura figura : figureWithLargestArea) {
            System.out.println(figura + " area: " + figura.getArea());
        }

        System.out.println();
        System.out.println(figury.contains(new Kwadrat(10)));

        writePersonListToFile(figury, "figury.figura");
        List<Figura> figury2 = loadFiguryFromList("figury.figura");
    }

    private static List<Figura> loadFiguryFromList(String filename) {
        List<Figura> loadedFigures = null;
        try (
                FileInputStream fis = new FileInputStream(filename);
                ObjectInputStream ois = new ObjectInputStream(fis);
        ){
            loadedFigures = (List<Figura>) ois.readObject();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();

        }
        return loadedFigures;
    }

    private static void writePersonListToFile(List<Figura> Figures, String filename) {
        try (
                FileOutputStream fos = new FileOutputStream(filename);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ){

            oos.writeObject(Figures);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static List<Figura> getFigureWithLargestArea(List<Figura> figury) {
        List<Figura> figuryWithLargestArea = new ArrayList<>();

        for (Figura figura : figury) {
            figura.calculateArea();
        }

        Collections.sort(figury, Figura.BY_AREA_DESCENDING_COMPARATOR);
        double largestArea = figury.get(0).getArea();

        for (Figura figura : figury) {
            if(figura.getArea() == largestArea){
                figuryWithLargestArea.add(figura);
            }
        }
        return figuryWithLargestArea;
    }

    private static List<Figura> getFigureWithLargestPerimeter(List<Figura> figury) {
        List<Figura> figuryWithLargestPerimeter = new ArrayList<>();

        for (Figura figura : figury) {
            figura.calculatePerimeter();
        }

        Collections.sort(figury, Figura.BY_PERIMETER_DESCENDING_COMPARATOR);
        double largestPerimeter = figury.get(0).getPerimeter();

        for (Figura figura : figury) {
            if(figura.getPerimeter() == largestPerimeter){
                figuryWithLargestPerimeter.add(figura);
            }
        }
        return figuryWithLargestPerimeter;
    }
}
