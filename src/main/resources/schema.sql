CREATE TABLE transactions (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
customer_id BIGINT NOT NULL,
customer_name VARCHAR(255) not NULL,
amount DOUBLE,
transaction_date DATE);