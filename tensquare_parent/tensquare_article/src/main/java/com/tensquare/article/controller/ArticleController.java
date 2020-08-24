package com.tensquare.article.controller;

import com.tensquare.article.pojo.Article;
import com.tensquare.article.service.ArticleService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author TobyLi
 * @version 1.0
 * @date 2020/8/21 23:08
 * <p>
 * TODO:
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public Result findAll(){
        List<Article> articleList = articleService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", articleList);
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id){
        Article article = articleService.findById(id);
        return new Result(true, StatusCode.OK, "查询成功", article);
    }

    @GetMapping("/channel/{channelid}/{page}/{size}")
    public Result channel(@PathVariable String channelid, @PathVariable int page, @PathVariable int size){
        Page articleList = articleService.channel(channelid, page, size);
        PageResult pageResult = new PageResult(articleList.getTotalElements(), articleList.getContent());
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    @GetMapping("/column/{channelid}/{page}/{size}")
    public Result column(@PathVariable String channelid, @PathVariable int page, @PathVariable int size){
        Page articleList = articleService.channel(channelid, page, size);
        PageResult pageResult = new PageResult(articleList.getTotalElements(), articleList.getContent());
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    @PostMapping("/search")
    public Result search(@RequestBody Article article){
        List<Article> articleList = articleService.search(article);
        return new Result(true, StatusCode.OK, "查询成功", articleList);
    }

    @PostMapping("/search/{page}/{size}")
    public Result search(@RequestBody Article article, @PathVariable int page, @PathVariable int size){
        Page articleList = articleService.searchPage(article, page, size);
        PageResult pageResult = new PageResult(articleList.getTotalElements(), articleList.getContent());
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    @PostMapping
    public Result save(@RequestBody Article article){
        articleService.save(article);
        return new Result(true, StatusCode.OK, "添加成功", "");
    }

    @PutMapping
    public Result update(@RequestBody Article article){
        articleService.update(article);
        return new Result(true, StatusCode.OK, "修改成功", "");
    }

    @PutMapping("/thumbup/{articleid}")
    public Result thumbup(@PathVariable String articleid){
        articleService.thumbup(articleid);
        return new Result(true, StatusCode.OK, "修改成功", "");
    }

    @PutMapping("/examine/{articleid}")
    public Result examine(@PathVariable String articleid){
        articleService.examine(articleid);
        return new Result(true, StatusCode.OK, "审核成功", "");
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable String id){
        articleService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功", "");
    }
}
