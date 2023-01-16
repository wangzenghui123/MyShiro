package com.demo.myshiro.service.impl;

import com.demo.myshiro.dao.RoleDao;
import com.demo.myshiro.entity.Role;
import com.demo.myshiro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;


    @Override
    public List<Role> queryRoleById(String id) {
        return roleDao.queryRoleById(id);
    }
}
