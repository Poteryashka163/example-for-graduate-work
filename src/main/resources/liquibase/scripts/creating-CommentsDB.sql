-- liquibase formatted sql

-- changeset ikovpik:1
CREATE TABLE comments
(
    comment_id INT AUTO_INCREMENT PRIMARY KEY,
    author_id  INT          NOT NULL,
    ad_id      INT          NOT NULL,
    created_at TIMESTAMP    NOT NULL,
    text       VARCHAR(255) NOT NULL,
    CONSTRAINT author_id FOREIGN KEY (author_id) REFERENCES users (id),
    CONSTRAINT ad_id FOREIGN KEY (ad_id) REFERENCES ads (ad_id)
);