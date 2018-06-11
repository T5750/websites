/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50638
Source Host           : localhost:3306
Source Database       : rocketmq-transaction-balance

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2018-06-11 12:52:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `balance`
-- ----------------------------
DROP TABLE IF EXISTS `balance`;
CREATE TABLE `balance` (
  `userid` varchar(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `amount` double DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
