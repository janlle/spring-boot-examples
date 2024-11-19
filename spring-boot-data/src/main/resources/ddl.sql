create table t_user_address
(
    id                bigint primary key not null auto_increment,
    user_id           varchar(128)       not null comment '用户表用户ID',
    telephone         varchar(11)        not null comment '收货人手机号',
    username          varchar(128)       not null comment '收货人姓名',
    specifics_address varchar(255)       not null comment '详细地址',
    default_address   int(2)             not null comment '是否是默认地址',
    lock_version      int(2)             not null comment '乐观锁版本号',
    gmt_create        timestamp          not null default current_timestamp comment '创建时间' null,
    gmt_modified      timestamp          not null default current_timestamp comment '更新时间' null,
    deleted           int(2)             not null comment '是否删除'
) engine = InnoDB;
