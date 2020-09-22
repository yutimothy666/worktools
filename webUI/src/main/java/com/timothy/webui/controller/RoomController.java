package com.timothy.webui.controller;

import com.alibaba.excel.EasyExcel;
import com.timothy.webui.bean.RoomBean;
import com.timothy.webui.bean.RoomInfo;
import com.timothy.webui.excel.RoomExcel;
import com.timothy.webui.excel.RoomExcelListener;
import com.timothy.webui.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;

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
    public Object get(@RequestParam(value = "page", defaultValue = "1", required = false) Integer page) {
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
        Integer totalNum = roomService.GetTotalRoomNum();
        roomService.initRoomInfo();
        return totalNum;
    }

    @RequestMapping(value = {"", "/"})
    public String view() {
        return "/room/room";
    }

    @ResponseBody
    @PostMapping("/")
    public Object post(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        HashMap<String, String> object = new HashMap<>();
        System.out.println(file.getOriginalFilename());
        if (file != null && !file.isEmpty()) {
            object.put("msg", file.getOriginalFilename());
            EasyExcel.read(file.getInputStream(), RoomExcel.class, new RoomExcelListener()).sheet(0).doRead();
        } else {
            object.put("msg", "error");
        }
        return object;
    }
}
