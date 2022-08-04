insert into AUTHORITY (AUTHORITY_NAME) values('ROLE_USER');
insert into AUTHORITY (AUTHORITY_NAME) values('ROLE_ADMIN');

insert into users(user_id, department, position, name, email, phone, birthdate, password, andnum) values(90000, 'department_admin', 'position_admin', 'admin', 'admin', '01000000000', '000000',  '$2a$10$UhIJYsHKFsfSx9dGZL7thOnEzfAZcsJZkMOiKEWF1YNA5Xwx2BDjW', 'andNum0');
insert into users(user_id, department, position, name, email, phone, birthdate, password, andnum) values(90001, 'department1', 'position1', '정재훈', 'jjh@naver.com', '01020770880', '970816', '$2a$10$UhIJYsHKFsfSx9dGZL7thOnEzfAZcsJZkMOiKEWF1YNA5Xwx2BDjW', 'andNum1');
insert into users(user_id, department, position, name, email, phone, birthdate, password, andnum) values(90002, 'department2', 'position2', '김은비', 'keb@naver.com', '01026207411', '011208', '$2a$10$UhIJYsHKFsfSx9dGZL7thOnEzfAZcsJZkMOiKEWF1YNA5Xwx2BDjW', 'andNum2');
insert into users(user_id, department, position, name, email, phone, birthdate, password, andnum) values(90003, 'department3', 'position3', '김현빈', 'khb@naver.com', '01065597556', '012345', '$2a$10$UhIJYsHKFsfSx9dGZL7thOnEzfAZcsJZkMOiKEWF1YNA5Xwx2BDjW', 'andNum3');

insert into USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values(90000, 'ROLE_ADMIN');
insert into USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values(90001, 'ROLE_USER');
insert into USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values(90002, 'ROLE_USER');
insert into USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values(90003, 'ROLE_USER');

insert into notice(id, title, day) values(1, '공지사항 test1', to_char(sysdate, 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, day) values(2, '공지사항 test2', to_char(sysdate, 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, day) values(3, '공지사항 test3', to_char(sysdate, 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, day) values(4, '공지사항 test4', to_char(sysdate, 'YYYY.MM.DD"("dy")"'));
insert into notice(id, title, day) values(5, '공지사항 test5', to_char(sysdate, 'YYYY.MM.DD"("dy")"'));

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