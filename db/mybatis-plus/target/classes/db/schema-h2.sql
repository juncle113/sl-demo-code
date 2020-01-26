DROP TABLE IF EXISTS user;

CREATE TABLE user
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	parent_id BIGINT(20) NULL DEFAULT NULL COMMENT '上级ID',
	create_time DATETIME NULL DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (id)
);