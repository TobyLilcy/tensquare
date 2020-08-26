package com.tensquare.user.dao;

import com.tensquare.user.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Toby.Li on 2020/8/26.
 */
public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
}
