package com.timothy.webui.config;

import org.junit.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.stream.IntStream;


/**
 * @Description:
 * @Author: timothyyu
 * @Date: 2020/9/18 12:54
 */
class RoomPropertiesTest {
    public static void main(String[] args) {
        IntStream.range(0, 6).parallel().forEach(System.out::println);
    }
}