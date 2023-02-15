package com.demo.myshiro.controller;


import com.demo.myshiro.entity.Permission;
import com.demo.myshiro.exception.BusinessException;
import com.demo.myshiro.service.PermissionService;
import com.demo.myshiro.util.DataResult;
import com.demo.myshiro.vo.req.PermissionAddReqVO;
import com.demo.myshiro.vo.req.PermissionUpdateReqVO;
import com.demo.myshiro.vo.resp.PermissionRespNodeVO;
import io.lettuce.core.ScriptOutputType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.ArrayList;
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
    public DataResult<List<Permission>> permissions(){
        DataResult dataResult = DataResult.success();
        dataResult.setData(permissionService.selectAll());
        return dataResult;
    }

    @RequestMapping("/permissionTree")
    @ApiOperation(value = "获取菜单权限树")
    @ResponseBody
    public DataResult<List<PermissionRespNodeVO>> permissionTree(){
        DataResult dataResult = DataResult.success();
        List<PermissionRespNodeVO> list = new ArrayList<>();
        PermissionRespNodeVO permissionRespNodeVO = new PermissionRespNodeVO();
        permissionRespNodeVO.setTitle("默认顶级菜单");
        permissionRespNodeVO.setId("0");
        permissionRespNodeVO.setChildren(permissionService.selectAllMenuByTree());
        list.add(permissionRespNodeVO);
        dataResult.setData(list);
        return dataResult;
    }




    @RequestMapping("/addPermission")
    @ApiOperation(value = "新增权限")
    @ResponseBody
    public DataResult addPermission(@RequestBody PermissionAddReqVO permissionAddReqVO) throws BusinessException {
        DataResult dataResult = DataResult.success();
        int i = permissionService.addPermission(permissionAddReqVO);
        dataResult.setData(i);
        return dataResult;
    }

    @RequestMapping(value = "deletePermission",method = RequestMethod.DELETE)
    @ApiOperation(value = "删除权限")
    @ResponseBody
    public DataResult deletePermission(@RequestBody PermissionUpdateReqVO permissionUpdateReqVO) throws BusinessException {
        permissionService.deletePermission(permissionUpdateReqVO);
        return DataResult.success();
    }

    @RequestMapping("/updatePermission")
    @ApiOperation(value = "修改权限")
    @ResponseBody
    public DataResult updatePermission(@RequestBody PermissionUpdateReqVO permissionUpdateReqVO) throws BusinessException{
        System.out.println(permissionUpdateReqVO);
        DataResult dataResult = DataResult.success();
        permissionService.updatePermission(permissionUpdateReqVO);
        return dataResult;
    }
}
