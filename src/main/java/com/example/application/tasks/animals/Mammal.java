package com.example.application.tasks.animals;

public abstract class Mammal extends Chordate {
    public Mammal(String name, int weight) {
        super(name, weight);
    }

    @Override
    public String toString() {
        return name + " " + weight;
    }
}
