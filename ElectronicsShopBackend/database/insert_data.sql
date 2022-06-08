USE ElectronicsShopSpringMVC

INSERT INTO role(name, code) VALUES (N'Quản trị', 'ADMIN');
INSERT INTO role(name, code) VALUES (N'Người dùng', 'USER');

INSERT INTO users (username, password, fullname, gender, birthday, phone, address, email, avatar, jobs, facebook, status)
VALUES ('admin', '$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG', N'Phạm Lê Việt Tú', N'Nam',
        '14-10-2001', '0966871026', N'Hà Nội', 'tupham1120@gmail.com', 'avatar.jpg', N'Sinh viên', null, 1);

INSERT INTO users (username, password, fullname, gender, birthday, phone, address, email, avatar, jobs, facebook, status)
VALUES ('nguyenvana', '$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG', N'Nguyễn Văn A', N'Nữ',
        '12-12-2000', '', N'', '', '', N'', null, 1);

INSERT INTO users (username, password, fullname, gender, birthday, phone, address, email, avatar, jobs, facebook, status)
VALUES ('nguyenvanb', '$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG', N'Nguyễn Văn B', N'Nam',
        '11-11-1999', '', N'', '', '', N'', null, 1);

INSERT INTO user_role(user_id, role_id) VALUES (1, 1);
INSERT INTO user_role(user_id, role_id) VALUES (2, 1);
INSERT INTO user_role(user_id, role_id) VALUES (3, 2);