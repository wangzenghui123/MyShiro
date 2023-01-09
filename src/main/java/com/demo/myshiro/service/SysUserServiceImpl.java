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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
            throw  new BusinessException(BaseResponseCode.PASSWORD_ERROR);
        }
        LoginRespVO loginRespVO = new LoginRespVO();
        Map<String, Object> map = new HashMap<>();
        map.put(Constant.JWT_USER_NAME,user.getUsername());
        String accessToken = JwtTokenUtil.getAccessToken(user.getId(), map);
        String refreshToken = null;
        if(loginReqVO.getType().equals("1")){
            Map<String, Object> map02= new HashMap<>();
            map02.put("Constant.JWT_USER_NAME",user.getUsername());
            refreshToken = JwtTokenUtil.getRefreshToken(user.getId(), map02);
            System.out.println(refreshToken.toString());
        }else{
            Map<String, Object> map01= new HashMap<>();
            map01.put(Constant.JWT_USER_NAME,user.getUsername());
            refreshToken = JwtTokenUtil.getRefreshAppToken(user.getId(),map01);
        }
        loginRespVO.setAccessToken(accessToken);
        loginRespVO.setRefreshToken(refreshToken);
        loginRespVO.setUserId(user.getId());
        return loginRespVO;
    }
}
