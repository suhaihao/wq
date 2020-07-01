package com.bhst.wq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WqApplication {

    public static void main(String[] args) {
        SpringApplication.run(WqApplication.class, args);
    }

}
