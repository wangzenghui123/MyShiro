package com.demo.myshiro.controller;


import com.demo.myshiro.entity.Permission;
import com.demo.myshiro.service.PermissionService;
import com.demo.myshiro.util.DataResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("perms")
@Api(tags = "权限模块",description = "权限相关接口")
public class PermissionController {


    @Autowired
    private PermissionService permissionService;


    @RequestMapping("/permissions")
    @ApiOperation(value = "获取所有权限信息")
    @ResponseBody
   // @RequiresPermissions("111")
    public DataResult<List<Permission>> permissions(){
        DataResult dataResult = DataResult.success();
        dataResult.setData(permissionService.selectAll());
        return dataResult;
    }
}
