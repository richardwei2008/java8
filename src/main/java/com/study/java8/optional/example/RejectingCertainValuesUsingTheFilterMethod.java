package com.study.java8.optional.example;

import com.study.java8.optional.model.USB;

import java.util.Optional;

/**
 * @Author: H13995 魏喆
 * @Description: 
 * @Date: Created in 下午4:28 2018/4/8
 * @Modified: by 
 */
public class RejectingCertainValuesUsingTheFilterMethod {

    public static void main(String[] args) {
//        USB usb = ...;
//        if(usb != null && "3.0".equals(usb.getVersion())){
//            System.out.println("ok");
//        }

        Optional<USB> maybeUSB = Optional.of(new USB("2.0"));
        maybeUSB.filter(usb -> "3.0".equals(usb.getVersion()))
                .ifPresent(x -> System.out.println("ok"));
    }
}
