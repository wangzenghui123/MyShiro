package com.demo.myshiro.exception;

import com.demo.myshiro.exception.code.BaseResponseCode;
import com.demo.myshiro.util.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "com.demo.myshiro")
public class RestExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public DataResult handleException(){

        log.error("Exception:{}");
        return new DataResult<>(BaseResponseCode.SYSTEM_ERROR);
    }

    @ExceptionHandler(value = BusinessException.class)
    public DataResult handleBusinessException(BusinessException e){
        log.error("BusinessException:{}",e);
        return new DataResult(e.getCode(),e.getMsg());
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public DataResult handleUnauthorizedException(UnauthorizedException e){

        log.error("UnauthorizedException:{}",e);
        return new DataResult<>(BaseResponseCode.NOT_PERMISSION);}
}
