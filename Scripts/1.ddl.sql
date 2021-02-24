select user(), database();

-- 내 스키마
DROP SCHEMA IF EXISTS native_jdbc;

-- 내 스키마
CREATE SCHEMA native_jdbc;

-- 직책
CREATE TABLE native_jdbc.Title (
	tno   INT         NOT NULL COMMENT '직책코드', -- 직책코드
	tname VARCHAR(20) NOT NULL COMMENT '직책명' -- 직책명
)
COMMENT '직책';

-- 직책
ALTER TABLE native_jdbc.Title
	ADD CONSTRAINT PK_Title -- 직책 기본키
		PRIMARY KEY (
			tno -- 직책코드
		);

-- 부서
CREATE TABLE native_jdbc.Department (
	deptNo   INT         NOT NULL COMMENT '부서번호', -- 부서번호
	deptName VARCHAR(20) NOT NULL COMMENT '부서명', -- 부서명
	floor    INT         NULL     COMMENT '위치' -- 위치
)
COMMENT '부서';

-- 부서
ALTER TABLE native_jdbc.Department
	ADD CONSTRAINT PK_Department -- 부서 기본키
		PRIMARY KEY (
			deptNo -- 부서번호
		);

-- 사원
CREATE TABLE native_jdbc.Employee (
	empno   INT           NOT NULL COMMENT '사원번호', -- 사원번호
	empname VARCHAR(20)   NULL     COMMENT '사원명', -- 사원명
	title   INT           NULL     COMMENT '직책', -- 직책
	manager INT           NULL     COMMENT '직속상사', -- 직속상사
	salary  INT           NULL     COMMENT '급여', -- 급여
	dept    INT           NULL     COMMENT '부서' -- 부서
)
COMMENT '사원';

-- 사원
ALTER TABLE native_jdbc.Employee
	ADD CONSTRAINT PK_Employee -- 사원 기본키
		PRIMARY KEY (
			empno -- 사원번호
		);

-- 사원
ALTER TABLE native_jdbc.Employee
	ADD CONSTRAINT FK_Title_TO_Employee -- 직책 -> 사원
		FOREIGN KEY (
			title -- 직책
		)
		REFERENCES native_jdbc.Title ( -- 직책
			tno -- 직책코드
		);

-- 사원
ALTER TABLE native_jdbc.Employee
	ADD CONSTRAINT FK_Employee_TO_Employee -- 사원 -> 사원
		FOREIGN KEY (
			manager -- 직속상사
		)
		REFERENCES native_jdbc.Employee ( -- 사원
			empno -- 사원번호
		);

-- 사원
ALTER TABLE native_jdbc.Employee
	ADD CONSTRAINT FK_Department_TO_Employee -- 부서 -> 사원
		FOREIGN KEY (
			dept -- 부서
		)
		REFERENCES native_jdbc.Department ( -- 부서
			deptNo -- 부서번호
		);
		
-- 계정 권한 부여
	
 grant all
 		on student.* 
		to 'user_native_jdbc'@'localhost'
		identified by 'rootroot';