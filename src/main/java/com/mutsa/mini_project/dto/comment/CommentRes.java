package com.mutsa.mini_project.dto.comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mutsa.mini_project.models.Comment;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
