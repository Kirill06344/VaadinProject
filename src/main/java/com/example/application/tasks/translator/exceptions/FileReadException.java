package com.example.application.tasks.translator.exceptions;

public class FileReadException extends Exception {

    public FileReadException() {
        super("Unable to read current file!");
    }

    public FileReadException(String message) {
        super(message);
    }
}
