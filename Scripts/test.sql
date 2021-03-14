datselect user(), database();

show tables;

desc title;
desc department;
desc employee;

select tno,tname from title;
select tno,tname from title where tno = 3;
select empno,empname,title,manager,salary,dept from employee;

insert into title values (6, '인턴');
delete from title where tno = 6;
update title set tname = '계약직' where tno = 6;

select deptNo,deptName,floor from department;
insert into department values (5, "디자인", 20);
delete from department where deptNo = 5;
update department set deptName = '총무' where deptNo = 4;

select empno,empname,title,manager,salary,dept from employee where empno = 1004;
insert into employee values (1004, "우정아", 5, 4377, 1500000, 3);
delete from employee where empno = 1004;
update employee set empname = '짱수린' where empno = 1004;

-- view

select * from vw_full_employee;

create or replace view vw_full_employee
as 
select  e.empno,
		e.empname, 
		t.tno as title_no,
		t.tname as title_name,
		e.manager as manager_no,
		m.empname as manager_name,
		e.salary, 
		d.deptno, 
		d.deptName,
		d.floor
from employee e join title t on e.title = t.tno
	 left join employee m on e.manager = m.empno 
	 join department d on e.dept=d.deptno;
	 
select empno,empname,title_no,title_name,manager_no,manager_name,salary,deptno,deptName, floor
from vw_full_employee;

select  empno, 
		empname, 
		title as title_no, 
		manager as manager_no, 
		salary, 
		dept as deptno 
from employee;

insert into employee 
values(1004, '천사', 5, 4377, 2000000, 1);

update employee 
set dept = 3 
where empno = 1004;

delete 
from employee 
where empno = 1004;

select  empno, empname, title as title_no, manager as manager_no, salary, dept as deptno 
from employee 
where empno = 4377;


select tno,tname from title;
select deptNo,deptName,floor from department;

insert into title values (6, "인턴");
insert into department values (5, "비상계획부", 10);

delete from title where tno = 6;
delete from department where deptno = 5;


select * from employee;
select * from department;
select * from title;
-- 부서가 1인 사원정보를 출력 > 결과값이 여러명일 수 있다
select *
from employee
where dept in (select deptno from department where deptno = 1);