drop table if exists user;

drop table if exists logs;

/*用户表*/
create table user(
	id int primary key auto_increment,
	username varchar(20),
	password varchar(32),
	nickname varchar(20),
	sex char(1),
	age int(11),
	tel varchar(15),
	pic varchar(100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*日志表*/
create table logs(
	id int primary key auto_increment,
	username varchar(20), 
	log_info varchar(50),
	model_name varchar(50),
	method_name varchar(50),
	ip varchar(15),
	log_time timestamp,
	user_id int(11),
	foreign key fk_user_log(user_id) references user(id) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARSET=utf8;