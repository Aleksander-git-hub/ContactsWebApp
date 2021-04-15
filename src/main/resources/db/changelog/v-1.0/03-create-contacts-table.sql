CREATE TABLE contacts(
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(35) NOT NULL,
    last_name VARCHAR(35) NOT NULL,
    phone_number VARCHAR(35) NOT NULL,
    email VARCHAR(255) NOT NULL,
    created TIMESTAMP DEFAULT current_timestamp NOT NULL,
    updated TIMESTAMP DEFAULT current_timestamp NOT NULL,
    status VARCHAR(20) NOT NULL,
    owner_id BIGINT REFERENCES users(id) ON DELETE RESTRICT
);

OK