package com.jooq.project.app.v1.controller;

import com.jooq.project.app.v1.service.BoardServiceV1;
import com.jooq.project.dto.BoardDto;
import com.jooq.project.dto.BoardPageRequest;
import com.jooq.project.dto.BoardPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardControllerV1 {

    private final BoardServiceV1 boardService;

    @GetMapping("/search")
    public ResponseEntity<BoardPageResponse> searchBoardList(BoardPageRequest boardPageRequest) {
        BoardPageResponse boardPageResponse = boardService.searchBoardList(boardPageRequest);
        return ResponseEntity.ok(boardPageResponse);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardDto> getBoard(@PathVariable("boardId") long boardId) {
        BoardDto boardDto = boardService.getBoard(boardId);
        return ResponseEntity.ok(boardDto);
    }

}
