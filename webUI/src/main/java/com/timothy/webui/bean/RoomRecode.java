package com.timothy.webui.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * @Description:
 * @Author: timothyyu
 * @Date: 2020/9/24 12:16
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomRecode {
    private String id;
    private Boolean isUse;
}
