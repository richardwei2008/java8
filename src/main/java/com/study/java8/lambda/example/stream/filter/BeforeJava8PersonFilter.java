package com.study.java8.lambda.example.stream.filter;

import com.study.java8.lambda.example.stream.Person;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Richard on 17/11/3.
 */
public class BeforeJava8PersonFilter {

    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("mkyong", 30),
                new Person("jack", 20),
                new Person("lawrence", 40)
        );

        Person result = getStudentByName(persons, "jack");
        System.out.println(result);
    }

    private static Person getStudentByName(List<Person> persons, String name) {
        Person result = null;
        for (Person temp:
             persons) {
            if (name.equals(temp.getName())) {
                result = temp;
            }
        }
        return result;
    }
}
