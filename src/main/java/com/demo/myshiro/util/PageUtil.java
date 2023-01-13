package com.demo.myshiro.util;

import com.demo.myshiro.vo.resp.PageRespVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class PageUtil {

    public static <T> PageRespVO getPageRespVO(List<T> list){
        PageRespVO<T> pageRespVO = new PageRespVO<>();
        if(list instanceof Page){
            Page page = (Page) list;
            pageRespVO.setTotalRows(page.getTotal());
            pageRespVO.setPageNum(page.getPageNum());
            pageRespVO.setPageSize(page.getPageSize());
            pageRespVO.setCurPageSize(page.size());
            pageRespVO.setTotalPages(page.getPages());
            pageRespVO.setList(page.getResult());
        }
        return  pageRespVO;
    }
}
