package com.mutsa.mini_project.dto.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mutsa.mini_project.models.status.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

@Data
@JsonInclude(NON_NULL)
public class ItemDetailRes {
    private String title;
    private String description;
    private int minPriceWanted;
    private String imageUrl;
    private String status;

    public ItemDetailRes(String title, String description, int minPriceWanted, String imageUrl, ItemStatus status) {
        this.title = title;
        this.description = description;
        this.minPriceWanted = minPriceWanted;
        this.imageUrl = imageUrl;
        this.status = status.getName();
    }
}
