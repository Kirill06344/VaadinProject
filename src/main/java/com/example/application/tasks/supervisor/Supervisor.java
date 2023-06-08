package com.example.application.tasks.supervisor;

public class Supervisor implements Runnable {

    private final AbstractProgram abstractProgram;

    private final Thread executableThread;


    public Supervisor(AbstractProgram abstractProgram) {
        this.abstractProgram = abstractProgram;
        executableThread = new Thread(abstractProgram);
    }


    @Override
    public void run() {
        executableThread.start();
        synchronized (abstractProgram) {
            while (!Thread.interrupted() || executableThread.isInterrupted()) {
                try {
                    abstractProgram.wait();
                    responseToStateChange(abstractProgram.getState());
                    abstractProgram.notify();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Supervisor: FATAL ERROR -> abstract program finished performance");
                    break;
                }
            }
        }
    }

    private void responseToStateChange(ProgramState state) throws InterruptedException {
        switch (state) {
            case FATAL_ERROR -> {
                executableThread.interrupt();
                throw new InterruptedException("Msg");
            }
            case STOPPING -> restartProgram();
            default -> System.out.println("Supervisor: state of abstract program is " + state);
        }
    }

    public void restartProgram() {
        System.out.println("Supervisor: Due to the state STOPPING -> restart program.");
        System.out.println("Supervisor: counter value on the moment of restarting program: " + abstractProgram.getCounter());
        abstractProgram.resetCounter();
    }
}
