package com.example.application.tasks.animals;

public abstract class Chordate {

    protected String name;
    protected int weight;

    public Chordate(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public abstract void voice();

    @Override
    public String toString() {
        return name + " " + weight;
    }
}
