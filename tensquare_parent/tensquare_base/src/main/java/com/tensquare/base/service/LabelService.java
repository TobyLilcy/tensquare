package com.tensquare.base.service;

import com.tensquare.base.dao.labelDao;
import com.tensquare.base.pojo.Label;
import entity.PageResult;
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
 * Created by Toby.Li on 2020/8/19.
 */
@Service
@Transactional
public class LabelService {

    @Autowired
    private labelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll(){
        return labelDao.findAll();
    }

    public Label findById(String id){
        return labelDao.findById(id).get();
    }

    public void  save(Label label){
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    public void  update(Label label){
        labelDao.save(label);
    }

    public void deleteById(String id){
        labelDao.deleteById(id);
    }

    /**
     *
     * @param label
     * @return
     * root: 根对象, 也就是要把条件封装到哪里对象中, where 类名 = label.getId
     * criteriaQuery: 封装的都是查询关键字, 比如group by order by 等
     * criteriaBuilder: 用来封装查询条件, 如果返回null表示不需要任何条件
     */
    public List<Label> search(Label label) {
        return labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //创建一个list来收集查询对象Predicate
                List<Predicate> list = new ArrayList<>();
                if(!StringUtils.isEmpty(label.getLabelname())){
                    // where labelname like % labelname %
                    Predicate labelname = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(labelname);
                }
                if(!StringUtils.isEmpty(label.getState())){
                    // where state = state
                    Predicate state = criteriaBuilder.equal(root.get("state").as(String.class), label.getState());
                    list.add(state);
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        });
    }

    public Page<Label> searchPage(Label label, int page, int size) {
        //添加分页条件
        Pageable pageable = PageRequest.of(page -1, size);
        return labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //创建一个list来收集查询对象Predicate
                List<Predicate> list = new ArrayList<>();
                if(!StringUtils.isEmpty(label.getLabelname())){
                    // where labelname like % labelname %
                    Predicate labelname = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(labelname);
                }
                if(!StringUtils.isEmpty(label.getState())){
                    // where state = state
                    Predicate state = criteriaBuilder.equal(root.get("state").as(String.class), label.getState());
                    list.add(state);
                }
                return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
            }
        }, pageable);
    }
}
