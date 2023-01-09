package com.demo.myshiro.controller;

import org.apache.catalina.core.ApplicationContext;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    @RequestMapping("/lisi")
    @RequiresRoles("admin")
    public String list(){
        return "redirect:/order.jsp";
    }
}
