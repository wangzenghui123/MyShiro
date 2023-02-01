package com.demo.myshiro.service.impl;

import com.demo.myshiro.dao.RoleDao;
import com.demo.myshiro.entity.Role;
import com.demo.myshiro.service.RoleService;
import com.demo.myshiro.util.PageUtil;
import com.demo.myshiro.vo.req.RolePageReqVO;
import com.demo.myshiro.vo.resp.PageRespVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;


    @Override
    public List<Role> queryRoleById(String id) {
        return roleDao.queryRoleById(id);
    }

    @Override
    public PageRespVO selectAll(RolePageReqVO rolePageReqVO) {
        PageHelper.startPage(rolePageReqVO.getPageNum(), rolePageReqVO.getPageSize());
        List<Role> roleList = roleDao.selectAll(rolePageReqVO);
        return PageUtil.getPageRespVO(roleList);
    }
}
