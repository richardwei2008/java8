package com.study.java8.lambda.example.stream;

/**
 * Created by Richard on 17/11/3.
 */
public class StaffPublic {

    private String name;
    private int age;
    private String extra;


    public void setName(String name) {
        this.name = name;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "StaffPublic{"
                + "name='" + this.name + "', "
                + "age='" + this.age + ","
                + "extra='" + this.extra + "'"
                + "}";
    }
}
