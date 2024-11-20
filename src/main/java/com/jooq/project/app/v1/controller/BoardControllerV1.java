package com.jooq.project.app.v1.controller;

import com.jooq.project.app.v1.service.BoardServiceV1;
import com.jooq.project.dto.BoardDto;
import com.jooq.project.dto.BoardPageRequest;
import com.jooq.project.dto.BoardPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
public class BoardControllerV1 {

    private final BoardServiceV1 boardService;

    @GetMapping("/search")
    public ResponseEntity<BoardPageResponse> searchBoardList(BoardPageRequest boardPageRequest) {
        boardPageRequest.validate();
        BoardPageResponse boardPageResponse = boardService.searchBoardList(boardPageRequest);
        return ResponseEntity.ok(boardPageResponse);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardDto> getBoard(@PathVariable("boardId") long boardId) {
        BoardDto boardDto = boardService.getBoard(boardId);
        return ResponseEntity.ok(boardDto);
    }

    @PostMapping
    public ResponseEntity<Boolean> createBoard(@RequestBody BoardDto boardDto) {
        boolean result = boardService.createBoard(boardDto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("{boardId}")
    public ResponseEntity<Boolean> updateBoard(@PathVariable("boardId") long boardId, @RequestBody BoardDto boardDto) {
        boolean result = boardService.updateBoard(boardId, boardDto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("{boardId}")
    public ResponseEntity<Boolean> deleteBoard(@PathVariable("boardId") long boardId) {
        boolean result = boardService.deleteBoard(boardId);
        return ResponseEntity.ok(result);
    }

}
