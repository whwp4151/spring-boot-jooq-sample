package com.jooq.project.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardPageResponseV3 {

    private Integer currentPage;

    private Integer currentElementCount;

    private Long totalElementCount;

    private Integer totalPages;

    private Boolean isLast;

    private List<BoardDtoV3> data;

    public static BoardPageResponseV3 of(List<BoardDtoV3> data, Pageable pageable, Long totalCount) {
        int totalPages = (int) Math.ceil((double) totalCount / pageable.getPageSize());
        boolean isLast = (long) (pageable.getPageNumber() + 1) * pageable.getPageSize() >= totalCount;

        return BoardPageResponseV3.builder()
                .currentPage(pageable.getPageNumber() + 1)
                .currentElementCount(data.size())
                .totalPages(totalPages)
                .totalElementCount(totalCount)
                .isLast(isLast)
                .data(data)
                .build();
    }

}
