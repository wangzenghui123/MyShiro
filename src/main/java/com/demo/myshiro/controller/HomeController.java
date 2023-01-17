package com.demo.myshiro.controller;


import com.demo.myshiro.service.HomeService;
import com.demo.myshiro.util.DataResult;
import com.demo.myshiro.vo.resp.HomeRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;

/**
 * @author wzh
 *
 */
@Controller
@RequestMapping("/api")
@Api(value = "首页模块",description = "首页模块相关接口")
public class HomeController {


    @Autowired
    private HomeService homeService;

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("获取首页相关信息")
    public DataResult<HomeRespVO> home(ServletRequest servletRequest){
        DataResult dataResult = DataResult.success();
        HomeRespVO homeRespVO = homeService.getHomeInfo(servletRequest);
        dataResult.setData(homeRespVO);
        return dataResult;
    }

}
