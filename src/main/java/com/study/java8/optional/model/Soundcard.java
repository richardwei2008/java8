package com.study.java8.optional.model;

public class Soundcard {
    private USB usb;

    private String name;

    public Soundcard(String name) {
        this.name = name;
    }

    public USB getUSB() {
      return this.usb;
  }

    @Override
    public String toString() {
        return "Soundcard{" +
                "usb=" + usb +
                ", name='" + name + '\'' +
                '}';
    }
}