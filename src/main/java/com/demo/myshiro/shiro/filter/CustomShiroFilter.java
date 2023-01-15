package com.demo.myshiro.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.demo.myshiro.constant.Constant;
import com.demo.myshiro.exception.BusinessException;
import com.demo.myshiro.exception.code.BaseResponseCode;
import com.demo.myshiro.shiro.token.CustomUsernamePasswordToken;
import com.demo.myshiro.util.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class CustomShiroFilter extends AccessControlFilter {

    //返回true，进入下一个filter
    //返回false，进入onAccessDenied方法
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    //返回true,进入下一个filter
    //返回false,请求失败
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response)  {
        System.out.println("进入CustomSHhiroFilter.......");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String accessToken = httpServletRequest.getHeader(Constant.ACCESS_TOKEN);
        try {
            if (StringUtils.isEmpty(accessToken)) {
                throw new BusinessException(BaseResponseCode.TOKEN_NOT_NULL);
            }
            CustomUsernamePasswordToken token = new CustomUsernamePasswordToken(accessToken);
            getSubject(request,response).login(token);
        }catch (BusinessException e){
            responseException(e,response);
            return false;
        }catch (AuthenticationException e){
            if(e.getCause() instanceof BusinessException){
                responseException((BusinessException)e.getCause(),response);
            }else{
                responseException(new BusinessException(BaseResponseCode.TOKEN_ERROR),response);
            }
            return false;
        }catch (Exception e){
            responseException(new BusinessException(BaseResponseCode.SYSTEM_ERROR),response);
            return false;
        }
        return true;
    }

    private void responseException(BusinessException e,ServletResponse response) {
        try {
            DataResult<BusinessException> dataResult = new DataResult<>();
            dataResult.setCode(e.getCode());
            dataResult.setMsg(e.getMsg());

            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json;charset=utf-8");


            PrintWriter writer = httpServletResponse.getWriter();
            writer.write(JSON.toJSONString(dataResult));
            writer.flush();
            writer.close();
        } catch (IOException ex) {
           log.error(" responseException");
        }

    }
}
