package com.demo.myshiro.dao;

import com.demo.myshiro.entity.Role;
import com.demo.myshiro.vo.req.RolePageReqVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface RoleDao {

    List<Role> queryRoleById(String id);

    ArrayList<String> queryPermissionIdByRoleId(String roleId);

    List<Role> queryRolesByPermissionId(String id);

    List<Role> selectAll(RolePageReqVO rolePageReqVO);

    int updateRoleById(String roleId, String roleName, String description, Timestamp updateTime);

    void deletePermissionByRoleId(String id);

    void addRolePermission(@Param("id") String id, @Param("roleId") String roleId,@Param("permissionId")  String permissionId, @Param("updateTime") Timestamp updateTime);

    ArrayList<String> queryUserIdByRoleId(String roleId);
}
