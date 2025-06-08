package com.Arteria.ArteriaBackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Aplica a todos los endpoints
                        .allowedOrigins("http://127.0.0.1:5501, http://127.0.0.1:5502") // MI ruta de live Server
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos  HTTPque se permiten
                        .allowedHeaders("*") // Permitir cualquier header
                        .allowCredentials(true); // Permitir enviar cookies (En caso de que se usen)
            }
        };
    }
}