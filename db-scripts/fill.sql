USE bookshelf;

INSERT INTO genre VALUES (1, 'sci-fi', 'Science fiction'),
 (2, 'Pump Fiction', 'Pump Fiction'),
 (3, 'selfdevelopment', 'Selfdevelopment.'),
 (4, 'novel', 'Novel.'),
 (5, 'adventure', 'Adventure'),
 (6, 'satire', 'Satire.'),
 (7, 'mystery', 'Mystery.'),
 (8, 'science', 'Science.'),
 (9, 'programming', 'Programming');

INSERT INTO book VALUES
	(9780131872486, 'Thinking in Java', 74.2, 'Thinking in Java should be read cover to cover by every Java programmer, then kept close at hand for frequent reference. The exercises are challenging, and the chapter on Collections is superb! Not only did this book help me to pass the Sun Certified Java Programmer exam; its also the first book I turn to whenever I have a Java question.'),
	(9780826401076, 'The Futurological Congress', 15.1, 'The Futurological Congress is a 1971 black humour science fiction novel by Polish author Stanislaw Lem. It details the exploits of the hero of a number of his books, Ijon Tichy, as he visits the Eighth World Futurological Congress at a Hilton Hotel in Costa Rica.'),
	(9781613739266, 'Monday starts on saturday', 7.3, 'The novel is written from the point of view of Aleksandr Ivanovich Privalov (usually called Sasha), a young programmer from Leningrad, who picks up two hitchhikers during a road trip north through Karelia. After the two find out that he is a programmer, they convince him to stay in Solovets and work together with them in the Scientific Research Institute of Sorcery and Wizardry'),
	(9782207248539, 'Solaris', 24.3, 'Solaris chronicles the ultimate futility of attempted communications with the extraterrestrial life inhabiting a distant alien planet named Solaris. The planet is almost completely covered with an ocean of gel that is revealed to be a single, planet-encompassing organism. Terran scientists conclude it is a sentient being and attempt to communicate with it.'),
	(9783518743423, 'The Invincible', 45, 'A very powerful and armed interstellar space ship called Invincible lands on the planet Regis III, which seems uninhabited and bleak, to investigate the loss of her sister ship, Condor. During the investigation, the crew finds evidence of a form of quasi-life, born through evolution of autonomous, self-replicating machines, apparently left behind by an alien civilization which had inhabited the planet a very long time ago.'),
	(9785865470595, 'Hard to Be a God', 54, 'The prologue shows a scene from Antons childhood, in which he goes on an adventures with his friends Pashka (Paul) and Anka (Anna) and plays a game based on melodramatic recreations of events on the unnamed medieval planet. The children live in a futuristic utopia, and the teenagers feel drawn to adventure. While children play they find an abandoned road with a road sign reading Anton decides to go further and discovers remnants from World War II a skeleton of a German gunner chained to his machine gun (or so he says to his friends).'),
	(9788071453611, 'Cats Cradle', 12, 'At the opening of the book, the narrator, an everyman named John (but calling himself Jonah), describes a time when he was planning to write a book about what important Americans did on the day Hiroshima was bombed. While researching this topic, John becomes involved with the children of Felix Hoenikker, a Nobel laureate physicist who helped develop the atomic bomb. John travels to Ilium, New York, to interview the Hoenikker children and others for his book.'),
	(9789536185344, 'Code complete', 84, 'Code Complete is a software development book, written by Steve McConnell and published in 1993 by Microsoft Press, encouraging developers to continue past code-and-fix programming and the big design up front and waterfall models. It is also a compendium of software construction techniques, which include techniques from naming variables to deciding when to write a subroutine.');

INSERT INTO author VALUES (1, 'Lev Tolstoy', 1828, 1910, 'Russian writer who is regarded as one of the greatest authors of all time'),
                      (2, 'Charles Bukowski', 1920, 1994, 'Henry Charles Bukowski was a German-American poet, novelist, and short story writer. His writing was influenced by the social, cultural, and economic ambiance of his home city of Los Angeles.'),
                      (3, 'Bruce Eckel', 1957, null, 'Bruce Eckel is a computer programmer, author and consultant. His best known works are Thinking in Java and Thinking in C++, aimed at programmers wanting to learn the Java or C++ programming languages, particularly those with little experience of object-oriented programming.'),
                      (4, 'Stanislaw Lem', 1921, 2006, 'Stanislaw Herman Lem was a Polish writer of science fiction, philosophy, and satire, and a trained physician. Lem''s books have been translated into 41 languages and have sold over 45 million copies. From the 1950s to 2000s, he published many books, both science fiction and philosophical/futurological.'),
                      (5, 'Arkady and Boris Strugatsky', null, null, 'The brothers Arkady Natanovich Strugatsky and Boris Natanovich Strugatsky were Soviet-Russian science fiction authors who collaborated through most of their careers.'),
                      (6, 'Kurt Vonnegut', 1922, 2007, 'Kurt Vonnegut Jr. was an American writer. In a career spanning over 50 years, Vonnegut published 14 novels, three short story collections, five plays, and five works of non-fiction, with further collections being published after his death.'),
                      (7, 'Steve McConnell', 1962, null, 'Steven C. McConnell is an author of software engineering textbooks such as Code Complete, Rapid Development, and Software Estimation. He is cited as an expert in software engineering and project management.');


INSERT INTO book_authors VALUES (9780131872486, 3),
                        (9780826401076, 4),
                        (9781613739266, 5),
                        (9782207248539, 4),
                        (9783518743423, 4),
                        (9785865470595, 5),
                        (9788071453611, 6),
                        (9789536185344, 7);

INSERT INTO book_genres VALUES (9780131872486, 9),
                        (9780826401076, 1),
                        (9781613739266, 6), (9781613739266, 7),
                        (9782207248539, 1),
                        (9783518743423, 1), (9783518743423, 7), (9783518743423, 5),
                        (9785865470595, 1), (9785865470595, 5),
                        (9788071453611, 4),
                        (9789536185344, 9);

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