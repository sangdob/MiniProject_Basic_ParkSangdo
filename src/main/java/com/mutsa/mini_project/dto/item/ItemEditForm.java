package com.mutsa.mini_project.dto.item;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemEditForm {
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    @Min(0)
    private int minPriceWanted;
    @NotEmpty
    private String writer;
    @NotEmpty
    private String password;
}
