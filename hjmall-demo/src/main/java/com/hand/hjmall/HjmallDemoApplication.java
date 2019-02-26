package com.hand.hjmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hand.hjmall.mapper")
public class HjmallDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(HjmallDemoApplication.class, args);
    }

}
