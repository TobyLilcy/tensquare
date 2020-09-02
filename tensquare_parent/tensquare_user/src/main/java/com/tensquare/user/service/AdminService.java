package com.tensquare.user.service;

import com.tensquare.user.dao.AdminDao;
import com.tensquare.user.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import utils.IdWorker;

import javax.transaction.Transactional;

/**
 * Created by Toby.Li on 2020/8/31.
 */
@Service
@Transactional
public class AdminService {

    @Autowired
    private AdminDao adminDao;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private IdWorker idWorker;

    public void add(Admin admin){
        admin.setId(idWorker.nextId() + "'");
        admin.setPassword(encoder.encode(admin.getPassword()));
        adminDao.save(admin);
    }

    public Admin findByLoginnameAndPassword(String loginname, String password){
        Admin admin = adminDao.findByLoginname(loginname);
        if(admin != null && encoder.matches(password, admin.getPassword())){
            return admin;
        }
        return null;
    }
}
