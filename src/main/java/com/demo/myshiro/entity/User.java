package com.demo.myshiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.jws.HandlerChain;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String id;

    private String username;

    private String password;

    private String salt;

}
