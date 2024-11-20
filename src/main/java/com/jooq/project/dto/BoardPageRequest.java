package com.jooq.project.dto;

import lombok.*;
import org.springframework.util.StringUtils;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardPageRequest {

    private int page;

    private int size;

    private String search;

    private String sort = "createdAt";

    private String direction = "desc";

    public void validate() {
        if (page < 0) {
            throw new IllegalArgumentException("Page index must not be less than zero!");
        }
        if (size < 1) {
            throw new IllegalArgumentException("Page size must not be less than one!");
        }
        if (size > 100) {
            throw new IllegalArgumentException("Page size must not be greater than 100!");
        }
        if (!StringUtils.hasText(sort)) {
            throw new IllegalArgumentException("Sort must not be null!");
        }
        if (!StringUtils.hasText(direction)) {
            throw new IllegalArgumentException("Direction must not be null!");
        }
    }

}
