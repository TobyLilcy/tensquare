package com.tensquare.recruit.controller;

import com.tensquare.recruit.pojo.Recruit;
import com.tensquare.recruit.service.RecruitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Toby.Li on 2020/8/20.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/recruit")
public class RecruitController {

    @Autowired
    private RecruitService recruitService;

    @GetMapping
    public Result findAll(){
        List<Recruit> recruitList = recruitService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", recruitList);
    }

    @GetMapping(value = "/{id}")
    public Result findByid(@PathVariable String id){
        Recruit recruit = recruitService.findById(id);
        return new Result(true, StatusCode.OK, "查询成功", recruit);
    }

    @GetMapping(value = "/search/recommend")
    public Result recommend(){
        List<Recruit> recruitList = recruitService.recommend("2");
        return new Result(true, StatusCode.OK, "查询成功", recruitList);
    }

    @GetMapping(value = "/search/newlist")
    public Result newlist(){
        List<Recruit> recruitList = recruitService.newlist("0");
        return new Result(true, StatusCode.OK, "查询成功", recruitList);
    }

    @PostMapping
    public Result save(@RequestBody Recruit recruit){
        recruitService.save(recruit);
        return new Result(true, StatusCode.OK, "添加成功", "");
    }

    @PostMapping(value = "/search")
    public Result search(@RequestBody Recruit recruit){
        List<Recruit> enterpriseList = recruitService.search(recruit);
        return new Result(true, StatusCode.OK, "查询成功", enterpriseList);
    }

    @PostMapping(value = "/search/{page}/{size}")
    public Result searchPage(@RequestBody Recruit recruit, @PathVariable int page, @PathVariable int size){
        Page enterprises = recruitService.searchPage(recruit, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult(enterprises.getTotalElements(), enterprises.getContent()));
    }

    @PutMapping
    public Result update(@RequestBody Recruit recruit){
        recruitService.update(recruit);
        return new Result(true, StatusCode.OK, "修改成功", "");
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id){
        recruitService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功", "");
    }

}
