package com.java8.lambda.example.stream.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Richard on 17/12/5.
 */
public class CovertMapToList {

    public static void main(String[] args) {
//        Map<String, String> map = new HashMap<>();
//
//        // Convert all Map Keys to a List
//        List<String> result1 = new ArrayList<>(map.keySet());
//
//        // Convert all Map Values to a List
//        List<String> result2 = new ArrayList<>(map.values());
//
//        // Java 8, Convert all Map keys to a List
//        List<String> result3 = map.keySet().stream()
//                .collect(Collectors.toList());
//
//        // Java 8, Convert all Map values to a List
//        List<String> result4 = map.values().stream()
//                .collect(Collectors.toList());
//
//        // Java 8, seem a bit long, but you can enjoy the Stream features like filter and etc.
//        List<String> result5 = map.values().stream()
//                .filter(x -> !"apple".equalsIgnoreCase(x))
//                .collect(Collectors.toList());

        // Java 8, split a map into 2 List, it works!
        // refer example 3 below

        System.out.println("Map To List:");

        Map<Integer, String> map = new HashMap<>();
        map.put(10, "apple");
        map.put(20, "orange");
        map.put(30, "banana");
        map.put(40, "watermelon");
        map.put(50, "dragonfruit");

        System.out.println("\n1. Export Map Key to List...");

        List<Integer> result1 = new ArrayList(map.keySet());

        result1.forEach(System.out::println);

        System.out.println("\n2. Export Map Value to List...");

        List<String> result2 = new ArrayList(map.values());

        result2.forEach(System.out::println);


        System.out.println("\nIn Java 8...");

        System.out.println("\n1. Export Map Key to List...");

        List<Integer> result3 = map.keySet().stream()
                .collect(Collectors.toList());

        result3.forEach(System.out::println);

        System.out.println("\n2. Export Map Value to List...");

        List<String> result4 = map.values().stream()
                .collect(Collectors.toList());

        result4.forEach(System.out::println);

        System.out.println("\n3. Export Map Value to List..., say no to banana");
        List<String> result5 = map.values().stream()
                .filter(x -> !"banana".equalsIgnoreCase(x))
                .collect(Collectors.toList());

        result5.forEach(System.out::println);

        System.out.println("\nJava 8 – Convert Map into 2 List:");

        // split a map into 2 List
        List<Integer> resultSortedKey = new ArrayList<>();
        List<String> resultValues = map.entrySet().stream()
                // sort a Map by key and stored in resultSortedKey
                .sorted(Map.Entry.<Integer, String>comparingByKey().reversed())
                .peek(e -> resultSortedKey.add(e.getKey()))
                .map(x -> x.getValue())
                // filter banana and return it to resultValues
                .filter(x -> !"banana".equalsIgnoreCase(x))
                .collect(Collectors.toList());

        System.out.println("//resultSortedKey");
        resultSortedKey.forEach(System.out::println);
        System.out.println("//resultValues");
        resultValues.forEach(System.out::println);

    }
}
