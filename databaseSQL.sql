create database plProject;

create table admin_login
(
email char(50) ,
password char(50) 
);

create table inst_login
(
email char(50) ,
password char(50) ,
inst_id int references courses(inst_id)
);

create table std_login
(
email char(50) not null,
password char(50) not null,
std_id int references std_info(std_id)
);

create table std_info
(
std_id int AUTO_INCREMENT primary key,
first_name char(50) ,
last_name char(50) ,
adress char(50) ,
phone_number int ,
gender char(10) ,
birth_date char(20)
);

create table courses
(
course_name char(50),
courses_id int primary key,
start_date char(20),
end_date char(20),
days int,
room char(50),
price int,
branch char(50),
inst_name char(50),
inst_id int unique,
number_of_students int ,
grades int ,
parent_course char(50)
);

create table course_std(
std_id int references std_info(std_id),
course_id int references courses(courses_id),
grades int
);
create table surveys(
survey char(200),
std_name char(50),
course_name char(50)

);



create table parent_n_courses(
parent_course char(50) ,
parent_course_id int primary key ,
course_id int references courses(courses_id)
);

INSERT INTO courses 
values('thomas',2,'2022-12-17','2023-1-17',30,'f',3,'g','h','t',22,50);

INSERT INTO courses 
values('mm',3,'2','2',30,'f',3,'g','h','t',23,100);


INSERT INTO std_info
values(1,'thomas','maurice','ttttt',30,'male','3');
INSERT INTO std_info
values(2,'beshoy','baha','ttttt',30,'male','3');




drop table admin_login;
drop table inst_login;
drop table std_login;
drop table course_std;
drop table parent_n_courses ;
drop table surveys;



drop table std_info;
drop table courses;

drop database plProject;