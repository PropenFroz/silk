package com.a03.silk.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Atur path yang ingin diizinkan CORS
                .allowedOrigins("http://silk-purwa.up.railway.app/home") // Atur domain FE yang diizinkan
                .allowedMethods("GET", "POST", "PUT", "DELETE"); // Atur metode HTTP yang diizinkan
    }
}

