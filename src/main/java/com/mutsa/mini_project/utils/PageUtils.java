package com.mutsa.mini_project.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageUtils {
    public static boolean isOutOfRange(Pageable pageable) {
        return pageable.getPageNumber() < 1;
    }

    public static PageRequest of(Pageable pageable) {
        return PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize(), pageable.getSort());
    }
}
