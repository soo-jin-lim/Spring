package com.example.sbjap001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Sbjap001Application {

    public static void main(String[] args) {
        SpringApplication.run(Sbjap001Application.class, args);
    }

}
