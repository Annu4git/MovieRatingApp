-- creating users

INSERT INTO users_info (user_name, pass, active)
    values ('user',
    'pass1234',
    true);

INSERT INTO users_info (user_name, pass, active)
    values ('admin',
    'admin',
    true);

INSERT INTO authorities_info (user_name, auth)
    values ('user', 'ROLE_USER');

INSERT INTO authorities_info (user_name, auth)
    values ('admin', 'ROLE_ADMIN');