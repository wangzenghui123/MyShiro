package com.demo.myshiro.dao;

import com.demo.myshiro.entity.Role;
import com.demo.myshiro.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRoleDao {

    List<UserRole> queryUserRoleByUserId(String userId);

}
