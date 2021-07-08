INSERT INTO roles (pk_role, name)
SELECT 'ddd6a1cc-747f-4b94-a3a3-d82848150878' AS pk_role, 'USER' AS name
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'USER');
INSERT INTO roles (pk_role, name)
SELECT '4e252b96-25cd-4487-bff1-359a3e7b3bf0' AS pk_role, 'ADMINISTRATOR' AS name
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'ADMINISTRATOR');

INSERT INTO account_user (pk_account_user, username, fullname, password)
SELECT 'b75a031b-68b0-46f9-8247-ca4afb2ca4db', 'normal_user', 'User I', '$2a$10$mzL6yEZ2Age6g2qt2MoOMekJQhKaOAqI9kUGYNcr.ZTInS6rWb6qu'
WHERE NOT EXISTS (SELECT 1 FROM account_user WHERE username = 'normal_user');
INSERT INTO account_roles (fk_account_user, fk_role)
SELECT (SELECT pk_account_user FROM account_user WHERE username = 'normal_user') AS fk_account_user
, (SELECT pk_role FROM roles WHERE name = 'USER') AS fk_role
WHERE NOT EXISTS (SELECT pk_account_user FROM account_user WHERE username = 'normal_user');

INSERT INTO account_user (pk_account_user, username, fullname, password)
SELECT '64fe4fa0-4a41-4c2e-91b7-59cb9e5e3f2d', 'superuser', 'Super User I', '$2a$10$mzL6yEZ2Age6g2qt2MoOMekJQhKaOAqI9kUGYNcr.ZTInS6rWb6qu'
WHERE NOT EXISTS (SELECT 1 FROM account_user WHERE username = 'superuser');
INSERT INTO account_roles (fk_account_user, fk_role)
SELECT (SELECT pk_account_user FROM account_user WHERE username = 'superuser') AS fk_account_user
, (SELECT pk_role FROM roles WHERE name = 'ADMINISTRATOR') AS fk_role
WHERE NOT EXISTS (SELECT pk_account_user FROM account_user WHERE username = 'superuser');