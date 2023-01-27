package com.demo.myshiro.service.impl;

import com.demo.myshiro.dao.PermissionDao;
import com.demo.myshiro.entity.Permission;
import com.demo.myshiro.exception.BusinessException;
import com.demo.myshiro.exception.code.BaseResponseCode;
import com.demo.myshiro.service.PermissionService;
import com.demo.myshiro.vo.req.PermissionAddReqVO;
import com.demo.myshiro.vo.resp.PermissionRespNodeVO;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PermissionServiceImpl  implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public Permission queryPermissionById(String id) {
        return permissionDao.queryPermissionById(id);
    }

    @Override
    public List<Permission> selectAll() {

        return permissionDao.selectAll();
    }

    @Override
    public List<PermissionRespNodeVO> selectAllMenuByTree() {
        List<Permission> permissions = selectAll();
        List<PermissionRespNodeVO> list = new ArrayList<>();
        for (Permission permission : permissions) {
            if(permission.getPid().equals("0")){
                List<PermissionRespNodeVO> children = getChildren(permission, permissions);
                PermissionRespNodeVO permissionRespNodeVO = new PermissionRespNodeVO();
                permissionRespNodeVO.setChildren(children);
                permissionRespNodeVO.setTitle(permission.getName());
                permissionRespNodeVO.setId(permission.getId());
                list.add(permissionRespNodeVO);
            }
        }
        return list;
    }

    @Override
    public int addPermission(PermissionAddReqVO permissionAddReqVO) throws BusinessException {
        String pid = permissionAddReqVO.getPid();
        Integer type = permissionAddReqVO.getType();
        Permission perms = permissionDao.queryPermissionById(pid);
        validate(type, Integer.valueOf(pid),perms);
        Permission permission = new Permission();
        BeanUtils.copyProperties(permissionAddReqVO,permission);
        permission.setId(UUID.randomUUID().toString());
        permission.setDeleted(1);
        permission.setCreateTime(new Date(System.currentTimeMillis()));
        permission.setUpdateTime(new Date(System.currentTimeMillis()));
        return permissionDao.addPermission(permission);
    }

    private List<PermissionRespNodeVO> getChildren(Permission permission, List<Permission> list){
        String permissionId = permission.getId();
        List<PermissionRespNodeVO> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(permissionId.equals(list.get(i).getPid()) && list.get(i).getType() != 3){
                PermissionRespNodeVO permissionRespNodeVO = new PermissionRespNodeVO();
                permissionRespNodeVO.setId(list.get(i).getId());
                permissionRespNodeVO.setTitle(list.get(i).getName());
                permissionRespNodeVO.setChildren(getChildren(list.get(i),list));
                list1.add(permissionRespNodeVO);
            }
        }
        return list1;
    }

    private void validate(Integer type,Integer pid,Permission parent) throws BusinessException {

        switch (type){
            case 1:
                if(parent != null){
                    if(parent.getType() != 1){
                        throw new BusinessException(BaseResponseCode.SYSTEM_ERROR);
                    }
                }else{
                    if(pid != 0){
                        throw  new BusinessException(BaseResponseCode.SYSTEM_ERROR);
                    }
                }
                break;
                case 2:
                    if(parent != null){
                        if(parent.getType() != 1){
                            throw  new BusinessException(BaseResponseCode.SYSTEM_ERROR);
                        }
                    }else{
                        throw  new BusinessException(BaseResponseCode.SYSTEM_ERROR);
                    }
                    break;
            case 3:
                if(parent != null){
                    if(parent.getType() != 2){
                        throw  new BusinessException(BaseResponseCode.SYSTEM_ERROR);
                    }
                }else{
                    throw  new BusinessException(BaseResponseCode.SYSTEM_ERROR);
                }
                break;
        }
        return;
    }
}
