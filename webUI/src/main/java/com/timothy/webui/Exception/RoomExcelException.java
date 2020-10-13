package com.timothy.webui.Exception;

/**
 * @Description:
 * @Author: timothyyu
 * @Date: 2020/10/10 10:56
 */
public class RoomExcelException extends RuntimeException {
    private String errorMessage;

    public RoomExcelException() {

    }

    public RoomExcelException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
