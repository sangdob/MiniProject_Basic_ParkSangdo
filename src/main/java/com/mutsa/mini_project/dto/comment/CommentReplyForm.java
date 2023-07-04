package com.mutsa.mini_project.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentReplyForm {
    private String writer;
    private String password;
    private String reply;
}
