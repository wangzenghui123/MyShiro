package com.demo.myshiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.demo")
public class MyShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyShiroApplication.class, args);
    }

}
