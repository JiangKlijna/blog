create table blog_user(
	id serial PRIMARY KEY,
	username varchar(50) not null,
	password varchar(50) not null,
	avatar varchar(200) not null,
	numberOfWords int8 default(0) not null, --字数
	createTime int8 not null
)
create table blog_subject(
	id serial PRIMARY KEY,
	title varchar(50) not null,
	createTime int8 not null
)
create table blog_article(
	id serial PRIMARY KEY,
	title varchar(50) not null,
	content text not null,
	userid int4 not null references blog_user(id),
	subjectid int4 not null references blog_subject(id),
	favoriteNumber int4 default(0) not null, --喜欢数
	seeNumber int4 default(0) not null, --查看数
	createTime int8 not null
)
create table blog_follow_user(
	id serial PRIMARY KEY,
	fromUser int4 not null references blog_user(id),
	toUser int4 not null references blog_user(id),
	createTime int8 not null
)
create table blog_follow_subject(
	id serial PRIMARY KEY,
	fromUser int4 not null references blog_user(id),
	toSubject int4 not null references blog_subject(id),
	createTime int8 not null
)
create table blog_comment(
	id serial PRIMARY KEY,
	content varchar(300) not null,
	userid int4 not null references blog_user(id),
	articleid int4 not null references blog_article(id),
	createTime int8 not null
)
create view v_blog_user as select * from blog_user --关注数，粉丝数，文章数，文章喜欢数
create view v_blog_subject as select * from blog_subject --文章数，关注数
create view v_blog_article as select * from blog_article --评论数
