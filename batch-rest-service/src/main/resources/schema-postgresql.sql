DROP TABLE IF EXISTS customer;

CREATE TABLE customer  (
    id Bigserial PRIMARY KEY NOT NULL,
    first_name VARCHAR(20),
    last_name VARCHAR(20)
);