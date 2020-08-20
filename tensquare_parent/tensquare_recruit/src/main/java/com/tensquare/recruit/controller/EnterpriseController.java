package com.tensquare.recruit.controller;

import com.tensquare.recruit.pojo.Enterprise;
import com.tensquare.recruit.service.EnterpriseService;
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
@RequestMapping(value = "/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    @GetMapping
    public Result findAll(){
        List<Enterprise> enterpriseList = enterpriseService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", enterpriseList);
    }

    @GetMapping(value = "/{id}")
    public Result findByid(@PathVariable String id){
        Enterprise enterprise = enterpriseService.findById(id);
        return new Result(true, StatusCode.OK, "查询成功", enterprise);
    }

    @GetMapping(value = "/search/hotlist")
    public Result findByIshot(){
        List<Enterprise> enterpriseList = enterpriseService.findByIshot("1");
        return new Result(true, StatusCode.OK, "查询成功", enterpriseList);
    }

    @PostMapping
    public Result save(@RequestBody Enterprise enterprise){
        enterpriseService.save(enterprise);
        return new Result(true, StatusCode.OK, "添加成功", "");
    }

    @PostMapping(value = "/search")
    public Result search(@RequestBody Enterprise enterprise){
        List<Enterprise> enterpriseList = enterpriseService.search(enterprise);
        return new Result(true, StatusCode.OK, "查询成功", enterpriseList);
    }

    @PostMapping(value = "/search/{page}/{size}")
    public Result searchPage(@RequestBody Enterprise enterprise, @PathVariable int page, @PathVariable int size){
        Page enterprises = enterpriseService.searchPage(enterprise, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult(enterprises.getTotalElements(), enterprises.getContent()));
    }

    @PutMapping
    public Result update(@RequestBody Enterprise enterprise){
        enterpriseService.update(enterprise);
        return new Result(true, StatusCode.OK, "修改成功", "");
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id){
        enterpriseService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功", "");
    }

}
