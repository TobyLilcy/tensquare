package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Toby.Li on 2020/8/24.
 */
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @GetMapping
    public Result findAll(){
        List<Spit> spitList = spitService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", spitList);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id){
        Spit spit = spitService.findById(id);
        return new Result(true, StatusCode.OK, "查询成功", spit);
    }
}
