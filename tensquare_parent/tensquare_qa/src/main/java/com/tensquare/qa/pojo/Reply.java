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
@Data
@Table(name = "tb_reply")
public class Reply implements Serializable {

    @Id
    private String id;
    private String problemid; //问题ID
    private String content; //回答内容
    private Date createtime; //回答日期
    private Date updatetime; //更新日期
    private String userid; //回答人ID
    private String nickname; //回答人昵称
}
