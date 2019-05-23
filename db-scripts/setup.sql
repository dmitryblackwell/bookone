CREATE USER 'student'@'localhost' IDENTIFIED BY 'salt';
GRANT ALL ON *.* TO 'student'@'localhost';

DROP DATABASE  IF EXISTS bookshelf;
CREATE DATABASE  IF NOT EXISTS bookshelf;