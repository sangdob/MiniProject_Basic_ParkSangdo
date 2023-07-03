package com.mutsa.mini_project.dto.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mutsa.mini_project.models.status.ItemStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

@Data
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class ItemRes {
    private Long id;
    private String title;
    private String description;
    private int minPriceWanted;
    private String imageUrl;
    private ItemStatus status;
}
