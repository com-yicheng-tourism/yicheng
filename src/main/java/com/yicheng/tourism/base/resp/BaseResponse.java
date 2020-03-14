package com.yicheng.tourism.base.resp;

import com.yicheng.tourism.base.enums.StatusEnum;
import com.yicheng.tourism.util.StringUtil;
import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
    private String code;

    private String message;

    private T data;

    public BaseResponse() {
    }

    public BaseResponse(T data) {
        this.data = data;
    }

    public BaseResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> BaseResponse<T> create(T t) {
        return new BaseResponse<T>(t);
    }

    public static <T> BaseResponse<T> create(T t, StatusEnum statusEnum) {
        return new BaseResponse<T>(statusEnum.getCode(), statusEnum.getMessage(), t);
    }

    public static <T> BaseResponse<T> create(String code, String message) {
        return new BaseResponse<T>(code, message, null);
    }

    public static <T> BaseResponse<T> create(T t, String prefix, StatusEnum statusEnum) {
        return new BaseResponse<T>(StringUtil.isNullOrEmpty(prefix) ? statusEnum.getCode() : prefix + statusEnum.getCode(), statusEnum.getMessage(), t);
    }

    public static <T> BaseResponse<T> createSuccess(T t, String message) {
        return new BaseResponse<T>(StatusEnum.SUCCESS.getCode(), StringUtil.isNullOrEmpty(message) ? StatusEnum.SUCCESS.getMessage() : message, t);
    }

    public static <T> BaseResponse<T> createSuccess(T t, String prefix, String message) {
        return new BaseResponse<T>(StringUtil.isNullOrEmpty(prefix) ? StatusEnum.SUCCESS.getCode() : prefix + StatusEnum.SUCCESS.getCode(), StringUtil.isNullOrEmpty(message) ? StatusEnum.SUCCESS.getMessage() : message, t);
    }

    public static <T> BaseResponse<T> createFail(T t, String message) {
        return new BaseResponse<T>(StatusEnum.FAIL.getCode(), StringUtil.isNullOrEmpty(message) ? StatusEnum.FAIL.getMessage() : message, t);
    }

    public static <T> BaseResponse<T> createFail(T t, String prefix, String message) {
        return new BaseResponse<T>(StringUtil.isNullOrEmpty(prefix) ? StatusEnum.FAIL.getCode() : prefix + StatusEnum.FAIL.getCode(), StringUtil.isNullOrEmpty(message) ? StatusEnum.FAIL.getMessage() : message, t);
    }

    public static <T> BaseResponse<T> create(T t, StatusEnum statusEnum, String message) {

        return new BaseResponse<T>(statusEnum.getCode(), message, t);
    }

    public static <T> BaseResponse<T> create(T t, String prefix, StatusEnum statusEnum, String message) {
        return new BaseResponse<T>(StringUtil.isNullOrEmpty(prefix) ? statusEnum.getCode() : prefix + statusEnum.getCode(), message, t);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
