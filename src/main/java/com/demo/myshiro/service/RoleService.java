package com.demo.myshiro.service;

import com.demo.myshiro.entity.Role;
import com.demo.myshiro.vo.req.RolePageReqVO;
import com.demo.myshiro.vo.resp.PageRespVO;

import java.util.List;

public interface RoleService {

    List<Role> queryRoleById(String id);

    PageRespVO selectAll(RolePageReqVO rolePageReqVO);

}
