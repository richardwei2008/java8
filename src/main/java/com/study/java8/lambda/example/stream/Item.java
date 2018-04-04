package com.study.java8.lambda.example.stream;

import java.math.BigDecimal;

/**
 * Created by Richard on 17/11/3.
 */
public class Item {

    private String name;
    private int qty;
    private BigDecimal price;


    public Item(String name, int qty, BigDecimal price) {
        this.name = name;
        this.qty = qty;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public int getQty() {
        return this.qty;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public String toString() {
        return "Item{"
                + "name='" + this.getName() + "', "
                + "qty=" + this.getQty() + ", "
                + "price=" + this.getPrice()
                + "}";
    }
}
