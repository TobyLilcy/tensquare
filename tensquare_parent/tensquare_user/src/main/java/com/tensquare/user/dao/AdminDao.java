package com.tensquare.user.dao;

import com.tensquare.user.pojo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Toby.Li on 2020/8/31.
 */
public interface AdminDao extends JpaRepository<Admin, String>, JpaSpecificationExecutor<Admin> {

    public Admin findByLoginname(String loginname);
}
