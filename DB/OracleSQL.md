#OracleSQL
## 📘 Oracle SQL Cheat Sheet

### 🔹 기본 SELECT 구문

```sql
SELECT column1, column2
FROM table_name
WHERE condition
ORDER BY column1 [ASC|DESC];
```

- `ROWNUM`: 결과의 행 수 제한
    

```sql
SELECT * FROM employees WHERE ROWNUM <= 10;
```

---

### 🔹 데이터 정의어 (DDL)

|명령어|설명|
|:--|:--|
|`CREATE TABLE`|테이블 생성|
|`ALTER TABLE`|테이블 구조 수정|
|`DROP TABLE`|테이블 삭제|
|`TRUNCATE TABLE`|모든 데이터 삭제 (롤백 불가)|

```sql
CREATE TABLE employees (
  emp_id NUMBER PRIMARY KEY,
  name VARCHAR2(100),
  hire_date DATE,
  salary NUMBER(10,2)
);
```

---

### 🔹 데이터 조작어 (DML)

```sql
INSERT INTO table_name (col1, col2) VALUES (val1, val2);
UPDATE table_name SET col1 = val1 WHERE condition;
DELETE FROM table_name WHERE condition;
```

---

### 🔹 조건절

```sql
-- 비교
WHERE salary > 5000 AND department = 'HR';

-- IN / BETWEEN / LIKE
WHERE dept IN ('HR', 'SALES');
WHERE hire_date BETWEEN TO_DATE('2022-01-01','YYYY-MM-DD') AND SYSDATE;
WHERE name LIKE 'J%';
```

---

### 🔹 Oracle 날짜 함수

|함수|설명|
|:--|:--|
|`SYSDATE`|현재 날짜 및 시간|
|`SYSTIMESTAMP`|타임존 포함 현재 시각|
|`ADD_MONTHS(d, n)`|날짜에 월 추가|
|`MONTHS_BETWEEN(d1, d2)`|월 차이 계산|
|`TRUNC(d)`|날짜에서 시간 제거 (00:00:00)|

---

### 🔹 숫자/문자 함수

|함수|설명|
|:--|:--|
|`ROUND(n, x)`|소수점 반올림|
|`TRUNC(n, x)`|소수점 절삭|
|`TO_CHAR(d, 'YYYY-MM-DD')`|날짜 → 문자|
|`TO_DATE('2025-01-01','YYYY-MM-DD')`|문자 → 날짜|
|`NVL(expr1, expr2)`|NULL일 경우 대체값|

---

### 🔹 그룹 함수 & GROUP BY

```sql
SELECT department, COUNT(*), AVG(salary)
FROM employees
GROUP BY department
HAVING AVG(salary) > 5000;
```

|함수|설명|
|:--|:--|
|`COUNT`|행 수|
|`SUM`|합계|
|`AVG`|평균|
|`MAX`|최대값|
|`MIN`|최소값|

---

### 🔹 JOIN

```sql
-- INNER JOIN
SELECT e.name, d.name
FROM employees e
JOIN departments d ON e.dept_id = d.id;

-- LEFT OUTER JOIN
SELECT e.name, d.name
FROM employees e
LEFT JOIN departments d ON e.dept_id = d.id;

-- ORACLE 전통 JOIN (ANSI 이전)
SELECT e.name, d.name
FROM employees e, departments d
WHERE e.dept_id = d.id(+); -- LEFT OUTER
```

---

### 🔹 Subquery

```sql
-- 스칼라 서브쿼리
SELECT name, (SELECT MAX(salary) FROM employees) AS max_salary FROM dual;

-- IN 절
SELECT name FROM employees WHERE dept_id IN (SELECT id FROM departments WHERE location = 'Seoul');
```

---

### 🔹 SEQUENCE (자동 증가)

```sql
-- 생성
CREATE SEQUENCE emp_seq START WITH 1 INCREMENT BY 1;

-- 사용
INSERT INTO employees (emp_id, name) VALUES (emp_seq.NEXTVAL, 'John');
```

---

### 🔹 VIEW (가상 테이블)

```sql
CREATE VIEW emp_summary AS
SELECT department, AVG(salary) AS avg_sal
FROM employees
GROUP BY department;
```

---

### 🔹 인덱스

```sql
CREATE INDEX idx_emp_name ON employees(name);
```

---

### 🔹 사용자와 권한

```sql
-- 사용자 생성 및 권한 부여
CREATE USER dev IDENTIFIED BY password;
GRANT CONNECT, RESOURCE TO dev;
```

---

### 🔹 PL/SQL 기본 구조

```sql
DECLARE
  v_total NUMBER;
BEGIN
  SELECT SUM(salary) INTO v_total FROM employees;
  DBMS_OUTPUT.PUT_LINE('Total Salary: ' || v_total);
END;
```

- 출력 활성화:
    

```sql
SET SERVEROUTPUT ON;
```

---

### 🔹 기타 유용한 SQL

```sql
-- 중복 제거
SELECT DISTINCT department FROM employees;

-- 컬럼 별칭
SELECT name AS employee_name FROM employees;

-- CASE 문
SELECT name,
  CASE 
    WHEN salary >= 10000 THEN 'High'
    WHEN salary >= 5000 THEN 'Medium'
    ELSE 'Low'
  END AS salary_level
FROM employees;
```

---
