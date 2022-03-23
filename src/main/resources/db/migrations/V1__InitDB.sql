CREATE TYPE operation AS ENUM ('QUERY', 'MUTATION', 'SUBSCRIPTION');

CREATE TABLE permissions
(
    id SERIAL PRIMARY KEY,
    operation operation NOT NULL,
    query VARCHAR NOT NULL
);

CREATE TABLE roles
(
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL UNIQUE
);

CREATE TABLE role_permissions
(
    role_id INT REFERENCES roles(id) ON DELETE CASCADE,
    permission_id INT REFERENCES permissions(id) ON DELETE CASCADE
);