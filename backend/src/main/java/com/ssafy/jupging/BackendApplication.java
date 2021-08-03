package com.ssafy.jupging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

//    public static final String APPLICATION_LOCATIONS = "spring.config.location="
//                                                        + "classpath:aws.yml";

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

}
