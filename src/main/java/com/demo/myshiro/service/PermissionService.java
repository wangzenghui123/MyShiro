package com.demo.myshiro.service;

import com.demo.myshiro.entity.Permission;
import com.demo.myshiro.exception.BusinessException;
import com.demo.myshiro.vo.req.PermissionAddReqVO;
import com.demo.myshiro.vo.req.PermissionUpdateReqVO;
import com.demo.myshiro.vo.resp.PermissionRespNodeVO;

import java.util.List;

public interface PermissionService {

    Permission queryPermissionById(String id);

    List<Permission> selectAll();

    List<PermissionRespNodeVO> selectAllMenuByTree();

    int addPermission(PermissionAddReqVO permissionAddReqVO) throws BusinessException;

    int updatePermission(PermissionUpdateReqVO permissionUpdateReqVO) throws BusinessException;



}
