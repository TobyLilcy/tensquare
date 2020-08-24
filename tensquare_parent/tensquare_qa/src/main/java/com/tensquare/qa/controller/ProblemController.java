package com.tensquare.qa.controller;

import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.service.ProblemService;
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

    @GetMapping("/newlist/{labelid}/{page}/{size}")
    public Result newlist(@PathVariable String labelid, @PathVariable int page, @PathVariable int size){
        Page newlist = problemService.newlist(labelid, page, size);
        PageResult pageResult = new PageResult(newlist.getTotalElements(), newlist.getContent());
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    @GetMapping("/hotlist/{labelid}/{page}/{size}")
    public Result hotlist(@PathVariable String labelid, @PathVariable int page, @PathVariable int size){
        Page hotlist = problemService.hotlist(labelid, page, size);
        PageResult pageResult = new PageResult(hotlist.getTotalElements(), hotlist.getContent());
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    @GetMapping("/waitlist/{labelid}/{page}/{size}")
    public Result waitlist(@PathVariable String labelid, @PathVariable int page, @PathVariable int size){
        Page waitlist = problemService.waitlist(labelid, page, size);
        PageResult pageResult = new PageResult(waitlist.getTotalElements(), waitlist.getContent());
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    @PostMapping("/search")
    public Result search(@RequestBody Problem problem){
        List<Problem> problemList = problemService.search(problem);
        return new Result(true, StatusCode.OK, "查询成功", problemList);
    }

    @PostMapping("/search/{page}/{size}")
    public Result searchPage(@RequestBody Problem problem, @PathVariable int page, @PathVariable int size){
        Page problemList = problemService.searchPage(problem, page, size);
        return new Result(true, StatusCode.OK, "查询成功",
                new PageResult<Problem>(problemList.getTotalElements(), problemList.getContent()));
    }

    @PostMapping
    public Result save(@RequestBody Problem problem){
        problemService.save(problem);
        return new Result(true, StatusCode.OK, "添加成功", "");
    }

    @PutMapping
    public Result update(@RequestBody Problem problem){
        problemService.update(problem);
        return new Result(true, StatusCode.OK, "修改成功", "");
    }

    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable String id){
        problemService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功", "");
    }

}
