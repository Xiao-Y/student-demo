/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : mytestdb

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2017-07-04 10:08:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_leave
-- ----------------------------
DROP TABLE IF EXISTS `t_leave`;
CREATE TABLE `t_leave` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `start_time` datetime DEFAULT NULL COMMENT '请假起始时间',
  `end_time` datetime DEFAULT NULL COMMENT '请假结束时间',
  `apply_time` datetime DEFAULT NULL COMMENT '请假申请时间',
  `leave_type` varchar(3) NOT NULL COMMENT '请假类型',
  `reason` varchar(400) DEFAULT NULL COMMENT '原因',
  `status` varchar(2) NOT NULL COMMENT '状态 1-提交申请，3-审批中，5-审批通过，7-驳回',
  `user_name` varchar(11) DEFAULT NULL COMMENT '用户名称',
  `type` varchar(11) DEFAULT NULL COMMENT '流程类型',
  `process_instance_id` varchar(100) DEFAULT NULL COMMENT '流程实例id',
  PRIMARY KEY (`id`,`leave_type`,`status`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;
