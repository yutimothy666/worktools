package com.timothy.webui.utils;

import com.alibaba.fastjson.JSON;
import com.timothy.webui.config.AjaxResult;
import com.timothy.webui.config.WebCookiesInfo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownContentTypeException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Description:
 * @Author: timothyyu
 * @Date: 2020/9/18 14:26
 */
@Component
public class RestTemplateClient {

    @Resource
    private WebCookiesInfo webCookiesInfo;

    @Resource
    private RestTemplate restTemplate;

    public HttpEntity<MultiValueMap<String, String>> getRequest(MultiValueMap<String, String> params) {
        HttpHeaders httpHeaders = new HttpHeaders();
        MediaType mediaType = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8");
        httpHeaders.setContentType(mediaType);
        httpHeaders.add("Cookie", "JSESSIONID=" + webCookiesInfo.getCookiesId() + "; dormitory_login=false; collegeId=" + webCookiesInfo.getSchoolId());
        return new HttpEntity<>(params, httpHeaders);
    }

    public <T> ResponseEntity<T> exchangeDefault(String url, MultiValueMap<String, String> params, ParameterizedTypeReference<T> responseType) {
        return restTemplate.exchange(url, HttpMethod.POST, getDefaultHttpEntity(params), responseType);
    }

    public <T> ResponseEntity<T> exchangeDefault(String url, MultiValueMap<String, String> params, ParameterizedTypeReference<T> responseType, Map<String, ?> uriVariables) {
        return restTemplate.exchange(url, HttpMethod.POST, getDefaultHttpEntity(params), responseType, uriVariables);
    }

    public <T> T postForObjectDefault(String url, MultiValueMap<String, String> params, Class<T> responseType) {
        return restTemplate.postForObject(url, getDefaultHttpEntity(params), responseType);
    }

    public HttpEntity<MultiValueMap<String, String>> getDefaultHttpEntity(MultiValueMap<String, String> params) {
        HttpHeaders httpHeaders = new HttpHeaders();
        MediaType mediaType = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8");
        httpHeaders.setContentType(mediaType);
        httpHeaders.add("Cookie", "JSESSIONID=" + webCookiesInfo.getCookiesId() + "; dormitory_login=false; collegeId=" + webCookiesInfo.getSchoolId());
        return new HttpEntity<>(params, httpHeaders);
    }
}
