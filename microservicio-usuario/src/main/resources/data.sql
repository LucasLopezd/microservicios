#-- Cliente password = 12345
#-- Admin password = 12345

INSERT INTO usuarios (username, password) VALUES ('usuario', '$2a$10$iC.zKTMpPw6g8cswvjT8RuEXMcLJVQooea.xdMXQHtI0b2N063F1e');
INSERT INTO usuarios (username, password) VALUES ('admin', '$2a$10$j536Iqv0XeDFQaxBO4sqUeZwd5HSTouW9cYaUcWXnVE.YlfEGAr7C');

INSERT INTO roles (name) VALUES ('CLIENTE');
INSERT INTO roles (name) VALUES ('ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1 , 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2 , 2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2 , 1);