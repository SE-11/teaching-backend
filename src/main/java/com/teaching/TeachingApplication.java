package com.teaching;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.teaching.mapper")
public class TeachingApplication {
    public static void main(String[] args) {
        SpringApplication.run(TeachingApplication.class, args);
    }
}
