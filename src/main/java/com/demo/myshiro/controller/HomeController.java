package com.demo.myshiro.controller;


import com.demo.myshiro.constant.Constant;
import com.demo.myshiro.util.DataResult;
import com.demo.myshiro.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api")
@Api(tags = "主页模块",description = "主页模块相关接口")
public class HomeController {

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    @ResponseBody
    public DataResult home(ServletRequest servletRequest){
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader(Constant.ACCESS_TOKEN);
        String userId = JwtTokenUtil.getUserId(token);
        return null;
    }
}
