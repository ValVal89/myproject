package com.example.myproject;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class Example {

    public static void main(String[] args) {
        SpringApplication.run(Example.class, args);
        /* SpringApplication app = new SpringApplication(Example.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);*/
       }

}