package com.example.application.tasks.hero;

public class Walking implements MovementType{

    @Override
    public void printMovementType() {
        System.out.println("Walking...");
    }

    @Override
    public int makeAMove() {
        return 3;
    }
}
