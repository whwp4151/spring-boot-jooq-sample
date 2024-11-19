package com.jooq.project.app.v1.service;

import com.jooq.project.dto.BoardDto;
import com.jooq.project.dto.BoardPageRequest;
import com.jooq.project.dto.BoardPageResponse;
import com.jooq.project.jpa.entity.BoardJpaEntity;
import com.jooq.project.jpa.repository.BoardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceV1 {

    private final BoardJpaRepository boardJpaRepository;

    public BoardPageResponse searchBoardList(BoardPageRequest boardPageRequest) {
        PageRequest pageRequest = PageRequest.of(boardPageRequest.getPage(), boardPageRequest.getSize());

        Page<BoardJpaEntity> boardPage = boardJpaRepository.findAll(pageRequest);

        return BoardPageResponse.builder()
                .currentPage(boardPage.getPageable().getPageNumber())
                .currentElementCount(boardPage.getNumberOfElements())
                .totalPages(boardPage.getTotalPages())
                .totalElementCount(boardPage.getTotalElements())
                .isLast(boardPage.isLast())
                .data(boardPage.map(this::convertBoardDto).toList())
                .build();
    }

    public BoardDto getBoard(long boardId) {
        return boardJpaRepository.findById(boardId)
                .map(this::convertBoardDto)
                .orElse(null);
    }

    private BoardDto convertBoardDto(BoardJpaEntity boardJpaEntity) {
        return BoardDto.builder()
                .boardId(boardJpaEntity.getId())
                .title(boardJpaEntity.getTitle())
                .content(boardJpaEntity.getContent())
                .createdAt(boardJpaEntity.getCreatedAt())
                .updatedAt(boardJpaEntity.getUpdatedAt())
                .build();
    }

}
