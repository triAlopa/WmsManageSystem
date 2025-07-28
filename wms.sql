/*
 Navicat Premium Data Transfer

 Source Server         : Mysql80
 Source Server Type    : MySQL
 Source Server Version : 80041
 Source Host           : localhost:3306
 Source Schema         : wms

 Target Server Type    : MySQL
 Target Server Version : 80041
 File Encoding         : 65001

 Date: 28/07/2025 17:04:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '货名',
  `storage` int NOT NULL COMMENT '仓库',
  `goodsType` int NOT NULL COMMENT '分类',
  `count` int NULL DEFAULT NULL COMMENT '数量',
  `remark` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '篮球', 1, 1, 588, '2223');
INSERT INTO `goods` VALUES (2, '足球', 2, 2, 27, '21');
INSERT INTO `goods` VALUES (3, '羽毛球', 2, 2, 81, '');
INSERT INTO `goods` VALUES (5, '排球', 1, 0, 666, '');

-- ----------------------------
-- Table structure for goodstype
-- ----------------------------
DROP TABLE IF EXISTS `goodstype`;
CREATE TABLE `goodstype`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '分类名',
  `remark` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2075025410 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goodstype
-- ----------------------------
INSERT INTO `goodstype` VALUES (0, '食品类', '75');
INSERT INTO `goodstype` VALUES (1, '蔬菜类', '111122');
INSERT INTO `goodstype` VALUES (2, '医疗类', '烦烦烦方法');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int NOT NULL,
  `menuCode` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单编码',
  `menuName` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单名字',
  `menuLevel` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单级别',
  `menuParentCode` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单的父Code',
  `menuClick` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '点击触发的函数',
  `menuRight` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限：0超级管理员，1管理员，2普通用户（可逗号组合）',
  `menuComponent` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `menuIcon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '001', '管理员管理', '1', NULL, 'Admin', '0', 'admin/AdminManage.vue', 'el-icon-s-custom');
INSERT INTO `menu` VALUES (2, '002', '用户管理', '1', NULL, 'User', '0,1', 'user/UserManage.vue', 'el-icon-user-solid');
INSERT INTO `menu` VALUES (3, '003', '仓库管理', '1', NULL, 'Storage', '0,1', 'storage/StorageManage.vue', 'el-icon-receiving');
INSERT INTO `menu` VALUES (4, '004', '分类管理', '1', NULL, 'GoodsType', '0,1', 'goodsType/GoodsTypeManage.vue', 'el-icon-menu');
INSERT INTO `menu` VALUES (5, '005', '物品管理', '1', NULL, 'Goods', '0,1,2', 'goods/GoodsManage.vue', 'el-icon-coin');
INSERT INTO `menu` VALUES (6, '006', '记录管理', '1', NULL, 'Record', '0,1,2', 'record/RecordManage.vue', 'el-icon-edit-outline');

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods` int NOT NULL COMMENT '货品ID',
  `userId` int NULL DEFAULT NULL COMMENT '取货人/补货人ID',
  `admin_id` int NULL DEFAULT NULL COMMENT '操作人ID',
  `count` int NULL DEFAULT NULL COMMENT '数量',
  `createtime` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间(默认当前时间)',
  `remark` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '操作记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES (-1926275071, 1, NULL, 1, 20, '2025-07-28 16:57:10', '2223');
INSERT INTO `record` VALUES (-1712390142, 1, 5, 1, 13, '2025-07-27 23:19:03', '2223');
INSERT INTO `record` VALUES (-332464126, 1, 5, 1, 16, '2025-07-27 23:19:42', '2223');
INSERT INTO `record` VALUES (15663106, 2, 17, 1, -33, '2025-07-27 23:20:32', '21');
INSERT INTO `record` VALUES (2070872065, 1, 5, 1, 19, '2025-07-27 23:19:20', '2223');

-- ----------------------------
-- Table structure for storage
-- ----------------------------
DROP TABLE IF EXISTS `storage`;
CREATE TABLE `storage`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '仓库名',
  `remark` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1810845698 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of storage
-- ----------------------------
INSERT INTO `storage` VALUES (1, '仓库1', '1111');
INSERT INTO `storage` VALUES (2, '仓库2', NULL);
INSERT INTO `storage` VALUES (3, '仓库3', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `no` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '账号',
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '姓名',
  `password` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '密码',
  `age` int NULL DEFAULT NULL,
  `sex` int NULL DEFAULT NULL COMMENT '性别',
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '电话',
  `role_id` int NULL DEFAULT NULL COMMENT '角色 0超级管理员, 1管理员,2普通账号',
  `isValid` varchar(4) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT 'Y' COMMENT '是否有效, Y有效,其他无效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '1', 'superadmin', '123', 18, 0, '123456', 0, 'Y');
INSERT INTO `user` VALUES (2, 'admin11', 'user2', '123', 18, 0, '15677888877', 1, 'Y');
INSERT INTO `user` VALUES (4, 'admin4', 'user4', 'qwer1234', 121, 0, NULL, 1, 'Y');
INSERT INTO `user` VALUES (5, 'adja', 'user5', '123456', 18, 0, '15766778899', 2, 'Y');
INSERT INTO `user` VALUES (7, 'adada', 'user', '1232dasd', NULL, 1, NULL, 1, 'Y');
INSERT INTO `user` VALUES (8, 'adada', 'user', '1232dasd', NULL, 1, NULL, 1, 'Y');
INSERT INTO `user` VALUES (9, 'adada', 'user', '1232dasd', NULL, 1, NULL, 1, 'Y');
INSERT INTO `user` VALUES (10, 'adada', 'user', '1232dasd', NULL, 1, NULL, 1, 'Y');
INSERT INTO `user` VALUES (11, 'adada', 'user', '1232dasd', NULL, 1, NULL, 1, 'Y');
INSERT INTO `user` VALUES (12, 'adada', 'user', '1232dasd', NULL, 1, NULL, 1, 'Y');
INSERT INTO `user` VALUES (13, 'adada', 'user', '1232dasd', NULL, 1, NULL, 1, 'Y');
INSERT INTO `user` VALUES (14, 'adada', 'user', '1232dasd', NULL, 1, NULL, 1, 'Y');
INSERT INTO `user` VALUES (15, 'adada', 'user', '1232dasd', NULL, 1, NULL, 1, 'Y');
INSERT INTO `user` VALUES (16, 'adada', 'user', '1232dasd', NULL, 1, NULL, 1, 'Y');
INSERT INTO `user` VALUES (17, '16', '123', '312', 123, 1, '123123', 2, 'Y');
INSERT INTO `user` VALUES (18, '18', '123', '123123', 412, 1, '12324', 1, 'Y');
INSERT INTO `user` VALUES (19, '19', '123', '1234', 123, 0, '123123', 1, 'Y');
INSERT INTO `user` VALUES (20, '20', '123', 'fe', 36, 1, '43567', 1, 'Y');
INSERT INTO `user` VALUES (21, '123', '123', '123', 12, 1, '15675527862', 1, 'Y');
INSERT INTO `user` VALUES (24, 'admin123', 'admin123', 'admin123', 16, 1, '15677990088', 1, 'Y');
INSERT INTO `user` VALUES (25, 'admin112', 'admin112', '123455', 17, 0, '15744667788', 1, 'Y');

SET FOREIGN_KEY_CHECKS = 1;
