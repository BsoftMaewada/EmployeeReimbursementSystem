
DROP TABLE IF EXISTS reimbursement CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS user_roles CASCADE ; 
DROP TABLE IF EXISTS reimbursement_type CASCADE; 
DROP TABLE IF EXISTS reimbursement_status CASCADE;
DROP EXTENSION IF EXISTS pgcrypto;


--CREATE SCHEMA ERSApp;

CREATE TABLE user_roles (
id SERIAL PRIMARY KEY,
role VARCHAR(20) NOT NULL
);

INSERT INTO user_roles (role)
VALUES 
('manager'),
('employee');


CREATE TABLE reimbursement_type (
id SERIAL PRIMARY KEY,
type VARCHAR(200) NOT NULL
);

INSERT INTO reimbursement_type  (type)
VALUES ('lodging'),
('travel'),
('food'),
('other');


CREATE TABLE reimbursement_status (
id SERIAL PRIMARY KEY,
status VARCHAR(200) NOT NULL
);

INSERT INTO reimbursement_status (status)
VALUES 
('Pending'),
('Approved'),
('Denied');

CREATE EXTENSION PGCRYPTO;

CREATE TABLE users (
id SERIAL PRIMARY KEY,
username VARCHAR(200) UNIQUE NOT NULL,
password VARCHAR(200) NOT NULL,
first_name VARCHAR(200) NOT NULL,
last_name VARCHAR(200) NOT NULL,
email VARCHAR(200) UNIQUE NOT NULL,
role_id INTEGER NOT NULL,

CONSTRAINT fk_user_role foreign key(role_id) references user_roles(id) ON DELETE CASCADE
);

INSERT INTO users (username, password, first_name, last_name, email, role_id)
VALUES 
--('Bsoft', '123456', 'Buhari', 'Maiwada', 'bm@gmail.com', 1),
--('Muy', 'pass123', 'Maryam', 'Usman', 'muy@gmail.com', 2),
--('Chris05','password', 'Chris', 'Rock', 'chrisRock@live.com', 2),
--('Willie','will1234', 'Will', 'Smith', 'williamsmith@yahoo.com', 2);

('Bsoft', crypt('123456', gen_salt('md5')), 'Buhari', 'Maiwada', 'bm@gmail.com', 1),
('Muy', crypt('pass123', gen_salt('md5')), 'Maryam', 'Usman', 'muy@gmail.com', 2),
('Chris05', crypt('password', gen_salt('md5')), 'Chris', 'Rock', 'chrisRock@live.com', 2),
('Willie', crypt('will1234', gen_salt('md5')), 'Will', 'Smith', 'williamsmith@yahoo.com', 2);


CREATE TABLE reimbursement (
id SERIAL PRIMARY KEY,
amount INTEGER NOT NULL,
date_submitted TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
date_resolved TIMESTAMP,
description VARCHAR (200) NOT NULL,

recepit_image BYTEA,

employee_id INTEGER NOT NULL,
manager_id INTEGER DEFAULT 1,
status_id INTEGER NOT NULL DEFAULT 1,
type_id INTEGER NOT NULL,


CONSTRAINT fk_user_employee foreign key(employee_id) references users(id),
CONSTRAINT fk_user_manager foreign key(manager_id) references users(id),
CONSTRAINT fk_status_id foreign key(status_id) references reimbursement_status(id),
CONSTRAINT fk_type_id foreign key(type_id) references reimbursement_type(id)
);

INSERT INTO reimbursement (amount, description, employee_id, type_id)
VALUES 
(100, 'pizza', 2, 3),
(100, 'laptop', 2, 4),
(159, 'airplane ticket', 3, 2)
;




SELECT * FROM user_roles; 
SELECT * FROM reimbursement_type;
SELECT * FROM reimbursement_status;
SELECT * FROM users;
SELECT * FROM reimbursement;





/*
 
 man.id, man.username, man.password, man.first_name, man.last_name, man.EMAIL
 
SELECT a.id, a.username, a.password, a.first_name, a.last_name, a.EMAIL, b.role
FROM users a
JOIN USER_ROLES b ON a.role_id = b.id
WHERE a.username= 'Bsoft' AND a.password = crypt('123456',a.password);

WHERE a.username= 'samuel1' AND a.password = 'password1';
SELECT R.ID, R.AMOUNT, R.DATE_SUBMITTED, R.DATE_RESOLVED, R.DESCRIPTION, 
emp.id AS employee_id, emp.username AS employee_username, emp.PASSWORD AS employee_password, emp.first_name AS employee_firstname, emp.last_name AS employee_lastname, emp.EMAIL AS employee_email, 
man.id AS manager_id, man.username AS manager_username, man.PASSWORD AS manager_password, man.first_name AS manager_firstname, man.last_name AS manager_lastname, man.EMAIL AS manager_email,
RS.STATUS, RT."type"
FROM reimbursement R
JOIN REIMBURSEMENT_STATUS RS ON R.STATUS_ID = RS.ID
JOIN REIMBURSEMENT_TYPE RT ON R.TYPE_ID = RT.ID
LEFT JOIN USERS emp ON R.EMPLOYEE_ID = emp.ID
LEFT JOIN USERS man ON R.MANAGER_ID = man.ID
WHERE R.EMPLOYEE_ID = 2
;
UPDATE REIMBURSEMENT
SET STATUS_ID = 2,
MANAGER_ID = 1,
DATE_RESOLVED = CURRENT_TIMESTAMP
WHERE id = 1;
*/