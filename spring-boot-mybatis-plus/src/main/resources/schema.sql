
CREATE TABLE `user1`  (
  `user_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `gender` int(1) NULL DEFAULT NULL,
  `age` int(3) NULL DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `deleted` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4;





