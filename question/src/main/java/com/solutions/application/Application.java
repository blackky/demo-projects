package com.solutions.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.solutions")
public class Application {
public static void main(String[] args){
	SpringApplication.run(Application.class, args);
}
}
