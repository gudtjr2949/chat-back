package com.example.chatnormal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ChatNormalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatNormalApplication.class, args);
    }

}
