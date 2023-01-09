package com.demo.myshiro.service;

import com.demo.myshiro.constant.Constant;
import com.demo.myshiro.dao.SysUserDao;
import com.demo.myshiro.dao.SysUserDao;
import com.demo.myshiro.entity.SysUser;
import com.demo.myshiro.exception.BusinessException;
import com.demo.myshiro.exception.code.BaseResponseCode;
import com.demo.myshiro.service.impl.SysUserService;
import com.demo.myshiro.util.JwtTokenUtil;
import com.demo.myshiro.util.SaltUtil;
import com.demo.myshiro.vo.req.LoginReqVO;
import com.demo.myshiro.vo.resp.LoginRespVO;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao userDao;

    @Override
    public void register(SysUser user) {
        String salt = SaltUtil.getSalt(8);
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,1024);
        user.setPassword(md5Hash.toHex());
        user.setSalt(salt);
        user.setId(UUID.randomUUID().toString());
        userDao.insert(user);
    }

    @Override
    public SysUser queryUserByName(String username) {
        SysUser user = userDao.queryUserByName(username);
        return user;
    }

    @Override
    public LoginRespVO login(LoginReqVO loginReqVO) throws BusinessException {
        String username = loginReqVO.getUsername();
        SysUser user = userDao.queryUserByName(username);
        System.out.println(user.toString());
        if(null == user){
            throw new BusinessException(BaseResponseCode.ACCOUNT_ERROR);
        }
        if(user.getStatus().equals("1")){
            throw  new BusinessException(BaseResponseCode.ACCOUNT_LOCK);
        }
        if(!user.getPassword().equals(SaltUtil.getMD5Password(user.getSalt(),loginReqVO.getPassword()))){
            throw  new BusinessException(BaseResponseCode.ACCOUNT_PASSWORD_ERROR);
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put(Constant.JWT_USER_NAME,user.getUsername());
        claims.put(Constant.ROLES_INFOS_KEY,getRoleByUserId(user.getId()));
        claims.put(Constant.PERMISSIONS_INFOS_KEY,getPermissionByUserId(user.getId()));
        String accessToken = JwtTokenUtil.getAccessToken(user.getId(), claims);
        String refreshToken = null;
        Map<String, Object> refreshTokenClaims= new HashMap<>();
        refreshTokenClaims.put(Constant.JWT_USER_NAME,user.getUsername());
        if(loginReqVO.getType().equals("1")){
            refreshToken = JwtTokenUtil.getRefreshToken(user.getId(), refreshTokenClaims);
        }else{
            refreshToken = JwtTokenUtil.getRefreshAppToken(user.getId(),refreshTokenClaims);
        }
        LoginRespVO loginRespVO = new LoginRespVO();
        loginRespVO.setAccessToken(accessToken);
        loginRespVO.setRefreshToken(refreshToken);
        loginRespVO.setUserId(user.getId());
        return loginRespVO;
    }

    private List<String> getRoleByUserId(String userId){
        List<String> roleList = new ArrayList<>();
        roleList.add("admin");
        roleList.add("dev");
        return roleList;
    }

    private List<String> getPermissionByUserId(String userId){
        List<String> permissionList = new ArrayList<>();
        permissionList.add("sys:user:delete");
        permissionList.add("sys:user:query");
        permissionList.add("sys:user:update");
        permissionList.add("sys:user:add");
        return permissionList;
    }
}
