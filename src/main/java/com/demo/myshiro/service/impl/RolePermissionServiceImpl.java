package com.demo.myshiro.service.impl;

import com.demo.myshiro.dao.RolePermissionDao;
import com.demo.myshiro.entity.RolePermission;
import com.demo.myshiro.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    public List<RolePermission> queryRolePermissionByRoleId(String roleId) {
        return rolePermissionDao.queryRolePermissionByRoleId(roleId);
    }
}
