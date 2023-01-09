package com.demo.myshiro.dao;

import com.demo.myshiro.entity.SysUser;
import com.demo.myshiro.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserDao {

    void insert(SysUser user);

    SysUser queryUserByName(String username);
}
