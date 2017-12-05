package com.java8.lambda.example.stream.flatmap;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by Richard on 17/11/3.
 */
public class FlatMap {

    /**
     * In Java 8, Stream can hold different data types, for examples:
     *
     * Stream<String[]>
     * Stream<Set<String>>
     * Stream<List<String>>
     * Stream<List<Object>>
     *
     * But, the Stream operations (filter, sum, distinct...)
     * and collectors do not support it, so, we need flatMap() to do
     * the following conversation:
     *
     * Stream<String[]>         -> flatMap      ->  Stream<String>
     * Stream<Set<String>>      -> flatMap      ->  Stream<String>
     * Stream<List<String>>     -> flatMap      ->  Stream<String>
     * Stream<List<Object>>     -> flatMap      ->  Stream<Object>
     *
     * How flatMap() works:
     *
     * {{1,2}, {3,4}, {5,6}}                    -> flatMap  ->  {1,2,3,4,5,6}
     * {{'a', 'b'}, {'c', 'd'}, {'e', 'f'}}     -> flatMap  ->  {'a', 'b', 'c', 'd', 'e', 'f'}
     *
     */

    public static void main(String[] args) {

        String[][] data = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};

        /**
         * The below example will print an empty result,
         * because filter() has no idea how to filter a stream of String[]
         */

        // Stream<String[]>
        Stream<String[]> temp = Arrays.stream(data);

        // filter a stream of string[], and return a string[]?
        Stream<String[]> stream = temp.filter(x -> "a".equals(x.toString()));

        stream.forEach(System.out::println);

        /**
         * We should use flatMap() to convert Stream<String[]> to Stream<String>
         */
        Stream<String[]> temp2 = Arrays.stream(data);

        // Stream<String>, GOOD!
        Stream<String> stringStream = temp2.flatMap(x -> Arrays.stream(x));

        Stream<String> stream1 = stringStream.filter(x -> "a".equals(x.toString()));

        stream1.forEach(System.out::println);

    }

}
