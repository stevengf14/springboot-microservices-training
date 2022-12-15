INSERT INTO users (username, password, enabled, name, last_name, email) VALUES ('sguaman', '1234', 1, 'Steven', 'Guaman', 'sguaman@test.com');
INSERT INTO users (username, password, enabled, name, last_name, email) VALUES ('afigueroa', '1234', 1, 'Andres', 'Figueroa', 'afigueroa@test.com');
INSERT INTO users (username, password, enabled, name, last_name, email) VALUES ('nlatorre', '1234', 1, 'Nicole', 'Latorre', 'nlatorre@test.com');
INSERT INTO users (username, password, enabled, name, last_name, email) VALUES ('amazon', '1234', 1, 'Abigail', 'Mazon', 'amazon@test.com');

INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_EMPLOYEE');
INSERT INTO roles (name) VALUES ('ROLE_ASSISTANT');
INSERT INTO roles (name) VALUES ('ROLE_PRINCESS');
 
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 3);
INSERT INTO users_roles (user_id, role_id) VALUES (3, 4);
INSERT INTO users_roles (user_id, role_id) VALUES (4, 4);