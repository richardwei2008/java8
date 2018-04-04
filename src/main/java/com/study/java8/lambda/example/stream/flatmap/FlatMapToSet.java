package com.study.java8.lambda.example.stream.flatmap;

import com.study.java8.lambda.example.stream.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Richard on 17/12/5.
 */
public class FlatMapToSet {

    public static void main(String[] args) {

        System.out.println("flatMap() and Set example:");

        Student obj1 = new Student();
        obj1.setName("Richard");
        obj1.addBook("Java 8 in Action");
        obj1.addBook("Spring Boot in Action");
        obj1.addBook("Effective Java (2nd Edition)");

        Student obj2 = new Student();
        obj2.setName("Zilap");
        obj2.addBook("Learning Python, 5th Edition");
        obj2.addBook("Effective Java (2nd Edition)");

        List<Student> list = new ArrayList<>();
        list.add(obj1);
        list.add(obj2);

        List<String> collect = list.stream()
                                .map(x -> x.getBook())      // Stream<Set<String>>
                                .flatMap(x -> x.stream())   // Stream<String>
                                .distinct()
                                .collect(Collectors.toList());

        collect.forEach(x -> System.out.println(x));

        System.out.println();
        System.out.println("Stream + Primitive + flatMapToInt:");

        int[] intArray = {1, 2, 3, 4, 5, 6};

        // 1. Stream<int[]>
        Stream<int[]> streamArray = Stream.of(intArray);

        // 2. Stream<int[]> -> flatMap -> IntStream
        IntStream intStream = streamArray.flatMapToInt(x -> Arrays.stream(x));

        intStream.forEach(x -> System.out.println(x));


    }
}
