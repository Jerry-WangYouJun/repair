/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost
 Source Database       : sxsh

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : utf-8

 Date: 02/10/2019 21:57:08 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `card`
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card` (
  `card_id` int(10) NOT NULL AUTO_INCREMENT,
  `card_name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '卡名称',
  `card_type` int(10) NOT NULL COMMENT '卡类型',
  `card_range` int(10) DEFAULT NULL COMMENT '辖区代码',
  `card_number` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '卡号',
  `card_balance` double DEFAULT NULL COMMENT '余额',
  `card_status` int(2) NOT NULL COMMENT '0 有效 1 失效',
  `member_id` int(10) DEFAULT NULL COMMENT '会员id',
  `remark` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `pwd` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`card_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `card_type`
-- ----------------------------
DROP TABLE IF EXISTS `card_type`;
CREATE TABLE `card_type` (
  `type_id` int(10) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '类型名称',
  `type_code` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '类型代码',
  `bonus` double DEFAULT NULL COMMENT '赠送金额',
  `remark` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `dic_company`
-- ----------------------------
DROP TABLE IF EXISTS `dic_company`;
CREATE TABLE `dic_company` (
  `name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '公司名称',
  `code` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '公司简称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `dic_division`
-- ----------------------------
DROP TABLE IF EXISTS `dic_division`;
CREATE TABLE `dic_division` (
  `division_code` int(10) NOT NULL COMMENT '辖区代码',
  `division_name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '辖区名称',
  `parent_code` int(10) DEFAULT NULL COMMENT '父级代码'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `member`
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `member_id` int(10) NOT NULL AUTO_INCREMENT,
  `member_code` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '会员编号',
  `role` int(10) NOT NULL COMMENT '角色',
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
  `id_card` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证号',
  `consume_detaile` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `open_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '微信open_id',
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` int(10) NOT NULL AUTO_INCREMENT,
  `order_number` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '清单号',
  `card_number` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '卡号',
  `order_content` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '服务内容',
  `order_date` date NOT NULL COMMENT '消费日期',
  `duration` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '时长',
  `order_money` double NOT NULL COMMENT '消费金额',
  `brokerage` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '经手人',
  `remark` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `state` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `purchase_record`
-- ----------------------------
DROP TABLE IF EXISTS `purchase_record`;
CREATE TABLE `purchase_record` (
  `record_id` int(10) NOT NULL AUTO_INCREMENT,
  `card_number` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '卡号',
  `order_number` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '工单号',
  `order_date` date DEFAULT NULL COMMENT '日期',
  `purchase_money` double DEFAULT NULL COMMENT '金额',
  `purchase_type` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'consume 消费  recharge充值',
  `remark` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(10) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '角色名称',
  `role_code` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '角色代码',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '登录账号',
  `password` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '登录密码',
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '昵称',
  `role_id` int(10) NOT NULL COMMENT '角色',
  `openid` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

SET FOREIGN_KEY_CHECKS = 1;
