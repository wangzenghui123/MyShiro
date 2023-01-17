package com.demo.myshiro.service;

import com.demo.myshiro.vo.resp.HomeRespVO;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;

public interface HomeService {

    HomeRespVO getHomeInfo(ServletRequest servletRequest);
}
