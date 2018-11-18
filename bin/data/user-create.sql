-- DROP DATABASE  IF EXISTS bookshelf;

-- CREATE DATABASE  IF NOT EXISTS bookshelf;

USE bookshelf;

DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;


CREATE TABLE users (
	username varchar(50) NOT NULL,
	password varchar(68) NOT NULL,
	enabled tinyint(1) NOT NULL,
	PRIMARY KEY (username)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE authorities (
	username varchar(50) NOT NULL,
	authority varchar(50) NOT NULL,
	UNIQUE KEY authorities_idx_1 (username,authority),
	CONSTRAINT authorities_ibfk_1 FOREIGN KEY (username) REFERENCES
	users (username)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO users VALUES 
		('herasim','{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1), -- fun123
		('mumu','{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1);
		
INSERT INTO authorities VALUES 
			('herasim','ROLE_USER'),
			('mumu','ROLE_USER'),
			('mumu','ROLE_ADMIN');
			








