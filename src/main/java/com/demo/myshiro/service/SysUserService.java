package com.demo.myshiro.service;

import com.demo.myshiro.entity.SysUser;
import com.demo.myshiro.entity.User;
import com.demo.myshiro.exception.BusinessException;
import com.demo.myshiro.vo.req.LoginReqVO;
import com.demo.myshiro.vo.req.UserPageReqVO;
import com.demo.myshiro.vo.resp.LoginRespVO;
import com.demo.myshiro.vo.resp.PageRespVO;
import com.github.pagehelper.PageInfo;

public interface SysUserService {

    void register(SysUser user);
    SysUser queryUserByName(String username);
    LoginRespVO login(LoginReqVO loginReqVO) throws BusinessException;
    PageRespVO<SysUser>  selectAll(UserPageReqVO userPageReqVO);
}
