package com.mutsa.mini_project.exceptions.handler;

import com.mutsa.mini_project.exceptions.ErrorCode;
import com.mutsa.mini_project.exceptions.ExceptionResponse;
import com.mutsa.mini_project.exceptions.exception.BusinessException;
import com.mutsa.mini_project.exceptions.exception.CustomIoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler{

    /**
     * java validation or binding error 발생시 에러 처리
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getClass().getName(), e);
        final ExceptionResponse response = ExceptionResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
        return new ResponseEntity<>(response, INTERNAL_SERVER_ERROR);
    }

    /**
     * json type binding Exception
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    protected ResponseEntity<ExceptionResponse> handleHttpMessageConversionException(HttpMessageConversionException e) {
        log.error("handleHttpMessageConversionException", e);
        final ExceptionResponse response = ExceptionResponse.of(ErrorCode.INVALID_INPUT_VALUE);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Enum type binding Exception
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ExceptionResponse> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e) {
        log.error("handleMethodArgumentTypeMismatchException", e);
        final ExceptionResponse response = ExceptionResponse.of(e);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * @ModelAttribute에서 binding error Exception
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ExceptionResponse> handleBindException(BindException e) {
        log.error("handleBindException", e);
        final ExceptionResponse response = ExceptionResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ExceptionResponse> handleBusinessException(final BusinessException e) {
        log.error(e.getClass().getName(), e);
        final ErrorCode errorCode = e.getErrorCode();
        final ExceptionResponse response = ExceptionResponse.of(errorCode);
        return new ResponseEntity<>(response, errorCode.getCode());
    }

    @ExceptionHandler(IOException.class)
    protected ResponseEntity<ExceptionResponse> handleIoException(final CustomIoException e) {
        log.error(e.getClass().getName(), e);
        final ErrorCode errorCode = e.getErrorCode();
        final ExceptionResponse response = ExceptionResponse.of(errorCode);
        return new ResponseEntity<>(response, errorCode.getCode());
    }

    /**
     * 예상하지 못하는 Exception
     * Status 500
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionResponse> handleException(Exception e) {
        log.error(e.getClass().getName(), e);
        final ExceptionResponse response = ExceptionResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(response, INTERNAL_SERVER_ERROR);
    }
}
