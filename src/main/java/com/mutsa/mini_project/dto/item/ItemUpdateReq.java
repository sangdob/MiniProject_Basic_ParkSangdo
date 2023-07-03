package com.mutsa.mini_project.dto.item;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemUpdateReq {
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    @NotNull
    @Min(0)
    private int minPriceWanted;
    @NotEmpty
    private String writer;
    @NotEmpty
    private String password;
}
