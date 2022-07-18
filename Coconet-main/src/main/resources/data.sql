insert into AUTHORITY (AUTHORITY_NAME) values ('ROLE_USER');
insert into AUTHORITY (AUTHORITY_NAME) values ('ROLE_ADMIN');

insert into users values(90001, 'andNum1', '970816', 'jjh@naver.com', '정재훈', '$2a$10$ujiRnEFVBvKpNPwjWtFWQu2CnaTQL3uZcnlwzKrzIGJICNBRI0c2q', '01012341234', 'team1', 'tier');
insert into users values(90002, 'andNum2', '011208', 'keb@naver.com', '김은비', '$2a$10$mBzomKLG/Hx2sOuRkToX2OrWNWhNbIn4lo..Y22Wl2W/W8t1puVjK', '01023452345', 'team2', 'tier');
insert into users values(90003, 'andNum1', '011208', 'khb@naver.com', '김현빈', '$2a$10$/n1LY2SrSTs22cI0cNoI7OgxsiOYMTUiGS/cpJ1nSEHAC8cjK7RUK', '01034563456', 'team3', 'tier');

INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values (90001, 'ROLE_USER');
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values (90001, 'ROLE_ADMIN');
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values (90002, 'ROLE_USER');
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values (90003, 'ROLE_USER');