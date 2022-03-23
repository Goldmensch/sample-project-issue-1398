WITH role AS (
    INSERT INTO roles (name) VALUES ('test') RETURNING id
),
perm1 AS (
    INSERT INTO permissions (operation, query) VALUES ('MUTATION', 'testMut1') RETURNING id
),
perm2 AS (
    INSERT INTO permissions (operation, query) VALUES ('MUTATION', 'testMut2') RETURNING id
)
INSERT INTO role_permissions (permission_id, role_id) VALUES
((SELECT  id FROM perm1), (SELECT id FROM role)),
((SELECT  id FROM perm2), (SELECT id FROM role));
