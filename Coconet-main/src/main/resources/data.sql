insert into AUTHORITY (AUTHORITY_NAME) values('ROLE_USER');
insert into AUTHORITY (AUTHORITY_NAME) values('ROLE_ADMIN');

insert into users(user_id, department, position, name, email, phone, birthdate, password, andnum) values(90000, 'department_admin', 'position_admin', 'admin', 'admin', '01000000000', '000000',  '$2a$10$UhIJYsHKFsfSx9dGZL7thOnEzfAZcsJZkMOiKEWF1YNA5Xwx2BDjW', 'andNum0');
insert into users(user_id, department, position, name, email, phone, birthdate, password, andnum) values(90001, '개발팀', '부장', '정재훈', 'jjh@naver.com', '01020770880', '970816', '$2a$10$UhIJYsHKFsfSx9dGZL7thOnEzfAZcsJZkMOiKEWF1YNA5Xwx2BDjW', 'andNum1');
insert into users(user_id, department, position, name, email, phone, birthdate, password, andnum) values(90002, '인사팀', '사장', '김은비', 'keb@naver.com', '01026207411', '011208', '$2a$10$UhIJYsHKFsfSx9dGZL7thOnEzfAZcsJZkMOiKEWF1YNA5Xwx2BDjW', 'andNum2');
insert into users(user_id, department, position, name, email, phone, birthdate, password, andnum) values(90003, '디자인팀', '사원', '김현빈', 'khb@naver.com', '01065597556', '012345', '$2a$10$UhIJYsHKFsfSx9dGZL7thOnEzfAZcsJZkMOiKEWF1YNA5Xwx2BDjW', 'andNum3');

insert into USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values(90000, 'ROLE_ADMIN');
insert into USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values(90001, 'ROLE_USER');
insert into USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values(90002, 'ROLE_USER');
insert into USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values(90003, 'ROLE_USER');

