package com.example.myproject;

import com.example.myproject.topic.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CallRestService implements CommandLineRunner {

   private static RestTemplate restTemplate = new RestTemplate();
   private static final Logger LOGGER = LoggerFactory.getLogger(CallRestService.class);

    public void run(String... args) {
       /* System.out.println("__________will be called just before SpringApplication.run completes_______________");
        System.out.println(args.length);
        //System.exit(exitCodeGenerator().getExitCode());*/

       postTopic();
       getTopic();
       putTopic();
       deleteTopic();
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

    private static void postTopic()
    {
        Topic topic = new Topic("myTopic","myTopic", "myTopic");
        HttpEntity<Topic> entity = new HttpEntity<Topic>(topic);
        ResponseEntity<Topic> response = restTemplate.postForEntity(
                "http://localhost:8080/topics", entity, Topic.class);
        LOGGER.info(topic.getName() + " was sucessfully added");
    }

    private static void getTopic()
    {
        Topic topic = restTemplate.getForObject("http://localhost:8080/topics/myTopic", Topic.class);
        //System.out.println("Topic ee has a name " + topic.getName());
        LOGGER.info(topic.getName() + " was sucessfully extracted");

    }
    private static void putTopic()
    {
        Topic topic = new Topic("newTopic", "newTopic", "newTopic");
        HttpEntity<Topic> entity = new HttpEntity<Topic>(topic);
        restTemplate.put(
                "http://localhost:8080/topics{id}", entity, "/myTopic");
        LOGGER.info(topic.getName() + " was sucessfully updated");
    }

    private static void deleteTopic()
    {
       restTemplate.delete("http://localhost:8080/topics{id}", "/newTopic");
        LOGGER.info("topic ee was sucessfully deleted");
    }

}