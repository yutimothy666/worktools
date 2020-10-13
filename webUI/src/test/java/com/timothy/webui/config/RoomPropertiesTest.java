package com.timothy.webui.config;

import lombok.Data;
import org.apache.poi.util.StringUtil;

import java.util.stream.IntStream;


/**
 * @Description:
 * @Author: timothyyu
 * @Date: 2020/9/18 12:54
 */
class RoomPropertiesTest {
    public static void main(String[] args) {
        IntStream.range(0, 6).parallel().forEach(System.out::println);
        String[] a = {"a"};
        System.out.println(StringUtil.join(a, ","));
        OK ok = new OK();
        setA(ok);
        System.out.println("ok.getAnInt() = " + ok.getAnInt());
    }

    public static Integer setA(OK ok) {
        ok.setAnInt(5);
        try {
            return 3;
        } finally {
            System.out.println("1 = " + 1);
        }

    }
}

@Data
class OK {
    private int anInt;
}