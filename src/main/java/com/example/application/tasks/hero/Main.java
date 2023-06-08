package com.example.application.tasks.hero;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Map<String, MovementType> initializeMovementTypes() {
        Map<String, MovementType> movementTypes = new HashMap<>();
        movementTypes.put("walking", new Walking());
        movementTypes.put("horse", new RidingAHorse());
        movementTypes.put("plane", new TravellingByPlane());
        return movementTypes;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Map<String, MovementType> movementTypes = initializeMovementTypes();
        Hero hero = new Hero();
        String command = "";
        while (!command.equals("exit")) {
            command = in.next();
            switch (command) {
                case "move":
                    hero.move();
                    break;
                case "point":
                    System.out.println("The hero is at the point: " + hero.getCurrentPoint());
                    break;
                case "change":
                    String type = in.next();
                    hero.setMovementType(movementTypes.get(type));
                    break;
                case "exit":
                    break;
                default:
                    System.out.println("Incorrect command! Try again!");
                    break;
            }
        }
    }
}