-- liquibase formatted sql

-- changeset ikovpik:1
CREATE TABLE ad
(
    ad_id       INT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(32) NOT NULL,
    description VARCHAR(64) NOT NULL,
    price       INT         NOT NULL,
    image_ad_id VARCHAR(255),
    user_id     INT         NOT NULL,
    CONSTRAINT image_ad_id FOREIGN KEY (image_ad_id) REFERENCES image_ad (id),
    CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES users (id)
);

