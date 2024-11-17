create table t_user(
user_id int primary key not null auto_increment,
account varchar(64) NOT NULL,
password varchar(200) DEFAULT NULL,
age int(1) DEFAULT 0,
description MEDIUMTEXT,
deleted tinyint DEFAULT 0,
create_time timestamp default current_timestamp
);