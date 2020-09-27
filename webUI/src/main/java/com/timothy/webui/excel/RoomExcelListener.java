package com.timothy.webui.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.timothy.webui.bean.RoomInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: timothyyu
 * @Date: 2020/9/21 17:05
 */
public class RoomExcelListener extends AnalysisEventListener<RoomExcel> {

    @Override

    public void invoke(RoomExcel data, AnalysisContext context) {
        RoomInfo.getRoomExcels().add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
    }
}
