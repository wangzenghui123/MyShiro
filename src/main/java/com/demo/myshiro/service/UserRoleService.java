package com.demo.myshiro.service;

import com.demo.myshiro.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface UserRoleService {

    List<UserRole> queryUserRoleByUserId(String userId);

}
