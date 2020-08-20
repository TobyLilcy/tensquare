package com.tensquare.qa.controller;

import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.service.ProblemService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Toby.Li on 2020/8/20.
 */
@RestController
@CrossOrigin
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @GetMapping
    public Result findAll(){
        List<Problem> problemList = problemService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", problemList);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id){
        Problem problem = problemService.findById(id);
        return new Result(true, StatusCode.OK, "查询成功", problem);
    }
}
