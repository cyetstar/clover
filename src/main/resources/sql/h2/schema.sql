drop table if exists tb_ablum;
drop table if exists tb_ablum_musician;
drop table if exists tb_book;
drop table if exists tb_celebrity;
drop table if exists tb_comic;
drop table if exists tb_comic_book;
drop table if exists tb_movie;
drop table if exists tb_movie_aka;
drop table if exists tb_movie_country;
drop table if exists tb_movie_credit;
drop table if exists tb_movie_genre;
drop table if exists tb_movie_language;
drop table if exists tb_movie_movie_country;
drop table if exists tb_movie_movie_language;
drop table if exists tb_movie_movie_genre;
drop table if exists tb_musician;
drop table if exists tb_song;

create table tb_ablum(
	id                  bigint not null auto_increment,
	douban_id			varchar(10),
	xiami_id			varchar(10),
	title				varchar(100) not null,
	alt_title			varchar(50),
	summary				varchar(2000),
	publisher			varchar(50),
	pubdate				varchar(10),
	version				varchar(10),
	rating				real not null,
	num_raters			int  not null,
	image				varchar(100),
	created_at			timestamp default 0 not null,
	updated_at			timestamp,
	primary key (id)
);

create table tb_ablum_musician(
	ablum_id			bigint not null,
	musician_id			bigint not null,
	primary key (ablum_id, musician_id)
);

create table tb_book(
	id                  bigint not null auto_increment,
	douban_id			varchar(10),
	isbn10				varchar(10),
	isbn13				varchar(13),
	title				varchar(100) not null,
	origin_title		varchar(100),
	alt_title			varchar(50),
	subtitle			varchar(50),
	author				varchar(50) not null,
	translator			varchar(50),
	publisher			varchar(50),
	pubdate				varchar(10),
	author_intro		varchar(2000),
	summary				varchar(2000),
	rating				real not null,
	num_raters			int  not null,
	image				varchar(100),
	created_at			timestamp default 0 not null,
	updated_at			timestamp,
	primary key (id)
);

create table tb_celebrity(
	id                  bigint not null auto_increment,
	douban_id			varchar(10),
	name				varchar(30) not null,
	name_en				varchar(30),
	summary				varchar(2000),
	gender				varchar(1),
	avatar				varchar(100),
	created_at			timestamp default 0 not null,
	updated_at			timestamp,
	primary key (id)
);

create table tb_comic(
	id                  bigint not null auto_increment,
	title				varchar(100) not null,
	origin_title		varchar(100),
	alt_title			varchar(50),
	author				varchar(50) not null,
	publisher			varchar(50),
	author_intro		varchar(2000),
	summary				varchar(2000),
	end					bool,
	total_volume		int,
	image				varchar(100),
	created_at			timestamp default 0 not null,
	updated_at			timestamp,
	primary key (id)
);

create table tb_comic_book(
	id                  bigint not null auto_increment,
	book_volume			int not null,
	scanner				varchar(30),
	image				varchar(100),
	comic_id			bigint not null,
	created_at			timestamp default 0 not null,
	updated_at			timestamp,
	primary key (id)
);

create table tb_movie(
	id                  bigint not null auto_increment,
	douban_id           varchar(10),
	imdb				varchar(10),
	title				varchar(50) not null,
	original_title      varchar(200),
	subtype				varchar(10) not null,
	year				varchar(4) not null,
	summary				varchar(2000),
	duration			varchar(100),
	rating				real default 0.0 not null,
	num_raters			int default 0 not null,
	image				varchar(100),
	created_at			timestamp default 0 not null,
	updated_at			timestamp,
	primary key (id)
);

create table tb_movie_aka(
	id                  bigint not null auto_increment,
	movie_id			bigint not null,
	value				varchar(200),
	primary key (id)
);

create table tb_movie_country(
	id                  bigint not null auto_increment,
	value				varchar(10) not null,
	primary key (id)
);

create table tb_movie_credit(
	id                  bigint not null auto_increment,
	movie_id			bigint not null,
	celebrity_id		bigint not null,
	role				varchar(10) not null,
	primary key (id)
);

create table tb_movie_genre(
	id                  bigint not null auto_increment,
	value				varchar(10) not null,
	primary key (id)
);

create table tb_movie_language(
	id                  bigint not null auto_increment,
	value				varchar(10) not null,
	primary key (id)
);

create table tb_movie_movie_country(
	movie_id            bigint not null,
	country_id      	bigint not null,
	primary key (movie_id, country_id)
);

create table tb_movie_movie_genre(
	movie_id            bigint not null,
	genre_id      		bigint not null,
	primary key (movie_id, genre_id)
);

create table tb_movie_movie_language(
	movie_id            bigint not null,
	language_id      	bigint not null,
	primary key (movie_id, language_id)
);

create table tb_musician(
	id                  bigint not null auto_increment,
	name				varchar(30) not null,
	name_en				varchar(30),
	summary				varchar(2000),
	avatar				varchar(100),
	created_at			timestamp default 0 not null,
	updated_at			timestamp,
	primary key (id)
);

create table tb_song(
	id                  bigint not null auto_increment,
	xiami_id			varchar(10),
	track_id			int not null,
	song_name			varchar(200) not null,
	ablum_id			bigint not null,
	created_at			timestamp default 0 not null,
	updated_at			timestamp,
	primary key (id)
);
