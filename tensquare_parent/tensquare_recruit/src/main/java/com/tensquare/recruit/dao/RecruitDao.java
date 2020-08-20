package com.tensquare.recruit.dao;


import com.tensquare.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


/**
 * Created by Toby.Li on 2020/8/20.
 */
public interface RecruitDao extends JpaRepository<Recruit, String>, JpaSpecificationExecutor<Recruit> {

    //推荐 state = 2
    public List<Recruit> findTop1ByStateOrderByCreatetimeDesc(String state);
    //最新的 state != 0
    public List<Recruit> findTop3ByStateNotOrderByCreatetimeDesc(String state);

}
