drop table if exists tb_movie;

create table tb_movie(
   id                   bigint not null auto_increment,
   douban_id            varchar(10),
   title           		varchar(50) not null,
   original_title       varchar(200),
   douban_url			varchar(50),
   subtype				varchar(10) not null,
   year					varchar(4) not null,
   summary				text,
   rating_id			bigint not null,
   image				varchar(50),
   primary key (id)
);