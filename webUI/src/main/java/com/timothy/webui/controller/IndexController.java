package com.timothy.webui.controller;

import com.timothy.webui.bean.RoomBean;
import com.timothy.webui.config.RoomProperties;
import com.timothy.webui.utils.RestTemplateUtils;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Description: index
 * @Author: timothyyu
 * @Date: 2020/9/17 16:38
 */
@Controller
@RequestMapping(value = {"/index", "", "/"})
public class IndexController {
    private static final MultiValueMap<String, String> roomMap = new LinkedMultiValueMap<String, String>();

    @Resource
    RestTemplate restTemplate;
    @Resource
    RestTemplateUtils restTemplateUtils;

    @Resource
    RoomProperties roomProperties;


    @RequestMapping(value = {"index", "", "/"})
    public String index(ModelMap modelMap) {
        modelMap.put("ok", "ok");
        return "index/index";
    }

    @GetMapping(value = "test")
    public void test(HttpServletResponse response, @RequestParam(value = "page", required = false, defaultValue = "1") String page) throws IOException {
        String url = "https://yx.tsp189.com/xyyx/dorm/dorm_ajust_data_provider.shtml?pid=&";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("_search", "false");
        params.add("nd", String.valueOf(System.currentTimeMillis()));
        params.add("rows", "20");
        params.add("page", "2");
        params.add("sidx", "");
        params.add("sord", "asc");
        HttpEntity<MultiValueMap<String, String>> entity = restTemplateUtils.getRequest(params);
        String body = restTemplate.postForObject(url, entity, String.class);
        System.out.println(body);
        assert body != null;
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(body);
    }

    @ResponseBody
    @GetMapping(value = "object")
    public Object object(@RequestParam(value = "page", required = false, defaultValue = "1") String page) throws IOException {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("_search", "false");
        params.add("nd", String.valueOf(System.currentTimeMillis()));
        params.add("rows", "20");
        params.add("page", "2");
        params.add("sidx", "");
        params.add("sord", "asc");
        HttpEntity<MultiValueMap<String, String>> request = restTemplateUtils.getRequest(params);
        RoomBean roomBean = restTemplate.postForObject(roomProperties.getDormAjustDataProvider(), request, RoomBean.class);
        assert roomBean != null;
        roomBean.getGridModel().forEach(k -> {
            roomMap.add(k.getDormAllName(), String.valueOf(k.getId()));
        });
        System.out.println(roomMap);
        return roomBean;
    }
}
