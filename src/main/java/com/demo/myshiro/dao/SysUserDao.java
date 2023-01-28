package com.demo.myshiro.dao;

import com.demo.myshiro.entity.SysUser;
import com.demo.myshiro.vo.req.UserPageReqVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface SysUserDao {

    void insert(SysUser user);

    SysUser queryUserByName(String username);
    SysUser queryUserById(String userId);

    List<SysUser> selectAll(UserPageReqVO userPageReqVO);
    ArrayList<String> queryRoleIdByUserId(String userId);

    List<SysUser> queryUserByRoleId(String roleId);
}
