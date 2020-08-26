package com.tensquare.user.exception;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by Toby.Li on 2020/8/26.
 */
@RestControllerAdvice
public class UserException {

    @ExceptionHandler(value = Exception.class)
    public Result error(Exception e){
        e.printStackTrace();
        return new Result(true, StatusCode.ERROR, "", e.getMessage());
    }
}
