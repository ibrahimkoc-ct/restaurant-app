package com.ba;

import com.ba.builder.MediaBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Run {
    public static void main(String[] args) {
        SpringApplication.run(Run.class,args);
        System.out.println("hello World");

    }
}
