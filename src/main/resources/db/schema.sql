-- 시퀀스 생성
-- boards_id_seq 시퀀스가 없다면 생성
CREATE SEQUENCE IF NOT EXISTS boards_id_seq START WITH 10000;

-- 게시판 테이블 생성
CREATE TABLE boards
(
    id         BIGINT       NOT NULL DEFAULT nextval('boards_id_seq'),
    title      VARCHAR(255) NOT NULL,
    content    TEXT         NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP    NOT NULL,

    CONSTRAINT boards_pkey PRIMARY KEY (id)
);

-- 시퀀스 생성
-- boards_id_seq 시퀀스가 없다면 생성
CREATE SEQUENCE IF NOT EXISTS comments_id_seq START WITH 10000;

-- 댓글 테이블
CREATE TABLE comments
(
    id         BIGINT       NOT NULL DEFAULT nextval('comments_id_seq'),
    board_id   BIGINT       NOT NULL,
    author     VARCHAR(255) NOT NULL,
    content    TEXT         NOT NULL,
    created_at TIMESTAMP    NOT NULL DEFAULT NOW(),

    CONSTRAINT comments_pkey PRIMARY KEY (id)
);

-- 시퀀스 생성
-- boards_id_seq 시퀀스가 없다면 생성
CREATE SEQUENCE IF NOT EXISTS attachments_id_seq START WITH 10000;

-- 첨부 파일 테이블
CREATE TABLE attachments
(
    id       BIGINT       NOT NULL DEFAULT nextval('attachments_id_seq'),
    board_id BIGINT       NOT NULL,
    file_url VARCHAR(500) NOT NULL,

    CONSTRAINT attachments_pkey PRIMARY KEY (id)
);
