INSERT INTO CATEGORY(id, name) VALUES (1, 'Eurogames');
INSERT INTO CATEGORY(id, name) VALUES (2, 'Ameritrash');
INSERT INTO CATEGORY(id, name) VALUES (3, 'Familiar');


INSERT INTO AUTHOR(id, name, nationality) VALUES (1, 'Alan R. Moon', 'US');
INSERT INTO AUTHOR(id, name, nationality) VALUES (2, 'Vital Lacerda', 'PT');
INSERT INTO AUTHOR(id, name, nationality) VALUES (3, 'Simone Luciani', 'IT');
INSERT INTO AUTHOR(id, name, nationality) VALUES (4, 'Perepau Llistosella', 'ES');
INSERT INTO AUTHOR(id, name, nationality) VALUES (5, 'Michael Kiesling', 'DE');
INSERT INTO AUTHOR(id, name, nationality) VALUES (6, 'Phil Walker-Harding', 'US');


INSERT INTO CUSTOMER(id, name) VALUES (1, 'Manuel Gil');
INSERT INTO CUSTOMER(id, name) VALUES (2, 'Maria Ros');
INSERT INTO CUSTOMER(id, name) VALUES (3, 'Robert Castanyer');
INSERT INTO CUSTOMER(id, name) VALUES (4, 'LLuis Roures');
INSERT INTO CUSTOMER(id, name) VALUES (5, 'Antonio Marin');

INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (1, 'On Mars', '14', 1, 2);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (2, 'Aventureros al tren', '8', 3, 1);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (3, '1920: Wall Street', '12', 1, 4);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (4, 'Barrage', '14', 1, 3);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (5, 'Los viajes de Marco Polo', '12', 1, 3);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (6, 'Azul', '8', 3, 5);


INSERT INTO LOAN(id, game_id, customer_id, start_date, end_date) VALUES (1, 1, 2, '2022-06-05T23:00:00.000', '2022-06-19T23:00:00.000');
INSERT INTO LOAN(id, game_id, customer_id, start_date, end_date) VALUES (2, 3, 1, '2022-06-05T23:00:00.000', '2022-06-12T23:00:00.000');
INSERT INTO LOAN(id, game_id, customer_id, start_date, end_date) VALUES (3, 4, 4, '2022-06-12T23:00:00.000', '2022-06-22T23:00:00.000');
INSERT INTO LOAN(id, game_id, customer_id, start_date, end_date) VALUES (4, 6, 2, '2022-06-13T23:00:00.000', '2022-06-23T23:00:00.000');
INSERT INTO LOAN(id, game_id, customer_id, start_date, end_date) VALUES (5, 2, 2, '2022-06-14T23:00:00.000', '2022-06-24T23:00:00.000');
INSERT INTO LOAN(id, game_id, customer_id, start_date, end_date) VALUES (6, 3, 3, '2022-06-15T23:00:00.000', '2022-06-25T23:00:00.000');
