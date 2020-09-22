package com.timothy.webui.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @Description: excel
 * @Author: timothyyu
 * @Date: 2020/9/21 16:55
 */
public class RoomExcel implements Serializable {
    public RoomExcel() {
    }

    @ExcelProperty(value = "号码")
    private String code;
    @ExcelProperty(value = "学校")
    private String schoolName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RoomExcel.class.getSimpleName() + "[", "]")
                .add("code='" + code + "'")
                .add("schoolName='" + schoolName + "'")
                .toString();
    }
}
