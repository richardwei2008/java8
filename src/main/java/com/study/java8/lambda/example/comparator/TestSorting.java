package com.study.java8.lambda.example.comparator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Richard on 17/11/3.
 *
 * 1. Classic Comparator example
 *
 * Comparator<Developr> byName = new Comparator<Developer>() {
 *     @Override
 *     public int compare(Developer o1, Developer o2) {
 *         return o1.getName().compareTo(o2.getName());
 *     }
 *
 * }
 *
 * 2. Lambda expression equivalent.
 *
 * Comparator<Developer> byName =
 *      (Developer o1, Developer o2)->o1.getName().compareTo(o2.getName());
 *
 */
public class TestSorting {

    public static void main(String[] args) {

        List<Developer> listDevs = getDevelopers();

        System.out.println("Before Sort");

        for (Developer developer : listDevs) {
            System.out.println(developer);
        }

        // sort by age
        Collections.sort(listDevs, new Comparator<Developer>() {
            public int compare(Developer o1, Developer o2) {
                return o1.getAge() - o2.getAge();
            }
        });

        // Lambda expression to sort a List using their salary
        // Comparator<Developer> salaryComparator = (o1, o2)->(o1.getSalary().compareTo(o2.getSalary()));

        // Lambda expression to sort a List using their salary, reversed order
        // listDevs.sort(salaryComparator.reversed());

        // lambda
        // listDevs.sort((Developer o1, Developer o2)->(o1.getAge()-o2 .getAge()));

        // lambda, valid, parameter type is optional
        // listDevs.sort((o1, o2)->(o1.getAge()-o2.getAge()));

        System.out.println("After Sort");
        for (Developer developer : listDevs) {
            System.out.println(developer);
        }
    }

    private static List<Developer> getDevelopers() {

        List<Developer> result = new ArrayList<Developer>();

        result.add(new Developer("mkyong", new BigDecimal("70000"), 33));
        result.add(new Developer("alvin", new BigDecimal("80000"), 20));
        result.add(new Developer("jason", new BigDecimal("100000"), 10));
        result.add(new Developer("iris", new BigDecimal("170000"), 55));

        return result;
    }

    public static void sortByAge(List<Developer> listDevs) {
        Collections.sort(listDevs, new Comparator<Developer>() {
            public int compare(Developer o1, Developer o2) {
                return o1.getAge() - o2.getAge();
            }
        });
    }

    public static void sortByName(List<Developer> listDevs) {
        Collections.sort(listDevs, new Comparator<Developer>() {
            public int compare(Developer o1, Developer o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    public static void sortBySalary(List<Developer> listDevs) {
        Collections.sort(listDevs, new Comparator<Developer>() {
            public int compare(Developer o1, Developer o2) {
                return o1.getSalary().compareTo(o2.getSalary());
            }
        });
    }

}
