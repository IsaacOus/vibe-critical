package com.esgi.jeeproject.vibecritical;

import com.esgi.jeeproject.vibecritical.domain.Role;
import com.esgi.jeeproject.vibecritical.domain.User;
import com.esgi.jeeproject.vibecritical.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class VibeCriticalApplication {

    public static void main(String[] args) {
        SpringApplication.run(VibeCriticalApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
