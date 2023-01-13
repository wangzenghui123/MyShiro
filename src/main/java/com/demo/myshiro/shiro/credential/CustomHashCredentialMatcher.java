package com.demo.myshiro.shiro.credential;

import com.demo.myshiro.constant.Constant;
import com.demo.myshiro.exception.BusinessException;
import com.demo.myshiro.exception.code.BaseResponseCode;
import com.demo.myshiro.service.RedisService;
import com.demo.myshiro.shiro.token.CustomUsernamePasswordToken;
import com.demo.myshiro.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomHashCredentialMatcher  extends HashedCredentialsMatcher {

    @Autowired
    private RedisService redisService;

    @SneakyThrows
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        CustomUsernamePasswordToken customUsernamePasswordToken = (CustomUsernamePasswordToken) token;
        String userId = JwtTokenUtil.getUserId((String) customUsernamePasswordToken.getPrincipal());
        if(redisService.hasKey(Constant.DELETED_USER_KEY+userId)){
            throw new BusinessException(BaseResponseCode.ACCOUNT_HAS_DELETED_ERROR);
        }
        if(redisService.hasKey(Constant.ACCOUNT_LOCK_KEY+userId)){
            throw new BusinessException(BaseResponseCode.ACCOUNT_LOCK);
        }
        if(!JwtTokenUtil.validateToken((String) customUsernamePasswordToken.getPrincipal())){
            throw new BusinessException(BaseResponseCode.TOKEN_PAST_DUE);
        }
        return true;
    }
}
