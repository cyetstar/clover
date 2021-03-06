drop table if exists tb_ablum;

drop table if exists tb_ablum_relations_musician;

drop table if exists tb_book;

drop table if exists tb_book_tag;

drop table if exists tb_celebrity;

drop table if exists tb_comic;

drop table if exists tb_comic_book;

drop table if exists tb_movie;

drop table if exists tb_movie_aka;

drop table if exists tb_movie_country;

drop table if exists tb_movie_credit;

drop table if exists tb_movie_file;

drop table if exists tb_movie_genre;

drop table if exists tb_movie_language;

drop table if exists tb_movie_relations_movie_country;

drop table if exists tb_movie_relations_movie_genre;

drop table if exists tb_movie_relations_movie_language;

drop table if exists tb_movie_set;

drop table if exists tb_movie_set_item;

drop table if exists tb_musician;

drop table if exists tb_song;

create table tb_ablum
(
   id                   bigint not null auto_increment,
   douban_id            varchar(10),
   xiami_id             varchar(20),
   title                varchar(100) not null,
   alt_title            varchar(50),
   summary              text,
   publisher            varchar(100),
   pubdate              varchar(20),
   version              varchar(10),
   rating               float,
   num_raters           int,
   cover                varchar(50),
   created_at           timestamp not null,
   updated_at           timestamp,
   primary key (id)
);

create table tb_ablum_relations_musician
(
   ablum_id             bigint not null,
   musician_id          bigint not null,
   primary key (ablum_id, musician_id)
);

create table tb_book
(
   id                   bigint not null auto_increment,
   douban_id            varchar(10),
   isbn10               varchar(10),
   isbn13               varchar(13),
   title                varchar(100) not null,
   original_title       varchar(100),
   alt_title            varchar(50),
   subtitle             varchar(50),
   author               varchar(100) not null,
   translator           varchar(100),
   publisher            varchar(100),
   pubdate              varchar(20),
   author_intro         text,
   summary              text,
   rating               float,
   num_raters           int,
   cover                varchar(50),
   created_at           timestamp not null,
   updated_at           timestamp,
   primary key (id)
);

create table tb_book_tag
(
   id                   bigint not null auto_increment,
   book_id              bigint not null,
   name                 varchar(20),
   primary key (id)
);

create table tb_celebrity
(
   id                   bigint not null auto_increment,
   douban_id            varchar(10),
   name                 varchar(30) not null,
   name_en              varchar(50),
   summary              text,
   gender               varchar(1),
   avatar               varchar(50),
   created_at           timestamp not null,
   updated_at           timestamp,
   primary key (id)
);

create table tb_comic
(
   id                   bigint not null auto_increment,
   title                varchar(100) not null,
   original_title       varchar(100),
   alt_title            varchar(50),
   author               varchar(100) not null,
   publisher            varchar(100),
   author_intro         text,
   summary              text,
   end                  bool not null,
   total_volume         int,
   cover                varchar(50),
   created_at           timestamp not null,
   updated_at           timestamp,
   primary key (id)
);

create table tb_comic_book
(
   id                   bigint not null auto_increment,
   comic_id             bigint,
   book_volume          int,
   scanner              varchar(50),
   cover                varchar(50),
   created_at           timestamp not null,
   updated_at           timestamp,
   primary key (id)
);

create table tb_movie
(
   id                   bigint not null auto_increment,
   douban_id            varchar(10),
   imdb                 varchar(10),
   title                varchar(100),
   original_title       varchar(100),
   subtype              varchar(10) not null,
   year                 varchar(4) not null,
   summary              text,
   duration             varchar(50),
   rating               float,
   num_raters           int,
   poster               varchar(50),
   created_at           timestamp not null,
   updated_at           timestamp,
   primary key (id)
);

create table tb_movie_aka
(
   id                   bigint not null auto_increment,
   movie_id             bigint not null,
   title                varchar(100) not null,
   primary key (id)
);

create table tb_movie_country
(
   id                   bigint not null auto_increment,
   value                varchar(20) not null,
   primary key (id)
);

create table tb_movie_credit
(
   id                   bigint not null auto_increment,
   movie_id             bigint not null,
   celebrity_id         bigint not null,
   role                 varchar(10) not null,
   created_at           timestamp not null,
   updated_at           timestamp,
   primary key (id)
);

create table tb_movie_file
(
   id                   bigint not null auto_increment,
   movie_id             bigint not null,
   created_at           timestamp not null,
   updated_at           timestamp,
   filename             varchar(100) not null,
   primary key (id)
);

create table tb_movie_genre
(
   id                   bigint not null auto_increment,
   value                varchar(20) not null,
   primary key (id)
);

create table tb_movie_language
(
   id                   bigint not null auto_increment,
   value                varchar(20) not null,
   primary key (id)
);

create table tb_movie_relations_movie_country
(
   movie_id             bigint not null,
   country_id           bigint not null,
   primary key (movie_id, country_id)
);

create table tb_movie_relations_movie_genre
(
   movie_id             bigint not null,
   genre_id             bigint not null,
   primary key (movie_id, genre_id)
);

create table tb_movie_relations_movie_language
(
   movie_id             bigint not null,
   language_id          bigint not null,
   primary key (movie_id, language_id)
);

create table tb_movie_set
(
   id                   bigint not null auto_increment,
   title                varchar(100),
   summary              text,
   created_at           timestamp not null,
   updated_at           timestamp,
   primary key (id)
);

create table tb_movie_set_item
(
   id                   bigint not null auto_increment,
   movie_id             bigint not null,
   set_id               bigint not null,
   idx                  int not null,
   comment              varchar(500),
   created_at           timestamp not null,
   updated_at           timestamp,
   primary key (id)
);

create table tb_musician
(
   id                   bigint not null auto_increment,
   name                 varchar(30),
   name_en              varchar(50),
   summary              text,
   avatar               varchar(50),
   created_at           timestamp not null,
   updated_at           timestamp,
   primary key (id)
);

create table tb_song
(
   id                   bigint not null auto_increment,
   ablum_id             bigint not null,
   xiami_id             varchar(20),
   track_id             int,
   name                 varchar(100) not null,
   created_at           timestamp not null,
   updated_at           timestamp,
   primary key (id)
);
