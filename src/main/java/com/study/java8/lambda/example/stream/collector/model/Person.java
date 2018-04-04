package com.study.java8.lambda.example.stream.collector.model;

public class Person {

        private final String name;
        private final int group;
        private final int age;

        public Person(String name, int group, int age) {
            this.name = name;
            this.group = group;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getGroup() {
            return group;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", group=" + group +
                    ", age=" + age +
                    '}';
        }
    }