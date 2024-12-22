package com.mindmap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.mindmap.repository")
public class MindMapApplication {
    public static void main(String[] args) {
        SpringApplication.run(MindMapApplication.class, args);
    }
} 