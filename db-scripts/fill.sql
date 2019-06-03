USE bookshelf;

INSERT INTO genre VALUES (1, 'sci-fi', 'Science fiction'), (2, 'Pump Fiction', 'Pump Fiction');

INSERT INTO book VALUES
	(9780131872486, 'Thinking in Java', 74.2, "Thinking in Java should be read cover to cover by every Java programmer, then kept close at hand for frequent reference. The exercises are challenging, and the chapter on Collections is superb! Not only did this book help me to pass the Sun Certified Java Programmer exam; its also the first book I turn to whenever I have a Java question."),
	(9780826401076, 'The Futurological Congress', 15.1, "The Futurological Congress is a 1971 black humour science fiction novel by Polish author Stanislaw Lem. It details the exploits of the hero of a number of his books, Ijon Tichy, as he visits the Eighth World Futurological Congress at a Hilton Hotel in Costa Rica.");

INSERT INTO author VALUES(default, 'Lev Tolstoy', 1828, 1910, 'Count Lev Nikolayevich Tolstoy, usually referred to in English as Leo Tolstoy, was a Russian writer who is regarded as one of the greatest authors of all time. '),
                      (default, 'Charles Bukowski', 1920, 1994, 'Henry Charles Bukowski was a German-American poet, novelist, and short story writer. His writing was influenced by the social, cultural, and economic ambiance of his home city of Los Angeles.');

INSERT INTO book_authors VALUES (9780131872486, 1), (9780826401076, 1);
INSERT INTO book_genres VALUES (9780131872486, 1), (9780826401076, 1);

INSERT INTO users VALUES 
		('herasim', 'herasim@mail.com', 'Herasim Herasimovich', '050 1234 567', default, default, '{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', default), -- fun123
		('mumu', 'mumu@mu.mu', 'Mumu Mumumovichna', '050 1234 567', default, default, '{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', default);
		
INSERT INTO authorities VALUES 
			('herasim','ROLE_USER'),
			('mumu','ROLE_USER'),
			('mumu','ROLE_ADMIN');
            
INSERT INTO comments VALUES
		(default, 5, 9780131872486, 'mumu', 'Great Book', 'This is greate book beacouse i said so!');

INSERT INTO orders VALUES
    ('ME1234', 'mumu', 9780131872486, 0);