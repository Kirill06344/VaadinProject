package com.example.application.tasks.hero;

public class TravellingByPlane implements MovementType{

    @Override
    public void printMovementType() {
        System.out.println("Travelling by plane...");

    }

    @Override
    public int makeAMove() {
        return 9;
    }
}
