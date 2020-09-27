package com.timothy.webui.utils;

import com.timothy.webui.config.RoomProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: timothyyu
 * @Date: 2020/9/18 14:26
 */
@Component
public class RestTemplateClient {

    @Resource
    private RoomProperties roomProperties;

    @Resource
    private RestTemplate restTemplate;

    public HttpEntity<MultiValueMap<String, String>> getRequest(MultiValueMap<String, String> params) {
        HttpHeaders httpHeaders = new HttpHeaders();
        MediaType mediaType = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8");
        httpHeaders.setContentType(mediaType);
        httpHeaders.add("Cookie", "JSESSIONID=" + roomProperties.getCookiesId() + "; dormitory_login=false; collegeId=" + roomProperties.getSchoolId());
        return new HttpEntity<>(params, httpHeaders);
    }

    public <T> ResponseEntity<T> exchangeDefault(String url, MultiValueMap<String, String> params, ParameterizedTypeReference<T> responseType) {
        return restTemplate.exchange(url, HttpMethod.POST, getDefaultHttpEntity(params), responseType);
    }

    public <T> T postForObjectDefault(String url, MultiValueMap<String, String> params, Class<T> responseType) {
        return restTemplate.postForObject(url, getDefaultHttpEntity(params), responseType);
    }

    public HttpEntity<MultiValueMap<String, String>> getDefaultHttpEntity(MultiValueMap<String, String> params) {
        HttpHeaders httpHeaders = new HttpHeaders();
        MediaType mediaType = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8");
        httpHeaders.setContentType(mediaType);
        httpHeaders.add("Cookie", "JSESSIONID=" + roomProperties.getCookiesId() + "; dormitory_login=false; collegeId=" + roomProperties.getSchoolId());
        return new HttpEntity<>(params, httpHeaders);
    }
}
