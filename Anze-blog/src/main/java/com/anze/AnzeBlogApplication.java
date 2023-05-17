package com.anze;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(basePackages = {"com.anze.mapper"})
@EnableScheduling
@EnableSwagger2
public class AnzeBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnzeBlogApplication.class,args);
    }
}
