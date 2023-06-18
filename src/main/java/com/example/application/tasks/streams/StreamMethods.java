package com.example.application.tasks.streams;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class StreamMethods {
    public static double getAverageValue(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    public static List<String> transformListOfStrings(List<String> list) {
        return list.stream().map(str -> "_new_" + str.toUpperCase()).collect(Collectors.toList());
    }

    public static List<Integer> getListOfSquares(List<Integer> list) {
       return list.stream().collect(Collectors.toMap(k -> k,v -> 1, Integer::sum))
                .entrySet()
                .stream()
                .filter(i -> i.getValue() == 1)
                .mapToInt(val -> val.getKey() * val.getKey())
                .boxed()
                .collect(Collectors.toList());
    }

    public static <T> T getLastElement(Collection<T> collection) throws EmptyCollectionException {
        return collection.stream().reduce((i, j) -> j).orElseThrow(EmptyCollectionException::new);
    }

    public static List<String> getStringsStartingWithLetter(Collection<String> collection, char letter) {
        return collection.stream().filter(str -> !str.isEmpty() && str.charAt(0) == letter)
                .sorted()
                .collect(Collectors.toList());
    }


    public int getSumOfEvenElements(int [] arr) {
        return Arrays.stream(arr).filter(i -> i % 2 == 0).reduce(0, Integer::sum);
    }

    public Map<Character, List<String>> createMapFromList(List<String> list) {
        return list.stream()
                .filter(str -> !str.isEmpty())
                .collect(Collectors.groupingBy(k ->k.charAt(0),
                        Collectors.mapping(v -> v.substring(1), Collectors.toList())));
    }
}
