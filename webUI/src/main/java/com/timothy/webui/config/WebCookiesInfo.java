package com.timothy.webui.config;

import com.timothy.webui.utils.MyUtils;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Author yutimothy
 * @Date 2020/9/28 19:55
 * @Version 1.0
 */
@Component
@Data
public class WebCookiesInfo {
    private String schoolId = "3";
    private String cookiesId = "A1879359854E225C1A5FDD65F4420F45";

    public void setAll(WebCookiesInfo webCookiesInfo) {
        if (MyUtils.isNotEmpty(webCookiesInfo.getCookiesId())) {
            this.setCookiesId(webCookiesInfo.getCookiesId().trim());
        }
        if (MyUtils.isNotEmpty(webCookiesInfo.getSchoolId())) {
            this.setSchoolId(webCookiesInfo.getSchoolId().trim());
        }
    }
}
