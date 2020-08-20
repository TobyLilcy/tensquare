package com.tensquare.recruit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import utils.IdWorker;

/**
 * Created by Toby.Li on 2020/8/20.
 */
@SpringBootApplication
public class RecruitApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecruitApplication.class, args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
