package com.tensquare.article.dao;

import com.tensquare.article.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


/**
 * @author TobyLi
 * @version 1.0
 * @date 2020/8/21 22:57
 * <p>
 * TODO:
 */
public interface ArticleDao extends JpaRepository<Article, String>, JpaSpecificationExecutor<Article> {

    // 根据频道ID获取文章列表
    @Query(value = "select * from tb_article where channelid=?", nativeQuery = true)
    public Page channel(String channelid, Pageable pageable);

    //根据专栏ID获取文章列表
    @Query(value = "select * from tb_article where columnid=?", nativeQuery = true)
    public Page column(String chlumuid, Pageable pageable);

    //点赞
    @Modifying
    @Query(value = "update tb_article a set thumbup=thumbup+1 where id=?", nativeQuery = true)
    public void thumbup(String articleid);

    //文章审核
    @Modifying
    @Query(value = "update tb_article set state='1' where id=?", nativeQuery = true)
    public void examine(String articleid);
}
