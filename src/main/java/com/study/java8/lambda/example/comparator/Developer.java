package com.study.java8.lambda.example.comparator;

import java.math.BigDecimal;

/**
 * Created by Richard on 17/11/3.
 */
public class Developer {


    private String name;

    private BigDecimal salary;

    private int age;

    public Developer(String name, BigDecimal salary, int age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Developer ["
                + "name=" + this.getName() + ", "
                + "salary=" + this.getSalary().toString() + ", "
                + "age=" + this.getAge()
                + "]";
    }
}
