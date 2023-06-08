package com.example.application.tasks.animals;

public class SimpleHedgehog extends Hedgehog {

    public SimpleHedgehog(String name, int weight) {
        super(name, weight);
    }

    @Override
    public void voice() {
        System.out.println("Shhhhh.I'm simple hedgehog");
    }

}
