package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/comment/{parentid}/{page}/{size}")
    public Result comment(@PathVariable String parentid, @PathVariable int page, @PathVariable int size){
        Page pageLists = spitService.comment(parentid, page, size);
        PageResult pageResult = new PageResult(pageLists.getTotalElements(), pageLists.getContent());
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    @PostMapping
    public Result save(@RequestBody Spit spit){
        spitService.save(spit);
        return new Result(true, StatusCode.OK, "添加成功", "");
    }

    @PutMapping
    public Result update(@RequestBody Spit spit){
        spitService.update(spit);
        return new Result(true, StatusCode.OK, "修改成功", "");
    }

    @PutMapping("/thumbup/{id}/{userid}")
    public Result thumbup(@PathVariable String id, @PathVariable String userid){
        spitService.thumbup(id, userid);
        return new Result(true, StatusCode.OK, "点赞成功", "");
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id){
        spitService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功", "");
    }
}

