package com.timothy.webui.controller;

import com.timothy.webui.bean.TableResultRoot;
import com.timothy.webui.config.RoomProperties;
import com.timothy.webui.utils.RestTemplateClient;
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
    RestTemplateClient restTemplateClient;


    @RequestMapping(value = {""})
    public String index(ModelMap modelMap) {
        modelMap.put("ok", "ok");
        return "index/index";
    }

    @ResponseBody
    @GetMapping(value = "test2")
    public void test(HttpServletResponse response, @RequestParam(value = "page", required = false, defaultValue = "1") String page) throws IOException {
        String url = "https://yx.tsp189.com/xyyx/dorm/dorm_ajust_data_provider.shtml?pid=&";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("_search", "false");
        params.add("nd", String.valueOf(System.currentTimeMillis()));
        params.add("rows", "8000");
        params.add("page", "2");
        params.add("sidx", "");
        params.add("sord", "asc");
        String body = restTemplateClient.postForObjectDefault(url, params, String.class);
        System.out.println(body);
        assert body != null;
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(body);
    }

}
