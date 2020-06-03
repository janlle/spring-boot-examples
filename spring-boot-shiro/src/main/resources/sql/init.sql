/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : db01

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 02/06/2020 17:08:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `permission_id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `sort` int(0) NULL DEFAULT NULL,
  `parent_id` int(0) NULL DEFAULT NULL,
  `permission_type` int(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, '用户管理', '/shiro/user', 1, 1, 0, 0, '2020-05-25 14:45:18');
INSERT INTO `sys_permission` VALUES (2, '用户添加', '/shiro/user/add', 1, 1, 1, 0, '2020-05-25 14:45:18');
INSERT INTO `sys_permission` VALUES (3, '用户删除', '/shiro/user/delete', 1, 1, 1, 0, '2020-05-25 14:45:18');
INSERT INTO `sys_permission` VALUES (4, '用户修改', '/shiro/user/update', 1, 1, 1, 0, '2020-05-25 14:45:18');
INSERT INTO `sys_permission` VALUES (5, '文章管理', '/shiro/article', 1, 1, 0, 0, '2020-05-25 14:45:18');
INSERT INTO `sys_permission` VALUES (6, '文章管理修改', '/shiro/article/update', 1, 1, 5, 0, '2020-05-25 14:45:18');
INSERT INTO `sys_permission` VALUES (7, '文章管理删除', '/shiro/article/delete', 1, 1, 5, 0, '2020-05-25 14:45:18');
INSERT INTO `sys_permission` VALUES (8, '文章管理查询', '/shiro/article/list', 1, 1, 5, 0, '2020-05-25 14:45:18');

-- ----------------------------
-- Table structure for sys_permission_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_role`;
CREATE TABLE `sys_permission_role`  (
  `id` int(0) NOT NULL,
  `permission_id` int(0) NULL DEFAULT NULL,
  `role_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `permission`(`permission_id`) USING BTREE,
  INDEX `role`(`role_id`) USING BTREE,
  CONSTRAINT `permission` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`permission_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_permission_role
-- ----------------------------
INSERT INTO `sys_permission_role` VALUES (1, 1, 1);
INSERT INTO `sys_permission_role` VALUES (2, 2, 1);
INSERT INTO `sys_permission_role` VALUES (3, 3, 1);
INSERT INTO `sys_permission_role` VALUES (4, 4, 1);
INSERT INTO `sys_permission_role` VALUES (5, 1, 2);
INSERT INTO `sys_permission_role` VALUES (6, 2, 2);
INSERT INTO `sys_permission_role` VALUES (7, 5, 1);
INSERT INTO `sys_permission_role` VALUES (8, 6, 1);
INSERT INTO `sys_permission_role` VALUES (9, 7, 1);
INSERT INTO `sys_permission_role` VALUES (10, 8, 1);
INSERT INTO `sys_permission_role` VALUES (11, 1, 4);
INSERT INTO `sys_permission_role` VALUES (12, 2, 4);
INSERT INTO `sys_permission_role` VALUES (13, 3, 4);
INSERT INTO `sys_permission_role` VALUES (14, 4, 4);
INSERT INTO `sys_permission_role` VALUES (15, 1, 3);
INSERT INTO `sys_permission_role` VALUES (16, 2, 3);
INSERT INTO `sys_permission_role` VALUES (17, 4, 3);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', '管理员角色', '2020-06-01 14:44:26', 1);
INSERT INTO `sys_role` VALUES (2, '员工', '员工角色', '2020-06-01 14:44:26', 1);
INSERT INTO `sys_role` VALUES (3, '组长', '组长角色', '2020-06-01 14:44:26', 1);
INSERT INTO `sys_role` VALUES (4, '经理', '经理角色', '2020-06-01 14:44:26', 1);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'admin',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '员工', 'james', '21232f297a57a5a743894a0e4a801fc3', '123', 1, '2020-06-01 14:44:26');
INSERT INTO `sys_user` VALUES (2, '老板', 'tom', '21232f297a57a5a743894a0e4a801fc3', '123', 1, '2020-06-01 14:44:26');
INSERT INTO `sys_user` VALUES (3, '经理', 'kobe', '21232f297a57a5a743894a0e4a801fc3', '123', 1, '2020-06-01 14:44:26');
INSERT INTO `sys_user` VALUES (4, '组长', 'lyon', '21232f297a57a5a743894a0e4a801fc3', '123', 1, '2020-06-01 14:44:26');
INSERT INTO `sys_user` VALUES (5, '员工', 'Jack', '21232f297a57a5a743894a0e4a801fc3', '123', 1, '2020-06-01 14:44:26');
INSERT INTO `sys_user` VALUES (6, '员工', 'andy', '21232f297a57a5a743894a0e4a801fc3', '123', 1, '2020-06-01 14:44:26');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(0) NOT NULL,
  `role_id` int(0) NULL DEFAULT NULL,
  `user_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user`(`user_id`) USING BTREE,
  INDEX `rule_user`(`role_id`) USING BTREE,
  CONSTRAINT `rule_user` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 2);
INSERT INTO `sys_user_role` VALUES (2, 2, 1);
INSERT INTO `sys_user_role` VALUES (3, 1, 3);
INSERT INTO `sys_user_role` VALUES (4, 3, 4);
INSERT INTO `sys_user_role` VALUES (5, 4, 3);
INSERT INTO `sys_user_role` VALUES (7, 2, 3);
INSERT INTO `sys_user_role` VALUES (8, 2, 4);
INSERT INTO `sys_user_role` VALUES (9, 2, 5);
INSERT INTO `sys_user_role` VALUES (10, 2, 6);
INSERT INTO `sys_user_role` VALUES (11, 1, 3);
INSERT INTO `sys_user_role` VALUES (12, 4, 2);

SET FOREIGN_KEY_CHECKS = 1;
