package com.demo.myshiro.util;

import org.springframework.stereotype.Component;

@Component
public class InitializerUtil {

    public InitializerUtil(TokenSetting tokenSetting){
        JwtTokenUtil.setProperty(tokenSetting);
    }
}
