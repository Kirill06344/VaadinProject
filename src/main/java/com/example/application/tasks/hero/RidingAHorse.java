package com.example.application.tasks.hero;

public class RidingAHorse implements MovementType{

    @Override
    public void printMovementType() {
        System.out.println("Riding a horse...");

    }

    @Override
    public int makeAMove() {return 6;

    }
}
