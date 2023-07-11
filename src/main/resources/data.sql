CREATE TABLE if not exists job_listings
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    publisher_id INT          NOT NULL,
    category     VARCHAR(255) NOT NULL,
    date         DATE         NOT NULL,
    FOREIGN KEY (publisher_id) REFERENCES users (id)
);
