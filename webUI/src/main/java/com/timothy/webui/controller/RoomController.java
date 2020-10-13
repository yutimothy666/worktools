package com.timothy.webui.controller;

import com.alibaba.excel.EasyExcel;
import com.timothy.webui.Exception.RoomExcelException;
import com.timothy.webui.bean.DepartmentBean;
import com.timothy.webui.bean.DepartmentInfo;
import com.timothy.webui.bean.RoomInfo;
import com.timothy.webui.bean.RoomRecode;
import com.timothy.webui.config.AjaxResult;
import com.timothy.webui.config.WebCookiesInfo;
import com.timothy.webui.excel.RoomExcel;
import com.timothy.webui.excel.RoomExcelListener;
import com.timothy.webui.service.DepartmentService;
import com.timothy.webui.service.RoomService;
import com.timothy.webui.utils.WebUIUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

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
    DepartmentService departmentService;

    @Resource
    WebCookiesInfo webCookiesInfo;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

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
    public AjaxResult post(@RequestParam(value = "file", required = false) MultipartFile file, WebCookiesInfo info) throws
            IOException, InterruptedException {
        webCookiesInfo.setAll(info);
        Integer changeRoomNum = 0;

        logger.info("上传文件名-{}", file.getOriginalFilename());

        HashMap<String, Object> object = new HashMap<>();

        List<String> roomChangeDetail = new ArrayList<>();
        if (!file.isEmpty()) {
            object.put("msg", file.getOriginalFilename());
            EasyExcel.read(file.getInputStream(), RoomExcel.class, new RoomExcelListener()).sheet(0).doRead();
            List<RoomExcel> roomExcels = RoomInfo.getRoomExcels();
            roomExcels.forEach(item -> {
                logger.info("xls info-{}", item);
            });

            logger.info("--开始初始化宿舍信息");
            roomService.initRoomInfo();
            logger.info("--初始化宿舍信息完成");

            logger.info("--开始初始化院系信息");
            departmentService.InitDepartmentInfo();
            logger.info("--初始化院系班级信息完成");

            logger.info("开始移动宿舍");
            MultiValueMap<String, RoomRecode> roomMap = RoomInfo.getRoomMap();

            ThreadLocal<Map<String, Boolean>> cacheRoomUseMap = new ThreadLocal<>();
            cacheRoomUseMap.set(new HashMap<>());
            for (RoomExcel roomExcel : roomExcels) {
                if (roomExcel.getRoomNum() - roomExcel.getRoomUsed() > 0) {
                    String roomNameKey = roomExcel.getRoomName().trim();

                    List<RoomRecode> roomRecodes = roomMap.get(roomNameKey);
                    roomRecodes.forEach(k -> {
                        if (!cacheRoomUseMap.get().containsKey(k.getId())) {
                            cacheRoomUseMap.get().put(k.getId(), false);
                        }
                    });
                    Integer getNum = 0;
                    ArrayList<String> roomList = new ArrayList<>();
                    for (RoomRecode roomRecode : roomRecodes) {
                        if (!cacheRoomUseMap.get().get(roomRecode.getId())) {
                            roomList.add(roomRecode.getId());
                            cacheRoomUseMap.get().put(roomRecode.getId(), true);
                            getNum++;
                        }
                        if (getNum.equals(roomExcel.getRoomUsed())) {
                            break;
                        }
                    }
                    Thread.sleep(200);
                    ThreadLocal<Boolean> isPointer = new ThreadLocal<>();
                    isPointer.set(false);
                    DepartmentBean departmentBean = new DepartmentBean();
                    if (WebUIUtils.isNotEmpty(roomExcel.getClassCode())) {
                        departmentBean.setClassCode(roomExcel.getClassCode().trim());
                        isPointer.set(true);
                    }
                    if (WebUIUtils.isNotEmpty(roomExcel.getMajorName())) {
                        departmentBean.setMajorName(roomExcel.getMajorName());
                        isPointer.set(true);
                    }
                    if (!isPointer.get()) {
                        throw new RoomExcelException();
                    }
                    logger.info("before{}", departmentBean);
                    departmentService.CreateDepartmentBean(departmentBean);//组装id
                    logger.info("after{}", departmentBean);

                    String result = roomService.AdjustMajor(roomList, departmentBean);
                    String msg = roomExcel.getRoomName() + "移动了" + getNum + "间房间 " + "操作结果：" + result;
                    logger.info(msg);
                    roomChangeDetail.add(msg);
                    changeRoomNum++;
                }
            }
            String msgAll = "一共移动了" + changeRoomNum + "个房间";
            logger.info(msgAll);
            roomChangeDetail.add(msgAll);
            object.put("detail", roomChangeDetail);
            AtomicReference<Integer> cancelNum = new AtomicReference<>(0);
            cacheRoomUseMap.get().forEach((k, v) -> {
                logger.info("{}-{}", k, v);
                if (!v) {
                    roomService.AdjustCancel(Long.valueOf(k));
                    logger.info("取消房间id-{}", k);
                    cancelNum.getAndSet(cancelNum.get() + 1);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        logger.error("error", e);
                    }
                }
            });
            logger.info("取消房间总数-{}", cancelNum.get());
        } else {
            roomChangeDetail.add("test1");
            roomChangeDetail.add("test1");
            roomChangeDetail.add("test1");
            object.put("num", String.valueOf(changeRoomNum));
            object.put("detail", roomChangeDetail);
            return AjaxResult.toAjax("200", "提交文件为空", object);
        }
        return AjaxResult.success(object);
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

    @ResponseBody
    @GetMapping("/department")
    public AjaxResult department(@RequestParam(value = "classCode", required = false, defaultValue = "") String
                                         classCode, @RequestParam(value = "majorName", required = false, defaultValue = "") String majorName) {
        AjaxResult ajaxResult = departmentService.InitDepartmentInfo();
        if (!WebUIUtils.isNotEmpty(classCode) && !WebUIUtils.isNotEmpty(majorName)) {
            return AjaxResult.success(DepartmentInfo.getDepartmentInfo());
        }
        DepartmentBean departmentBean = new DepartmentBean();
        departmentBean.setMajorName(majorName);
        departmentBean.setClassCode(classCode);
        departmentService.CreateDepartmentBean(departmentBean);
        return AjaxResult.success(departmentBean);
    }

    @ResponseBody
    @GetMapping("/webInfo")
    public AjaxResult webInfo(WebCookiesInfo info) {
        webCookiesInfo.setAll(info);
        return AjaxResult.success(webCookiesInfo);
    }

    @ResponseBody
    @GetMapping("/test")
    public AjaxResult test2(String bedsId, String classCode) {
        System.out.println("bedsId = " + bedsId);
        System.out.println("classCode = " + classCode);
        DepartmentBean departmentBean = new DepartmentBean();
        departmentBean.setClassCode(classCode);
        departmentService.InitDepartmentInfo();
        departmentService.CreateDepartmentBean(departmentBean);
        return AjaxResult.success(roomService.AdjustMajor(Arrays.asList(bedsId.split(",")), departmentBean));
    }
}
