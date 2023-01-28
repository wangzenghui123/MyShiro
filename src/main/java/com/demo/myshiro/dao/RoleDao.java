package com.demo.myshiro.dao;

import com.demo.myshiro.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface RoleDao {

    List<Role> queryRoleById(String id);

    ArrayList<String> queryPermissionIdByRoleId(String roleId);

    List<Role> queryRolesByPermissionId(String id);

}
