package com.demo.myshiro.service.impl;

import com.demo.myshiro.dao.PermissionDao;
import com.demo.myshiro.entity.Permission;
import com.demo.myshiro.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl  implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public Permission queryPermissionById(String id) {
        return permissionDao.queryPermissionById(id);
    }
}
