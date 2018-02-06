package com.example.myproject;

import com.example.myproject.topic.Topic;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CallRestService implements CommandLineRunner {

    public void run(String... args) {
       /* System.out.println("__________will be called just before SpringApplication.run completes_______________");
        System.out.println(args.length);
        //System.exit(exitCodeGenerator().getExitCode());*/

       callRestService();
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

    private static void callRestService()
    {
        RestTemplate restTemplate = new RestTemplate();
        Topic topic = restTemplate.getForObject("http://localhost:8080/topics/ee", Topic.class);
        System.out.println("Topic ee has a name " + topic.getName());
    }


}