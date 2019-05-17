package com.example.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class BasicApplication {



    public static void main(String[] args) {
        SpringApplication.run(BasicApplication.class, args);
        log.info("hello");

    }

}
