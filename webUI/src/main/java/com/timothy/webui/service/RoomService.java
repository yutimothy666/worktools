package com.timothy.webui.service;

import com.timothy.webui.bean.RoomBean;

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
    String AdjustMajor(Long[] beds, Long facultyId, Long majorId, Long classId);

    /**
     * 取消床位预分
     *
     * @param beds 床位id
     * @return "ok"
     */
    String AdjustCancel(Long beds);

    RoomBean DataProvider(Integer page);

    /**
     * 初始化迎新网学生信息
     */
    void initRoomInfo() throws InterruptedException;
}
