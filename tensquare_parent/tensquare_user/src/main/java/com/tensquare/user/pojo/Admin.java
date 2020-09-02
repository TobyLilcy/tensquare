package com.tensquare.user.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Toby.Li on 2020/8/31.
 */
@Entity
@Table(name = "tb_admin")
@Data
public class Admin {

    @Id
    private String id;
    private String loginname;
    private String password;
    private String state;
}
