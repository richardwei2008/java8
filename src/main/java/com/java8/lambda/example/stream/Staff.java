package com.java8.lambda.example.stream;

import java.math.BigDecimal;

/**
 * Created by Richard on 17/11/3.
 */
public class Staff {

    private String name;
    private int age;
    private BigDecimal salary;

    public Staff(String name, int age, BigDecimal salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
