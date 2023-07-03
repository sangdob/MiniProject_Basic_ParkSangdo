package com.mutsa.mini_project.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.*;
import static org.springframework.http.HttpStatus.*;

@JsonFormat(shape = OBJECT)
@AllArgsConstructor
@Getter
public enum ErrorCode {
    /* Common Error */
    INVALID_INPUT_VALUE(BAD_REQUEST, " 잘못된 입력값입니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, " 요청메서드가 허용되지 않습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에서 오류가 발생했습니다."),
    INVALID_TYPE_VALUE(BAD_REQUEST, " 잘못된 타입입니다."),
    FILE_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "파일 저장에 실패했습니다."),

    INVALID_JSON_FORMAT(BAD_REQUEST, "Json형식과 맞지 않습니다."),

    NOT_MATCH_WRITER(NOT_FOUND, "작성자된 정보가 옳지 않습니다."),
    NOT_FOUND_ENTITY(NOT_FOUND, "데이터가 존재하지 않습니다."),

    ;

    private final HttpStatus code;
    private final String message;
}
