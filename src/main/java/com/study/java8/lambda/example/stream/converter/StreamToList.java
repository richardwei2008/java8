package com.study.java8.lambda.example.stream.converter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Richard on 17/11/3.
 */
public class StreamToList {

    public static void main(String[] args){
        Stream<String> language = Stream.of("java", "python", "node");

        // Convert a Stream to List
        List<String> result = language.collect(Collectors.toList());

        result.forEach(System.out::println);

        /**
         * filter and convert
         */
        Stream<Integer> number = Stream.of(1, 2, 3, 4, 5);

        List<Integer> result2 = number.filter(x -> x != 3).collect(Collectors.toList());

        result2.forEach(x -> System.out.println(x));

    }
}
