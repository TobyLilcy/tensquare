package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.EnterpriseDao;
import com.tensquare.recruit.pojo.Enterprise;
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
public class EnterpriseService {

    @Autowired
    private EnterpriseDao enterpriseDao;
    @Autowired
    private IdWorker idWorker;

    public List<Enterprise> findAll(){
        return enterpriseDao.findAll();
    }

    public Enterprise findById(String id){
        return enterpriseDao.findById(id).get();
    }

    public void save(Enterprise enterprise){
        enterprise.setId(idWorker.nextId() + "");
        enterpriseDao.save(enterprise);
    }

    public void update(Enterprise enterprise){
        enterpriseDao.save(enterprise);
    }

    public void deleteById(String id){
        enterpriseDao.deleteById(id);
    }

    public List<Enterprise> search(Enterprise enterprise) {
        Specification specification = createSpecification(enterprise);
        return enterpriseDao.findAll(specification);
    }

    private Specification createSpecification(Enterprise enterprise) {
        return new Specification<Enterprise>() {
            @Override
            public Predicate toPredicate(Root<Enterprise> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //创建一个list来收集查询对象Predicate
                List<Predicate> list = new ArrayList<>();
                if(!StringUtils.isEmpty(enterprise.getName())){
                    // where labelname like % labelname %
                    Predicate labelname = criteriaBuilder.like(root.get("name").as(String.class), "%" + enterprise.getName() + "%");
                    list.add(labelname);
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        };
    }

    public Page<Enterprise> searchPage(Enterprise enterprise, int page, int size) {
        Specification specification = createSpecification(enterprise);
        Pageable pageable = PageRequest.of(page - 1, size);
        return enterpriseDao.findAll(specification, pageable);
    }

    public List<Enterprise> findByIshot(String ishot){
        return enterpriseDao.findByIshot(ishot);
    }
}
