package org.example.testtask;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Messages {
        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }


