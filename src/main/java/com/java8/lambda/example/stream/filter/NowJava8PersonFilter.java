package com.java8.lambda.example.stream.filter;

import com.java8.lambda.example.stream.Person;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Richard on 17/11/3.
 */
public class NowJava8PersonFilter {

    public static void main(String[] args) {

        List<Person> persons = Arrays.asList(
                new Person("mkyong", 30),
                new Person("jack", 20),
                new Person("lawrence", 40)
        );

        Person result1 = persons.stream()                   // Convert to steam
                .filter(x -> "jack".equals(x.getName()))    // we want "jack" only
                .findAny()                                  // If 'findAny' then return found
                .orElse(null);                       // If not found, return null

        System.out.println(result1);

        Person result2 = persons.stream()
                .filter(x -> "ahmook".equals(x.getName()))
                .findAny()
                .orElse(null);

        System.out.println(result2);
    }
}
