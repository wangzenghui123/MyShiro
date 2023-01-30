package com.demo.myshiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {

    private String id;

    private String name;

    private String code;

    private String perms;

    private String url;

    private String method;

    private String pid;

    private String pName;

    private int orderNum;

    private int type;

    private int status;

    private Timestamp createTime;

    private Timestamp updateTime;

    private int deleted;



}
