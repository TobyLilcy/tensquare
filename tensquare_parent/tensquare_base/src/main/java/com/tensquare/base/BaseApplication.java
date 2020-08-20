package com.tensquare.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import utils.IdWorker;

/**
 * Created by Toby.Li on 2020/8/19.
 */
@SpringBootApplication
public class BaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }

    //谁需要Id生成器就把IdWorker放到容器中
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1, 1);
    }
}
