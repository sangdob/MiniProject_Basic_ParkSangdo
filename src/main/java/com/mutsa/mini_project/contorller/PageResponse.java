package com.mutsa.mini_project.contorller;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PageResponse<T> {

    private List<T> content;
    private int totalPages;
    private Long totalElements;
    private boolean last;
    private int size;
    private int number;
    private int numberOfElements;
    private boolean first;
    private boolean empty;

    public PageResponse(Page<T> page) {
        this.content = page.getContent();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.last = page.isLast();
        this.size = page.getSize();
        this.number = page.getNumber() + 1;
        this.numberOfElements = page.getNumberOfElements();
        this.first = page.isFirst();
        this.empty = page.isEmpty();
    }

}
