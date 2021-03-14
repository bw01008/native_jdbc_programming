-- ShoppingMall
DROP SCHEMA IF EXISTS ShoppingMall;

-- ShoppingMall
CREATE SCHEMA ShoppingMall;

-- 회원정보
CREATE TABLE ShoppingMall.customer (
	csNo    VARCHAR(5)  NOT NULL COMMENT '회원번호', -- 회원번호
	csName  VARCHAR(4)  NULL     COMMENT '회원명', -- 회원명
	birth   VARCHAR(8)  NULL     COMMENT '생년월일', -- 생년월일
	phoneNo VARCHAR(13) NULL     COMMENT '휴대전화', -- 휴대전화
	sex     VARCHAR(2)  NULL     COMMENT '성별' -- 성별
)
COMMENT '회원정보';

-- 회원정보
ALTER TABLE ShoppingMall.customer
	ADD CONSTRAINT PK_customer -- 회원정보 기본키
		PRIMARY KEY (
			csNo -- 회원번호
		);

-- 제품정보
CREATE TABLE ShoppingMall.product (
	pCode VARCHAR(2)  NOT NULL COMMENT '제품코드', -- 제품코드
	pName VARCHAR(20) NULL     COMMENT '제품명', -- 제품명
	price INT(10)     NULL     COMMENT '단가' -- 단가
)
COMMENT '제품정보';

-- 제품정보
ALTER TABLE ShoppingMall.product
	ADD CONSTRAINT PK_product -- 제품정보 기본키
		PRIMARY KEY (
			pCode -- 제품코드
		);

-- 판매내역
CREATE TABLE ShoppingMall.sale (
	date  DATE       NULL     COMMENT '날짜', -- 날짜
	csNo  VARCHAR(5) NOT NULL COMMENT '회원번호', -- 회원번호
	pCode VARCHAR(2) NOT NULL COMMENT '제품코드', -- 제품코드
	sale  INT(3)     NULL     COMMENT '판매수량' -- 판매수량
)
COMMENT '판매내역';

-- 판매내역
ALTER TABLE ShoppingMall.sale
	ADD CONSTRAINT PK_sale -- 판매내역 기본키
		PRIMARY KEY (
			csNo,  -- 회원번호
			pCode  -- 제품코드
		);

-- 판매내역
ALTER TABLE ShoppingMall.sale
	ADD CONSTRAINT FK_customer_TO_sale -- 회원정보 -> 판매내역
		FOREIGN KEY (
			csNo -- 회원번호
		)
		REFERENCES ShoppingMall.customer ( -- 회원정보
			csNo -- 회원번호
		);

-- 판매내역
ALTER TABLE ShoppingMall.sale
	ADD CONSTRAINT FK_product_TO_sale -- 제품정보 -> 판매내역
		FOREIGN KEY (
			pCode -- 제품코드
		)
		REFERENCES ShoppingMall.product ( -- 제품정보
			pCode -- 제품코드
		);
		
-- 권한 부여
	GRANT ALL 
		ON	ShoppingMall.*
		TO 'manager'@'localhost'
		identified BY 'rootroot';
		

-- 테이블 확인
	select user(), database();
	show tables;
	desc customer;
	desc product;
	desc sale;
	
-- 데이터 입력

insert into customer values
(12001, '홍길동', 19770405, 01097415821, '남자'),
(12002, '김연수', 19850301, 01045685581, '여자'),
(12003, '김지원', 19860708, 01091115556, '여자'),
(12004, '문희원', 19800108, 01077777777, '여자'),
(12005, '유일한', 19790205, 01045668886, '남자'),
(12006, '김동수', 19811123, 01012311211, '남자'),
(12007, '배진태', 19820707, 01078777777, '남자'),
(12008, '류은수', 19830301, 01074441474, '남자'),
(12009, '김동철', 19870426, 01085251235, '남자'),
(12010, '최홍석', 19900405, 01032146547, '남자');

select * from customer;

insert into product values
('PA', '책상', 10000),
('PB', '냉장고', 36000),
('PC', '세탁기', 22000),
('PD', 'VTR', 30000),
('PE', '자전거', 90000),
('PF', '시계', 6000),
('PG', 'TV', 8000),
('PH', '탁자', 3000);

select * from product;

insert into sale values
(20120324, 12003, 'PA', 2),
(20120327, 12001, 'PA', 1),
(20120403, 12009, 'PC', 1),
(20120407, 12010, 'PF', 5),
(20120413, 12003, 'PF', 4),
(20120414, 12002, 'PE', 3),
(20120414, 12004, 'PH', 10),
(20120415, 12005, 'PG', 7),
(20120417, 12006, 'PG', 2),
(20120419, 12007, 'PA', 9),
(20120420, 12001, 'PB', 3),
(20120420, 12005, 'PD', 2),
(20120420, 12006, 'PG', 2),
(20120422, 12010, 'PH', 1),
(20120326, 12010, 'PH', 5),
(20120501, 12003, 'PB', 7);

select * from sale;









