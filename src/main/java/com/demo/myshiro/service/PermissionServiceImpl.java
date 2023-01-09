package com.demo.myshiro.service;

import com.demo.myshiro.dao.PermissionDao;
import com.demo.myshiro.entity.Permission;
import com.demo.myshiro.service.impl.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
