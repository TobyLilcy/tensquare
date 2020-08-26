package com.tensquare.user.service;

import com.tensquare.user.dao.UserDao;
import com.tensquare.user.pojo.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import utils.IdWorker;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Toby.Li on 2020/8/26.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public List<User> findAll(){
        return userDao.findAll();
    }

    public User findById(String id){
        return userDao.findById(id).get();
    }

    public void save(User user, String code) throws Exception {
        String checkcode = (String) redisTemplate.opsForValue().get("checkcode_" + user.getMobile());
        if(checkcode.isEmpty()){
            throw new Exception("验证码不能为空");
        }
        if(!code.equals(checkcode)){
            throw new Exception("请输入正确的验证码");
        }
        user.setId(idWorker.nextId() + "");
        user.setFollowcount((long) 0);//关注数
        user.setFanscount((long) 0);//粉丝数
        user.setOnline((long) 0);//在线时长
        Date date = new Date();
        user.setRegdate(date);//注册日期
        user.setUpdatedate(date);//更新日期
        user.setLastdate(date);//最后登陆日期
        userDao.save(user);
    }

    public void update(User user){
        userDao.save(user);
    }

    public void sendSms(String mobile){
        String random = RandomStringUtils.randomNumeric(6);
        redisTemplate.opsForValue().set("checkcode_" + mobile, random, 1, TimeUnit.MINUTES);
        Map<String, Object> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("code", random);
        rabbitTemplate.convertAndSend("sms", map);
        System.out.println(random);
    }

}
