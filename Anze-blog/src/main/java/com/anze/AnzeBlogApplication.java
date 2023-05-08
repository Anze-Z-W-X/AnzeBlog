package com.anze;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.anze.mapper"})
public class AnzeBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnzeBlogApplication.class,args);
    }
}
