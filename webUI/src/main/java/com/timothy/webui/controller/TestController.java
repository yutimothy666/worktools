package com.timothy.webui.controller;

import com.timothy.webui.config.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: test
 * @Author: timothyyu
 * @Date: 2020/10/10 11:37
 */
@RestController()
@RequestMapping("/test")
public class TestController {
    @RequestMapping(value = {"/", ""})
    public AjaxResult index() {
        return AjaxResult.toAjax("200", "ok");
    }
}
