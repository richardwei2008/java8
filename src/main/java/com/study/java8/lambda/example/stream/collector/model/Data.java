package com.study.java8.lambda.example.stream.collector.model;

public class Data {
        double average;
        double sum;

        public Data(double average, double sum) {
            this.average = average;
            this.sum = sum;
        }

        public double getAverage() {
            return average;
        }

        public void setAverage(double average) {
            this.average = average;
        }

        public double getSum() {
            return sum;
        }

        public void setSum(double sum) {
            this.sum = sum;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "average=" + average +
                    ", sum=" + sum +
                    '}';
        }
    }