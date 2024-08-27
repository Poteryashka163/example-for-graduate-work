-- liquibase formatted sql

-- changeset ikovpik:1
CREATE TABLE user
(
    id        SERIAL PRIMARY KEY,
    email     VARCHAR(32),
    firstName VARCHAR(16),
    lastName  VARCHAR(16),
    phone     CHAR(16),
    role      VARCHAR(255),
    image     VARCHAR(255),
    password  VARCHAR(16)
);