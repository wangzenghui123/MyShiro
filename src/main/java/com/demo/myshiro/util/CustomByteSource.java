package com.demo.myshiro.util;

import org.apache.shiro.util.SimpleByteSource;

import java.io.Serializable;

public class CustomByteSource  extends SimpleByteSource implements Serializable {

    public CustomByteSource(String string) {
        super(string);
    }
}
