package com.tensquare.article.service;

import com.tensquare.article.dao.ArticleDao;
import com.tensquare.article.pojo.Article;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import utils.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author TobyLi
 * @version 1.0
 * @date 2020/8/21 22:58
 * <p>
 * TODO:
 */
@Service
@Transactional
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RedisTemplate redisTemplate;

    public List<Article> findAll(){
        return articleDao.findAll();
    }

    public Article findById(String id){
        //1: 先从redis缓存中获取
        Article article = (Article) redisTemplate.opsForValue().get("article_" + id);
        if(article == null){
            //2: 如果redis没有, 就查询数据库
            article = articleDao.findById(id).get();
            //2.1: 将数据库查询的数据放到redis中
            redisTemplate.opsForValue().set("article_" + id, article);
        }
        return article;
    }

    public void save(Article article){
        article.setId(idWorker.nextId() + "");
        articleDao.save(article);
    }

    public void update(Article article){
        //修改的时候要删除redis缓存, 以便刷新redis
        redisTemplate.delete("article_" + article.getId());
        articleDao.save(article);
    }

    public void deleteById(String id){
        //修改的时候要删除redis缓存, 以便刷新redis
        redisTemplate.delete("article_" + id);
        articleDao.deleteById(id);
    }

    public List<Article> search(Article article){
        Specification specification = createSpecification(article);
        return articleDao.findAll(specification);
    }

    public Page searchPage(Article article, int page, int size){
        Specification specification = createSpecification(article);
        Pageable pageable = PageRequest.of(page - 1, size);
        return articleDao.findAll(specification, pageable);
    }

    private Specification createSpecification(Article article) {
        return new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if(StringUtils.hasText(article.getColumnid())){
                    Predicate predicate = criteriaBuilder.like(root.get("columnid").as(String.class), "%" + article.getColumnid() + "%");
                    predicateList.add(predicate);
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }

    public Page channel(String channelid, int page, int size){
        Pageable pageable = PageRequest.of(page - 1, size);
        return articleDao.channel(channelid, pageable);
    }

    public Page column(String chlumuid, int page, int size){
        Pageable pageable = PageRequest.of(page - 1, size);
        return articleDao.column(chlumuid, pageable);
    }

    public void thumbup(String articleid){
        articleDao.thumbup(articleid);
    }

    public void examine(String articleid){
        articleDao.examine(articleid);
    }
}
