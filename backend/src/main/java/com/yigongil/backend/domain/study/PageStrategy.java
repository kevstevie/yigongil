package com.yigongil.backend.domain.study;

import lombok.Getter;
import org.springframework.data.domain.Sort;

@Getter
public enum PageStrategy {

    ID_DESC(Constants.PAGE_SIZE, Sort.by("id").descending());

    private final int size;
    private final Sort sort;

    PageStrategy(int size, Sort sort) {
        this.size = size;
        this.sort = sort;
    }

    public static class Constants {

        public static final int PAGE_SIZE = 30;
    }
}