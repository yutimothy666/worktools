package com.timothy.webui.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.StringJoiner;

/**
 * @Description: roomProperties
 * @Author: timothyyu
 * @Date: 2020/9/18 12:50
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "room", ignoreUnknownFields = true)
public class RoomProperties {

    private String adjustMajor;

    private String dormAjustDataProvider;
    /**
     * 会话id
     */
    private String cookiesId;
    /**
     * 学校id
     */
    private String schoolId;
    /**
     * 预分级别
     */
    private Integer presortLevel;

    private String adjustCancel;
    private String baseUrl;
}
