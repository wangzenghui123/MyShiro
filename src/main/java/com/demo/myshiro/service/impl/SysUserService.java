package com.demo.myshiro.service.impl;

import com.demo.myshiro.entity.SysUser;
import com.demo.myshiro.entity.User;
import com.demo.myshiro.exception.BusinessException;
import com.demo.myshiro.vo.req.LoginReqVO;
import com.demo.myshiro.vo.resp.LoginRespVO;

public interface SysUserService {

    void register(SysUser user);
    SysUser queryUserByName(String username);
    LoginRespVO login(LoginReqVO loginReqVO) throws BusinessException;
}
