-- liquibase formatted sql

-- changeset ikovpik:1
CREATE TABLE comments
(
    pkComments SERIAL PRIMARY KEY,
    text      VARCHAR(64),
    createdAt DATETIME
);