package com.timothy.webui;

import com.timothy.webui.config.AjaxResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @Description: application
 * @Author: timothyyu
 * @Date: 2020/9/17 15:57
 */
@SpringBootApplication
public class WebUIApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebUIApplication.class, args);
        System.out.println("--------ok----------");
    }

    @Controller
    static class Test {

        @ResponseBody
        @RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST})
        public AjaxResult test() {
            return AjaxResult.success("test");
        }
    }
}
