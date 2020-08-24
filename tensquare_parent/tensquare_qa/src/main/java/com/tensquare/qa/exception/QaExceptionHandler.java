package com.tensquare.qa.exception;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author TobyLi
 * @version 1.0
 * @date 2020/8/20 17:25
 * <p>
 * TODO: qa的微服务异常统一处理
 */
@RestControllerAdvice
public class QaExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result error(Exception e){
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, "异常", e.getMessage());
    }
}
