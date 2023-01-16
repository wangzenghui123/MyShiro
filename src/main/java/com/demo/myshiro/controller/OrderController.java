package com.demo.myshiro.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.core.ApplicationContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "订单模块",description = "订单相关内容")
@Controller
@RequestMapping("/order")
public class OrderController {

    @RequestMapping("/lisi")
    @RequiresRoles("admin")
    @ApiOperation(value = "订单列表")
    public String list(){
        return "main";
    }
}
