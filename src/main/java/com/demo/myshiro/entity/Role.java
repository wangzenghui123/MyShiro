package com.demo.myshiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private String id;

    private String name;

    private String description;

    private int status;

    private Date createTime;

    private Date updateTime;

    private String deleted;

}
