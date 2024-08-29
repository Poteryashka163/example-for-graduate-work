-- liquibase formatted sql

-- changeset ikovpik:1
CREATE TABLE users
(
    id         INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    image_id   VARCHAR(255),
    username   VARCHAR(32)  NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    first_name VARCHAR(16)  NOT NULL,
    last_name  VARCHAR(16)  NOT NULL,
    phone      VARCHAR(20)  NOT NULL,
    role       INTEGER      NOT NULL,
    FOREIGN KEY (image_id) REFERENCES image (id)
);
