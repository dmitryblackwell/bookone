USE bookshelf;

DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS genres;


CREATE TABLE genres(
	id INT UNSIGNED NOT NULL auto_increment PRIMARY KEY,
	name VARCHAR(32)
);

INSERT INTO genres(name) VALUES('sci-fi'), ('selfdevelopment'), ('novel'), ('adventure'), ('satire'), ('mystery'), ('science'), ('programming');

CREATE TABLE books(
	isbn BIGINT NOT NULL PRIMARY KEY,
	author VARCHAR(32),
	name VARCHAR(32),
	price float,
	genre INT UNSIGNED NOT NULL,
	description VARCHAR(2048),
	FOREIGN KEY (genre) REFERENCES genres(id)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO books VALUES 
	(9780131872486, 'Bruce Eckel', 'Thinking in Java', 74.2, 8, "Thinking in Java should be read cover to cover by every Java programmer, then kept close at hand for frequent reference. The exercises are challenging, and the chapter on Collections is superb! Not only did this book help me to pass the Sun Certified Java Programmer exam; its also the first book I turn to whenever I have a Java question."),
	(9780826401076, 'Stanislaw Lem', 'The Futurological Congress', 15.1, 1, "The Futurological Congress is a 1971 black humour science fiction novel by Polish author Stanislaw Lem. It details the exploits of the hero of a number of his books, Ijon Tichy, as he visits the Eighth World Futurological Congress at a Hilton Hotel in Costa Rica."),
	(9781613739266, 'Arkady and Boris Strugatsky', 'Monday starts on saturday', 7.3, 5, "The novel is written from the point of view of Aleksandr Ivanovich Privalov (usually called Sasha), a young programmer from Leningrad, who picks up two hitchhikers during a road trip north through Karelia. After the two find out that he is a programmer, they convince him to stay in Solovets and work together with them in the Scientific Research Institute of Sorcery and Wizardry"),
	(9782207248539, 'Stanislaw Lem', 'Solaris', 24.3, 1, "Solaris chronicles the ultimate futility of attempted communications with the extraterrestrial life inhabiting a distant alien planet named Solaris. The planet is almost completely covered with an ocean of gel that is revealed to be a single, planet-encompassing organism. Terran scientists conclude it is a sentient being and attempt to communicate with it."),
	(9783518743423, 'Stanislaw Lem', 'The Invincible', 45, 1, "A very powerful and armed interstellar space ship called Invincible lands on the planet Regis III, which seems uninhabited and bleak, to investigate the loss of her sister ship, Condor. During the investigation, the crew finds evidence of a form of quasi-life, born through evolution of autonomous, self-replicating machines, apparently left behind by an alien civilization which had inhabited the planet a very long time ago."),
	(9785865470595, 'Arkady and Boris Strugatsky', 'Hard to Be a God', 54, 1, "The prologue shows a scene from Anton's childhood, in which he goes on an adventures with his friends Pashka (Paul) and Anka (Anna) and plays a game based on melodramatic recreations of events on the unnamed medieval planet. The children live in a futuristic utopia, and the teenagers feel drawn to adventure. While children play they find an abandoned road with a road sign reading Anton decides to go further and discovers remnants from World War II a skeleton of a German gunner chained to his machine gun (or so he says to his friends)."),
	(9788071453611, 'Kurt Vonnegut', 'Cats Cradle', 12, 1, "At the opening of the book, the narrator, an everyman named John (but calling himself Jonah), describes a time when he was planning to write a book about what important Americans did on the day Hiroshima was bombed. While researching this topic, John becomes involved with the children of Felix Hoenikker, a Nobel laureate physicist who helped develop the atomic bomb. John travels to Ilium, New York, to interview the Hoenikker children and others for his book."),
	(9789536185344, 'Steve McConnell ', 'Code complete', 84, 8, "Code Complete is a software development book, written by Steve McConnell and published in 1993 by Microsoft Press, encouraging developers to continue past code-and-fix programming and the big design up front and waterfall models. It is also a compendium of software construction techniques, which include techniques from naming variables to deciding when to write a subroutine.");
						

CREATE TABLE orders(
	orderNo varchar(12) NOT NULL PRIMARY KEY,
	username varchar(50) NOT NULL,
	isbn BIGINT NOT NULL,
	status INT NOT NULL DEFAULT 0,
	
	FOREIGN KEY (username) REFERENCES users(username),
	FOREIGN KEY (isbn) REFERENCES books(isbn)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO orders VALUES
	("ME3421", "mumu", 9780131872486, default);

CREATE TABLE comments(
	isbn BIGINT NOT NULL,
  username varchar(50) NOT NULL,
  comment varchar(4096),

 	FOREIGN KEY (username) REFERENCES users(username),
	FOREIGN KEY (isbn) REFERENCES books(isbn)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
