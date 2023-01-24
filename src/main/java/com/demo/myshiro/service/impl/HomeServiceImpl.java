package com.demo.myshiro.service.impl;

import com.demo.myshiro.constant.Constant;
import com.demo.myshiro.dao.PermissionDao;
import com.demo.myshiro.dao.RoleDao;
import com.demo.myshiro.dao.SysUserDao;
import com.demo.myshiro.entity.Permission;
import com.demo.myshiro.entity.SysUser;
import com.demo.myshiro.service.HomeService;
import com.demo.myshiro.util.JwtTokenUtil;
import com.demo.myshiro.vo.resp.HomeRespVO;
import com.demo.myshiro.vo.resp.PermissionRespNodeVO;
import com.demo.myshiro.vo.resp.UserInfoRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Service
@Transactional
public class HomeServiceImpl implements HomeService {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;


    @Override
    public HomeRespVO getHomeInfo(ServletRequest servletRequest) {
        HttpServletRequest requst = (HttpServletRequest) servletRequest;
        String accessToken = requst.getHeader(Constant.ACCESS_TOKEN);
        String userId = JwtTokenUtil.getUserId(accessToken);

        HomeRespVO homeRespVO = new HomeRespVO();

        SysUser sysUser = sysUserDao.queryUserById(userId);
        UserInfoRespVO userInfoRespVO = new UserInfoRespVO(sysUser.getId(),sysUser.getUsername(),sysUser.getDeptId(),sysUser.getDeptName());
        homeRespVO.setUserInfoRespVO(userInfoRespVO);


        HashSet<String> permissionIdSet = new HashSet<>();
        ArrayList<String> roleIdArr = sysUserDao.queryRoleIdByUserId(sysUser.getId());
        for (String roleId : roleIdArr) {
            ArrayList<String> permissionIdArr = roleDao.queryPermissionIdByRoleId(roleId);
            for (String permissionId : permissionIdArr) {
                permissionIdSet.add(permissionId);

            }
        }

        List<PermissionRespNodeVO> permissionRespNodeVOList = new ArrayList<>();
        List<PermissionRespNodeVO> permissionRespNodeVOList01 = new ArrayList<>();

        for (String permissionId : permissionIdSet) {
            Permission permission = permissionDao.queryPermissionById(permissionId);
            PermissionRespNodeVO permissionRespNodeVO = new PermissionRespNodeVO();
            permissionRespNodeVO.setId(permission.getId());
            permissionRespNodeVO.setPermission(permission.getPerms());
            permissionRespNodeVO.setTitle(permission.getName());
            permissionRespNodeVO.setUrl(permission.getUrl());
            permissionRespNodeVO.setPid(permission.getPid());
            permissionRespNodeVOList.add(permissionRespNodeVO);
        }
        for (PermissionRespNodeVO permissionRespNodeVO : permissionRespNodeVOList) {
            if(permissionRespNodeVO.getPid().equals("0")){
                List<PermissionRespNodeVO> children = getChildren(permissionRespNodeVO, permissionRespNodeVOList);
                permissionRespNodeVO.setChildren(children);
                permissionRespNodeVOList01.add(permissionRespNodeVO);
            }

        }
        homeRespVO.setPermissionRespNodeVO(permissionRespNodeVOList01);
        return homeRespVO;
    }

    private List<PermissionRespNodeVO> getChildren(PermissionRespNodeVO permissionRespNodeVO,List<PermissionRespNodeVO> permissionRespNodeVOList){

        String id = permissionRespNodeVO.getId();
        List<PermissionRespNodeVO> list = new ArrayList<>();
        for (PermissionRespNodeVO respNodeVO : permissionRespNodeVOList) {
            if (respNodeVO.getPid().equals(id)){
                list.add(respNodeVO);
            }
        }
        if(list.size() > 0){
            permissionRespNodeVO.setChildren(list);
            for (PermissionRespNodeVO respNodeVO : list) {
                getChildren(respNodeVO,permissionRespNodeVOList);
            }
        }
        return list;
    }
}
