package com.demo.myshiro.util;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

import java.util.Random;

public class SaltUtil {

    public static String getSalt(int n){
        char[] arr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()_+".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char ch = arr[new Random().nextInt(arr.length)];
            stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }
    public static String getMD5Password(String salt,String password){
        Md5Hash md5Hash = new Md5Hash(password,salt,1024);
        return md5Hash.toHex();
    }
}
