package com.binea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ValidationJsonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ValidationJsonApplication.class, args);
    }
}
