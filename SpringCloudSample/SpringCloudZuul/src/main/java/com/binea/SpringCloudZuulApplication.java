package com.binea;

import com.binea.filter.AccessFilter;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringCloudApplication
@EnableZuulProxy
public class SpringCloudZuulApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringCloudZuulApplication.class).web(true).run(args);
    }

    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }
}
