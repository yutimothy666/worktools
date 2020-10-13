package com.timothy.webui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author: timothyyu
 * @Date: 2020/10/12 9:43
 */
@Controller
@RequestMapping("/vue")
public class VueController {
    @RequestMapping(value = {"", "/"})
    public String index() {
        return "/vue/vue";
    }
}
