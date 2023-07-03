package com.mutsa.mini_project.dto.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemDeleteForm {
    private String writer;
    private String password;

    protected ItemDeleteForm(String writer, String password){
        this.writer = writer;
        this.password = password;
    }

    public static ItemDeleteForm of(String writer, String password){
        return new ItemDeleteForm(writer, password);
    }
}
