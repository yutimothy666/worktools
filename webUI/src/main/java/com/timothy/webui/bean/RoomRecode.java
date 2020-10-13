package com.timothy.webui.bean;

import lombok.*;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * @Description:
 * @Author: timothyyu
 * @Date: 2020/9/24 12:16
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class RoomRecode {
    private String id;
    private Boolean isUse;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomRecode that = (RoomRecode) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
