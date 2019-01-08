create table if not exists t_broker_message_log (
  `message_id` varchar(128) not null,
  `message` varchar(1024)not null default '',
  `try_count` int(4) default 0,
  `status` varchar(10) default '',
  `next_retry` timestamp not null default '0000-00-00 00:00:00',
  `create_time` timestamp not null default '0000-00-00 00:00:00',
  `update_time` timestamp not null default '0000-00-00 00:00:00',
  primary key(message_id)
)engine=InnoDB default charset=utf8;