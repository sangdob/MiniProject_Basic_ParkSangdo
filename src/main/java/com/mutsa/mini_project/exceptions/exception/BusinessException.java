package com.mutsa.mini_project.exceptions.exception;

import com.mutsa.mini_project.exceptions.ErrorCode;
import lombok.Getter;


@Getter
public class BusinessException extends RuntimeException{
    private final ErrorCode errorCode;
    public BusinessException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;

    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
