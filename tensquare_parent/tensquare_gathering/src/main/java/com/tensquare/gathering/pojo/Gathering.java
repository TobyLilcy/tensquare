package com.tensquare.gathering.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Toby.Li on 2020/8/24.
 */
@Entity
@Table(name = "tb_gathering")
@Data
public class Gathering implements Serializable {

    @Id
    private String id;
    private String name;
    private String summary;
    private String detail;
    private String sponsor;
    private String image;
    private Date starttime;
    private Date endtime;
    private String address;
    private Date enrolltime;
    private String state;
    private String city;
}
