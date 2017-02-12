package org.hacklist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

/**
 * @author Aidar Shaifutdinov.
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = {
        "org.hacklist.service", "org.hacklist.util", "org.hacklist.task", "org.hacklist.logging"}
)
public class CoreConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
