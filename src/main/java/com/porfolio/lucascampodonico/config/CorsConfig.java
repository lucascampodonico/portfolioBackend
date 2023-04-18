package com.porfolio.lucascampodonico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Aplica CORS a todas las rutas
                    .allowedOrigins("*") // Permitir todos los orígenes
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                    .allowedHeaders("Content-Type", "Authorization") // Encabezados permitidos
                    .maxAge(3600); // Tiempo de vida de la solicitud preflight (en segundos)
            }
        };
    }
}