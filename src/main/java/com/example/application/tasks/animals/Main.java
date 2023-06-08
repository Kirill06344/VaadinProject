package com.example.application.tasks.animals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Hedgehog> srcCollection1 = new ArrayList<>();
        srcCollection1.add(new SimpleHedgehog("Anya", 12));
        srcCollection1.add(new SimpleHedgehog("Ilya", 12));
        srcCollection1.add(new SimpleHedgehog("Genadiy", 20));

        List<Insectivore> col1 = new ArrayList<>();
        List<Predator> col2 = new ArrayList<>();
        List<Predator> col3 = new ArrayList<>();
        segregate(srcCollection1, col1, col2, col3);

        System.out.println(col1);
        System.out.println(col2);
        System.out.println(col3);

    }

    public static void segregate(Collection<? extends Mammal> srcCollection,
                          Collection<? super SimpleHedgehog> col1, Collection<? super Manul> col2,
                          Collection<? super Lynx> col3) {
        for (Mammal animal : srcCollection) {
            if (animal instanceof SimpleHedgehog) {
                col1.add((SimpleHedgehog) animal);
            } else if (animal instanceof Manul) {
                col2.add((Manul) animal);
            } else {
                col3.add((Lynx)animal);
            }
        }
    }

}


