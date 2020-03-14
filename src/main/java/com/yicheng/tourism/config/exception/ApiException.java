package com.yicheng.tourism.config.exception;

import com.yicheng.tourism.base.enums.StatusEnum;

/**
 * StandardStatusEnum 封装的异常
 */
public class ApiException extends RuntimeException {

    private StatusEnum exceptionEnum;

    public ApiException(StatusEnum statusEnum) {
        super(statusEnum.getMessage());
        this.exceptionEnum = statusEnum;
    }

    public StatusEnum getExceptionEnum() {
        return exceptionEnum;
    }
}
