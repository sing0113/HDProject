package com.hand.hjmall.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
* @Description:    RestTemplate的配置
* @CreateDate:     2019/2/21 16:55
* @CreateUser:     SilenceTian
* @Version:        1.0
*/

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
