package com.timothy.webui.config;

import com.alibaba.excel.annotation.ExcelProperty;
import com.timothy.webui.Exception.RoomExcelException;
import com.timothy.webui.socketservice.WebSocketServer;
import io.lettuce.core.RedisConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.UnknownContentTypeException;

import java.net.UnknownHostException;

/**
 * @Author yutimothy
 * @Date 2020/9/28 22:24
 * @Version 1.0
 */
@RestControllerAdvice
public class WebUiAdvice {

    private static final Logger logger = LoggerFactory.getLogger(WebUiAdvice.class);

    @ExceptionHandler(UnknownContentTypeException.class)
    public AjaxResult unknownContentTypeException(UnknownContentTypeException exception) {
        logger.error("cookies过期", exception);
        return AjaxResult.toAjax("200", "cookie 过期,请重新获取cookie");
    }

    @ExceptionHandler(NullPointerException.class)
    public AjaxResult nullPointerException(NullPointerException e) {
        logger.error("空指针异常", e);
        return AjaxResult.toAjax("200", "程序异常");
    }

    @ExceptionHandler({UnknownHostException.class, ResourceAccessException.class})
    public AjaxResult unknownHostException(UnknownHostException e) {
        logger.error("network error", e);
        return AjaxResult.toAjax("200", "本地异常或者服务器网络异常");
    }

    @ExceptionHandler(value = RoomExcelException.class)
    public AjaxResult roomExcelException(RoomExcelException e) {
        logger.error("没有填写班级编码或者专业名称", e);
        return AjaxResult.toAjax("200", "没有填写班级编码或者专业名称");
    }

    @ExceptionHandler(RedisConnectionException.class)
    public AjaxResult residConnectionException(RedisConnectionException e) {
        logger.error("redis error", e);
        return AjaxResult.toAjax("200", "redis error");
    }

    @ExceptionHandler(RuntimeException.class)
    public AjaxResult runtimeException(RuntimeException e) {

        logger.info("run error", e);
        return AjaxResult.toAjax("200", "run error");
    }
}
