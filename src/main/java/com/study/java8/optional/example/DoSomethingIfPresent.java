package com.study.java8.optional.example;

import com.study.java8.optional.model.Soundcard;

import java.util.Optional;

/**
 * @Author: H13995 魏喆
 * @Description: 
 * @Date: Created in 下午4:19 2018/4/8
 * @Modified: by 
 */
public class DoSomethingIfPresent {

    public static void main(String[] args) {
        Optional<Soundcard> soundcard = Optional.of(new Soundcard("default"));
        soundcard.ifPresent(System.out::println);
    }
}
