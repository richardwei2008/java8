package com.study.java8.lambda.example.stream;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Richard on 17/11/3.
 */
public class Student {

    private String name;
    private Set<String> book;

    public void addBook(String book) {
        if (this.book == null) {
            this.book = new HashSet<>();
        }
        this.book.add(book);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getBook() {
        return book;
    }
    // getters and setters


}
