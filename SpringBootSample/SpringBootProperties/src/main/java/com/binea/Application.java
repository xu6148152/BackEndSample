package com.binea;

import com.binea.property.HomeProperties;
import com.binea.property.UserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sun.tools.jar.CommandLine;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private HomeProperties mHomeProperties;
    @Autowired
    private UserProperties mUserProperties;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.print("\n" + mHomeProperties.toString());
        System.out.print("\n" + mUserProperties.toString());
    }
}
