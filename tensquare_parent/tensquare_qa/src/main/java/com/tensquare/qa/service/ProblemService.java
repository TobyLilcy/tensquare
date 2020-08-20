package com.tensquare.qa.service;

import com.tensquare.qa.dao.ProblemDao;
import com.tensquare.qa.pojo.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.IdWorker;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Toby.Li on 2020/8/20.
 */
@Service
@Transactional
public class ProblemService {

    @Autowired
    private ProblemDao problemDao;
    @Autowired
    private IdWorker idWorker;

    public List<Problem> findAll(){
        return problemDao.findAll();
    }

    public Problem findById(String id){
        return problemDao.findById(id).get();
    }

    public void save(Problem problem){
        problem.setId(idWorker.nextId() + "");
        problemDao.save(problem);
    }

    public void update(Problem problem){
        problemDao.save(problem);
    }

    public void deleteById(String id){
        problemDao.deleteById(id);
    }
}
