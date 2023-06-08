package com.example.application.tasks.translator;


import com.example.application.tasks.translator.exceptions.FileReadException;
import com.example.application.tasks.translator.exceptions.InvalidFileFormatException;

import java.io.File;

public class Translator {

    private Dictionary dictionary;

    private final Vertex root = new Vertex();


    public Translator(File file) {
        setDictionary(file);
    }

    public String translate(String text) {
        Vertex tmp = root;
        StringBuilder result = new StringBuilder();
        int last = -1;
        for (int i = 0; i < text.length(); ) {
            tmp = root.to.get(Character.toLowerCase(text.charAt(i)) - 'a');
            if (tmp != null) {
                int j = i + 1;
                while (j < text.length() && tmp.to.get(Character.toLowerCase(text.charAt(j)) - 'a') != null) {
                    if (tmp.terminal) {
                        last = j;
                    }
                    tmp = tmp.to.get(Character.toLowerCase(text.charAt(j)) - 'a');
                    j++;
                }
                if (tmp.terminal) {
                    last = j;
                }
                if (last == -1) {
                    int lastInx = text.indexOf(' ', i + 1);
                    if (lastInx == -1) {
                        lastInx = text.length();
                    }
                    result.append(text, i, lastInx);
                    i = lastInx;
                } else {
                    var word = dictionary.getTranslate(text.substring(i, j).toLowerCase());
                    String translation = word.orElse(text.substring(i, j));
                    result.append(translation);
                    i = last;
                }
                last = -1;
            } else {
                result.append(text,i, ++i);
            }
        }
        return result.toString();
    }

    private void addString(String s) {
        Vertex v = root;
        for (int i = 0; i < s.length(); ++i) {
            int c = s.charAt(i) - 'a';
            if (v.to.get(c) == null) {
                v.to.put(c, new Vertex());
            }
            v = v.to.get(c);
        }
        v.terminal = true;
    }


    private void setDictionary(File file) {
        try {
            dictionary = new Dictionary(file);
            dictionary.getAllKeys().forEach(this::addString);
        } catch (FileReadException | InvalidFileFormatException e) {
            System.out.println(e.getMessage());
            System.out.println("Translator won't work, fix errors and try to load your dictionary again!");
            dictionary = null;
        }
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

}
