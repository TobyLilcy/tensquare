package com.tensquare.search.service;

import com.tensquare.search.dao.SearchDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import utils.IdWorker;

/**
 * Created by Toby.Li on 2020/8/25.
 */
@Service
public class SearchService {

    @Autowired
    private SearchDao searchDao;
    @Autowired
    private IdWorker idWorker;

    public void save(Article article){
        article.setId(idWorker.nextId() + "");
        searchDao.save(article);
    }

    public Page<Article> search(String title, String content, int page, int size){
        Pageable pageable = PageRequest.of(page - 1, size);
        return searchDao.findByTitleOrContentLike(title, content, pageable);
    }
}
