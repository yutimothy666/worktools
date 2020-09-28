package com.timothy.webui.utils;

import org.apache.logging.log4j.util.Strings;
import org.springframework.lang.NonNull;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: timothyyu
 * @Date: 2020/9/24 12:35
 */
public class MyUtils {
    public static boolean isNotEmpty(String s) {
        return Strings.isNotEmpty(s) && Strings.isNotBlank(s);
    }

    public static String getRoomName(String... args) {
        StringBuilder name = new StringBuilder();
        for (String arg : args) {
            if (isNotEmpty(arg)) {
                name.append(arg.trim()).append("-");
            }
        }
        return name.toString();
    }

    public static HttpServletResponse getResponse() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        return servletRequestAttributes.getResponse();
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        return servletRequestAttributes.getRequest();
    }
}
