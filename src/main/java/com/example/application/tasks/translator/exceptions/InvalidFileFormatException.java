package com.example.application.tasks.translator.exceptions;

public class InvalidFileFormatException extends Exception{

    public InvalidFileFormatException() {
        super("Invalid format of input file!");
    }
}
