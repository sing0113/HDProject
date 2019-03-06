package com.hand.hjmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan({"com.hand.hjmall.mapper","com.hand.hjmall.dao"})
@EnableTransactionManagement
public class HjmallAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(HjmallAdminApplication.class, args);
    }

}
