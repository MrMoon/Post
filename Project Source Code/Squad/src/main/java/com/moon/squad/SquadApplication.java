package com.moon.squad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com"})
public class SquadApplication {
    public static void main(String[] args) {
        SpringApplication.run(SquadApplication.class, args);
    }

}
