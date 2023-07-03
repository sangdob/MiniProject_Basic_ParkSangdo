package com.mutsa.mini_project.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionResponse {
    private String message;
    private int status;
    private List<FieldError> errors;
    private String code;

    public ExceptionResponse(final ErrorCode code) {
        this.message = code.getMessage();
        this.status = code.getCode().value();
        this.errors = new ArrayList<>();
    }

    public ExceptionResponse(final ErrorCode code, final List<FieldError> errors) {
        this.message = code.getMessage();
        this.status = code.getCode().value();
        this.errors = errors;
    }

    public static ExceptionResponse of(MethodArgumentTypeMismatchException e) {
        final String value = e.getValue() == null ? "" : e.getValue().toString();
        final List<ExceptionResponse.FieldError> errors = ExceptionResponse.FieldError.of(e.getName(), value, e.getErrorCode());
        return new ExceptionResponse(ErrorCode.INVALID_TYPE_VALUE, errors);
    }

    public static ExceptionResponse of(final ErrorCode code, final List<FieldError> errors) {
        return new ExceptionResponse(code, errors);
    }

    public static ExceptionResponse of(final ErrorCode errorCode) {
        return new ExceptionResponse(errorCode);
    }

    public static ExceptionResponse of(final ErrorCode code, final BindingResult bindingResult) {
        return new ExceptionResponse(code, FieldError.of(bindingResult));
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FieldError {
        private String field;
        private String value;
        private String reason;

        private FieldError(final String field, final String value, final String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static List<FieldError> of(final String field, final String value, final String reason) {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(field, value, reason));
            return fieldErrors;
        }

        private static List<FieldError> of(final BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }
}
