package com.example.myproject;

import com.example.myproject.topic.Topic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
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

    @Retryable(value = Exception.class, maxAttempts=9)
    public void getTopic()
    {
        Topic topic = restTemplate.getForObject("http://localhost:8080/topics/myTopic", Topic.class);
        System.out.println("oops");
        LOGGER.info(topic.getName() + " was sucessfully extracted");
    }

    @Recover
    public  void recover(Exception npe)
    {
        Topic topic = new Topic("del","del", "del");
        HttpEntity<Topic> entity = new HttpEntity<Topic>(topic);
        ResponseEntity<Topic> response = restTemplate.postForEntity(
                "http://localhost:8080/topics", entity, Topic.class);
        System.out.println("recover");
    }


    private static void putTopic()
    {
        Topic topicold = restTemplate.getForObject("http://localhost:8080/topics/myTopic", Topic.class);
        String topicoldId = topicold.getId();
        Topic topic = new Topic(topicoldId, "newTopic", "newTopic");
        HttpEntity<Topic> entity = new HttpEntity<Topic>(topic);
        restTemplate.put(
                "http://localhost:8080/topics{id}", entity, "/myTopic");
        LOGGER.info(topic.getName() + " was sucessfully updated");
    }

    private static void deleteTopic()
    {
       restTemplate.delete("http://localhost:8080/topics{id}", "/myTopic");
        LOGGER.info("topic ee was sucessfully deleted");
    }

}