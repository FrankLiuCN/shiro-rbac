CREATE DATABASE shiro_rbac;

USE shiro_rbac;

CREATE TABLE `user`(
`user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户编号',
`login_name` varchar(50) NOT NULL COMMENT '登录名',
`nick_name` varchar(20) NOT NULL COMMENT '昵称',
`password` varchar(50) NOT NULL COMMENT '密码',
`create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`last_login_time` TIMESTAMP COMMENT '最后登录时间',
`status` bigint(1) DEFAULT '1' COMMENT '1:有效，0:禁止登录',
PRIMARY KEY(user_id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8 COMMENT='用户信息表';

INSERT INTO 
	`user`(`login_name`,`nick_name`,`password`,`create_time`,`last_login_time`,`status`)
values
	('admin','管理员','123456',now(),null,1);

CREATE TABLE `role`(
`role_id` int NOT NULL AUTO_INCREMENT COMMENT '角色编号',
`role_name` varchar(20) NOT NULL COMMENT '角色名称',
PRIMARY KEY(role_id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8 COMMENT='角色表';

CREATE TABLE `user_role`(
`user_id` int NOT NULL COMMENT '用户编号',
`role_id` int NOT NULL COMMENT '角色编号'
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='用户角色表';

CREATE TABLE `menu`(
`menu_id`  int NOT NULL AUTO_INCREMENT COMMENT '菜单编号',
`parent_id` varchar(50) NOT NULL COMMENT '菜单父级编号',
`name` varchar(100) NOT NULL COMMENT '名称',
`sort` int COMMENT '排序',
`href` varchar(2000) COMMENT '链接',
`icon` varchar(100) COMMENT '图标',
`enable` char(1) NOT NULL COMMENT '是否启用',
`permission` varchar(100) COMMENT '权限标识',
`remark`  varchar(255) COMMENT '备注信息',
`create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
PRIMARY KEY(`menu_id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8 COMMENT='菜单表';

CREATE TABLE `role_menu`(
`user_id` int NOT NULL COMMENT '用户编号',
`menu_id` int NOT NULL COMMENT '菜单编号'
)ENGINE=InnoDB DEFAULT CHARSET=UTF8 COMMENT='角色菜单表';



















