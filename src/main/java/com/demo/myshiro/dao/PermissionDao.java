package com.demo.myshiro.dao;

import com.demo.myshiro.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionDao {

    List<Permission> queryPermissionById(String id);
}
