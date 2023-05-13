INSERT INTO users (id, username, email, password) VALUES ('5e8c23b1-5bc8-4d56-8519-09808f3c7bba', 'eagle', 'eagle@example.com', '$2a$10$5bHPac/dHLcFUlazPYlQ9e2G.LqaVwOTgPhhwQeYwfblIBj1Z4XSK');

INSERT INTO role (name)
VALUES ('ROLE_USER');

INSERT INTO user_role (user_id, role_id)
VALUES ('5e8c23b1-5bc8-4d56-8519-09808f3c7bba', 1);
