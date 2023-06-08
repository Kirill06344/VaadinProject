package com.example.application.tasks.animals;

public class Lynx extends Felidae{
    public Lynx(String name, int weight) {
        super(name, weight);
    }

    @Override
    public void voice() {
        System.out.println("Rrrrrrr!I'm lynx!");
    }
}
