package com.demo.myshiro.service;

import com.demo.myshiro.dao.UserRoleDao;
import com.demo.myshiro.entity.UserRole;
import com.demo.myshiro.service.impl.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<UserRole> queryUserRoleByUserId(String userId) {
        return userRoleDao.queryUserRoleByUserId(userId);
    }
}
