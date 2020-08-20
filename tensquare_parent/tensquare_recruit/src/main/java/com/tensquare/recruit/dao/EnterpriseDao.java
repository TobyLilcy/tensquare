package com.tensquare.recruit.dao;


import com.tensquare.recruit.pojo.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by Toby.Li on 2020/8/20.
 */
public interface EnterpriseDao extends JpaRepository<Enterprise, String>, JpaSpecificationExecutor<Enterprise> {

    public List<Enterprise> findByIshot(String ishot);  //where ishot = ?
}
