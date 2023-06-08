package com.example.application.tasks.translator;



import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.example.application.tasks.translator.exceptions.FileReadException;

public class Main {

    public static String readFile(String path, Charset encoding) throws FileReadException {
        try {
            return Files.readString(Paths.get(path), encoding);
        } catch (IOException e) {
            throw new FileReadException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        File file = new File("dictionary.txt");
        Translator translator = new Translator(file);

        String textFile = "example3.txt";
        try {
            String text = readFile(textFile, StandardCharsets.UTF_8);
            System.out.println(translator.translate(text));
        } catch (FileReadException e) {
            System.out.println(e.getMessage());
        }

    }

}
