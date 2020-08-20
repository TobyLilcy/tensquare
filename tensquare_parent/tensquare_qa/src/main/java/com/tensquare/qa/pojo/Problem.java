package com.tensquare.qa.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Toby.Li on 2020/8/20.
 */
@Entity
@Table(name = "tb_problem")
@Data
public class Problem implements Serializable {

    @Id
    private String id;
    private String title; //问题标题
    private String content; //问题内容
    private Date createtime; //发布时间
    private Date updatetime; //更新时间
    private String userid; //发布人id
    private String nickname; //发布人昵称
    private Long visits; //浏览量
    private Long thumbup; //点赞数
    private Long reply; //回复数
    private String solve; //是否解决
    private String replyname; //最新回复人
    private Date replytime; //最新回复时间
}
