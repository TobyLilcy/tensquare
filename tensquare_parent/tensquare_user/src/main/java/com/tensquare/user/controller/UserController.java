package com.tensquare.user.controller;

import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Toby.Li on 2020/8/26.
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Result findAll(){
        List<User> userList = userService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", userList);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id){
        User user = userService.findById(id);
        return new Result(true, StatusCode.OK, "查询成功", user);
    }

    @PostMapping("/{code}")
    public Result register(@RequestBody User user, @PathVariable String code) throws Exception {
        userService.save(user, code);
        return new Result(true, StatusCode.OK, "注册成功", "");
    }

    @PostMapping("/sendSms/{mobile}")
    public Result sendSms(@PathVariable String mobile){
        userService.sendSms(mobile);
        return new Result(true, StatusCode.OK, "发送成功", "");
    }

    @PutMapping
    public Result update(@RequestBody User user){
        userService.update(user);
        return new Result(true, StatusCode.OK, "修改成功", "");
    }
}
