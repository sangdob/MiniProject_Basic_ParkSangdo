package com.mutsa.mini_project.models.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemStatus {
    SALE("판매 중"), SOLD_OUT("판매 완료"),
    ;

    private final String name;
}
