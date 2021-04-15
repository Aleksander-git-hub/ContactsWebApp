CREATE TABLE users(
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(35) UNIQUE NOT NULL,
    first_name VARCHAR(35) NOT NULL,
    last_name VARCHAR(35) NOT NULL,
    age INTEGER NOT NULL,
    phone_number VARCHAR(25) NOT NULL,
    email VARCHAR(255) NOT NULL,
    gender VARCHAR(255),
    password VARCHAR(255) NOT NULL,
    created TIMESTAMP DEFAULT current_timestamp NOT NULL,
    updated TIMESTAMP DEFAULT current_timestamp NOT NULL,
    status VARCHAR(20) NOT NULL,
    activation_code VARCHAR(255)
);

OK