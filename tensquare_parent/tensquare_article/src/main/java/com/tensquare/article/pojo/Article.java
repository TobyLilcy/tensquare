package com.tensquare.article.pojo;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author TobyLi
 * @version 1.0
 * @date 2020/8/21 22:43
 * <p>
 * TODO:
 */
@Entity
@Table(name = "tb_article")
@Data
public class Article implements Serializable{

    @Id
    private String Id;
    private String columnid; //专栏ID
    private String userid; //用户ID
    private String title; //文章标题
    private String content; //文章内容
    private String image; //文章封面
    private Date createtime; //发表日期
    private Date updatetime; //更新日期
    private String ispublic; //是否公开 0:不公开 1:公开
    private String istop; //是否置顶 0:不置顶 1:置顶
    private Long visits; //浏览数
    private Long thumbup; //点赞数
    private Long comment; //评论数
    private String state; //审核状态 0:未审核 1:已审核
    private Long channelid; //所属频道, 关联频道表ID
    private String url; //URL地址
    private String type; //文章类型 0:分享 1:专栏
}
