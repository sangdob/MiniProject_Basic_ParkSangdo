package com.mutsa.mini_project.dto.item;

import com.mutsa.mini_project.models.SalesItem;
import com.mutsa.mini_project.models.embedded.RequiredWriter;
import com.mutsa.mini_project.models.status.ItemStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCreateReq {
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    @NotNull
    @Min(1)
    private Integer minPriceWanted;
    @NotEmpty
    private String writer;
    @NotEmpty
    private String password;

    public static SalesItem toEntity(final ItemCreateReq req) {
        return SalesItem.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .minPriceWanted(req.getMinPriceWanted())
                .requiredWriter(new RequiredWriter(req.getWriter()
                        , req.getPassword()))
                .status(ItemStatus.SALE)
                .build();
    }
}
