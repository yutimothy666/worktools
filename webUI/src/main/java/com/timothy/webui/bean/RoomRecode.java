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
public class RoomRecode {
    private Long id;
    private Boolean isUse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getUse() {
        return isUse;
    }

    public void setUse(Boolean use) {
        isUse = use;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomRecode that = (RoomRecode) o;
        return id.equals(that.id) &&
                isUse.equals(that.isUse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isUse);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RoomRecode.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("isUse=" + isUse)
                .toString();
    }
}
