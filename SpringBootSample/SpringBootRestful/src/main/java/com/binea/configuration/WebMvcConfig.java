package com.binea.configuration;

import com.binea.interceptor.AccessTokenVerifyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by binea
 * Date: 28/10/2017
 * TIME: 5:52 PM
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Bean
    public AccessTokenVerifyInterceptor tokenVerifyInterceptor() {
        return new AccessTokenVerifyInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenVerifyInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
