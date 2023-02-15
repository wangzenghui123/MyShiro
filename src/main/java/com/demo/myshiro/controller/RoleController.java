package com.demo.myshiro.controller;

import com.demo.myshiro.entity.Permission;
import com.demo.myshiro.entity.Role;
import com.demo.myshiro.service.RoleService;
import com.demo.myshiro.util.DataResult;
import com.demo.myshiro.vo.req.RolePageReqVO;
import com.demo.myshiro.vo.req.RoleUpdateReqVO;
import com.demo.myshiro.vo.resp.PageRespVO;
import com.demo.myshiro.vo.resp.PermissionRespNodeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")
@Api(tags = "角色管理",description = "角色管理相关")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/roles")
    @ApiOperation(value = "查询角色")
    @ResponseBody
    public DataResult<PageRespVO<Role>> roles(@RequestBody RolePageReqVO rolePageReqVO){
        System.out.println(rolePageReqVO.toString());
        DataResult dataResult = DataResult.success();
        dataResult.setData(roleService.selectAll(rolePageReqVO));
        return dataResult;
    }

    @RequestMapping("/getPermissionTreeById")
    @ApiOperation(value = "查询用户权限树")
    @ResponseBody
    public DataResult getPermissionTreeById(@RequestBody Role role){
        DataResult dataResult = DataResult.success();
        List<PermissionRespNodeVO> permissionRespNodeVOList = roleService.getPermissionTreeById(role.getId());
        dataResult.setData(permissionRespNodeVOList);
        return dataResult;
    }

    @RequestMapping("/updateRole")
    @ApiOperation(value = "更新角色信息")
    @ResponseBody
    public DataResult updateRole(@RequestBody RoleUpdateReqVO roleUpdateReqVO){
        DataResult dataResult = DataResult.success();
        roleService.updateRole(roleUpdateReqVO);
        return dataResult;
    }

}
