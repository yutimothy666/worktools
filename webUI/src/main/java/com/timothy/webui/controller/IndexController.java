package com.timothy.webui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: index
 * @Author: timothyyu
 * @Date: 2020/9/17 16:38
 */
@Controller
@RequestMapping(value = {"/index", "", "/"})
public class IndexController {

    @RequestMapping(value = {"index", "", "/"})
    public String index(ModelMap modelMap) {
        modelMap.put("ok", "ok");
        return "index/index";
    }
}
