drop table if exists t_user;
create table t_user (id varchar(255) not null, password varchar(255) not null, registerDate timestamp default current_timestamp, username varchar(255) not null, primary key (id));
