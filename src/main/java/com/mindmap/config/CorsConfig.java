package com.mindmap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // Allow frontend domains
        config.addAllowedOrigin("http://localhost:5173"); // Local development
        config.addAllowedOrigin("https://ai-board-front-efknttpuj-amirbekais-projects.vercel.app"); // Vercel deployment
        
        // Allow all HTTP methods
        config.addAllowedMethod("*");
        
        // Allow all headers
        config.addAllowedHeader("*");
        
        // Allow credentials
        config.setAllowCredentials(true);
        
        // Set max age for preflight requests
        config.setMaxAge(3600L);
        
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
} 