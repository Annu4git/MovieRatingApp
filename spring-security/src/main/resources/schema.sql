-- Spring boot uses this file to create schema

-- chaning table names, and column names deliberately
-- we will configure to tell spring security names of our table columns etc

create table users_info (
	user_name varchar_ignorecase(50) not null primary key,
	pass varchar_ignorecase(50) not null,
	active boolean not null
);

create table authorities_info (
	user_name varchar_ignorecase(50) not null,
	auth varchar_ignorecase(50) not null,
	constraint fk_authorities_info_users_info foreign key(user_name) references users_info(user_name)
);
create unique index ix_auth_user_name on authorities_info (user_name, auth);

