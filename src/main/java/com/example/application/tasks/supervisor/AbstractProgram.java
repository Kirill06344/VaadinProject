package com.example.application.tasks.supervisor;

import java.util.concurrent.ThreadLocalRandom;

public class AbstractProgram implements Runnable {

    private ProgramState state = ProgramState.UNKNOWN;

    private int counter = 0;
    @Override
    public void run() {
        var daemon = new Thread(() -> {
            while (true) {
                synchronized (this) {
                    notify();
                    try {
                        wait();
                        Thread.sleep(2000);
                        int randomState = ThreadLocalRandom.current().nextInt(1, 4);
                        state = ProgramState.values()[randomState];
                        System.out.println("Change state to: " + state);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        daemon.setDaemon(true);
        daemon.start();

        while (!Thread.currentThread().isInterrupted()) {
            doUselessWork();
        }
    }

    public ProgramState getState() {
        return state;
    }

    public int getCounter() {
        return counter;
    }

    public void doUselessWork() {
        counter++;
    }

    public void resetCounter() {
        counter = 0;
    }
}
