/*
Navicat MySQL Data Transfer

Source Server         : 120.77.33.46
Source Server Version : 50717
Source Host           : 120.77.33.46:3306
Source Database       : zcsys_bakups

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-01-02 15:26:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_schedulejob
-- ----------------------------
DROP TABLE IF EXISTS `t_schedulejob`;
CREATE TABLE `t_schedulejob` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(300) NOT NULL COMMENT '版本号',
  `name` varchar(300) NOT NULL COMMENT '客户ID',
  `cron` varchar(300) NOT NULL COMMENT '投资ID',
  `status` int(11) NOT NULL COMMENT '状态',
  `auto_start` int(11) DEFAULT NULL COMMENT '自动启动',
  `is_del` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(100) DEFAULT NULL COMMENT '创建人',
  `edit_date` datetime DEFAULT NULL COMMENT '修改时间',
  `editor` varchar(100) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_schedulelog
-- ----------------------------
DROP TABLE IF EXISTS `t_schedulelog`;
CREATE TABLE `t_schedulelog` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uuid` varchar(300) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `begin_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `run_duration` bigint(20) DEFAULT NULL,
  `error_msg` varchar(10000) DEFAULT NULL,
  `is_del` tinyint(1) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `creator` varchar(100) DEFAULT NULL,
  `edit_date` datetime DEFAULT NULL,
  `editor` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36066 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_zcsys_download_isdel
-- ----------------------------
DROP TABLE IF EXISTS `t_zcsys_download_isdel`;
CREATE TABLE `t_zcsys_download_isdel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `download_path` varchar(200) DEFAULT NULL COMMENT '下载地址',
  `save_path` varchar(200) DEFAULT NULL COMMENT '保存地址',
  `status` varchar(10) DEFAULT NULL COMMENT '状态 0.待下载 1.下载成功 2.下载失败',
  `download_time` datetime DEFAULT NULL COMMENT '下载时间',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `code` varchar(20) DEFAULT NULL COMMENT '企业编码',
  `version` varchar(20) DEFAULT NULL COMMENT '日期标识',
  `filesize` varchar(200) DEFAULT NULL COMMENT '文件大小',
  `arbtr_code` varchar(100) DEFAULT NULL COMMENT '立案编号',
  `type` varchar(10) DEFAULT NULL COMMENT 'zip : 压缩包 file： 文件',
  `filename` varchar(200) DEFAULT NULL COMMENT '文件名称',
  PRIMARY KEY (`id`),
  KEY `index_download_code` (`code`),
  KEY `index_download_path` (`download_path`),
  KEY `index_download_version` (`version`),
  KEY `index_download_savepath` (`save_path`)
) ENGINE=InnoDB AUTO_INCREMENT=1775 DEFAULT CHARSET=utf8 COMMENT='下载备份记录表';

-- ----------------------------
-- Table structure for t_zcsys_download_log
-- ----------------------------
DROP TABLE IF EXISTS `t_zcsys_download_log`;
CREATE TABLE `t_zcsys_download_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `download_path` varchar(200) DEFAULT NULL COMMENT '下载地址',
  `save_path` varchar(200) DEFAULT NULL COMMENT '保存地址',
  `status` varchar(10) DEFAULT NULL COMMENT '状态 0.待下载 1.下载成功 2.下载失败',
  `download_time` datetime DEFAULT NULL COMMENT '下载时间',
  `create_time` datetime DEFAULT NULL COMMENT '添加时间',
  `code` varchar(20) DEFAULT NULL COMMENT '企业编码',
  `version` varchar(20) DEFAULT NULL COMMENT '日期标识',
  `filesize` varchar(200) DEFAULT NULL COMMENT '文件大小',
  `arbtr_code` varchar(100) DEFAULT NULL COMMENT '立案编号',
  `type` varchar(10) DEFAULT NULL COMMENT 'zip : 压缩包 file： 文件',
  `filename` varchar(200) DEFAULT NULL COMMENT '文件名称',
  PRIMARY KEY (`id`),
  KEY `index_download_code` (`code`),
  KEY `index_download_path` (`download_path`),
  KEY `index_download_version` (`version`),
  KEY `index_download_savepath` (`save_path`)
) ENGINE=InnoDB AUTO_INCREMENT=1800 DEFAULT CHARSET=utf8 COMMENT='下载备份记录表';
