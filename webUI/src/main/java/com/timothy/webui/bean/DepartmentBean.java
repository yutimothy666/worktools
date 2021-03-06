package com.timothy.webui.bean;

import lombok.Data;

/**
 * @Description:
 * @Author: timothyyu
 * @Date: 2020/9/24 15:46
 */
@Data
public class DepartmentBean {
    private Long facultyId;
    private Long majorId;
    private Long classId;
    private String classCode;
    private String majorCode;
    private String className;
    private String majorName;
    private String facultyName;
}
