/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50517
Source Host           : localhost:3306
Source Database       : pms

Target Server Type    : MYSQL
Target Server Version : 50517
File Encoding         : 65001

Date: 2016-01-20 15:37:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `pms_picture`
-- ----------------------------
DROP TABLE IF EXISTS `pms_picture`;
CREATE TABLE `pms_picture` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `small_img_url` varchar(200) DEFAULT NULL COMMENT '压缩后的图片地址',
  `big_img_url` varchar(200) NOT NULL COMMENT '高清图片地址',
  `old_img_name` varchar(100) DEFAULT NULL COMMENT '图片原名称',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '上传时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志 （0 正常， 1 删除）',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `create_by` bigint(20) NOT NULL COMMENT '创建者',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `belong_by` bigint(20) NOT NULL COMMENT '图片属于哪个客户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pms_picture
-- ----------------------------

-- ----------------------------
-- Table structure for `pms_user`
-- ----------------------------
DROP TABLE IF EXISTS `pms_user`;
CREATE TABLE `pms_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(50) NOT NULL COMMENT '登录名',
  `salt` varchar(64) NOT NULL COMMENT '加密计算的盐',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `user_type` char(1) NOT NULL DEFAULT '1' COMMENT '用户类型 1-客户 2-商家 3-印刷厂商',
  `create_by` bigint(20) NOT NULL COMMENT '创建者',
  `create_date` timestamp NULL DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL,
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `login_ip` varchar(50) DEFAULT NULL COMMENT '最后登录IP',
  `login_flag` char(1) NOT NULL DEFAULT '0' COMMENT '是否可登录标志 （0 正常， 1 禁用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志 （0 正常， 1 删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pms_user
-- ----------------------------
INSERT INTO `pms_user` VALUES ('1', 'admin', 'w9348utfijaori', '123456', null, null, null, null, null, '1', '1', '2016-01-20 14:10:16', null, null, '2016-01-20 14:10:22', null, '0', '0');
