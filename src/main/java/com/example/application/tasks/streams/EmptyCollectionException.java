package com.example.application.tasks.streams;

public class EmptyCollectionException extends Exception{

    @Override
    public String toString() {
        return "Collection is empty!";
    }
}
