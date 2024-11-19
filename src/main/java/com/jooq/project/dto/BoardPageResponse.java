package com.jooq.project.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardPageResponse {

    private Integer currentPage;

    private Integer currentElementCount;

    private Long totalElementCount;

    private Integer totalPages;

    private Boolean isLast;

    private List<BoardDto> data;

}
