-- creating users

INSERT INTO users (username, password, enabled)
    values ('user',
    'pass1234',
    true);

INSERT INTO users (username, password, enabled)
    values ('admin',
    'admin',
    true);

INSERT INTO authorities (username, authority)
    values ('user', 'ROLE_USER');

INSERT INTO authorities (username, authority)
    values ('admin', 'ROLE_ADMIN');