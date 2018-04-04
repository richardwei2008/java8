package com.study.java8.lambda.example.stream;

/**
 * Created by Richard on 17/11/3.
 */
public class Hosting {

    private int id;
    private String name;
    private long websites;

    public Hosting(int id, String name, long websites) {
        this.id = id;
        this.name = name;
        this.websites = websites;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public long getWebSites() {
        return this.websites;
    }
}
