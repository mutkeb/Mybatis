create table  user(
    `id` bigint UNSIGNED AUTO_INCREMENT not null ,
    PRIMARY  key (`id`),
    `user_name` varchar(20) not null ,
    `pwd` varchar (32) not null ,
    `nick_name` varchar(20),
    `avatar` varchar(20),
    `gmt_created` datetime,
    `gmt_modified` datetime
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create  table comment(
    `id` bigint UNSIGNED AUTO_INCREMENT not null ,
    PRIMARY  key (`id`),
    `ref_id` varchar (32) not null ,
    `user_id` bigint not null ,
    `content` varchar (1000) not null,
    `parent_id` bigint,
    `gmt_created` datetime,
    `gmt_modified` datetime
)ENGINE=InnoDB DEFAULT  CHARSET=utf8;