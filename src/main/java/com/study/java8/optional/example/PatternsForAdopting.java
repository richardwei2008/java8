package com.study.java8.optional.example;

import com.study.java8.optional.model.Computer;
import com.study.java8.optional.model.Soundcard;
import com.study.java8.optional.model.USB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * http://www.oracle.com/technetwork/articles/java/java8-optional-2175753.html
 *
 * @Author: H13995 魏喆
 * @Description: 
 * @Date: Created in 下午3:58 2018/4/8
 * @Modified: by 
 */
public class PatternsForAdopting {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatternsForAdopting.class);

    public static void main(String[] args) {
        Optional<Computer> computer = Optional.of(new Computer());

        String name = computer.map(Computer::getSoundcard)
                .map(Soundcard::getUSB)
                .map(USB::getVersion)
                .orElse("UNKNOWN");

        LOGGER.debug("name: {}", name);

    }
}
