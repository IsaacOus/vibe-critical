package com.esgi.jeeproject.vibecritical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.modelmapper.ModelMapper;

@SpringBootApplication
public class VibeCriticalApplication {

    public static void main(String[] args) {
        SpringApplication.run(VibeCriticalApplication.class, args);
        System.out.println("it work");
    }
<<<<<<< HEAD
=======

   /* @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
*/
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

>>>>>>> dev
}
