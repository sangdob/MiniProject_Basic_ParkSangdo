package com.mutsa.mini_project.dto.comment;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentEditForm {
    @NotEmpty
    private String writer;
    @NotEmpty
    private String password;
    @NotEmpty
    private String content;
}
