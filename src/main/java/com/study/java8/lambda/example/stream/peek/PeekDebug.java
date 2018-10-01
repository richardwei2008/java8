package com.study.java8.lambda.example.stream.peek;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @Author: Richard
 * @Description: 
 * @Date: Created in 上午11:08 2018/10/1
 * @Modified: by
 *
 * https://minborgsjavapot.blogspot.com/2018/09/debugging-java-streams-with-intellij.html
 */
public class PeekDebug {

    public static void main(String[] args) {

        List<String> stringList = Stream.of("C", "A", "B")
                .sorted()
                .collect(toList());

        System.out.println(stringList);

        Stream<String> s0 = Stream.of("C", "A", "B"); // "C", "A", "B"
        Stream<String> s1 = s0.sorted();              // "A", "B", "C"
        List<String> strings = s1.collect(toList());  // [“A”, “B”, “C”]

        System.out.println(strings);

        Stream.of("C", "A", "B")
                .peek(saveStep(0))
                .sorted()
                .peek(saveStep(1))
                .collect(Collectors.toList());
    }

    private static Consumer<? super String> saveStep(int i) {
        Consumer<String> myConsumer = (x) ->
        {
            System.out.println("step: " + i);
            System.out.println("x = " + x);
        };
        return myConsumer;
    }
}
