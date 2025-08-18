물론입니다! 아래는 **MySQL Cheat Sheet**로, 자주 사용하는 명령어와 개념들을 **카테고리별로 정리**한 자료입니다. 학습과 실무에 모두 도움이 되도록 구성했습니다.

---

## 🧩 1. 기본 명령어

|명령어|설명|
|---|---|
|`mysql -u [사용자] -p`|MySQL 접속 (비밀번호 입력)|
|`SHOW DATABASES;`|전체 데이터베이스 목록 조회|
|`USE [데이터베이스];`|데이터베이스 선택|
|`SHOW TABLES;`|현재 DB의 테이블 목록 조회|
|`DESCRIBE [테이블명];`|테이블 구조 확인|
|`EXIT;` 또는 `QUIT;`|MySQL 종료|

---

## 🛠️ 2. 데이터베이스 관리

|명령어|설명|
|---|---|
|`CREATE DATABASE [이름];`|새 DB 생성|
|`DROP DATABASE [이름];`|DB 삭제|
|`ALTER DATABASE [이름] CHARACTER SET utf8mb4;`|문자 인코딩 변경|

---

## 📦 3. 테이블 관리

|명령어|설명|
|---|---|
|`CREATE TABLE [이름] (...);`|테이블 생성|
|`DROP TABLE [이름];`|테이블 삭제|
|`RENAME TABLE A TO B;`|테이블 이름 변경|
|`TRUNCATE TABLE [이름];`|테이블 비우기 (데이터만 삭제)|
|`ALTER TABLE [이름] ADD COLUMN [컬럼명] [타입];`|컬럼 추가|
|`ALTER TABLE [이름] DROP COLUMN [컬럼명];`|컬럼 삭제|
|`ALTER TABLE [이름] MODIFY COLUMN [컬럼명] [새 타입];`|컬럼 타입 수정|

---

## 📄 4. 데이터 타입

|타입|설명|
|---|---|
|`INT`, `BIGINT`|정수|
|`FLOAT`, `DOUBLE`, `DECIMAL(10,2)`|실수|
|`CHAR(n)`, `VARCHAR(n)`|고정/가변 길이 문자열|
|`TEXT`, `BLOB`|긴 문자열 / 바이너리|
|`DATE`, `DATETIME`, `TIMESTAMP`|날짜/시간|

---

## ✍️ 5. 데이터 조작 (CRUD)

### 🔹 INSERT

sql

복사편집

`INSERT INTO 테이블명 (컬럼1, 컬럼2) VALUES (값1, 값2);`

### 🔹 SELECT

sql

복사편집

`SELECT * FROM 테이블명; SELECT 컬럼1, 컬럼2 FROM 테이블명 WHERE 조건; SELECT DISTINCT 컬럼명 FROM 테이블명; SELECT COUNT(*) FROM 테이블명;`

### 🔹 UPDATE

sql

복사편집

`UPDATE 테이블명 SET 컬럼1 = 값1 WHERE 조건;`

### 🔹 DELETE

sql

복사편집

`DELETE FROM 테이블명 WHERE 조건;`

---

## 🔎 6. 조건 / 정렬 / 그룹핑

|구문|설명|
|---|---|
|`WHERE`|조건문|
|`AND`, `OR`, `NOT`|논리 연산자|
|`ORDER BY 컬럼 ASC/DESC`|정렬|
|`LIMIT 10 OFFSET 0`|페이징|
|`GROUP BY`|그룹화|
|`HAVING`|그룹 조건 필터링|

---

## 🧮 7. 함수

### 🔸 집계 함수

sql

복사편집

`COUNT(), SUM(), AVG(), MAX(), MIN()`

### 🔸 문자열 함수

sql

복사편집

`CONCAT(), SUBSTRING(), LENGTH(), REPLACE()`

### 🔸 날짜 함수

sql

복사편집

`NOW(), CURDATE(), DATE_ADD(), DATEDIFF()`

---

## 🔗 8. JOIN

|종류|설명|
|---|---|
|`INNER JOIN`|두 테이블 모두에 존재하는 데이터|
|`LEFT JOIN`|왼쪽 테이블은 모두, 오른쪽은 매칭되는 것만|
|`RIGHT JOIN`|오른쪽 테이블은 모두, 왼쪽은 매칭되는 것만|
|`FULL OUTER JOIN`|MySQL은 직접 지원 X → UNION으로 구현|

### 예시

sql

복사편집

`SELECT A.*, B.* FROM A INNER JOIN B ON A.id = B.a_id;`

---

## 🔐 9. 사용자 & 권한

|명령어|설명|
|---|---|
|`CREATE USER 'user'@'localhost' IDENTIFIED BY 'pw';`|사용자 생성|
|`GRANT ALL PRIVILEGES ON db.* TO 'user'@'localhost';`|권한 부여|
|`FLUSH PRIVILEGES;`|권한 적용|
|`DROP USER 'user'@'localhost';`|사용자 삭제|

---

## 💾 10. 백업 & 복원

|명령어|설명|
|---|---|
|`mysqldump -u root -p dbname > backup.sql`|백업|
|`mysql -u root -p dbname < backup.sql`|복원|

---

## 🧠 11. 기타 팁

|내용|설명|
|---|---|
|`SHOW PROCESSLIST;`|실행 중 쿼리 확인|
|`SHOW STATUS;`|서버 상태 정보|
|`SHOW VARIABLES;`|설정 변수 조회|
|`EXPLAIN SELECT ...;`|쿼리 실행 계획 보기 (성능 분석)|

---

필요하시면 **PDF 형식으로 저장**, 또는 **특정 카테고리만 더 자세히** 작성해드릴 수도 있어요. 원하시나요?