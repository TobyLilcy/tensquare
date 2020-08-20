package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.RecruitDao;
import com.tensquare.recruit.pojo.Recruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
public class RecruitService {

    @Autowired
    private RecruitDao recruitDao;
    @Autowired
    private IdWorker idWorker;

    public List<Recruit> findAll(){
        return recruitDao.findAll();
    }

    public Recruit findById(String id){
        return recruitDao.findById(id).get();
    }

    public void save(Recruit recruit){
        recruit.setId(idWorker.nextId() + "");
        recruitDao.save(recruit);
    }

    public void update(Recruit recruit){
        recruitDao.save(recruit);
    }

    public void deleteById(String id){
        recruitDao.deleteById(id);
    }

    public List<Recruit> search(Recruit recruit) {
        Specification specification = createSpecification(recruit);
        return recruitDao.findAll(specification);
    }

    private Specification createSpecification(Recruit recruit) {
        return new Specification<Recruit>() {
            @Override
            public Predicate toPredicate(Root<Recruit> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //创建一个list来收集查询对象Predicate
                List<Predicate> list = new ArrayList<>();
                if(!StringUtils.isEmpty(recruit.getJobname())){
                    // where labelname like % labelname %
                    Predicate labelname = criteriaBuilder.like(root.get("jobname").as(String.class), "%" + recruit.getJobname() + "%");
                    list.add(labelname);
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
    }

    public Page<Recruit> searchPage(Recruit recruit, int page, int size) {
        Specification specification = createSpecification(recruit);
        Pageable pageable = PageRequest.of(page - 1, size);
        return recruitDao.findAll(specification, pageable);
    }

    public List<Recruit> recommend(String state) {
        return recruitDao.findTop1ByStateOrderByCreatetimeDesc(state);
    }

    public List<Recruit> newlist(String state) {
        return recruitDao.findTop3ByStateNotOrderByCreatetimeDesc(state);
    }
}
