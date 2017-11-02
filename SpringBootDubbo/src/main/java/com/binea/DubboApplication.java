package com.binea;

import com.binea.dubbo.CityDubboConsumerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DubboApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DubboApplication.class, args);
        CityDubboConsumerService service = run.getBean(CityDubboConsumerService.class);
        service.printCity();
    }
}
