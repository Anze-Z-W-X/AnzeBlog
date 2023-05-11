package com.anze.handler.exception;

import com.anze.domain.ResponseResult;
import com.anze.enums.AppHttpCodeEnum;
import com.anze.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@Configuration
//@ResponseBody与下面注解大同小异

@RestControllerAdvice
@Slf4j  //日志,加后可使用log
public class GlobalExceptionHandler {
    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e){
        //打印异常参数
        log.error("出现了异常! {}",e);
        //从异常对象中获取信息封装返回
        return ResponseResult.errorResult(e.getCode(),e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult ExceptionHandler(Exception e){
        //打印异常参数
        log.error("出现了异常! {}",e);
        //从异常对象中获取信息封装返回
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR.getCode(),e.getMessage());
    }
}