insert into notice(id, title, date, day) values(1, '공지사항 test1', '안녕하세요, 네이버 윅스입니다.'||CHAR(13)||CHAR(10)||'여기부터 엔터를 했는데 적용이 될까?', to_char(sysdate, 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(2, '공지사항 test2', '공지사항 내용2', to_char(sysdate, 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(3, '공지사항 test3', '공지사항 내용3', to_char(sysdate, 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(4, '공지사항 test4', '공지사항 내용4', to_char(sysdate, 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(5, '공지사항 test5', '공지사항 내용5', to_char(sysdate, 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(6, '공지사항 test6', '공지사항 내용6', to_char('2021.08.05(금)', 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, date, day) values(7, '공지사항 test7', '공지사항 내용7', to_char('2020.08.05(금)', 'YYYY.MM.DD"("dy")"'));

insert into chart_data(num, title, value, color) values(1, '근무중', 60, '#2CB0D7');
insert into chart_data(num, title, value, color) values(2, '휴식', 7, '#F0D828');
insert into chart_data(num, title, value, color) values(3, '외근', 11, '#2C89DE');
insert into chart_data(num, title, value, color) values(4, '휴가', 4, '#5AB6B1');
insert into chart_data(num, title, value, color) values(5, '출장', 3, '#6571DC');
insert into chart_data(num, title, value, color) values(6, '출근전', 15, '#BFC8D2');

insert into approval_data(num, state, color, name, day,img) values(1, '휴가', '#5AB6B1', '정사원', to_char(sysdate, 'YYYY.MM.DD"("dy")"'), '프사 링크1');
insert into approval_data(num, state, color, name, day,img) values(2, '외근', '#2C89DE', '정사원', to_char(sysdate, 'YYYY.MM.DD"("dy")"'), '프사 링크2');
insert into approval_data(num, state, color, name, day,img) values(3, '출장', '#6571DC', '정사원', to_char(sysdate, 'YYYY.MM.DD"("dy")"'), '프사 링크3');

insert into log_data(num, state, color, name, position, time, img) values(1, '퇴근', '#86ca89', '김대리', '백엔드 개발', to_char(sysdate, 'PM HH:MI'), '프사 링크1');
insert into log_data(num, state, color, name, position, time, img) values(2, '퇴근', '#86ca89', '김대리', '백엔드 개발', to_char(sysdate, 'PM HH:MI'), '프사 링크1');
insert into log_data(num, state, color, name, position, time, img) values(3, '퇴근', '#86ca89', '김대리', '백엔드 개발', to_char(sysdate, 'PM HH:MI'), '프사 링크1');
insert into log_data(num, state, color, name, position, time, img) values(4, '퇴근', '#86ca89', '김대리', '백엔드 개발', to_char(sysdate, 'PM HH:MI'), '프사 링크1');
insert into log_data(num, state, color, name, position, time, img) values(5, '퇴근', '#86ca89', '김대리', '백엔드 개발', to_char(sysdate, 'PM HH:MI'), '프사 링크1');
insert into log_data(num, state, color, name, position, time, img) values(6, '퇴근', '#86ca89', '김대리', '백엔드 개발', to_char(sysdate, 'PM HH:MI'), '프사 링크1');
insert into log_data(num, state, color, name, position, time, img) values(7, '퇴근', '#86ca89', '김대리', '백엔드 개발', to_char(sysdate, 'PM HH:MI'), '프사 링크1');

insert into todo_data(num, user_Num, user_Name, todo) values(1, 90003, '김현빈', '1회차 회의');
insert into todo_data(num, user_Num, user_Name, todo) values(2, 90003, '김현빈', '1차 JWT 토근 기능 구현');
insert into todo_data(num, user_Num, user_Name, todo) values(3, 90003, '김현빈', '1차 메인페이지 작업');
insert into todo_data(num, user_Num, user_Name, todo) values(4, 90003, '김현빈', '1차 코드 리펙토링');
insert into todo_data(num, user_Num, user_Name, todo) values(5, 90002, '김은비', '2회차 회의');
insert into todo_data(num, user_Num, user_Name, todo) values(6, 90002, '김은비', '2차 JWT 토근 기능 구현');
insert into todo_data(num, user_Num, user_Name, todo) values(7, 90002, '김은비', '2차 메인페이지 작업');
insert into todo_data(num, user_Num, user_Name, todo) values(8, 90002, '김은비', '2차 코드 리펙토링');
insert into todo_data(num, user_Num, user_Name, todo) values(9, 90001, '정재훈', '3회차 회의');
insert into todo_data(num, user_Num, user_Name, todo) values(10, 90001, '정재훈', '3차 JWT 토근 기능 구현');
insert into todo_data(num, user_Num, user_Name, todo) values(11, 90001, '정재훈', '3차메인페이지 작업');
insert into todo_data(num, user_Num, user_Name, todo) values(12, 90001, '정재훈', '3차코드 리펙토링');

insert into department(department_id, department) values(101, '인사팀');
insert into department(department_id, department) values(102, '회계팀');
insert into department(department_id, department) values(103, '영업팀');
insert into department(department_id, department) values(104, '개발팀');
insert into department(department_id, department) values(105, '디자인팀');

insert into position(position_id, department_id, position) values(201, 101, '사장');
insert into position(position_id, department_id, position) values(202, 101, '부장');
insert into position(position_id, department_id, position) values(203, 101, '대리');
insert into position(position_id, department_id, position) values(204, 101, '사원');
insert into position(position_id, department_id, position) values(205, 101, '인턴');

insert into position(position_id, department_id, position) values(206, 102, '사장');
insert into position(position_id, department_id, position) values(207, 102, '부장');
insert into position(position_id, department_id, position) values(208, 102, '대리');
insert into position(position_id, department_id, position) values(209, 102, '사원');
insert into position(position_id, department_id, position) values(210, 102, '인턴');

insert into position(position_id, department_id, position) values(211, 103, '사장');
insert into position(position_id, department_id, position) values(212, 103, '부장');
insert into position(position_id, department_id, position) values(213, 103, '대리');
insert into position(position_id, department_id, position) values(214, 103, '사원');
insert into position(position_id, department_id, position) values(215, 103, '인턴');

insert into position(position_id, department_id, position) values(216, 104, '사장');
insert into position(position_id, department_id, position) values(217, 104, '부장');
insert into position(position_id, department_id, position) values(218, 104, '대리');
insert into position(position_id, department_id, position) values(219, 104, '사원');
insert into position(position_id, department_id, position) values(220, 104, '인턴');

insert into position(position_id, department_id, position) values(221, 105, '사장');
insert into position(position_id, department_id, position) values(222, 105, '부장');
insert into position(position_id, department_id, position) values(223, 105, '대리');
insert into position(position_id, department_id, position) values(224, 105, '사원');
insert into position(position_id, department_id, position) values(225, 105, '인턴');

insert into user_status(user_id, status) values(90000, 15);
insert into user_status(user_id, status) values(90001, 15);
insert into user_status(user_id, status) values(90002, 15);
insert into user_status(user_id, status) values(90003, 15);