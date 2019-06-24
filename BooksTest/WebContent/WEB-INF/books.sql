CREATE TABLE books (book_id serial PRIMARY KEY,
title text NOT NULL,
description text NOT NULL,
price numeric NOT NULL);

CREATE TABLE genres (genre_id serial PRIMARY KEY,
genre text NOT NULL);

CREATE TABLE authors (author_id serial PRIMARY KEY,
author text NOT NULL);

CREATE TABLE orders (order_id serial PRIMARY KEY,
book_to_order_id INT REFERENCES books (book_id) ON UPDATE CASCADE ON DELETE CASCADE,
quantity numeric NOT NULL DEFAULT 1,
address text NOT NULL,
first_name text NOT NULL,
last_name text NOT NULL);

CREATE TABLE book_genre (book_id INT REFERENCES books (book_id) ON UPDATE CASCADE ON DELETE CASCADE,
 genre_id INT REFERENCES genres (genre_id) ON UPDATE CASCADE ON DELETE CASCADE,
 CONSTRAINT book_genre_pkey PRIMARY KEY (book_id, genre_id));
 
 CREATE TABLE book_author (book_id INT REFERENCES books (book_id) ON UPDATE CASCADE ON DELETE CASCADE,
 author_id INT REFERENCES authors (author_id) ON UPDATE CASCADE ON DELETE CASCADE,
 CONSTRAINT book_author_pkey PRIMARY KEY (book_id, author_id));
 
 
 
 INSERT INTO books (book_id, title, description, price) VALUES (1, 'Thinking in Java', 'Java Core Bible', 1100);
 INSERT INTO books (book_id, title, description, price) VALUES (2, 'Spring in Action', 'Nice to start learn Spring Framework with it', 2200);
 INSERT INTO books (book_id, title, description, price) VALUES (3, 'Head First Design Patterns', 'Nice to start learn design patterns with it', 1600);
 
 INSERT INTO genres (genre_id, genre) VALUES (1, 'Programming');
 INSERT INTO genres (genre_id, genre) VALUES (2, 'Java');
 INSERT INTO genres (genre_id, genre) VALUES (3, 'Spring');
 INSERT INTO genres (genre_id, genre) VALUES (4, 'Patterns');
 
 INSERT INTO authors (author_id, author) VALUES (1, 'Bruce Eckel');
 INSERT INTO authors (author_id, author) VALUES (2, 'Craig Walls');
 INSERT INTO authors (author_id, author) VALUES (3, 'Eric Freeman');
 INSERT INTO authors (author_id, author) VALUES (4, 'Elisabeth Robson');
 INSERT INTO authors (author_id, author) VALUES (5, 'Kathy Sierra');
 INSERT INTO authors (author_id, author) VALUES (6, 'Bert Bates');
 
 
 
 INSERT INTO book_author (book_id, author_id) VALUES (1, 1);
 INSERT INTO book_author (book_id, author_id) VALUES (2, 2);
 INSERT INTO book_author (book_id, author_id) VALUES (3, 3);
 INSERT INTO book_author (book_id, author_id) VALUES (3, 4);
 INSERT INTO book_author (book_id, author_id) VALUES (3, 5);
 INSERT INTO book_author (book_id, author_id) VALUES (3, 6);
 
 INSERT INTO book_genre (book_id, genre_id) VALUES (1, 1);
 INSERT INTO book_genre (book_id, genre_id) VALUES (1, 2);
 INSERT INTO book_genre (book_id, genre_id) VALUES (2, 1);
 INSERT INTO book_genre (book_id, genre_id) VALUES (2, 3);
 INSERT INTO book_genre (book_id, genre_id) VALUES (3, 1);
 INSERT INTO book_genre (book_id, genre_id) VALUES (3, 4);