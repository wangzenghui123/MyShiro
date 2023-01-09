package com.demo.myshiro.controller;

import com.demo.myshiro.entity.SysUser;
import com.demo.myshiro.entity.User;
import com.demo.myshiro.exception.BusinessException;
import com.demo.myshiro.service.impl.SysUserService;
import com.demo.myshiro.util.DataResult;
import com.demo.myshiro.vo.req.LoginReqVO;
import com.demo.myshiro.vo.resp.LoginRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/user")
@Api(value = "用户接口")
public class UserController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/register")
    public String register(SysUser user){
        try {
            System.out.println(user.toString());
            userService.register(user);
            return "redirect:/login.jsp";
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/register.jsp";
        }
    }
    @RequestMapping("/login")
    @ResponseBody
    @ApiOperation(value = "用户登录方法")
    public DataResult  login(@RequestBody LoginReqVO loginReqVO) throws BusinessException {
        DataResult dataResult = DataResult.success();
        System.out.println(loginReqVO.toString());
        LoginRespVO login = userService.login(loginReqVO);
        dataResult.setData(login);
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
}
