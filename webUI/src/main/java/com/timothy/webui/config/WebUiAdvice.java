package com.timothy.webui.config;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.UnknownContentTypeException;

/**
 * @Author yutimothy
 * @Date 2020/9/28 22:24
 * @Version 1.0
 */
@RestControllerAdvice
public class WebUiAdvice {
    @ExceptionHandler(UnknownContentTypeException.class)
    public AjaxResult unknownContentTypeException() {
        return AjaxResult.toAjax("200", "cookie 过期,请重新获取cookie", null);
    }

    @ExceptionHandler(NullPointerException.class)
    public AjaxResult nullPointerException() {
        return AjaxResult.toAjax("200", "程序异常", null);
    }
}
