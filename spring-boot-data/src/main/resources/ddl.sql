create table t_user_address (
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


DROP TABLE IF EXISTS trade_order_item;
CREATE TABLE trade_order_item (
    id           bigint not null auto_increment,
    order_id     varchar(255) default null,
    user_id      varchar(255) default null,
    goods_id     varchar(255) default null,
    goods_name   varchar(255) default null,
    goods_type   int,
    order_state  int,
    goods_pic    varchar(255) default null,
    item_price   decimal(18, 6),
    item_count   int,
    deleted      int,
    lock_version int,
    gmt_create   datetime,
    gmt_modified datetime,
    PRIMARY KEY (id)
) ENGINE = InnoDB CHARACTER SET = UTF8;

DROP TABLE IF EXISTS trade_order;
CREATE TABLE trade_order (
    id              bigint not null auto_increment,
    user_id         varchar(255) default null,
    user_address_id varchar(255) default null,
    state           int,
    item_count      int,
    paid_amount     decimal(18, 6),
    pay_channel     int,
    pay_time        datetime,
    finish_time     datetime,
    deleted         int,
    lock_version    int,
    gmt_create      datetime,
    gmt_modified    datetime,
    PRIMARY KEY (id)
) ENGINE = InnoDB CHARACTER SET = UTF8;
