package com.demo.myshiro.controller;

import com.demo.myshiro.entity.SysUser;
import com.demo.myshiro.exception.BusinessException;
import com.demo.myshiro.service.SysUserService;
import com.demo.myshiro.shiro.token.CustomUsernamePasswordToken;
import com.demo.myshiro.util.DataResult;
import com.demo.myshiro.vo.req.LoginReqVO;
import com.demo.myshiro.vo.req.UserPageReqVO;
import com.demo.myshiro.vo.resp.LoginRespVO;
import com.demo.myshiro.vo.resp.PageRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/user")
@Api(value = "用户接口")
public class UserController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@RequestBody SysUser user){
        try {
            System.out.println(user.toString());
            userService.register(user);
            return "login";
        }catch (Exception e){
            e.printStackTrace();
            return "register";
        }
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "用户登录方法")
    public DataResult login(@RequestBody LoginReqVO loginReqVO) throws BusinessException {
        DataResult dataResult = DataResult.success();
        LoginRespVO login = userService.login(loginReqVO);
        dataResult.setData(login);
        SecurityUtils.getSubject().login(new CustomUsernamePasswordToken(login.getAccessToken()));
        return dataResult;
    }

    @RequestMapping("/loginhtml")
    public String loginHTML(){
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        String  principal = (String) subject.getPrincipal();
        System.out.println(principal);
        redisTemplate.opsForHash().delete("com.demo.myshiro.shiro.realm.CustomRealm.authorizationCache",principal);
        subject.logout();
        return  "redirect:/login.jsp";

    }

    @RequestMapping(value = "/pageInfo",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "页码获取用户列表")
    public DataResult<PageRespVO<SysUser>> pageInfo(@RequestBody UserPageReqVO userPageReqVO){
        DataResult<PageRespVO<SysUser>> dataResult = DataResult.success();
        PageRespVO<SysUser> sysUserPageRespVO = userService.selectAll(userPageReqVO);
        dataResult.setData(sysUserPageRespVO);
        return dataResult;
    }
}
