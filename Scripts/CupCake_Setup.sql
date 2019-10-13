DROP SCHEMA if exists CupCake;
CREATE SCHEMA CupCake DEFAULT CHARACTER SET utf8 COLLATE utf8_danish_ci;
USE CupCake;

CREATE TABLE user(
id INT NOT NULL AUTO_INCREMENT,
eMail VARCHAR(50) NOT NULL,
password VARCHAR(128) NOT NULL,
firstName VARCHAR(50) NOT NULL,
lastName VARCHAR(50) NOT NULL,
PRIMARY KEY(id)
);

INSERT INTO users VALUES(1, 'testTest@E-mail.com', 'PasswordTest', 'firstTestName', 'lastTestName');

select * from users;