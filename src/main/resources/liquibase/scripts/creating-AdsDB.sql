-- liquibase formatted sql

-- changeset ikovpik:1
CREATE TABLE ad
(
    pkAd        SERIAL PRIMARY KEY,
    imageAds    VARCHAR(255),
    price       INTEGER,
    title       VARCHAR(32),
    description VARCHAR(64)
);

