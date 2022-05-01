create database if not exists npb;

use npb;

drop table if exists players;

create table players (
	id int(3) not null auto_increment,
    lastname varchar(30) not null,
    firstname varchar(30) not null,
    team varchar(30) not null,
    pos varchar(3) not null,
    primary key(id)
);