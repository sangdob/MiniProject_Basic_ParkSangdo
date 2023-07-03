package com.mutsa.mini_project.exceptions.exception;

import com.mutsa.mini_project.exceptions.ErrorCode;

public class InvalidInputException extends BusinessException{
    public InvalidInputException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public InvalidInputException(ErrorCode errorCode) {
        super(errorCode);
    }
}
