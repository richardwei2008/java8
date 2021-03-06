package com.study.java8.lambda.example.stream;

/**
 * Created by Richard on 17/11/3.
 */
public class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    // gettersm setters, toString

    public String toString() {
        return "Person{"
                + "name=" + this.getName() + ", "
                + "age=" + this.getAge()
                + "}";
    }

    public int getAge() {
        return age;
    }
}
