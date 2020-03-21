create table question{

	id INT auto_increment primary key,
	title VARCHAR(50),
	description TEXT,
	gmt_create BIGINT,
	gmt_modified BIGINT,
	creator INT ,
	comment_count INT default 0,
	view_count INT default 0,
	like_count INT default 0,
	tag VARCHAR(256)
};
