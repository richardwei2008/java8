package com.study.java8.lambda.example.stream.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Richard on 17/11/3.
 */
public class BeforeJava8Filter {

    public static void main(String[] args) {
        List<String> lines = Arrays.asList("spring", "node", "mkyong");
        List<String> result = getFilterOutput(lines, "mykong");
        for (String temp:
             result) {
            System.out.println(temp); // output: spring node
        }
    }

    private static List<String> getFilterOutput(List<String> lines, String mykong) {
        List<String> result = new ArrayList<>();
        for (String line:
                lines) {
            if (!"mkyong".equals(line)) { // we dont like mkyong
                result.add(line);
            }
        }
        return result;
    }
}
