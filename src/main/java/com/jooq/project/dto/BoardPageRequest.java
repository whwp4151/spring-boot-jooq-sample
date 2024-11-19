package com.jooq.project.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardPageRequest {

    @Builder.Default
    private int page = 0;

    @Builder.Default
    private int size = 10;

}
