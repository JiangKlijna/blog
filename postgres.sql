create database blog
create table blog_user(
	id serial PRIMARY KEY,
	username varchar(50) not null,
	password varchar(50) not null,
	createTime int8 not null
);
create table blog_subject(
	id serial PRIMARY KEY,
	title varchar(50) not null,
	createTime int8 not null
);
create table blog_article(
	id serial PRIMARY KEY,
	title varchar(50) not null,
	content text not null,
	userid int4 not null references blog_user(id),
	subjectid int4 not null references blog_subject(id),
	favoriteNumber int4 default(0) not null, --喜欢数
	seeNumber int4 default(0) not null, --查看数
	numberOfWords int8 default(0) not null, --字数
	createTime int8 not null
);
create table blog_follow_user(
	id serial PRIMARY KEY,
	fromUser int4 not null references blog_user(id),
	toUser int4 not null references blog_user(id),
	createTime int8 not null
);
create table blog_follow_subject(
	id serial PRIMARY KEY,
	fromUser int4 not null references blog_user(id),
	toSubject int4 not null references blog_subject(id),
	createTime int8 not null
);
create table blog_comment(
	id serial PRIMARY KEY,
	content varchar(300) not null,
	userid int4 not null references blog_user(id),
	articleid int4 not null references blog_article(id),
	createTime int8 not null
);
create table blog_message(
	id serial PRIMARY KEY,
	fromUser int4 not null references blog_user(id),
	toUser int4 not null references blog_user(id),
	flag int4 default(0) not null, --消息的类型
	isread boolean default(false) not null,
	createTime int8 not null
);
create view v_blog_user as
    select u.id, u.username, u.createtime,
    (select count(id) from blog_article where userid = u.id) as numberOfArticles, -- 文章数
    (select count(id) from blog_follow_user where fromuser = u.id) as numberOfConcerns, -- 关注数
    (select count(id) from blog_follow_user where touser = u.id) as numberOfFans, -- 粉丝数
    (select sum(favoriteNumber) from blog_article where userid = u.id) as favoriteNumber, -- 喜欢数
    (select sum(numberOfWords) from blog_article where userid = u.id) as numberOfWords -- 字数
from blog_user as u;

create view v_blog_article as
    select a.*,
    (select username from blog_user where id = a.userid) as username, -- 用户名
    (select count(id) from blog_comment where articleid = a.id) as numberOfComments -- 评论数
from blog_article as a;

create view v_blog_subject as
	select s.*,
	(select count(id) from blog_article where subjectid = s.id) as numberOfArticles, -- 文章数
	(select count(id) from blog_follow_subject where tosubject = s.id) as numberOfConcerns -- 关注数
from blog_subject as s;

create view v_blog_comment as
	select c.*,
	(select username from blog_user where id = c.userid) as username
from blog_comment as c
