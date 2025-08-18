-- 1. 데이터베이스 생성 및 사용
DROP DATABASE IF EXISTS kosa;
CREATE DATABASE kosa DEFAULT CHARACTER SET utf8mb4;
USE kosa;

-- 2. 테이블 생성

CREATE TABLE member (
  mem_id INT PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL,
  nickname VARCHAR(50) NOT NULL,
  gender ENUM('M', 'F') NOT NULL,
  profile_img VARCHAR(255),
  goal_lv INT DEFAULT 0,
  goal_name VARCHAR(100),
  goal_exp INT DEFAULT 0
);

CREATE TABLE todo (
  todo_id INT PRIMARY KEY AUTO_INCREMENT,
  todo_done BOOLEAN DEFAULT FALSE,
  todo_content VARCHAR(255) NOT NULL,
  todo_date DATE NOT NULL,
  todo_important BOOLEAN DEFAULT FALSE,
  mem_id INT,
  FOREIGN KEY (mem_id) REFERENCES member(mem_id) ON DELETE CASCADE
);

CREATE TABLE account (
  acc_id INT PRIMARY KEY AUTO_INCREMENT,
  acc_date DATE NOT NULL,
  acc_income BOOLEAN DEFAULT FALSE,
  acc_category VARCHAR(50) NOT NULL,
  acc_amount BIGINT UNSIGNED NOT NULL,
  acc_desc VARCHAR(255),
  acc_payment VARCHAR(50),
  acc_etc VARCHAR(255),
  mem_id INT,
  FOREIGN KEY (mem_id) REFERENCES member(mem_id) ON DELETE CASCADE
);

CREATE TABLE health (
  heal_id INT PRIMARY KEY AUTO_INCREMENT,
  heal_name VARCHAR(100) NOT NULL,
  heal_amount INT DEFAULT 0,
  heal_done BOOLEAN DEFAULT FALSE,
  mem_id INT,
  FOREIGN KEY (mem_id) REFERENCES member(mem_id) ON DELETE CASCADE
);

CREATE TABLE note (
  note_id INT PRIMARY KEY AUTO_INCREMENT,
  note_name VARCHAR(100) NOT NULL,
  note_content TEXT,
  note_important BOOLEAN DEFAULT FALSE,
  mem_id INT,
  FOREIGN KEY (mem_id) REFERENCES member(mem_id) ON DELETE CASCADE
);

CREATE TABLE friend (
  mem1_id INT,
  mem2_id INT,
  PRIMARY KEY (mem1_id, mem2_id),
  FOREIGN KEY (mem1_id) REFERENCES member(mem_id) ON DELETE CASCADE,
  FOREIGN KEY (mem2_id) REFERENCES member(mem_id) ON DELETE CASCADE
);

CREATE TABLE target_account (
  mem_id INT,
  month INT,
  target_acc BIGINT UNSIGNED,
  PRIMARY KEY (mem_id, month),
  FOREIGN KEY (mem_id) REFERENCES member(mem_id) ON DELETE CASCADE
);

-- 개인 건강정보
CREATE TABLE p_info (
    mem_id INT PRIMARY KEY,
    height DECIMAL(5, 2),
    weight DECIMAL(5, 2),
    FOREIGN KEY (mem_id) REFERENCES member(mem_id) ON DELETE CASCADE
);

-- 3. 더미 데이터 삽입


-- member
DESC member;
INSERT INTO member VALUES (1, 'alice@example.com', 'alice1234', 'Alice', 'F', 'profile1.jpg', 2, '헬스 Lv2', 150);
INSERT INTO member VALUES (2, 'bob@example.com', 'bob5678', 'Bob', 'M', 'profile2.jpg', 3, '독서 Lv3', 300);
INSERT INTO member VALUES (3, 'charlie@example.com', 'charlie999', 'Charlie', 'M', 'profile3.jpg', 1, '식단 Lv1', 50);

SELECT * FROM member;

-- todo

INSERT INTO todo VALUES (101, FALSE, '운동 30분 하기', '2025-07-23', TRUE, 1);
INSERT INTO todo VALUES (102, TRUE, '장보기', '2025-07-22', FALSE, 1);
INSERT INTO todo VALUES (103, FALSE, '책 1장 읽기', '2025-07-23', TRUE, 2);
INSERT INTO todo VALUES (104, FALSE, '일기 쓰기', '2025-07-21', FALSE, 3);


