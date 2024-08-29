-- liquibase formatted sql

-- changeset ikovpik:1
CREATE TABLE image
(
    id    VARCHAR(255) PRIMARY KEY NOT NULL,
    image BYTEA
);
