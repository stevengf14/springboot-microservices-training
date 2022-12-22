INSERT INTO users (username, password, enabled, name, last_name, email) VALUES ('sguaman', '$2a$10$id4/KWcJiPGKTAeAXXj29OYqRP1JC0NrfnyXBTbMhXlUovBsFjyFm', 1, 'Steven', 'Guaman', 'sguaman@test.com');
INSERT INTO users (username, password, enabled, name, last_name, email) VALUES ('afigueroa', '$2a$10$A6IL8up57J/B53L/KbpOHe2TNom9fIltg/b37x8AjQ3nAeA6bnoR6', 1, 'Andres', 'Figueroa', 'afigueroa@test.com');
INSERT INTO users (username, password, enabled, name, last_name, email) VALUES ('nlatorre', '$2a$10$qqXP5w4X5oQWAMtnsQs9FOBNux58QL/B1dA4EGjOFJ02VSi7ti05.', 1, 'Nicole', 'Latorre', 'nlatorre@test.com');
INSERT INTO users (username, password, enabled, name, last_name, email) VALUES ('amazon', '$2a$10$MX85NioRpJhPgcBBoL.roeCVTqOPV0laiDQkoNJruBy/a8ZIve0rm', 1, 'Abigail', 'Mazon', 'amazon@test.com');

INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_EMPLOYEE');
INSERT INTO roles (name) VALUES ('ROLE_ASSISTANT');
INSERT INTO roles (name) VALUES ('ROLE_PRINCESS');
 
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 3);
INSERT INTO users_roles (user_id, role_id) VALUES (3, 4);
INSERT INTO users_roles (user_id, role_id) VALUES (4, 4);