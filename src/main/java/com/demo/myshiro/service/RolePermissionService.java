package com.demo.myshiro.service;

import com.demo.myshiro.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface RolePermissionService {

    List<RolePermission> queryRolePermissionByRoleId(String roleId);

}
