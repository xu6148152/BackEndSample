package com.binea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;
import org.springframework.context.annotation.Configuration;

//@SpringCloudApplication
//@EnableHystrixDashboard
//@EnableTurbine
//@EnableTurbineStream
//@Configuration
//@EnableAutoConfiguration
@Configuration
@EnableAutoConfiguration
@EnableTurbineStream
@EnableDiscoveryClient
public class SpringCloudHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudHystrixApplication.class, args);
    }
}
