package com.binea;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by binea
 * Date: 4/11/2017
 * TIME: 5:26 PM
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket creatRestApi() {
        return new Docket((DocumentationType.SWAGGER_2)).apiInfo(apiInfo()).select().apis(
                RequestHandlerSelectors.basePackage("com.binea.web"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("swagger2 api document").description(
                "swagger2 api document").termsOfServiceUrl("http://xu6148152.github.io").contact("binea").version(
                "0.1").build();
    }

}
