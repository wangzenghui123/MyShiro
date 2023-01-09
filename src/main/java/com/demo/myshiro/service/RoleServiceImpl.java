package com.demo.myshiro.service;

import com.demo.myshiro.dao.RoleDao;
import com.demo.myshiro.entity.Role;
import com.demo.myshiro.service.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;


    @Override
    public Role queryRoleById(String id) {
        return roleDao.queryRoleById(id);
    }
}
