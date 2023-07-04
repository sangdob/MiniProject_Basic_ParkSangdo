package com.mutsa.mini_project.dto.comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mutsa.mini_project.models.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CommentRes {
    private Long id;
    private String content;
    private String reply;

    public static CommentRes of(Comment comment) {
        return new CommentRes(comment.getId(),
                comment.getContent(),
                comment.getReply());
    }
}
