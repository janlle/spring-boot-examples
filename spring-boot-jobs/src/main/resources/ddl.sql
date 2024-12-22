CREATE TABLE `job_info` (
  `id` bigint primary key AUTO_INCREMENT,
  `job_id` varchar(32) DEFAULT NULL COMMENT '任务ID',
  `job_name` varchar(20) DEFAULT NULL COMMENT '任务类名',
  `job_group` varchar(20) DEFAULT NULL COMMENT '任务分组',
  `job_class_name` varchar(255) DEFAULT NULL COMMENT '任务类名',
  `job_cron` varchar(255) DEFAULT NULL COMMENT 'cron表达式',
  `job_param` varchar(255) DEFAULT NULL COMMENT '参数',
  `job_desc` varchar(255) DEFAULT NULL COMMENT '描述',
  `job_status` int(1) DEFAULT NULL COMMENT '任务状态',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modify` datetime DEFAULT NULL COMMENT '修改时间',
  `deleted` int(1) DEFAULT 0 COMMENT '删除状态:0未删除，1已删除'
) ENGINE=InnoDB COMMENT='任务信息表';
