package com.tensquare.qa.service;

import com.tensquare.qa.dao.ProblemDao;
import com.tensquare.qa.pojo.Problem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import utils.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
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

    public List<Problem> search(Problem problem) {
        Specification specification = createSpecification(problem);
        return problemDao.findAll(specification);
    }

    private Specification<Problem> createSpecification(Problem problem) {
        return new Specification<Problem>() {
            @Override
            public Predicate toPredicate(Root<Problem> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if(StringUtils.hasText(problem.getNickname())){
                    Predicate predicate = criteriaBuilder.like(root.get("nickname").as(String.class), "%" + problem.getNickname() + "%");
                    predicateList.add(predicate);
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }

    public Page searchPage(Problem problem, int page, int size) {
        Specification specification = createSpecification(problem);
        Pageable pageable = PageRequest.of(page - 1, size);
        return problemDao.findAll(specification, pageable);
    }


    public Page newlist(String labelid, int page, int size){
        Pageable pageable = PageRequest.of(page - 1, size);
        return problemDao.newlist(labelid, pageable);
    }

    public Page hotlist(String labelid, int page, int size){
        Pageable pageable = PageRequest.of(page - 1, size);
        return problemDao.hotlist(labelid, pageable);
    }

    public Page waitlist(String labelid, int page, int size){
        Pageable pageable = PageRequest.of(page - 1, size);
        return problemDao.waitlist(labelid, pageable);
    }
}
