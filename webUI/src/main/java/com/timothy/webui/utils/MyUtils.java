package com.timothy.webui.utils;

import org.apache.logging.log4j.util.Strings;

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
}
