package com.jooq.project.jooq;

import com.jooq.project.dto.AttachmentDto;
import com.jooq.project.dto.BoardDto;
import com.jooq.project.dto.BoardDtoV3;
import com.jooq.project.dto.BoardPageRequest;
import com.jooq.project.dto.BoardPageResponse;
import com.jooq.project.dto.BoardPageResponseV3;
import com.jooq.project.dto.CommentDto;
import com.jooq.project.generated.tables.daos.BoardsDao;
import com.jooq.project.generated.tables.pojos.BoardsPojo;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.jooq.project.generated.Tables.*;

@Repository
public class BoardJooqRepository {

    private final DSLContext dslContext;

    private final BoardsDao boardsDao;

    public BoardJooqRepository(Configuration configuration, DSLContext dslContext) {
        this.dslContext = dslContext;
        this.boardsDao = new BoardsDao(configuration);
    }

    public BoardPageResponse searchBoardList(BoardPageRequest boardPageRequest) {
        Pageable pageable = boardPageRequest.toPageable();

        // 검색 조건 설정
        Condition searchCondition = createSearchCondition(boardPageRequest.getSearch());

        // 정렬 설정
        SortField<?> sortField = createSortField(boardPageRequest.getSort(), boardPageRequest.getDirection());

        // 전체 카운트 조회
        int totalCount = dslContext
                .fetchCount(BOARDS.where(searchCondition));

        // 페이지네이션 데이터 조회
        List<BoardDto> boardList = dslContext
                .selectFrom(BOARDS)
                .where(searchCondition)
                .orderBy(sortField)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchInto(BoardsPojo.class)
                .stream()
                .map(this::convertBoardDto)
                .toList();

        // 총 페이지 수 계산
        int totalPages = (int) Math.ceil((double) totalCount / boardPageRequest.getSize());

        return BoardPageResponse.builder()
                .currentPage(boardPageRequest.getPage())
                .currentElementCount(boardList.size())
                .totalPages(totalPages)
                .totalElementCount((long) totalCount)
                .isLast(boardPageRequest.getPage() >= totalPages - 1)
                .data(boardList)
                .build();
    }

    private Condition createSearchCondition(String search) {
        return StringUtils.hasText(search)
                ? BOARDS.TITLE.containsIgnoreCase(search)
                .or(BOARDS.CONTENT.containsIgnoreCase(search))
                : DSL.trueCondition();
    }

    private SortField<?> createSortField(String sort, String direction) {
        Field<?> field = switch (sort) {
            case "title" -> BOARDS.TITLE;
            case "content" -> BOARDS.CONTENT;
            default -> BOARDS.CREATED_AT;
        };

        return direction.equals("asc") ? field.asc() : field.desc();
    }

    public BoardDto getBoard(Long boardId) {
        return boardsDao.fetchOptionalByJId(boardId)
                .map(this::convertBoardDto)
                .orElse(null);
    }

    public BoardDto getBoardWithDSL(Long boardId) {
        return dslContext.selectFrom(BOARDS)
                .where(BOARDS.ID.eq(boardId))
                .fetchOptionalInto(BoardsPojo.class)
                .map(this::convertBoardDto)
                .orElse(null);
    }

    private BoardDto convertBoardDto(BoardsPojo boardsPojo) {
        return BoardDto.builder()
                .boardId(boardsPojo.getId())
                .title(boardsPojo.getTitle())
                .content(boardsPojo.getContent())
                .createdAt(boardsPojo.getCreatedAt())
                .updatedAt(boardsPojo.getUpdatedAt())
                .build();
    }

    public void createBoard(BoardDto boardDto) {
        BoardsPojo pojo = new BoardsPojo();
        pojo.setTitle(boardDto.getTitle());
        pojo.setContent(boardDto.getContent());
        pojo.setCreatedAt(LocalDateTime.now());
        pojo.setUpdatedAt(LocalDateTime.now());

        boardsDao.insert(pojo);
    }

