package fr.gamedev.question.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author djer13
 *
 */
@SpringBootApplication(proxyBeanMethods = false)
public class QuestionApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestionApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
