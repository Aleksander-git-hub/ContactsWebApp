CREATE TABLE user_roles(
    user_id BIGINT REFERENCES users(id) ON DELETE RESTRICT,
    role_id BIGINT REFERENCES roles(id) ON DELETE RESTRICT
);

OK