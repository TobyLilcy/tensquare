package com.tensquare.search.controller;

import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.SearchService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Toby.Li on 2020/8/25.
 */
@RestController
@CrossOrigin
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping
    public Result save(@RequestBody Article article){
        searchService.save(article);
        return new Result(true, StatusCode.OK, "添加成功", "");
    }

    @GetMapping("/{title}/{content}/{page}/{size}")
    public Result search(@PathVariable String title, @PathVariable String content, @PathVariable int page, @PathVariable int size){
        Page<Article> search = searchService.search(title, content, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(search.getTotalElements(), search.getContent()));
    }
}
