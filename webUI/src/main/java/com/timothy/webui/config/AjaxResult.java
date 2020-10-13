package com.timothy.webui.config;

import java.util.HashMap;

/**
 * @Author yutimothy
 * @Date 2020/9/27 22:52
 * @Version 1.0
 */
public class AjaxResult extends HashMap<String, Object> {
    private static final String codeText = "code";
    private static final String dataText = "data";
    private static final String msgText = "msg";

    public AjaxResult() {

    }

    public AjaxResult(String code, String msg, Object data) {
        this.put(codeText, code);
        this.put(dataText, data);
        this.put(msgText, msg);
    }

    public static AjaxResult toAjax(String code, String msg, Object data) {
        return new AjaxResult(code, msg, data);
    }

    public static AjaxResult toAjax(String code, String msg) {
        return new AjaxResult(code, msg, null);
    }

    public static AjaxResult success(Object data) {
        return toAjax("200", "msg", data);
    }
}
