package com.tensquare.gathering.controller;

import com.tensquare.gathering.pojo.Gathering;
import com.tensquare.gathering.service.GatheringService;
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
@RequestMapping("/gathering")
public class GatheringController {

    @Autowired
    private GatheringService gatheringService;

    @GetMapping
    public Result findAll(){
        List<Gathering> gatheringList = gatheringService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", gatheringList);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id){
        Gathering gathering = gatheringService.findById(id);
        return new Result(true, StatusCode.OK, "查询成功", gathering);
    }

    @GetMapping("/city/{city}/{page}/{size}")
    public Result findByCity(@PathVariable String city, @PathVariable int page, @PathVariable int size){
        Page gatheringList = gatheringService.findByCity(city, page, size);
        PageResult pageResult = new PageResult(gatheringList.getTotalElements(), gatheringList.getContent());
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    @PostMapping
    public Result svae(@RequestBody Gathering gathering){
        gatheringService.save(gathering);
        return new Result(true, StatusCode.OK, "添加成功", "");
    }

    @PostMapping("/search")
    public Result search(@RequestBody Gathering gathering){
        List<Gathering> gatheringList = gatheringService.search(gathering);
        return new Result(true, StatusCode.OK, "查询成功", gatheringList);
    }

    @PostMapping("/search/{page}/{size}")
    public Result search(@RequestBody Gathering gathering, @PathVariable int page, @PathVariable int size){
        Page gatheringList = gatheringService.searchPage(gathering, page, size);
        PageResult pageResult = new PageResult(gatheringList.getTotalElements(), gatheringList.getContent());
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    @PutMapping
    public Result update(@RequestBody Gathering gathering){
        gatheringService.update(gathering);
        return new Result(true, StatusCode.OK, "修改成功", "");
    }

    @DeleteMapping("/{id}")
    public Result deletaById(@PathVariable String id){
        gatheringService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功", "");
    }
}
