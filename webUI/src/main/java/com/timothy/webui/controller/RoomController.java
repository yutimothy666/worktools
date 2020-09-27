package com.timothy.webui.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.timothy.webui.bean.RoomInfo;
import com.timothy.webui.bean.RoomRecode;
import com.timothy.webui.excel.RoomExcel;
import com.timothy.webui.excel.RoomExcelListener;
import com.timothy.webui.service.RoomService;
import org.apache.logging.log4j.util.Strings;
import org.apache.poi.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @Description:
 * @Author: timothyyu
 * @Date: 2020/9/18 16:32
 */
@RequestMapping("/room")
@Controller
public class RoomController {
    @Resource
    RoomService roomService;

    @Resource
    RoomInfo roomInfo;

    @ResponseBody
    @GetMapping("list")
    public Object get(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page) {
        return roomService.DataProvider(page);
    }

    @ResponseBody
    @GetMapping("totally")
    public Object totally() {
        return roomService.GetTotalRoomNum();
    }

    @ResponseBody
    @GetMapping("start")
    public Integer start() throws InterruptedException {
        return roomService.initRoomInfo();
    }

    @RequestMapping(value = {"", "/"})
    public String view() {
        return "/room/room";
    }

    @ResponseBody
    @PostMapping("/")
    public Object post(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException, InterruptedException {
        Integer changeRoomNum = 0;
        HashMap<String, Object> object = new HashMap<>();
        System.out.println(file.getOriginalFilename());
        if (!file.isEmpty()) {
            object.put("msg", file.getOriginalFilename());
            EasyExcel.read(file.getInputStream(), RoomExcel.class, new RoomExcelListener()).sheet(0).doRead();
            List<RoomExcel> roomExcels = RoomInfo.getRoomExcels();
            roomService.initRoomInfo();
            List<String> roomChangeDetail = new ArrayList<>();
            for (RoomExcel roomExcel : roomExcels) {
                if (roomExcel.getRoomNum() - roomExcel.getRoomUsed() > 0) {
                    List<RoomRecode> roomRecodes = RoomInfo.getRoomMap().get(roomExcel.getRoomName());
                    Integer getNum = 0;
                    Long[] roomList = new Long[9];
                    for (RoomRecode roomRecode : roomRecodes) {
                        if (!roomRecode.getUse()) {
                            roomList[getNum] = roomRecode.getId();
                            roomRecode.setUse(true);
                            getNum++;
                        }
                        if (getNum.equals(roomExcel.getRoomUsed())) {
                            break;
                        }
                    }
                    Thread.sleep(200);
                    roomChangeDetail.add(roomExcel.getRoomName() + "移动了" + getNum + "间房间");
                    roomService.AdjustMajor(roomList, roomExcel.getFacultyId(), roomExcel.getMajorId(), roomExcel.getClassId());
                    changeRoomNum++;
                }
            }
            object.put("num", String.valueOf(changeRoomNum));
            object.put("detail", roomChangeDetail);
        } else {
            object.put("msg", "error");
            object.put("num", String.valueOf(0));
            object.put("detail", null);
        }
        return object;
    }

    @ResponseBody
    @GetMapping("/keys")
    public Object keys(@RequestParam(value = "key", required = false) String key) {
        MultiValueMap<String, RoomRecode> roomMap = RoomInfo.getRoomMap();
        roomMap.forEach((k, v) -> {
            System.out.println(k.replace(" ", "|"));
            System.out.println(v);
        });
        if (Strings.isNotBlank(key)) {
            return roomMap.get(key);
        } else {
            return "ok";
        }
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        List<RoomExcel> list = new ArrayList<>();
        list.add(RoomExcel.builder().DY(String.valueOf(1)).build());
        EasyExcel.write(response.getOutputStream(), RoomExcel.class).sheet("ok").doWrite(list);
        response.setHeader("Content-disposition", "attachment;filename=ok" + ".xlsx");
        response.setContentType("application/form-data");
        response.setHeader("ok", "ok");
        response.setCharacterEncoding("utf-8");
    }
}
