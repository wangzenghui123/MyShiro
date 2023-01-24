package com.demo.myshiro.service;

import com.demo.myshiro.entity.Permission;
import com.demo.myshiro.vo.resp.PermissionRespNodeVO;

import java.util.List;

public interface PermissionService {

    Permission queryPermissionById(String id);

    List<Permission> selectAll();

    List<PermissionRespNodeVO> selectAllMenuByTree();

}
