DROP TABLE IF EXISTS users;

CREATE TABLE users(
    user_id SERIAL PRIMARY KEY,
    user_nickname VARCHAR(50) UNIQUE,
    user_name VARCHAR(50),
    user_surname VARCHAR(50),
    user_email VARCHAR(50) UNIQUE NOT NULL,
    user_passwordHash VARCHAR(255) NOT NULL CHECK(LENGTH(user_passwordHash) > 63)
);

INSERT INTO
    users (user_nickname, user_name, user_surname, user_email, user_passwordHash)
VALUES
    ('TheFinalBoss', 'Gherman', 'The First Hunter', 'gherman@oldblood.yh', '0bdfc52df835e3459e76a9d5d1430bb3f134dc6db2d525ebabb2773af5d9a13c'),
    ('TheVicar', 'Laurence', 'The First Vicar', 'vicar@oldblood.yh', '0bdfc52df835e3459e76a9d5d1430bb3f134dc6db2d525ebabb2773af5d9a13c'),
    ('Mensis69', 'Mensis', 'Nightmare Host', 'mensis@scholars.yh', '0bdfc52df835e3459e76a9d5d1430bb3f134dc6db2d525ebabb2773af5d9a13c'),
    ('Djura', 'Djura', 'The Retired Hunter', 'djura@oldhunter.yh', '0bdfc52df835e3459e76a9d5d1430bb3f134dc6db2d525ebabb2773af5d9a13c')
    ;