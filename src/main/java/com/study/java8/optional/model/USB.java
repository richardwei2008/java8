package com.study.java8.optional.model;

public class USB {

  private String version;

    public USB(String version) {
        this.version = version;
    }

    public String getVersion(){
      return this.version;
  }

    @Override
    public String toString() {
        return "USB{" +
                "version='" + version + '\'' +
                '}';
    }
}