package com.study.java8.optional.example;

import com.study.java8.optional.model.Soundcard;
import com.study.java8.optional.model.USB;

import java.util.Optional;

/**
 * @Author: H13995 魏喆
 * @Description: 
 * @Date: Created in 下午4:32 2018/4/8
 * @Modified: by 
 */
public class ExtractingTransformingValuesUsingTheMapMethod {


    public static void main(String[] args) {
//        if(soundcard != null){
//            USB usb = soundcard.getUSB();
//            if(usb != null && "3.0".equals(usb.getVersion()){
//                System.out.println("ok");
//            }
//        }

        Optional<Soundcard> maybeSoundcard = Optional.of(new Soundcard("AKG"));
        maybeSoundcard.map(Soundcard::getUSB)
                .filter(usb -> "3.0".equals(usb.getVersion()))
                        .ifPresent(x -> System.out.println("ok"));

    }
}
