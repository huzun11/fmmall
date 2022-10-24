package com.qfedu.fmmall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig{


//    接口文档信息
        @Bean
        public Docket getDocket(){
            ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
            apiInfoBuilder.title("《商城》后端接口说明")
                    .description("此文档说明了商城项目的后端接口规范")
                    .version("v 2.0.1")
                    .contact(new Contact("胡哥","www.huge.cm","3067825233@qq.com"));
            ApiInfo apiInfo =  apiInfoBuilder.build();//封面信息

            /*---------------*/

            Docket docket = new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.qfedu.fmmall.controller"))
                    .paths(PathSelectors.any())
                    .build();

            return docket;

        }










}
