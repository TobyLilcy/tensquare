package com.tensquare.gathering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import utils.IdWorker;

/**
 * Created by Toby.Li on 2020/8/24.
 */
@SpringBootApplication
@EnableCaching
public class GatheringApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatheringApplication.class, args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1, 1);
    }
}
