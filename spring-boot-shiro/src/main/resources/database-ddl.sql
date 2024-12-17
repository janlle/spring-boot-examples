
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user (
    id bigint PRIMARY KEY auto_increment,
	user_id varchar(255) default null,
	username varchar(255) default null,
	password varchar(255) default null,
	salt varchar(255) default null,
	user_status int default 0,
	create_time datetime,
	modify_time datetime,
	deleted int default 0
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


DROP TABLE IF EXISTS sys_permission;
CREATE TABLE sys_permission (
    id bigint PRIMARY KEY auto_increment,
	permission_id varchar(255) default null,
	permission_name varchar(255) default null,
	permission_type int default 0,
	permission_status int default 0,
	url varchar(255) default null,
	sort int default 0,
	parent_id int default 0,
	create_time datetime,
	modify_time datetime,
	deleted int default 0
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


DROP TABLE IF EXISTS role;
CREATE TABLE sys_role (
    id bigint PRIMARY KEY auto_increment,
	role_id varchar(255) default null,
	role_name varchar(255) default null,
	role_desc varchar(255) default null,
	role_status int default 0,
	create_time datetime,
	modify_time datetime,
	deleted int default 0
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE sys_user_role (
    id bigint PRIMARY KEY auto_increment,
	role_id varchar(255) default null,
	user_id varchar(255) default null,
	create_time datetime,
	modify_time datetime,
	deleted int default 0
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


DROP TABLE IF EXISTS sys_permission_role;
CREATE TABLE sys_permission_role (
    id bigint PRIMARY KEY auto_increment,
	role_id varchar(255) default null,
	permission_id varchar(255) default null,
	create_time datetime,
	modify_time datetime,
	deleted int default 0
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

