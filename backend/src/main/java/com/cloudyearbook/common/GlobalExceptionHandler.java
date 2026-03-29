package com.cloudyearbook.common;

import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BizException.class)
    public ApiResponse<Void> handleBiz(BizException e) {
        return ApiResponse.fail(e.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ApiResponse<Void> handleValid(Exception e) {
        return ApiResponse.fail("参数校验失败");
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleAny(Exception e) {
        return ApiResponse.fail("系统异常: " + e.getMessage());
    }
}
