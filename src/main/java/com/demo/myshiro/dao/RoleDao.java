package com.demo.myshiro.dao;

import com.demo.myshiro.entity.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleDao {

    Role queryRoleById(String id);

}
