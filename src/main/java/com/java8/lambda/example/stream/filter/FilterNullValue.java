package com.java8.lambda.example.stream.filter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Richard on 17/11/3.
 */
public class FilterNullValue {

    public static void main(String[] args) {
        Stream<String> language = Stream.of(
                "java", "python", "node", null, "ruby", null, "php");

        // List<String> result = language.collect(Collectors.toList());

        /**
         * Solution
         */
        List<String> result = language
                .filter(x -> x != null)
                .collect(Collectors.toList());

        /**
         * Alternative
         */
        // List<String> result = language
        //        .filter(Objects::nonNull)
        //        .collect(Collectors.toList());

        result.forEach(System.out::println);
    }

}