    public void createBoardWithDSL(BoardDto boardDto) {
        dslContext.insertInto(BOARDS)
                .set(BOARDS.TITLE, boardDto.getTitle())
                .set(BOARDS.CONTENT, boardDto.getContent())
                .set(BOARDS.CREATED_AT, LocalDateTime.now())
                .set(BOARDS.UPDATED_AT, LocalDateTime.now())
                .execute();
    }

    public boolean updateBoard(long boardId, BoardDto boardDto) {
        return boardsDao.fetchOptionalByJId(boardId)
                .map(pojo -> {
                    pojo.setTitle(boardDto.getTitle());
                    pojo.setContent(boardDto.getContent());
                    pojo.setUpdatedAt(LocalDateTime.now());

                    boardsDao.update(pojo);
                    return true;
                })
                .orElse(false);
    }

    public boolean updateBoardWithDSL(long boardId, BoardDto boardDto) {
        int updatedRows = dslContext.update(BOARDS)
                .set(BOARDS.TITLE, boardDto.getTitle())
                .set(BOARDS.CONTENT, boardDto.getContent())
                .set(BOARDS.UPDATED_AT, LocalDateTime.now())
                .where(BOARDS.ID.eq(boardId))
                .execute();
        return updatedRows > 0;
    }

    public boolean deleteBoard(long boardId) {
        return boardsDao.fetchOptionalByJId(boardId)
                .map(pojo -> {
                    boardsDao.delete(pojo);
                    return true;
                })
                .orElse(false);
    }

    public boolean deleteBoardWithDSL(long boardId) {
        int deletedRows = dslContext.deleteFrom(BOARDS)
                .where(BOARDS.ID.eq(boardId))
                .execute();
        return deletedRows > 0;
    }

    public BoardPageResponseV3 searchMultisetBoard(BoardPageRequest boardPageRequest) {
        Pageable pageable = boardPageRequest.toPageable();

        // 검색 조건 설정
        Condition searchCondition = createSearchCondition(boardPageRequest.getSearch());

        // 정렬 설정
        SortField<?> sortField = createSortField(boardPageRequest.getSort(), boardPageRequest.getDirection());

        // 전체 카운트 조회
        Long totalCount = dslContext
                .selectCount()
                .from(BOARDS)
                .where(searchCondition)
                .fetchOneInto(Long.class);

        // 페이지네이션 데이터 조회
        List<BoardDtoV3> boardList = dslContext
                .select(
                        BOARDS.ID,
                        BOARDS.TITLE,
                        BOARDS.CONTENT,
                        BOARDS.CREATED_AT,
                        BOARDS.UPDATED_AT,

                        // 댓글
                        DSL.multiset(
                                        dslContext.select(
                                                        COMMENTS.ID,
                                                        COMMENTS.AUTHOR,
                                                        COMMENTS.CONTENT,
                                                        COMMENTS.CREATED_AT
                                                )
                                                .from(COMMENTS)
                                                .where(COMMENTS.BOARD_ID.eq(BOARDS.ID))
                                                .orderBy(COMMENTS.CREATED_AT)
                                )
                                .convertFrom(r -> r.into(CommentDto.class))
                                .as("comments"),

                        // 첨부파일
                        DSL.multiset(
                                        dslContext.select(
                                                        ATTACHMENTS.ID,
                                                        ATTACHMENTS.FILE_URL
                                                )
                                                .from(ATTACHMENTS)
                                                .where(ATTACHMENTS.BOARD_ID.eq(BOARDS.ID))
                                                .orderBy(ATTACHMENTS.ID.desc())
                                )
                                .convertFrom(r -> r.into(AttachmentDto.class))
                                .as("attachments")
                )
                .from(BOARDS)
                .where(searchCondition)
                .orderBy(sortField)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchInto(BoardDtoV3.class);

        return BoardPageResponseV3.of(boardList, pageable, totalCount);
    }

}
