package com.example.ProgettoJavaSettimana5;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Scanner;

@Configuration
public class BeansConfig {

    @Bean
    public Faker getFaker() { return new Faker(Locale.ITALY);
    }

    @Bean
    public Scanner getScanner(){
        return new Scanner(System.in);
    }
}
