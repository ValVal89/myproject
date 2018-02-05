package com.example.myproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MyBean implements CommandLineRunner {

    public void run(String... args) {
        System.out.println("__________will be called just before SpringApplication.run completes_______________");
        System.out.println(args.length);
        //System.exit(exitCodeGenerator().getExitCode());
    }

    @Bean
    public ExitCodeGenerator exitCodeGenerator() {
        return new ExitCodeGenerator() {
            @Override
            public int getExitCode() {
                return 42;
            }
        };
    }


}