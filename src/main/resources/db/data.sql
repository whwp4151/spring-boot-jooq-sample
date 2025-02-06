select 1;

-- 1-5번 데이터
INSERT INTO boards (id, title, content, created_at, updated_at) VALUES
                                                                    (nextval('boards_id_seq'), '공지사항: 시스템 점검 안내', '2024년 1월 1일 새벽 2시부터 4시까지 시스템 점검이 있을 예정입니다.', '2024-01-01 10:00:00', '2024-01-01 10:00:00'),
                                                                    (nextval('boards_id_seq'), '신규 기능 업데이트 안내', '새로운 기능이 추가되었습니다. 자세한 내용은 본문을 확인해주세요.', '2024-01-02 11:30:00', '2024-01-02 11:30:00'),
                                                                    (nextval('boards_id_seq'), '맛집 추천 부탁드려요', '회사 근처 맛집 추천 부탁드립니다!', '2024-01-03 12:15:00', '2024-01-03 12:15:00'),
                                                                    (nextval('boards_id_seq'), '로그인 오류 문의', '로그인 시 발생하는 오류에 대해 문의드립니다.', '2024-01-04 14:20:00', '2024-01-04 14:20:00'),
                                                                    (nextval('boards_id_seq'), '주말 등산 같이가실 분', '이번 주말 북한산 등산 같이 가실 분 구합니다.', '2024-01-05 15:45:00', '2024-01-05 15:45:00');

-- 6-10번 데이터
INSERT INTO boards (id, title, content, created_at, updated_at) VALUES
                                                                    (nextval('boards_id_seq'), '결제 시스템 문의', '결제 진행 중 오류가 발생했습니다.', '2024-01-06 16:30:00', '2024-01-06 16:30:00'),
                                                                    (nextval('boards_id_seq'), '서비스 이용약관 변경 안내', '2024년 2월 1일부터 서비스 이용약관이 변경됩니다.', '2024-01-07 09:00:00', '2024-01-07 09:00:00'),
                                                                    (nextval('boards_id_seq'), '신년 모임 후기', '지난 주말 진행된 신년 모임 후기입니다.', '2024-01-08 10:20:00', '2024-01-08 10:20:00'),
                                                                    (nextval('boards_id_seq'), '비밀번호 변경 방법', '비밀번호 변경은 어떻게 하나요?', '2024-01-09 11:40:00', '2024-01-09 11:40:00'),
                                                                    (nextval('boards_id_seq'), '주말 영화 추천', '이번 주말에 볼만한 영화 추천해주세요.', '2024-01-10 13:15:00', '2024-01-10 13:15:00');

-- 11-15번 데이터
INSERT INTO boards (id, title, content, created_at, updated_at) VALUES
                                                                    (nextval('boards_id_seq'), '개인정보 처리방침 개정', '개인정보 처리방침이 개정되었습니다.', '2024-01-11 14:30:00', '2024-01-11 14:30:00'),
                                                                    (nextval('boards_id_seq'), '새해 목표 공유', '2024년 새해 목표를 공유해봐요!', '2024-01-12 15:45:00', '2024-01-12 15:45:00'),
                                                                    (nextval('boards_id_seq'), '앱 설치 오류', '앱 설치 중 발생하는 오류 해결 방법 문의', '2024-01-13 16:20:00', '2024-01-13 16:20:00'),
                                                                    (nextval('boards_id_seq'), '주말 나들이 후기', '가족과 함께한 주말 나들이 후기입니다.', '2024-01-14 17:00:00', '2024-01-14 17:00:00'),
                                                                    (nextval('boards_id_seq'), '서버 점검 완료 안내', '서버 점검이 완료되었습니다.', '2024-01-15 09:30:00', '2024-01-15 09:30:00');

-- 16-20번 데이터
INSERT INTO boards (id, title, content, created_at, updated_at) VALUES
                                                                    (nextval('boards_id_seq'), '재택근무 팁 공유', '효율적인 재택근무를 위한 팁을 공유합니다.', '2024-01-16 10:45:00', '2024-01-16 10:45:00'),
                                                                    (nextval('boards_id_seq'), '환불 절차 문의', '상품 환불 절차에 대해 문의드립니다.', '2024-01-17 11:20:00', '2024-01-17 11:20:00'),
                                                                    (nextval('boards_id_seq'), '직장인 점심 추천', '회사 근처 점심 메뉴 추천합니다.', '2024-01-18 12:40:00', '2024-01-18 12:40:00'),
                                                                    (nextval('boards_id_seq'), '신규 이벤트 안내', '새해 맞이 특별 이벤트를 시작합니다.', '2024-01-19 13:15:00', '2024-01-19 13:15:00'),
                                                                    (nextval('boards_id_seq'), '취미 공유 모임', '같은 취미를 가진 분들과 모임을 가져요.', '2024-01-20 14:30:00', '2024-01-20 14:30:00');

-- 21-25번 데이터
INSERT INTO boards (id, title, content, created_at, updated_at) VALUES
                                                                    (nextval('boards_id_seq'), '포인트 적립 문의', '포인트가 정상적으로 적립되지 않았습니다.', '2024-01-21 15:45:00', '2024-01-21 15:45:00'),
                                                                    (nextval('boards_id_seq'), '독서모임 후기', '지난 주말 독서모임 후기입니다.', '2024-01-22 16:20:00', '2024-01-22 16:20:00'),
                                                                    (nextval('boards_id_seq'), '모바일 앱 업데이트', '모바일 앱 버전이 업데이트되었습니다.', '2024-01-23 17:00:00', '2024-01-23 17:00:00'),
                                                                    (nextval('boards_id_seq'), '주말 전시회 후기', '현대미술관 전시회 다녀왔습니다.', '2024-01-24 18:15:00', '2024-01-24 18:15:00'),
                                                                    (nextval('boards_id_seq'), '회원정보 수정', '회원정보 수정은 어떻게 하나요?', '2024-01-25 19:30:00', '2024-01-25 19:30:00');

-- 댓글 추가
INSERT INTO comments (id, board_id, author, content, created_at) VALUES
                                                                     (nextval('comments_id_seq'), 10000, 'Alice', '첫 번째 댓글입니다.', NOW()),
                                                                     (nextval('comments_id_seq'), 10000, 'Bob', '두 번째 댓글입니다.', NOW()),
                                                                     (nextval('comments_id_seq'), 10001, 'Charlie', '두 번째 게시글의 첫 번째 댓글입니다.', NOW());

-- 첨부 파일 추가
INSERT INTO attachments (id, board_id, file_url) VALUES
                                                     (nextval('attachments_id_seq'), 10000, 'https://example.com/file1.png'),
                                                     (nextval('attachments_id_seq'), 10000, 'https://example.com/file2.png'),
                                                     (nextval('attachments_id_seq'), 10001, 'https://example.com/file3.png');