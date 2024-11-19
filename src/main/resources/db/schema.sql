-- 시퀀스 생성
-- boards_id_seq 시퀀스가 없다면 생성
CREATE SEQUENCE IF NOT EXISTS boards_id_seq START WITH 10000;

-- 게시판 테이블 생성
CREATE TABLE boards
(
    id                BIGINT       NOT NULL DEFAULT nextval('boards_id_seq'),
    title             VARCHAR(255) NOT NULL,
    content           TEXT         NOT NULL,
    created_at        TIMESTAMP    NOT NULL,
    updated_at        TIMESTAMP    NOT NULL,

    CONSTRAINT boards_pkey PRIMARY KEY (id)
);