# Tasks schema

# --- !Ups

CREATE TABLE user (
    id serial primary key,
    created_at timestamp NOT NULL,
    name varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    comment varchar(255)
);

# --- !Downs

DROP TABLE user;
