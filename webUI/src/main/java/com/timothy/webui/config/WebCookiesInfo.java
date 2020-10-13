package com.timothy.webui.config;

import com.timothy.webui.utils.WebUIUtils;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author yutimothy
 * @Date 2020/9/28 19:55
 * @Version 1.0
 */
@Component
@Data
public class WebCookiesInfo {
    private String schoolId = null;
    private String cookiesId = null;
    private static final Logger logger = LoggerFactory.getLogger(WebCookiesInfo.class);

    public void setAll(WebCookiesInfo webCookiesInfo) {
        if (WebUIUtils.isNotEmpty(webCookiesInfo.getCookiesId())) {
            logger.info("set CookiesId", cookiesId);
            this.setCookiesId(webCookiesInfo.getCookiesId().trim());
        }
        if (WebUIUtils.isNotEmpty(webCookiesInfo.getSchoolId())) {
            logger.info("set schoolId", schoolId);
            this.setSchoolId(webCookiesInfo.getSchoolId().trim());
        }
    }
}
