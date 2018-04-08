package com.study.java8.optional.example;

import com.study.java8.optional.model.Soundcard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * @Author: H13995 魏喆
 * @Description: 
 * @Date: Created in 下午4:23 2018/4/8
 * @Modified: by 
 */
public class DefaultValuesActions {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultValuesActions.class);


    public static void main(String[] args) {

//        Soundcard soundcard =
//                maybeSoundcard != null ? maybeSoundcard
//                        : new Soundcard("basic_sound_card");

        Optional<Soundcard> maybeSoundcard = Optional.of(new Soundcard("AKG"));
        Soundcard soundcard = maybeSoundcard.orElse(new Soundcard("default"));
        LOGGER.debug("Soundcard: {}", soundcard);
    }
}
