package com.timothy.webui.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Description: config
 * @Author: timothyyu
 * @Date: 2020/9/18 10:09
 */
@Configuration
public class WebUiConfig implements WebMvcConfigurer {
    @Resource
    private RestTemplateBuilder restTemplateBuilder;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate build = restTemplateBuilder.build();
        build.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return build;
    }

    @Bean
    public HttpMessageConverter responseBodyConverter() {
        //解决返回值中文乱码
        return new StringHttpMessageConverter(StandardCharsets.UTF_8);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(responseBodyConverter());
    }
    
}
