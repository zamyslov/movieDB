DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS movie;
DROP TABLE IF EXISTS actors;
DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS actors_movies;
DROP TABLE IF EXISTS user_movies;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
  id               INTEGER default global_seq.nextval primary key,
  name            VARCHAR                 NOT NULL,
  login            VARCHAR                 NOT NULL,
  password         VARCHAR(60)             NOT NULL,
);
CREATE UNIQUE INDEX users_unique_login_idx ON users (login);

CREATE TABLE user_roles
(
  user_id          INTEGER                 NOT NULL,
  role             VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE movie
(
  id              INTEGER default global_seq.nextval primary key,
  name            VARCHAR                 NOT NULL,
  year            INTEGER                 NOT NULL,
);
CREATE UNIQUE INDEX movies_unique_name_idx ON movie (name);

CREATE TABLE actors
(
  id              INTEGER default global_seq.nextval primary key,
  name            VARCHAR                 NOT NULL,
  surname         VARCHAR                 NOT NULL,
  dob            DATE                    NOT NULL,
);
CREATE UNIQUE INDEX actors_unique_name_surname_idx ON actors (name, surname);

CREATE TABLE actors_movies
(
  id               INTEGER default global_seq.nextval primary key,
  actor_id         INTEGER                 NOT NULL,
  movie_id         INTEGER                 NOT NULL,
  FOREIGN KEY (actor_id) REFERENCES actors (id) ON DELETE CASCADE,
  FOREIGN KEY (movie_id) REFERENCES movie (id) ON DELETE CASCADE
);

CREATE TABLE user_movies
(
  id               INTEGER default global_seq.nextval primary key,
  user_id         INTEGER                 NOT NULL,
  movie_id         INTEGER                 NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (movie_id) REFERENCES movie (id) ON DELETE CASCADE
);

CREATE TABLE votes
(
  id               INTEGER default global_seq.nextval primary key,
  user_id          INTEGER                 NOT NULL,
  movie_id         INTEGER                 NOT NULL,
  mark             DOUBLE                  NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (movie_id) REFERENCES movie (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX votes_idx ON votes (user_id, movie_id);
