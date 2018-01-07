package com.binea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class ServiceHIApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceHIApplication.class, args);
    }

    private static final Logger LOG = LoggerFactory.getLogger(ServiceHIApplication.class.getCanonicalName());

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/hi")
    public String getHi() {
        LOG.info("calling trace serviceHi");
        return restTemplate.getForObject("http://localhost:1120/miya", String.class);
    }

    @RequestMapping("/info")
    public String info() {
        LOG.info("calling trace serviceHi");
        return "i'm serviceHi";
    }

    @Bean
    public AlwaysSampler defaultSampler() {
        return new AlwaysSampler();
    }
}
