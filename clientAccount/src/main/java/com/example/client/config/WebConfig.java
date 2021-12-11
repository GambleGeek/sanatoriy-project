package com.example.client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/img/**",
                "/css/**",
                "/libs/**",
                "/jsp/**")
                .addResourceLocations(
                        "classpath:/static/image/",
                        "classpath:/static/css/",
                        "classpath:/static/libs/",
                        "classpath:/webapp/WEB-INF/jsp/");
    }

}