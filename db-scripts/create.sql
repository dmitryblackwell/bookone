USE bookshelf;

DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS genres;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
	username varchar(50) NOT NULL,
	email varchar(50) NOT NULL,
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

CREATE TABLE genres(
	id INT UNSIGNED NOT NULL auto_increment PRIMARY KEY,
	name VARCHAR(32)
);

CREATE TABLE books(
	isbn BIGINT NOT NULL PRIMARY KEY,
	author VARCHAR(32),
	name VARCHAR(32),
	price float,
	genre INT UNSIGNED NOT NULL,
	description VARCHAR(2048),
	FOREIGN KEY (genre) REFERENCES genres(id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE orders(
	orderNo varchar(12) NOT NULL PRIMARY KEY,
	username varchar(50) NOT NULL,
	isbn BIGINT NOT NULL,
	quantity INT DEFAULT 1,
	status INT NOT NULL DEFAULT 0,

	CONSTRAINT orders_username_ibfk_1 FOREIGN KEY (username) REFERENCES users(username),
	CONSTRAINT orders_isbn_ibfk_1 FOREIGN KEY (isbn) REFERENCES books(isbn)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE comments(
  id INT UNSIGNED NOT NULL auto_increment PRIMARY KEY,
	isbn BIGINT NOT NULL,
  username varchar(50) NOT NULL,
  comment varchar(4096),

 	FOREIGN KEY (username) REFERENCES users(username),
	FOREIGN KEY (isbn) REFERENCES books(isbn)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
