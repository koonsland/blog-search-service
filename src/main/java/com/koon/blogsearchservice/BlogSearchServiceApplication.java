package com.koon.blogsearchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class BlogSearchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogSearchServiceApplication.class, args);
    }

}
