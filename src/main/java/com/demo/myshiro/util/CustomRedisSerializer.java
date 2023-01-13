package com.demo.myshiro.util;


import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class CustomRedisSerializer implements RedisSerializer<Object> {

    private final Charset charset;


    public CustomRedisSerializer() {
        this(StandardCharsets.UTF_8);
    }

    public CustomRedisSerializer(Charset charset) {
        Assert.notNull(charset, "Charset must not be null!");
        this.charset = charset;
    }


    @Override
    public byte[] serialize(Object o) throws SerializationException {
        if(o == null) return new byte[0];
        if(o instanceof byte[]) return (byte[])o;
        if(o instanceof  String) return o.toString().getBytes(StandardCharsets.UTF_8);
        return JSON.toJSONString(o).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        return (bytes == null ? null : new java.lang.String(bytes, charset));
    }


}
