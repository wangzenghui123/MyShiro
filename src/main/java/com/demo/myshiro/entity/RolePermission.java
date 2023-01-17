package com.demo.myshiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermission {

    private String id;

    private String roleId;

    private String permissionId;

    private Date createTime;


}
