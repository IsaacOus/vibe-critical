package com.esgi.jeeproject.vibecritical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;

@SpringBootApplication
public class VibeCriticalApplication {

    public static void main(String[] args) {
        SpringApplication.run(VibeCriticalApplication.class, args);
        System.out.println("it work");
    }

}
