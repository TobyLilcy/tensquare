package com.tensquare.gathering.service;

import com.tensquare.gathering.dao.GatheringDao;
import com.tensquare.gathering.pojo.Gathering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
 * Created by Toby.Li on 2020/8/24.
 */
@Service
@Transactional
public class GatheringService {

    @Autowired
    private GatheringDao gatheringDao;
    @Autowired
    private IdWorker idWorker;

    public List<Gathering> findAll(){
        return gatheringDao.findAll();
    }

    @Cacheable(value = "cathering", key = "#id")
    public Gathering findById(String id){
        return gatheringDao.findById(id).get();
    }

    public void save(Gathering gathering){
        gathering.setId(idWorker.nextId() + "");
        gatheringDao.save(gathering);
    }

    @CacheEvict(value = "cathering", key = "#gathering.id")
    public void update(Gathering gathering){
        gatheringDao.save(gathering);
    }

    @CacheEvict(value = "cathering", key = "#gathering.id")
    public void deleteById(String id){
        gatheringDao.deleteById(id);
    }

    public List<Gathering> search(Gathering gathering){
        return gatheringDao.findAll(createSpecification(gathering));
    }

    public Page searchPage(Gathering gathering, int page, int size){
        Specification<Gathering> specification = createSpecification(gathering);
        Pageable pageable = PageRequest.of(page - 1, size);
        return gatheringDao.findAll(specification, pageable);
    }

    public Page findByCity(String city, int page, int size){
        Pageable pageable = PageRequest.of(page - 1, size);
        return gatheringDao.findByCity(city, pageable);
    }

    private Specification<Gathering> createSpecification(Gathering gathering) {
        return new Specification<Gathering>() {
            @Override
            public Predicate toPredicate(Root<Gathering> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if(StringUtils.hasText(gathering.getName())){
                    Predicate predicate = criteriaBuilder.like(root.get("name").as(String.class), "%" + gathering.getName() + "%");
                    predicateList.add(predicate);
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }
}
