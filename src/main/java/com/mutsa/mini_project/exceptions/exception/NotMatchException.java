package com.mutsa.mini_project.exceptions.exception;

import com.mutsa.mini_project.exceptions.ErrorCode;

public class NotMatchException extends BusinessException{

    public NotMatchException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public NotMatchException(ErrorCode errorCode) {
        super(errorCode);
    }
}
