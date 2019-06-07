USE bookshelf;

DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS book_authors;
DROP TABLE IF EXISTS book_genres;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
	username VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	full_name VARCHAR(50) NOT NULL,
	phone_number VARCHAR(50),
	creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	level INTEGER default 0,
	password VARCHAR(100) NOT NULL,
	enabled tinyint(1) DEFAULT 1,
	PRIMARY KEY (username)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE authorities (
	username VARCHAR(50) NOT NULL,
	authority VARCHAR(50) NOT NULL,
	UNIQUE KEY authorities_idx_1 (username,authority),
	CONSTRAINT authorities_ibfk_1 FOREIGN KEY (username) REFERENCES
	users (username)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE genre(
	id INTEGER UNSIGNED NOT NULL auto_increment PRIMARY KEY,
	name VARCHAR(32) NOT NULL,
	description VARCHAR(2048)
);

CREATE TABLE book(
	isbn BIGINT NOT NULL PRIMARY KEY,
	name VARCHAR(32) NOT NULL,
	price float,
	description VARCHAR(2048)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE author (
    id INTEGER auto_increment NOT NULL,
    full_name VARCHAR(50) NOT NULL,
    born INTEGER,
    die INTEGER,
    description VARCHAR(2048),
	PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE book_authors (
    book_isbn BIGINT NOT NULL REFERENCES book(isbn) ON DELETE CASCADE,
    authors_id INTEGER NOT NULL REFERENCES author(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE book_genres (
    book_isbn BIGINT NOT NULL REFERENCES book(isbn) ON DELETE CASCADE,
    genres_id INTEGER NOT NULL REFERENCES genre(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE orders(
	id VARCHAR(6) NOT NULL PRIMARY KEY,
	user_username VARCHAR(50) NOT NULL REFERENCES users(username),
	book_isbn BIGINT NOT NULL REFERENCES book(isbn),
	status INT DEFAULT 0
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE comments(
	id INT UNSIGNED NOT NULL auto_increment PRIMARY KEY,
    score float UNSIGNED,
	isbn BIGINT NOT NULL,
	username VARCHAR(50) NOT NULL,
	header VARCHAR(64),
	body VARCHAR(4096),

 	FOREIGN KEY (username) REFERENCES users(username),
	FOREIGN KEY (isbn) REFERENCES book(isbn)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

