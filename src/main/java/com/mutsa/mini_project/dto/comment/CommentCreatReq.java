package com.mutsa.mini_project.dto.comment;

import com.mutsa.mini_project.models.Comment;
import com.mutsa.mini_project.models.embedded.RequiredWriter;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreatReq {
    @NotEmpty
    private String writer;
    @NotEmpty
    private String password;
    @NotEmpty
    private String content;

    public static Comment toEntity(CommentCreatReq req) {
        return Comment.builder()
                .content(req.getContent())
                .requiredWriter(RequiredWriter.of(req.getWriter(), req.getPassword()))
                .build();
    }
}
