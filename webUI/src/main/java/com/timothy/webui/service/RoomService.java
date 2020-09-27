package com.timothy.webui.service;

import com.timothy.webui.bean.DepartmentBean;
import com.timothy.webui.bean.RoomJSONResult;
import com.timothy.webui.bean.TableResultRoot;

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
     * 更改床位预分班级或者专业
     *
     * @param beds      床位id
     * @param facultyId 院系id
     * @param majorId   专业id
     * @param classId   班级id
     * @return "ok'
     */
    String AdjustMajor(Long[] beds, DepartmentBean departmentBean);

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
