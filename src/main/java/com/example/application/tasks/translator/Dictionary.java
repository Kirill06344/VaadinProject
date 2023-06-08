package com.example.application.tasks.translator;


import com.example.application.tasks.translator.exceptions.FileReadException;
import com.example.application.tasks.translator.exceptions.InvalidFileFormatException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary {

    private final Map<String, String> dictionary;

    public Dictionary(File file) throws FileReadException, InvalidFileFormatException {
       this.dictionary = dictionaryReading(file);
    }

    private Map<String, String> dictionaryReading(File file) throws FileReadException {
        Map<String, String> dictionary = new HashMap<>();
        try(FileReader reader = new FileReader(file)) {
            Scanner in = new Scanner(reader);
            while(in.hasNextLine()) {
                String str = in.nextLine();
                String [] words = str.split("\\|");
                if (words.length != 2) {
                    throw new InvalidFileFormatException();
                }
                dictionary.put(words[0].trim(), words[1].trim());
            }
        } catch (IOException | InvalidFileFormatException e) {
            throw new FileReadException(e.getMessage());
        }
        return dictionary;
    }

    public void printDictionary() {
        dictionary.forEach((key, value) -> System.out.println(key + " | " + value));
    }

    public Set<String> getAllKeys() {
        return dictionary.keySet();
    }

    public Optional<String> getTranslate(String word) {
        return Optional.ofNullable(dictionary.get(word));
    }
}
