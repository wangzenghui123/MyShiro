package com.demo.myshiro.shiro.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.shiro.authc.UsernamePasswordToken;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUsernamePasswordToken extends UsernamePasswordToken {

    private String token;


    @Override
    public Object getPrincipal() {
        return token;
    }


}
