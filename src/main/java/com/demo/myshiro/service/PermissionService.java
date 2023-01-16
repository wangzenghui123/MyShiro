package com.demo.myshiro.service;

import com.demo.myshiro.entity.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> queryPermissionById(String id);

}
