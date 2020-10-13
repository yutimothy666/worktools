package com.timothy.webui.service;

import com.timothy.webui.bean.DepartmentBean;
import com.timothy.webui.bean.RoomJSONResult;
import com.timothy.webui.bean.TableResultRoot;

import java.util.List;

/**
 * @Description: roomservice
 * @Author: timothyyu
 * @Date: 2020/9/18 12:58
 */
public interface RoomService {
    /**
     * 总函数
     *
     * @return 整数
     */
    Integer GetTotalRoomNum();

    /**
     * @param beds           床位id
     * @param departmentBean 床位信息
     * @return 操作结果
     */
    String AdjustMajor(List<String> beds, DepartmentBean departmentBean);

    /**
     * 取消床位预分
     *
     * @param beds 床位id
     * @return "ok"
     */
    String AdjustCancel(Long beds);

    TableResultRoot<RoomJSONResult> DataProvider(Integer page);

    /**
     * 初始化迎新网学生信息
     */
    Integer initRoomInfo() throws InterruptedException;
}
