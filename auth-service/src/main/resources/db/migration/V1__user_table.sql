CREATE TABLE IF NOT EXISTS user_table (
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,

    CONSTRAINT pk_user_table PRIMARY KEY (id),
    CONSTRAINT uk_user_table_username UNIQUE (username),
    CONSTRAINT uk_user_table_email UNIQUE (email)
    ) ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_unicode_ci;