package com.example.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BasicApplication {
    private static final Logger logger = LoggerFactory.getLogger(BasicApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(BasicApplication.class, args);
        logger.error("error log");
        logger.warn("warn log");
        logger.info("info log");
        logger.debug("debug log");
        logger.trace("trace log");


    }

}
