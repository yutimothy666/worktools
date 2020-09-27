package com.timothy.webui.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author yutimothy
 * @Date 2020/9/27 22:14
 * @Version 1.0
 */
@Component
public class DepartmentInfo {
    private static Map<Integer, List<DepartmentTableJsonResult>> departmentInfo = new HashMap<>();

    public static List<DepartmentTableJsonResult> getDepartmentInfo(Integer typeId) {
        return departmentInfo.get(typeId);
    }

    public static void setDepartmentInfo(Integer typeId, List<DepartmentTableJsonResult> departmentInfoList) {
        departmentInfo.put(typeId, departmentInfoList);
    }

    @Override
    public String toString() {
        return departmentInfo.toString();
    }

    public static Map<Integer, List<DepartmentTableJsonResult>> getDepartmentInfo() {
        return departmentInfo;
    }
}
