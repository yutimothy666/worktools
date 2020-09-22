package com.timothy.webui.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: timothyyu
 * @Date: 2020/9/22 13:59
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentUse {
    private Long facultyId;
    private Long majorId;
    private Long classId;
    private String dormAllName;
    private Integer roomNum;
}
