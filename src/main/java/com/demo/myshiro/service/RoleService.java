package com.demo.myshiro.service;

import com.demo.myshiro.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> queryRoleById(String id);

}
