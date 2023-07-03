package com.mutsa.mini_project.exceptions.exception;

import com.mutsa.mini_project.exceptions.ErrorCode;
import lombok.Getter;

import java.io.IOException;

@Getter
public class CustomIoException extends RuntimeException {
    private final ErrorCode errorCode;
    public CustomIoException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;

    }

    public CustomIoException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
