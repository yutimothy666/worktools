package com.timothy.webui.bean;

import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @Description:
 * @Author: timothyyu
 * @Date: 2020/9/18 17:07
 */
@Component
public class RoomInfo {
    private static MultiValueMap<String, Long> roomMap = new LinkedMultiValueMap<>();


    public void add(String key, Long value) {
        roomMap.add(key, value);
    }

    public static void destroy() {
        roomMap = new LinkedMultiValueMap<>();
    }

    public static MultiValueMap<String, Long> getRoomMap() {
        return roomMap;
    }

    public synchronized static void putRoomBean(RoomBean roomBean) {
        roomBean.getGridModel().forEach(k -> {
            roomMap.add(k.getDormAllName(), k.getId());
        });
    }
}
