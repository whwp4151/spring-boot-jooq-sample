package com.jooq.project.app.v3.service;

import com.jooq.project.dto.BoardPageRequest;
import com.jooq.project.dto.BoardPageResponseV3;
import com.jooq.project.jooq.BoardJooqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardServiceV3 {

    private final BoardJooqRepository boardJooqRepository;

    @Transactional(readOnly = true)
    public BoardPageResponseV3 searchMultisetBoard(BoardPageRequest boardPageRequest) {
        return boardJooqRepository.searchMultisetBoard(boardPageRequest);
    }
}
