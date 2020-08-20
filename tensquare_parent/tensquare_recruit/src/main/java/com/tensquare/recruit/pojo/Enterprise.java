package com.tensquare.recruit.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Toby.Li on 2020/8/20.
 */
@Entity
@Table(name = "tb_enterprise")
@Data
public class Enterprise implements Serializable {

    @Id
    private String id;
    private String name; //企业名字
    private String summary; //企业简介
    private String address; //企业地址
    private String labels; //标签列表, 多个用逗号隔开
    private String coordinate; //企业坐标位置, 经纬度
    private String ishot; //是否热门, 0:非热门, 1:热门
    private String logo;
    private Long jobcount; //职位数
    private String url;
}
