insert into tb_movie(id, title, subtype, year, created_at) values (1, 'test', 'movie', '1980', '2013-01-08 20:21:00');

insert into tb_movie_aka(id, title, movie_id) values (1, 'test2', 1);

insert into tb_movie_genre(id, value) values (1, '剧情');
insert into tb_movie_genre(id, value) values (2, '喜剧');

insert into tb_celebrity(id, name, created_at) values (1, '姜文', '2013-01-08 20:21:00');
insert into tb_celebrity(id, name, created_at) values (2, '葛优', '2013-01-08 20:21:00');
insert into tb_celebrity(id, name, created_at) values (3, '周润发', '2013-01-08 20:21:00');
