package com.demo.myshiro.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
@Api(tags = "视图",description = "跳转试图控制器")
public class IndexController {

    @GetMapping("/404")
    public String error(){
        return "error/404";
    }
}
