package com.tensquare.recruit.exception;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by Toby.Li on 2020/8/19.
 */
@RestControllerAdvice //异常注解
public class RecruitExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result error(Exception e){
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, "", e.getMessage());
    }
}
