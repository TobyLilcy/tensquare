package com.tensquare.recruit.pojo;

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
@Table(name = "tb_recruit")
@Data
public class Recruit implements Serializable {

    @Id
    private String id;
    private String jobname; //招聘职位
    private String salary; //薪资范围
    private String condition; //经验要求
    private String education; //学历要求
    private String type; //任职方式
    private String address; //办公地址
    private String eid; //企业ID
    private Date createtime; //日期
    private String state; //状态 0:关闭 1:开始 2:推荐
    private String url; //原网址
    private String label; //标签
    private String content1; //职位描述
    private String content2; //职位描述
}
