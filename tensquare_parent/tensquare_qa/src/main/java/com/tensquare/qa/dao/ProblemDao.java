package com.tensquare.qa.dao;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Toby.Li on 2020/8/20.
 */
public interface ProblemDao extends JpaRepository<Problem, String>, JpaSpecificationExecutor<Problem> {

    //最新列表
    @Query(value = "select * from tb_problem, tb_pl where id=problemid and labelid=? order by replytime desc", nativeQuery = true)
    public Page<Problem> newlist(String labelid, Pageable pageable);
    //热门列表
    @Query(value = "select * from tb_problem, tb_pl where id=problemid and labelid=? order by reply desc", nativeQuery = true)
    public Page<Problem> hotlist(String labelid, Pageable pageable);
    //等待列表
    @Query(value = "select * from tb_problem, tb_pl where id=problemid and labelid=? and reply=0 order by createtime desc", nativeQuery = true)
    public Page<Problem> waitlist(String labelid, Pageable pageable);
}
