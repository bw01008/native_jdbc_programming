-- native_jdbc
-- DROP SCHEMA IF EXISTS native_jdbc;
-- 
-- native_jdbc
-- CREATE SCHEMA native_jdbc;

-- 학생
CREATE TABLE native_jdbc.student (
	stdno   INT(10)     NOT NULL COMMENT '학생번호', -- 학생번호
	stdname VARCHAR(20) NULL     COMMENT '학생명', -- 학생명
	kor     INT(10)     NULL     COMMENT '국어', -- 국어
	eng     INT(10)     NULL     COMMENT '영어', -- 영어
	math    INT(10)     NULL     COMMENT '수학' -- 수학
)
COMMENT '학생';

-- 학생
ALTER TABLE native_jdbc.student
	ADD CONSTRAINT PK_student -- 학생 기본키
		PRIMARY KEY (
			stdno -- 학생번호
		);
		
-- 권한 부여
	
	grant all 
		on native_jdbc.*
		to 'native_jdbc'@'localhost'
		identified by 'rootroot';