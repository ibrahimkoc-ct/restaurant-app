package com.ba;

import com.ba.builder.MediaBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class Run {
    public static void main(String[] args) {
        SpringApplication.run(Run.class,args);
        System.out.println("hello World");

    }
}
