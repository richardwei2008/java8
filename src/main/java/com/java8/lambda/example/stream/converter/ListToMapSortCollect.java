package com.java8.lambda.example.stream.converter;

import com.java8.lambda.example.stream.Hosting;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Richard on 17/11/3.
 */
public class ListToMapSortCollect {

    public static void main(String[] args) {

        List<Hosting> list = new ArrayList<>();
        list.add(new Hosting(1, "liquidweb.com", 80000));
        list.add(new Hosting(2, "linode.com", 90000));
        list.add(new Hosting(3, "digitalocean.com", 120000));
        list.add(new Hosting(4, "aws.amazon.com", 200000));
        list.add(new Hosting(5, "mkyong.com", 1));
        list.add(new Hosting(6, "linode.com", 100000));

        // example 1
        Map result1 = list.stream()
                .sorted(Comparator.comparingLong(Hosting::getWebSites).reversed())
                .collect(
                        Collectors.toMap(
                                Hosting::getName, Hosting::getWebSites, // key = name, value = websites
                                (oldValue, newValue) -> oldValue,       // if same key, take the old key
                                LinkedHashMap::new                      // returns a LinkedHashMap, keep order
                        )
                );

        System.out.println("Result 1 : " + result1);
    }
}
