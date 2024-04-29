CREATE DATABASE DB_FORO;

USE DB_FORO;

CREATE TABLE users (
    id INT PRIMARY KEY auto_increment,
    user VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(500) NOT NULL,
    status CHAR(1) DEFAULT 'A'
);

CREATE TABLE readers (
    id INT PRIMARY KEY auto_increment,
    name VARCHAR(100) NOT NULL,
    status CHAR(1) DEFAULT 'A'
);

CREATE TABLE blogs (
    id int PRIMARY KEY auto_increment,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(100) NOT NULL,
    status CHAR(1) DEFAULT 'A'
);

CREATE TABLE blogs_readers(
    r_id int,
    b_id int,
    foreign key (r_id) references readers(id),
    foreign key (b_id) references blogs(id)
);