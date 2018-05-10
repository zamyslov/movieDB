DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM movies;
DELETE FROM actors;
DELETE FROM votes;
DELETE FROM actors_movies;
DELETE FROM user_movies;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, login, password) VALUES
  ('User', 'user@yandex.ru', '$2a$10$DLVDKA7WrLvKwdcQy5HdjepOy74gIGQlkh159EqEys3bOtUE3aOHK'), --100000
  ('User1', 'user1@yandex.ru', 'password'), --100001
  ('User2', 'user2@yandex.ru', 'password'), --100002
  ('User3', 'user3@yandex.ru', 'password'), --100003
  ('Admin', 'admin@gmail.com', '$2a$10$TjHDJyLWPR5umEHZs4Vame7V98jz4FJvvk63uOXm6YF0s4PeJMCjy'); --100004

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_USER', 100001),
  ('ROLE_USER', 100002),
  ('ROLE_USER', 100003),
  ('ROLE_ADMIN', 100004);

INSERT INTO movies (name, year) VALUES
  ('Titanic', 1997), --100005
  ('Avatar', 2009), --100006
  ('Iron Man', 2008), --100007
  ('Avengers', 2012); --100008

INSERT INTO actors (name, surname, dob) VALUES
  ('DiCaprio', 'Leonardo', '1974-11-11'), --100009
  ('Winslet', 'Kate', '1975-10-05'), --100010
  ('Saldana', 'Zoe', '1978-06-19'), --100011
  ('Worthington', 'Samuel', '1976-08-02'), --100012
  ('Downey Jr', 'Robert', '1965-04-04'), --100013
  ('Paltrow', 'Gwyneth', '1972-09-27'), --100014
  ('Evans', 'Chris', '1981-06-13'); --100015

INSERT INTO votes (user_id, movie_id, mark) VALUES
  (100000, 100005, 5), --100016
  (100000, 100006, 5), --100017
  (100000, 100007, 4.5), --100018
  (100002, 100007, 5), --100019
  (100002, 100005, 3.5), --100020
  (100003, 100008, 5), --100021
  (100001, 100007, 4), --100022
  (100001, 100006, 5); --100023

INSERT INTO actors_movies (actor_id, movie_id) VALUES
  (100009, 100005), --100024
  (100010, 100005), --100025
  (100011, 100006), --100026
  (100012, 100006), --100027
  (100013, 100007), --100028
  (100014, 100007), --100029
  (100013, 100008), --100030
  (100014, 100008), --100031
  (100015, 100008); --100032

INSERT INTO user_movies (user_id, movie_id) VALUES
  (100000, 100005), --100033
  (100000, 100006), --100034
  (100000, 100007), --100035
  (100001, 100005), --100036
  (100001, 100007), --100037
  (100002, 100008), --100038
  (100002, 100007), --100039
  (100003, 100005); --100040
