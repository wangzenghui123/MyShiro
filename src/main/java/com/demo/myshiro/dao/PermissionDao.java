package com.demo.myshiro.dao;

import com.demo.myshiro.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionDao {

    Permission queryPermissionById(String id);
}
