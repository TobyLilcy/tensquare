package com.tensquare.qa.dao;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Toby.Li on 2020/8/20.
 */
public interface ProblemDao extends JpaRepository<Problem, String>, JpaSpecificationExecutor<Problem> {
}
