package com.demo.myshiro.service.impl;

import com.demo.myshiro.constant.Constant;
import com.demo.myshiro.dao.PermissionDao;
import com.demo.myshiro.dao.RoleDao;
import com.demo.myshiro.dao.SysUserDao;
import com.demo.myshiro.entity.Permission;
import com.demo.myshiro.entity.Role;
import com.demo.myshiro.service.RedisService;
import com.demo.myshiro.service.RoleService;
import com.demo.myshiro.util.JwtTokenUtil;
import com.demo.myshiro.util.PageUtil;
import com.demo.myshiro.util.TokenSetting;
import com.demo.myshiro.vo.req.RolePageReqVO;
import com.demo.myshiro.vo.req.RoleUpdateReqVO;
import com.demo.myshiro.vo.resp.PageRespVO;
import com.demo.myshiro.vo.resp.PermissionRespNodeVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RedisService redisService;


    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private SysUserDao userDao;

    @Autowired
    private TokenSetting tokenSetting;

    @Override
    public List<Role> queryRoleById(String id) {
        return roleDao.queryRoleById(id);
    }

    @Override
    public PageRespVO selectAll(RolePageReqVO rolePageReqVO) {
        PageHelper.startPage(rolePageReqVO.getPageNum(), rolePageReqVO.getPageSize());
        List<Role> roleList = roleDao.selectAll(rolePageReqVO);
        return PageUtil.getPageRespVO(roleList);
    }

    @Override
    public List<PermissionRespNodeVO> getPermissionTreeById(String id) {
        ArrayList<String> permissionId = roleDao.queryPermissionIdByRoleId(id);
        Set<String> permissionSet = new HashSet<>();
        for (String permId : permissionId) {
            Permission permission = permissionDao.queryPermissionById(permId);
            permissionSet.add(permission.getPerms());
        }
        List<PermissionRespNodeVO> permissionRespNodeVOList = selectAllMenuByTree(permissionSet);
        return permissionRespNodeVOList;
    }

    @Override
    public void updateRole(RoleUpdateReqVO roleUpdateReqVO) {
        String roleId = roleUpdateReqVO.getId();
        String roleName = roleUpdateReqVO.getName();
        String description = roleUpdateReqVO.getDescription();
        PermissionRespNodeVO[] permissions = roleUpdateReqVO.getPermissions();
        Timestamp updateTime = new Timestamp(System.currentTimeMillis());
        roleDao.updateRoleById(roleId,roleName,description,updateTime);
        Set<String> perm = new HashSet<>();
        if(permissions != null && permissions.length > 0){
            for (PermissionRespNodeVO permissionRespNodeVO : permissions) {
                perm.add(permissionRespNodeVO.getId());
                if(permissionRespNodeVO.getChildren() != null && permissionRespNodeVO.getChildren().size() > 0){
                    for (PermissionRespNodeVO child : permissionRespNodeVO.getChildren()) {
                        perm.add(child.getId());
                        if(child.getChildren() != null && child.getChildren().size() > 0){
                            for (PermissionRespNodeVO childChild : child.getChildren()) {
                                perm.add(childChild.getId());
                            }
                        }
                    }
                }
            }
        }
        roleDao.deletePermissionByRoleId(roleId);
        for (String item : perm) {
            roleDao.addRolePermission(UUID.randomUUID().toString(),roleId,item,updateTime);
        }

        ArrayList<String> userIdList = roleDao.queryUserIdByRoleId(roleId);
        if(userIdList != null && userIdList.size() > 0){
            for (String id : userIdList) {
                redisService.put(Constant.JWT_REFRESH_KEY+id,id, tokenSetting.getAccessTokenExpireTime().toMillis(), TimeUnit.MILLISECONDS);
                if(redisService.hasKey(Constant.IDENTITY_CACHE_KEY+id) ){
                    redisService.delete(Constant.IDENTITY_CACHE_KEY+id);
                }
            }

        }




    }

    public List<PermissionRespNodeVO> selectAllMenuByTree(Set permissionSet) {
        List<Permission> permissions = permissionDao.selectAll();
        List<PermissionRespNodeVO> list = new ArrayList<>();
        List<Permission> list1 = new ArrayList<>();
        for (Permission permission : permissions) {
            if(permission.getPid().equals("0")){
                list1.add(permission);
            }
        }
        for (Permission permission : list1) {

            List<PermissionRespNodeVO> children = getChildren(permission, permissions,permissionSet);
            PermissionRespNodeVO permissionRespNodeVO = new PermissionRespNodeVO();
            permissionRespNodeVO.setChildren(children);
            permissionRespNodeVO.setTitle(permission.getName());
            permissionRespNodeVO.setId(permission.getId());
            permissionRespNodeVO.setPName("默认顶级菜单");
            list.add(permissionRespNodeVO);
        }
        return list;
    }
    private List<PermissionRespNodeVO> getChildren(Permission permission, List<Permission> list,Set permissionSet){
        String permissionId = permission.getId();
        List<PermissionRespNodeVO> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(permissionId.equals(list.get(i).getPid())){
                PermissionRespNodeVO permissionRespNodeVO = new PermissionRespNodeVO();
                permissionRespNodeVO.setId(list.get(i).getId());
                permissionRespNodeVO.setTitle(list.get(i).getName());
                permissionRespNodeVO.setPName(permissionDao.queryPermissionById(list.get(i).getPid()).getName());
                if(list.get(i).getType() == 3 && permissionSet.contains(list.get(i).getPerms())){
                    permissionRespNodeVO.setChecked(true);
                }
                permissionRespNodeVO.setChildren(getChildren(list.get(i),list,permissionSet));
                list1.add(permissionRespNodeVO);
            }
        }
        return list1;
    }
}
