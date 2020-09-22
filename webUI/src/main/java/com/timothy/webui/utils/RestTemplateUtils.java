package com.timothy.webui.utils;

import com.timothy.webui.config.RoomProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: timothyyu
 * @Date: 2020/9/18 14:26
 */
@Component
public class RestTemplateUtils {

    @Resource
    private RoomProperties roomProperties;


    public HttpEntity<MultiValueMap<String, String>> getRequest(MultiValueMap<String, String> params) {
        HttpHeaders httpHeaders = new HttpHeaders();
        MediaType mediaType = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8");
        httpHeaders.setContentType(mediaType);
        httpHeaders.add("Cookie", "JSESSIONID=" + roomProperties.getCookiesId() + "; dormitory_login=false; collegeId=" + roomProperties.getSchoolId());
        return new HttpEntity<>(params, httpHeaders);
    }
}
