package com.jooq.project.app.v1.service;

import com.jooq.project.dto.BoardDto;
import com.jooq.project.dto.BoardPageRequest;
import com.jooq.project.dto.BoardPageResponse;
import com.jooq.project.jpa.entity.BoardJpaEntity;
import com.jooq.project.jpa.repository.BoardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardServiceV1 {

    private final BoardJpaRepository boardJpaRepository;

    @Transactional(readOnly = true)
    public BoardPageResponse searchBoardList(BoardPageRequest boardPageRequest) {
        PageRequest pageRequest = PageRequest.of(
                boardPageRequest.getPage(),
                boardPageRequest.getSize(),
                Sort.Direction.fromString(boardPageRequest.getDirection()),
                boardPageRequest.getSort()
        );

        Page<BoardJpaEntity> boardPage = boardJpaRepository.searchByKeyword(boardPageRequest.getSearch(), pageRequest);

        return BoardPageResponse.builder()
                .currentPage(boardPage.getPageable().getPageNumber())
                .currentElementCount(boardPage.getNumberOfElements())
                .totalPages(boardPage.getTotalPages())
                .totalElementCount(boardPage.getTotalElements())
                .isLast(boardPage.isLast())
                .data(boardPage.map(this::convertBoardDto).toList())
                .build();
    }

    @Transactional(readOnly = true)
    public BoardDto getBoard(long boardId) {
        return boardJpaRepository.findById(boardId)
                .map(this::convertBoardDto)
                .orElse(null);
    }

    @Transactional
    public boolean createBoard(BoardDto boardDto) {
        BoardJpaEntity boardJpaEntity = convertBoardEntity(boardDto);
        boardJpaRepository.save(boardJpaEntity);
        return true;
    }

    @Transactional
    public boolean updateBoard(long boardId, BoardDto boardDto) {
        return boardJpaRepository.findById(boardId)
                .map(boardJpaEntity -> {
                    boardJpaEntity.updateTitle(boardDto.getTitle());
                    boardJpaEntity.updateContent(boardDto.getContent());
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public boolean deleteBoard(long boardId) {
        return boardJpaRepository.findById(boardId)
                .map(boardJpaEntity -> {
                    boardJpaRepository.delete(boardJpaEntity);
                    return true;
                })
                .orElse(false);
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

    private BoardJpaEntity convertBoardEntity(BoardDto boardDto) {
        return BoardJpaEntity.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .build();
    }
}
