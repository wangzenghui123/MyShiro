package com.demo.myshiro.service.impl;

import com.demo.myshiro.constant.Constant;
import com.demo.myshiro.dao.PermissionDao;
import com.demo.myshiro.dao.RoleDao;
import com.demo.myshiro.dao.SysUserDao;
import com.demo.myshiro.entity.Permission;
import com.demo.myshiro.entity.Role;
import com.demo.myshiro.entity.SysUser;
import com.demo.myshiro.exception.BusinessException;
import com.demo.myshiro.exception.code.BaseResponseCode;
import com.demo.myshiro.service.PermissionService;
import com.demo.myshiro.service.RedisService;
import com.demo.myshiro.util.JwtTokenUtil;
import com.demo.myshiro.vo.req.PermissionAddReqVO;
import com.demo.myshiro.vo.req.PermissionUpdateReqVO;
import com.demo.myshiro.vo.resp.PermissionRespNodeVO;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class PermissionServiceImpl  implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private SysUserDao userDao;

    @Override
    public Permission queryPermissionById(String id) {
        return permissionDao.queryPermissionById(id);
    }

    @Override
    public int deletePermission(PermissionUpdateReqVO permissionUpdateReqVO) throws BusinessException {
        String id = permissionUpdateReqVO.getId();
        if (StringUtils.isEmptyOrWhitespace(id)){
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        Permission permission = permissionDao.queryPermissionById(id);
        if(permission == null){
            throw new BusinessException(BaseResponseCode.DATA_ERROR);
        }
        List<Permission> children = permissionDao.getChildren(permission.getId());
        if(children!= null && children.size() > 0){
            throw new BusinessException(BaseResponseCode.HAVE_CHILDREN);
        }
        return permissionDao.deletePermission(permission.getId());

    }

    @Override
    public List<Permission> selectAll() {
        List<Permission> permissions = permissionDao.selectAll();
        for (Permission permission : permissions) {
            Permission parPerm = permissionDao.queryPermissionById(permission.getPid());
            permission.setPName(parPerm == null ? "默认顶级菜单“": parPerm.getName());
        }
        return permissions;
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
                permissionRespNodeVO.setPName("默认顶级菜单");
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
        validate(type, pid,perms);
        Permission permission = new Permission();
        BeanUtils.copyProperties(permissionAddReqVO,permission);
        permission.setId(UUID.randomUUID().toString());
        permission.setDeleted(1);
        permission.setCreateTime(new Timestamp(System.currentTimeMillis()));
        permission.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return permissionDao.addPermission(permission);
    }

    @Override
    public int updatePermission(PermissionUpdateReqVO permissionUpdateReqVO) throws BusinessException {
        Permission permission = new Permission();
        BeanUtils.copyProperties(permissionUpdateReqVO,permission);

        Permission parent = permissionDao.queryPermissionById(permission.getPid());
        validate(permissionUpdateReqVO.getType(), permissionUpdateReqVO.getPid(),parent);

        if(StringUtils.isEmpty(permissionUpdateReqVO.getId())){
            throw new BusinessException(BaseResponseCode.SYSTEM_ERROR);
        }
        Permission permission1 = permissionDao.queryPermissionById(permissionUpdateReqVO.getId());
        if((parent != null) && (!permissionUpdateReqVO.getPid().equals(permission1.getPid()))){
            List<Permission> children = permissionDao.getChildren(permissionUpdateReqVO.getId());
            if(children != null && children.size() > 0){
                throw new BusinessException(BaseResponseCode.SYSTEM_ERROR);
            }
        }
        permission.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        int i = permissionDao.updatePermission(permission);

        List<Role> roleList = roleDao.queryRolesByPermissionId(permission.getId());
        List<SysUser> userList = new ArrayList<>();
        for (Role role : roleList) {
            userList.addAll(userDao.queryUserByRoleId(role.getId()));
        }

        for (SysUser sysUser : userList) {
            System.out.println(sysUser.toString());
        }
        if(userList != null && userList.size() > 0){
            for (SysUser sysUser : userList) {
                String id = sysUser.getId();
                redisService.put(Constant.JWT_REFRESH_KEY+id,id, JwtTokenUtil.refreshTokenExpireTime.toMillis(), TimeUnit.MILLISECONDS);
                if(redisService.hasKey(Constant.IDENTITY_CACHE_KEY+id) ){
                    redisService.delete(Constant.IDENTITY_CACHE_KEY+id);
                }
            }
        }

        return i;

    }

    private List<PermissionRespNodeVO> getChildren(Permission permission, List<Permission> list){
        String permissionId = permission.getId();
        List<PermissionRespNodeVO> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(permissionId.equals(list.get(i).getPid()) && list.get(i).getType() != 3){
                PermissionRespNodeVO permissionRespNodeVO = new PermissionRespNodeVO();
                permissionRespNodeVO.setId(list.get(i).getId());
                permissionRespNodeVO.setTitle(list.get(i).getName());
                permissionRespNodeVO.setPName(permissionDao.queryPermissionById(list.get(i).getPid()).getName());
                permissionRespNodeVO.setChildren(getChildren(list.get(i),list));
                list1.add(permissionRespNodeVO);
            }
        }
        return list1;
    }

    private void validate(Integer type,String pid,Permission parent) throws BusinessException {
        System.out.println(type);
        System.out.println(pid);
        System.out.println(parent.toString());
        switch (type){
            case 1:
                if(parent != null){
                    if(parent.getType() != 1){
                        throw new BusinessException(BaseResponseCode.SYSTEM_ERROR);
                    }
                }else{
                    if(!pid.equals("0")){
                        throw  new BusinessException(BaseResponseCode.SYSTEM_ERROR);
                    }
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
            case 2:
                if(parent != null){
                    if(parent.getType() != 1){
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
