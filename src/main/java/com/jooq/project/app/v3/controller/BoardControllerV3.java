package com.jooq.project.app.v3.controller;

import com.jooq.project.app.v3.service.BoardServiceV3;
import com.jooq.project.dto.BoardPageRequest;
import com.jooq.project.dto.BoardPageResponseV3;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v3/boards")
public class BoardControllerV3 {

    private final BoardServiceV3 boardService;

    @GetMapping("/multiset/search")
    public ResponseEntity<BoardPageResponseV3> searchMultisetBoard(BoardPageRequest boardPageRequest) {
        boardPageRequest.validate();
        BoardPageResponseV3 boardPageResponse = boardService.searchMultisetBoard(boardPageRequest);
        return ResponseEntity.ok(boardPageResponse);
    }

}
