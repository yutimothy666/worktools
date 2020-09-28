package com.timothy.webui.bean;

import com.timothy.webui.excel.RoomExcel;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: timothyyu
 * @Date: 2020/9/18 17:07
 */
@Component
public class RoomInfo {
    private static MultiValueMap<String, RoomRecode> roomMap = new LinkedMultiValueMap<>();

    public static List<RoomExcel> getRoomExcels() {
        return roomExcels;
    }

    public static void setRoomExcels(List<RoomExcel> roomExcels) {
        RoomInfo.roomExcels = roomExcels;
    }

    private static List<RoomExcel> roomExcels = new ArrayList<>();

    public void add(String key, RoomRecode roomRecode) {
        roomMap.add(key, roomRecode);
    }

    public static void destroy() {
        roomMap = new LinkedMultiValueMap<>();
    }

    public static MultiValueMap<String, RoomRecode> getRoomMap() {
        return roomMap;
    }

    public synchronized static void putRoomBean(TableResultRoot<RoomJSONResult> roomBean) {
        roomBean.getGridModel().forEach(k -> {
            roomMap.add(k.getDormAllName().replace(" ", ""), new RoomRecode(String.valueOf(k.getId()), false));
        });
    }
}
