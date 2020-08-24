package com.tensquare.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import utils.IdWorker;

/**
 * @author TobyLi
 * @version 1.0
 * @date 2020/8/21 22:40
 * <p>
 * TODO:
 */
@SpringBootApplication
public class ArticleAppliication {

    public static void main(String[] args) {
        SpringApplication.run(ArticleAppliication.class, args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
