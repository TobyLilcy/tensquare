package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Toby.Li on 2020/8/19.
 */
@RestController
@CrossOrigin //跨域
@RequestMapping(value = "/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @GetMapping
    public Result findAll(){
        List<Label> labels = labelService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", labels);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id){
        Label label = labelService.findById(id);
        return new Result(true, StatusCode.OK, "查询成功", label);
    }

    @PostMapping
    public Result save(@RequestBody Label label){
        labelService.save(label);
        return new Result(true, StatusCode.OK, "添加成功", "");
    }


    @PostMapping(value = "/search")
    public Result search(@RequestBody Label label){
        List<Label> labels = labelService.search(label);
        return new Result(true, StatusCode.OK, "查询成功", labels);
    }

    @PostMapping(value = "/search/{page}/{size}")
    public Result searchPage(@RequestBody Label label, @PathVariable int page, @PathVariable int size){
        PageResult<Label> result = new PageResult();
        Page pageLists = labelService.searchPage(label, page, size);
        result.setTotal(pageLists.getTotalElements());
        result.setRows(pageLists.getContent());
        return new Result(true, StatusCode.OK, "查询成功", result);
    }

    @PutMapping
    public Result update(@RequestBody Label label){
        labelService.update(label);
        return new Result(true, StatusCode.OK, "修改成功", "");
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id){
        labelService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功", "");
    }
}
