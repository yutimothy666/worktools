package com.timothy.webui.utils;

import com.timothy.webui.WebUIApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;


/**
 * @Description:
 * @Author: timothyyu
 * @Date: 2020/10/6 17:51
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebUIApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebUIUtilsTest {

    @Test
    void isNotEmpty() {
        Map<String, String> map = new HashMap<>();
        String orDefault = map.getOrDefault("1", "1");
        System.out.println("orDefault = " + orDefault);
    }

    @Test
    void getRoomName() {
    }

    @Test
    void getResponse() {
    }

    @Test
    void getRequest() {
    }
}