package com.jooq.project.app.v2.service;

import com.jooq.project.dto.BoardDto;
import com.jooq.project.dto.BoardPageRequest;
import com.jooq.project.dto.BoardPageResponse;
import com.jooq.project.jooq.BoardJooqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardServiceV2 {

    private final BoardJooqRepository boardJooqRepository;

    @Transactional(readOnly = true)
    public BoardPageResponse searchBoardList(BoardPageRequest boardPageRequest) {
        return boardJooqRepository.searchBoardList(boardPageRequest);
    }

    @Transactional(readOnly = true)
    public BoardDto getBoard(long boardId) {
        return boardJooqRepository.getBoard(boardId);
    }

    @Transactional
    public boolean createBoard(BoardDto boardDto) {
        boardJooqRepository.createBoard(boardDto);
        return true;
    }

    @Transactional
    public boolean updateBoard(long boardId, BoardDto boardDto) {
        return boardJooqRepository.updateBoard(boardId, boardDto);
    }

    @Transactional
    public boolean deleteBoard(long boardId) {
        return boardJooqRepository.deleteBoard(boardId);
    }

}
