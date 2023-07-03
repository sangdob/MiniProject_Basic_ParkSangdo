package com.mutsa.mini_project.contorller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.OBJECT;

@JsonFormat(shape = OBJECT)
@AllArgsConstructor
@Getter
public enum SuccessCode {
    SUCCESS_CREATED_ITEM(HttpStatus.OK, "상품이 등록헀습니다."),
    SUCCESS_MODIFIED_ITEM(HttpStatus.OK, "물품이 수정되었습니다."),
    SUCCESS_DELETED_ITEM(HttpStatus.OK, "물품을 삭제했습니다."),

    SUCCESS_CREATED_IMAGE(HttpStatus.OK, "이미지가 등록되었습니다."),
    SUCCESS_MODIFIED_IMAGE(HttpStatus.OK, "이미지가 수정되었습니다."),

    SUCCESS_CREATED_COMMENT(HttpStatus.OK, "댓글이 등록되었습니다."),
    SUCCESS_MODIFIED_COMMENT(HttpStatus.OK, "댓글이 수정되었습니다."),
    SUCCESS_DELETED_COMMENT(HttpStatus.OK, "댓글을 삭제했습니다."),

    SUCCESS_CREATED_REPLY(HttpStatus.OK, "댓글에 답변이 추가되었습니다."),

    SUCCESS_CREATED_PROPOSAL(HttpStatus.OK, "구매 제안이 등록되었습니다."),
    SUCCESS_MODIFIED_PROPOSAL(HttpStatus.OK, "제안이 수정되었습니다."),
    SUCCESS_DELETED_PROPOSAL(HttpStatus.OK, "제안을 삭제했습니다."),

    SUCCESS_MODIFIED_SALLER(HttpStatus.OK, "제안의 상태가 변경되었습니다."),
    SUCCESS_COMPLETED_SALLER(HttpStatus.OK, "구매가 확정되었습니다."),
    SUCCESS_FAILED_SALLER(HttpStatus.OK, "구매가 거절되었습니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
