drop table if exists t_person;

create table if not exists t_person (
  id       int primary key auto_increment comment '主键',
  name     varchar(48) comment '姓名',
  age      int comment '年龄',
  sex      varchar(8) comment '性别',
  address  varchar(48) comment '地址',
  birthday timestamp comment '生日'
);
