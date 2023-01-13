package com.demo.myshiro.exception;

import com.demo.myshiro.exception.code.BaseResponseCode;
import com.demo.myshiro.util.DataResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice//(basePackages = "com.demo.myshiro")
public class RestExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public DataResult handleException(){
        return new DataResult<>(BaseResponseCode.SYSTEM_ERROR);
    }

    @ExceptionHandler(value = BusinessException.class)
    public DataResult handleBusinessException(BusinessException e){
        return new DataResult(e.getCode(),e.getMsg());
    }
}
