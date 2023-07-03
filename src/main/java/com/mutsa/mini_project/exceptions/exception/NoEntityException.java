package com.mutsa.mini_project.exceptions.exception;

import com.mutsa.mini_project.exceptions.ErrorCode;

public class NoEntityException extends BusinessException{
    public NoEntityException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public NoEntityException(ErrorCode errorCode) {
        super(errorCode);
    }
}
