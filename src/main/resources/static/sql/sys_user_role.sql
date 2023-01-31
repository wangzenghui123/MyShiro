/*
 Navicat Premium Data Transfer

 Source Server         : wzh
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : test01

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 31/01/2023 12:37:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `cerate_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', 'd71c2f18-785e-42a9-952b-133a8a8307df', '1', NULL);
INSERT INTO `sys_user_role` VALUES ('2', 'd71c2f18-785e-42a9-952b-133a8a8307df', '2', NULL);
INSERT INTO `sys_user_role` VALUES ('3', '81c00141-95eb-429c-be40-2b4d7e3dac64', '1', NULL);
INSERT INTO `sys_user_role` VALUES ('4', 'd71c2f18-785e-42a9-952b-133a8a8307df', '3', NULL);

SET FOREIGN_KEY_CHECKS = 1;
