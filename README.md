# Examen de Ingreso




## Autor

- [@Lucas Ariel López Delgado](https://www.linkedin.com/in/lucas-l%C3%B3pez-delgado/)

## Usuarios

| Username             | Password |
| ----------------- | ------------------------------------------------------------------ |
| admin | 12345 |
| usuario | 12345 |



### Cargar tabla usuarios y roles:

INSERT INTO usuarios (username, password) VALUES ('usuario', '$2a$10$iC.zKTMpPw6g8cswvjT8RuEXMcLJVQooea.xdMXQHtI0b2N063F1e');

INSERT INTO usuarios (username, password) VALUES ('admin', '$$2a$10$/78NVcAlUa4jdiJDdsv8DuBbVFj9p5yPzqr0WRq82EToQu8uiLAkm');

INSERT INTO roles (name) VALUES ('CLIENTE');

INSERT INTO roles (name) VALUES ('ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1 , 1);

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2 , 2);

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2 , 1);

