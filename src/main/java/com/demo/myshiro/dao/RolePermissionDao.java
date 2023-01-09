package com.demo.myshiro.dao;

import com.demo.myshiro.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RolePermissionDao {

    List<RolePermission> queryRolePermissionByRoleId(String roleId);

}
