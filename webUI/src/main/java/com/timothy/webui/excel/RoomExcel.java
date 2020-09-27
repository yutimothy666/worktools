package com.timothy.webui.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.timothy.webui.utils.MyUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.util.StringUtil;
import org.apache.tomcat.util.buf.StringUtils;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @Description: excel
 * @Author: timothyyu
 * @Date: 2020/9/21 16:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomExcel implements Serializable {
    @ExcelProperty(value = "校区")
    private String SQ;
    @ExcelProperty(value = "楼栋")
    private String LD;
    @ExcelProperty(value = "单元")
    private String DY;
    @ExcelProperty(value = "楼层")
    private String LC;
    @ExcelProperty(value = "寝室")
    private String QS;
    @ExcelProperty(value = "床位数")
    private Integer roomNum;
    @ExcelProperty(value = "分配床位数")
    private Integer roomUsed;
    @ExcelProperty(value = "学院id")
    private Long facultyId;
    @ExcelProperty(value = "班级id")
    private Long classId;
    @ExcelProperty(value = "专业id")
    private Long majorId;

    public String getRoomName() {
        return MyUtils.getRoomName(SQ, LD, DY, LC, QS);
    }
}