-- account

INSERT INTO account VALUES (201, '2025-07-22', FALSE, '식비', 12000, '편의점', '카드', '', 1);
INSERT INTO account VALUES (202, '2025-07-21', TRUE, '월급', 2500000, '7월 급여', '이체', '', 1);
INSERT INTO account VALUES (203, '2025-07-20', FALSE, '문화생활', 8000, '영화 관람', '카드', '', 2);


-- health

INSERT INTO health VALUES (301, '물 마시기', 5, TRUE, 1);
INSERT INTO health VALUES (302, '걷기', 10000, FALSE, 2);
INSERT INTO health VALUES (303, '스트레칭', 3, TRUE, 1);


-- note

INSERT INTO note VALUES (401, '아이디어 정리', '프로젝트 아이디어 메모', TRUE, 1);
INSERT INTO note VALUES (402, '헬스 계획', '이번주 운동 루틴 정리', FALSE, 1);
INSERT INTO note VALUES (403, '독서노트', '책 내용 요약', TRUE, 2);


-- friend

INSERT INTO friend VALUES (1, 2);
INSERT INTO friend VALUES (1, 3);
INSERT INTO friend VALUES (2, 3);


-- target_account

INSERT INTO target_account VALUES (1, 2025, 7, 300000);
INSERT INTO target_account VALUES (2, 2025, 7, 500000);
INSERT INTO target_account VALUES (3, 2025, 7, 200000);



-- 추가 더미 데이터 (4개씩)

INSERT INTO member VALUES (4, 'diana@example.com', 'diana123', 'Diana', 'F', 'profile4.jpg', 2, '요가 Lv2', 120);
INSERT INTO member VALUES (5, 'edward@example.com', 'edward567', 'Edward', 'M', 'profile5.jpg', 4, '러닝 Lv4', 400);
INSERT INTO member VALUES (6, 'fiona@example.com', 'fiona789', 'Fiona', 'F', 'profile6.jpg', 1, '명상 Lv1', 30);
INSERT INTO member VALUES (7, 'george@example.com', 'george000', 'George', 'M', 'profile7.jpg', 3, '자전거 Lv3', 280);
INSERT INTO todo VALUES (105, FALSE, '명상 10분', '2025-07-24', TRUE, 4);
INSERT INTO todo VALUES (106, TRUE, '러닝화 구매', '2025-07-22', FALSE, 5);
INSERT INTO todo VALUES (107, FALSE, '일기 쓰기', '2025-07-23', TRUE, 6);
INSERT INTO todo VALUES (108, TRUE, '자전거 타기', '2025-07-25', TRUE, 7);
INSERT INTO account VALUES (204, '2025-07-19', FALSE, '카페', 4500, '커피', '카드', '', 4);
INSERT INTO account VALUES (205, '2025-07-18', TRUE, '용돈', 100000, '엄마에게 받음', '현금', '', 5);
INSERT INTO account VALUES (206, '2025-07-17', FALSE, '쇼핑', 22000, '운동복 구매', '카드', '', 6);
INSERT INTO account VALUES (207, '2025-07-16', FALSE, '자전거 부품', 35000, '안장 교체', '카드', '', 7);
INSERT INTO health VALUES (304, '숨쉬기 운동', 10, TRUE, 4);
INSERT INTO health VALUES (305, '달리기', 8000, FALSE, 5);
INSERT INTO health VALUES (306, '요가', 30, TRUE, 6);
INSERT INTO health VALUES (307, '사이클', 45, TRUE, 7);
INSERT INTO note VALUES (404, '러닝 후기', '첫 러닝 기록', FALSE, 5);
INSERT INTO note VALUES (405, '명상 느낌', '조용한 시간의 중요성', TRUE, 6);
INSERT INTO note VALUES (406, '식단 정리', '오늘 먹은 음식 목록', FALSE, 4);
INSERT INTO note VALUES (407, '자전거 일지', '오늘 코스 좋았음', TRUE, 7);
INSERT INTO friend VALUES (4, 5);
INSERT INTO friend VALUES (4, 6);
INSERT INTO friend VALUES (4, 7);
INSERT INTO friend VALUES (5, 7);
INSERT INTO target_account VALUES (4, 7, 250000);
INSERT INTO target_account VALUES (5, 7, 450000);
INSERT INTO target_account VALUES (6, 7, 150000);
INSERT INTO target_account VALUES (7, 7, 350000);