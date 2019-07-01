/*
Navicat MySQL Data Transfer

Source Server         : 3346
Source Server Version : 50717
Source Host           : 120.77.33.46:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-12-20 15:39:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_arbtr_subject_info
-- ----------------------------
DROP TABLE IF EXISTS `t_arbtr_subject_info`;
CREATE TABLE `t_arbtr_subject_info` (
  `ID` int(32) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `subject_type` tinyint(4) NOT NULL COMMENT '主体类别 0.自然人 1.企业',
  `subject_sex` tinyint(4) DEFAULT NULL COMMENT '性别 0.男 1.女',
  `subject_nation` tinyint(4) DEFAULT NULL COMMENT '民族',
  `subject_birthday` date DEFAULT NULL COMMENT '出生年月日 格式YYYY-MM-DD',
  `subject_entity_id` varchar(30) NOT NULL COMMENT '主体证件编号 1.自然人为身份证号 2.企业为统一社会信用编码',
  `subject_enterprise_type` varchar(50) DEFAULT NULL COMMENT '企业类型',
  `subject_address` varchar(200) DEFAULT NULL COMMENT '地址',
  `subject_phone` varchar(15) DEFAULT NULL COMMENT '联系电话 手机号是11位 企业400是10',
  `subject_email` varchar(320) DEFAULT NULL COMMENT '邮箱,email地址分为2个部分：local part和domain part,local part 为“@”前面的部分，最多64个字符domain part 为“@”后面的部分，最多255个字符，255+64+“@”一个=320个字符',
  `CREATION_DATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` int(11) NOT NULL DEFAULT '-1',
  `LAST_UPDATED_BY` int(11) NOT NULL DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='仲裁案件申请人 被很请人信息表';

-- ----------------------------
-- Table structure for t_main_manager
-- ----------------------------
DROP TABLE IF EXISTS `t_main_manager`;
CREATE TABLE `t_main_manager` (
  `TEMPLATE_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '表ID，主键，供其他表做外键',
  `ORGANIZATION_ID` tinyint(4) NOT NULL COMMENT '组织ID',
  `DESCRIPTION` varchar(30) DEFAULT NULL COMMENT '备注，有条件的话做成多行文本',
  `SOURCE_CODE` int(12) NOT NULL COMMENT '源系统代码，追溯字段，默认=当前Node',
  `SOURCE_LINE_ID` decimal(18,6) NOT NULL COMMENT '源系统行ID，追溯字段，默认=本表ID',
  `SOURCE_REFERENCE` date DEFAULT NULL COMMENT '源系统参考，显示在界面供用户看',
  `PROCESS_GROUP_ID` datetime DEFAULT NULL COMMENT '后台处理组ID，供分批、并发控制用',
  `PROCESS_STATUS` blob NOT NULL COMMENT '文本信息',
  `CREATION_DATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` int(11) NOT NULL DEFAULT '-1',
  `LAST_UPDATED_BY` int(11) NOT NULL DEFAULT '-1',
  `LAST_UPDATE_DATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_LOGIN` int(11) DEFAULT NULL,
  PRIMARY KEY (`TEMPLATE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='仲裁中心管理员表';

-- ----------------------------
-- Table structure for zcsys_arbitralr_admin
-- ----------------------------
DROP TABLE IF EXISTS `zcsys_arbitralr_admin`;
CREATE TABLE `zcsys_arbitralr_admin` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '仲裁管理员ID',
  `title` varchar(20) DEFAULT NULL COMMENT '仲裁管理员职称',
  `domicile` varchar(50) DEFAULT NULL COMMENT '居住地',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=503122822 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zcsys_arbitral_arbitrator
-- ----------------------------
DROP TABLE IF EXISTS `zcsys_arbitral_arbitrator`;
CREATE TABLE `zcsys_arbitral_arbitrator` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '仲裁管理员ID',
  `title` varchar(50) DEFAULT NULL COMMENT '仲裁管理员职称',
  `domicile` varchar(50) DEFAULT NULL COMMENT '居住地',
  `secretary_id` int(11) DEFAULT NULL,
  `signature` text COMMENT '签名',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=518161932 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zcsys_arbitral_court
-- ----------------------------
DROP TABLE IF EXISTS `zcsys_arbitral_court`;
CREATE TABLE `zcsys_arbitral_court` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '仲裁庭ID 全局一个表 自动递增',
  `arbitral_name` varchar(20) DEFAULT NULL COMMENT '仲裁庭全称',
  `arbitral_address` varchar(50) DEFAULT NULL COMMENT '仲裁庭地址',
  `parent_id` int(11) DEFAULT NULL COMMENT '仲裁庭所属机构 ID在本表中',
  `level` int(11) DEFAULT NULL COMMENT '仲裁庭机构级别 比如总部为1 外部机构和分支机构为2',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zcsys_arbitral_examiner
-- ----------------------------
DROP TABLE IF EXISTS `zcsys_arbitral_examiner`;
CREATE TABLE `zcsys_arbitral_examiner` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `examiner_id` int(11) DEFAULT NULL COMMENT '居住地',
  `domicile` varchar(255) DEFAULT NULL COMMENT '居住地',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zcsys_arbitral_user
-- ----------------------------
DROP TABLE IF EXISTS `zcsys_arbitral_user`;
CREATE TABLE `zcsys_arbitral_user` (
  `ID` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT COMMENT '仲裁人员ID',
  `arbitral_court_id` int(11) NOT NULL COMMENT '仲裁庭ID',
  `name` varchar(10) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(4) DEFAULT NULL COMMENT '性别',
  `birthday` varchar(20) DEFAULT NULL COMMENT '生日',
  `birth_place` varchar(50) DEFAULT NULL COMMENT '籍贯',
  `phone` varchar(11) DEFAULT NULL COMMENT '电话',
  `email_ads` varchar(50) DEFAULT NULL COMMENT '电子邮箱',
  `academic` varchar(10) DEFAULT NULL COMMENT '学历',
  `language` varchar(10) DEFAULT NULL COMMENT '外语情况',
  `domicile` varchar(30) DEFAULT NULL COMMENT '居住地',
  `user_type` int(11) DEFAULT NULL COMMENT '用户类型',
  `secretary_id` int(11) DEFAULT NULL COMMENT '秘书ID',
  `admin_id` int(11) DEFAULT NULL COMMENT '管理员ID',
  `arbitrator_id` int(11) DEFAULT NULL COMMENT '仲裁员信息',
  `evaluate` varchar(200) DEFAULT NULL COMMENT '自我评价',
  `position` varchar(20) NOT NULL COMMENT '职位',
  `work_unit` varchar(50) DEFAULT NULL COMMENT '工作单位',
  `major` varchar(50) DEFAULT NULL COMMENT '专业',
  `examiner_id` int(11) DEFAULT NULL COMMENT '审查员编号',
  PRIMARY KEY (`ID`,`position`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zcsys_arbitration
-- ----------------------------
DROP TABLE IF EXISTS `zcsys_arbitration`;
CREATE TABLE `zcsys_arbitration` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `arbtr_code` varchar(32) DEFAULT NULL COMMENT '仲裁编号',
  `client_casecode` varchar(50) DEFAULT NULL COMMENT '工单编号',
  `client_prod_type` varchar(20) DEFAULT NULL COMMENT '产品编号',
  `client_company_code` varchar(8) DEFAULT NULL COMMENT '客户公司代码',
  `client_company_subcode` varchar(8) DEFAULT NULL COMMENT '客户公司部门代码',
  `client_company_fullname` varchar(1000) DEFAULT NULL COMMENT '公司全称',
  `client_company_address` varchar(1000) DEFAULT NULL COMMENT '公司地址',
  `client_company_shortname` varchar(1000) DEFAULT NULL,
  `trigger_arbtr_reason` varchar(120) DEFAULT NULL COMMENT '仲裁原因',
  `contract_amount` varchar(24) DEFAULT NULL COMMENT '合同金额',
  `loan_amount` varchar(24) DEFAULT NULL COMMENT '借款金额',
  `batch_flag` varchar(4) DEFAULT NULL COMMENT '分期标志',
  `contract_sign_ts` varchar(23) DEFAULT NULL COMMENT '合同签订时间',
  `loan_apply_ts` varchar(23) DEFAULT NULL COMMENT '借款申请时间',
  `loan_start_ts` varchar(23) DEFAULT NULL COMMENT '借款开始时间',
  `loan_end_ts` varchar(23) DEFAULT NULL COMMENT '借款结束时间',
  `loan_period` varchar(8) DEFAULT NULL COMMENT '借款时长',
  `loan_period_unit` varchar(8) DEFAULT NULL COMMENT '借款时长单位',
  `loan_interest_yearly` varchar(8) DEFAULT NULL COMMENT '借款年利率',
  `loan_penal_interest_daily` varchar(8) DEFAULT NULL COMMENT '违约日利率',
  `loan_mortgage` varchar(40) DEFAULT NULL COMMENT '资产抵押信息',
  `loan_usage` varchar(1000) DEFAULT NULL,
  `loan_ppl_name` varchar(50) DEFAULT NULL,
  `loan_ppl_sex` varchar(4) DEFAULT NULL COMMENT '借款人性别',
  `loan_ppl_nation` varchar(24) DEFAULT NULL COMMENT '借款人民族',
  `loan_ppl_id` varchar(20) DEFAULT NULL COMMENT '借款人身份证',
  `loan_ppl_address` varchar(1000) DEFAULT NULL,
  `loan_ppl_phone` varchar(24) DEFAULT NULL COMMENT '借款人电话',
  `loan_foreign_related` varchar(4) DEFAULT NULL COMMENT '是否涉外',
  `backup_field1` varchar(800) DEFAULT NULL COMMENT '玖富工单编号',
  `backup_field2` varchar(16) DEFAULT NULL COMMENT '备用2',
  `backup_field3` varchar(150) DEFAULT NULL,
  `attachment` varchar(10000) DEFAULT NULL,
  `attachment_path1` varchar(100) DEFAULT NULL COMMENT '附件在FTS服务器路径',
  `attachment_path2` varchar(100) DEFAULT NULL COMMENT '附件永久保存路径',
  `paid_for_arbtr` varchar(4) DEFAULT NULL COMMENT '是否付费, Y/N',
  `triggered_arbtr` varchar(4) DEFAULT NULL COMMENT '是否触发仲裁, Y/N',
  `rcd_created_ts` varchar(23) DEFAULT NULL COMMENT '存入数据库的日期',
  `last_upd_dt` varchar(23) DEFAULT NULL COMMENT '最后更新时间',
  `last_upd_usr` varchar(23) DEFAULT NULL COMMENT '最后更新人员',
  `send_type` varchar(12) DEFAULT NULL,
  `email_address` varchar(255) DEFAULT NULL,
  `physical_address` varchar(255) DEFAULT NULL,
  `records` text,
  `package_name` varchar(255) DEFAULT NULL,
  `loan_credit_code` varchar(30) DEFAULT NULL,
  `loan_company_type` varchar(20) DEFAULT NULL,
  `loan_company_corporation` varchar(50) DEFAULT NULL,
  `loan_company_phone_no` varchar(20) DEFAULT NULL,
  `loan_company_address` varchar(500) DEFAULT NULL,
  `loan_company_name` varchar(500) DEFAULT NULL,
  `loan_is_company` varchar(1) DEFAULT NULL,
  `upload_oss_time` varchar(30) DEFAULT NULL,
  `off_line_time` varchar(30) DEFAULT NULL,
  `del_casefile_time` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `backup_field1` (`backup_field1`),
  KEY `arbtrIndex` (`arbtr_code`),
  KEY `company_case_index` (`client_company_code`,`client_casecode`),
  KEY `arb_created_ts_company_code` (`client_company_code`,`rcd_created_ts`)
) ENGINE=InnoDB AUTO_INCREMENT=4086156 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zcsys_arbitration_secretary
-- ----------------------------
DROP TABLE IF EXISTS `zcsys_arbitration_secretary`;
CREATE TABLE `zcsys_arbitration_secretary` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '仲裁秘书ID 所有机构的仲裁ID在这里',
  `secretary_id` int(11) DEFAULT NULL COMMENT '仲裁秘书编号 不同仲裁庭给的编号不同',
  `work_year` float(10,0) DEFAULT NULL COMMENT '工作年限',
  `work_place` varchar(50) DEFAULT NULL COMMENT '工作地点',
  `work_pro` varchar(20) DEFAULT NULL COMMENT '仲裁行业',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=425145625 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zcsys_arbitration_temporary
-- ----------------------------
DROP TABLE IF EXISTS `zcsys_arbitration_temporary`;
CREATE TABLE `zcsys_arbitration_temporary` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `arbtr_code` varchar(32) DEFAULT NULL COMMENT '仲裁编号',
  `client_casecode` varchar(24) DEFAULT NULL COMMENT '客户内部案例编号',
  `client_prod_type` varchar(20) DEFAULT NULL,
  `client_company_code` varchar(8) DEFAULT NULL COMMENT '客户公司代码',
  `client_company_subcode` varchar(8) DEFAULT NULL COMMENT '客户公司部门代码',
  `client_company_fullname` varchar(80) DEFAULT NULL COMMENT '客户公司全名',
  `client_company_address` varchar(160) DEFAULT NULL COMMENT '客户地址',
  `client_company_shortname` varchar(40) DEFAULT NULL COMMENT '客户名简称',
  `trigger_arbtr_reason` varchar(120) DEFAULT NULL COMMENT '仲裁原因',
  `contract_amount` varchar(24) DEFAULT NULL COMMENT '合同金额',
  `loan_amount` varchar(24) DEFAULT NULL COMMENT '借款金额',
  `batch_flag` varchar(4) DEFAULT '' COMMENT '分期标志',
  `contract_sign_ts` varchar(23) DEFAULT NULL COMMENT '合同签订时间',
  `loan_apply_ts` varchar(23) DEFAULT NULL COMMENT '借款申请时间',
  `loan_start_ts` varchar(23) DEFAULT NULL COMMENT '借款开始时间',
  `loan_end_ts` varchar(23) DEFAULT NULL COMMENT '借款结束时间',
  `loan_period` varchar(8) DEFAULT NULL COMMENT '借款时长',
  `loan_period_unit` varchar(8) DEFAULT NULL COMMENT '借款时长单位',
  `loan_interest_yearly` varchar(8) DEFAULT NULL COMMENT '借款年利率',
  `loan_penal_interest_daily` varchar(8) DEFAULT NULL COMMENT '违约日利率',
  `loan_mortgage` varchar(40) DEFAULT NULL COMMENT '资产抵押信息',
  `loan_usage` varchar(80) DEFAULT NULL COMMENT '借款用途',
  `loan_ppl_name` varchar(40) DEFAULT NULL COMMENT '借款人姓名',
  `loan_ppl_sex` varchar(4) DEFAULT NULL COMMENT '借款人性别',
  `loan_ppl_nation` varchar(24) DEFAULT NULL COMMENT '借款人民族',
  `loan_ppl_id` varchar(20) DEFAULT NULL COMMENT '借款人身份证',
  `loan_ppl_address` varchar(160) DEFAULT NULL COMMENT '借款人地址',
  `loan_ppl_phone` varchar(24) DEFAULT NULL COMMENT '借款人电话',
  `loan_foreign_related` varchar(4) DEFAULT NULL COMMENT '是否涉外',
  `attachment` varchar(2000) DEFAULT NULL,
  `paid_for_arbtr` varchar(4) DEFAULT NULL COMMENT '是否付费, Y/N',
  `triggered_arbtr` varchar(4) DEFAULT NULL COMMENT '是否触发仲裁, Y/N',
  `rcd_created_ts` varchar(23) DEFAULT NULL COMMENT '存入数据库的日期',
  `last_upd_dt` varchar(23) DEFAULT NULL COMMENT '最后更新时间',
  `last_upd_usr` varchar(23) DEFAULT NULL COMMENT '最后更新人员',
  `send_type` varchar(12) DEFAULT NULL COMMENT '裁决书送达方式',
  `email_address` varchar(255) DEFAULT NULL,
  `physical_address` varchar(255) DEFAULT NULL,
  `records` varchar(3000) DEFAULT NULL,
  `package_name` varchar(255) DEFAULT NULL,
  `loan_credit_code` varchar(30) DEFAULT NULL,
  `loan_company_name` varchar(500) DEFAULT NULL COMMENT '借款企业全称',
  `loan_company_type` varchar(20) DEFAULT NULL COMMENT '借款企业类型',
  `loan_company_corporation` varchar(50) DEFAULT NULL COMMENT '借款企业法人',
  `loan_company_phone_no` varchar(20) DEFAULT NULL,
  `loan_company_address` varchar(200) DEFAULT NULL COMMENT ' 借款企业地址',
  `loan_is_company` varchar(1) DEFAULT NULL,
  `secretary_name` varchar(255) DEFAULT '' COMMENT '秘书名字',
  `birthday` varchar(255) DEFAULT NULL COMMENT '生日',
  `arbitrationFee` varchar(255) DEFAULT NULL COMMENT '利息',
  `currentTime` varchar(255) DEFAULT NULL COMMENT '出裁决书时间',
  `signature` text COMMENT '仲裁员签名',
  `legaPerson` varchar(255) DEFAULT NULL COMMENT '公司法人',
  `arbitrator_name` varchar(255) DEFAULT NULL,
  `triggerDate` varchar(255) DEFAULT NULL,
  `attachment_path` varchar(255) DEFAULT NULL,
  `installment_amount` varchar(255) DEFAULT NULL,
  `intermediary_company` varchar(255) DEFAULT NULL,
  `loan_past_date` varchar(255) DEFAULT NULL,
  `loan_end_date` varchar(255) DEFAULT NULL,
  `backup_field1` varchar(255) DEFAULT NULL,
  `backup_field2` varchar(255) DEFAULT NULL,
  `backup_field3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zcsys_arbitration_trigger
-- ----------------------------
DROP TABLE IF EXISTS `zcsys_arbitration_trigger`;
CREATE TABLE `zcsys_arbitration_trigger` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `trigger_arbtr_code` varchar(255) NOT NULL COMMENT '仲裁立案编号',
  `trigger_reason` varchar(1000) DEFAULT NULL COMMENT '触发原因',
  `trigger_loan_amount` varchar(24) DEFAULT '0' COMMENT '出借金额',
  `trigger_repayment_amount` varchar(24) DEFAULT '0' COMMENT '已经偿还金额',
  `trigger_remain_amout` varchar(24) DEFAULT '0' COMMENT '未还金额',
  `is_divide` varchar(2) DEFAULT NULL COMMENT '是否分期',
  `divide_total_mount` varchar(15) DEFAULT NULL COMMENT '分期总期数',
  `overdue_start_index` varchar(15) DEFAULT NULL COMMENT '逾期开始期数',
  `backup_field1` varchar(1000) DEFAULT NULL COMMENT '备用1',
  `backup_field2` varchar(1000) DEFAULT NULL COMMENT '备用1',
  `backup_field3` varchar(1000) DEFAULT NULL COMMENT '备用1',
  `attachment` varchar(2000) DEFAULT NULL,
  `attachment_path1` varchar(100) DEFAULT NULL COMMENT '附件在FTS服务器路径',
  `attachment_path2` varchar(100) DEFAULT NULL COMMENT '附件永久保存路径',
  `trigger_created_ts` varchar(23) DEFAULT NULL COMMENT '存入数据库的日期',
  `last_upd_dt` varchar(23) DEFAULT NULL COMMENT '最后更新时间',
  `last_upd_usr` varchar(23) DEFAULT NULL COMMENT '最后更新人员',
  `arbitratorId` int(11) DEFAULT NULL,
  `secretaryId` int(11) DEFAULT NULL,
  `trigger_date` varchar(23) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `loan_ppl_address` varchar(255) DEFAULT NULL,
  `send_time` varchar(100) DEFAULT NULL,
  `reasons_for_refusal` varchar(255) DEFAULT NULL,
  `sec_start_time` date DEFAULT NULL,
  `sec_end_time` date DEFAULT NULL,
  `arb_end_time` date DEFAULT NULL,
  `exa_start_time` date DEFAULT NULL,
  `exa_end_time` date DEFAULT NULL,
  `arb_book_time` date DEFAULT NULL,
  `email_send_time` date DEFAULT NULL,
  `is_transmit` tinyint(4) DEFAULT NULL,
  `transmit_sec` int(11) DEFAULT NULL,
  `last_sec` int(11) DEFAULT NULL,
  `transmit_time` date DEFAULT NULL,
  `transmit_name` varchar(255) DEFAULT NULL,
  `arb_start_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zcsys_arbitrator
-- ----------------------------
DROP TABLE IF EXISTS `zcsys_arbitrator`;
CREATE TABLE `zcsys_arbitrator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '仲裁员名字',
  `phone` varchar(22) DEFAULT NULL COMMENT '联系方式',
  `specialty` varchar(225) DEFAULT NULL COMMENT '专业',
  `signature` varchar(225) DEFAULT NULL COMMENT '签名',
  `stamp` varchar(225) DEFAULT NULL COMMENT '公章',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `add_time` int(11) DEFAULT NULL COMMENT '加入时间',
  `flag` int(1) DEFAULT NULL COMMENT '是否有效,1有效，0无效',
  `address` varchar(225) DEFAULT NULL COMMENT '常住地',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zcsys_arbtr_ft_hist
-- ----------------------------
DROP TABLE IF EXISTS `zcsys_arbtr_ft_hist`;
CREATE TABLE `zcsys_arbtr_ft_hist` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `file_exchange_ts` varchar(23) DEFAULT NULL COMMENT '接收时间',
  `file_name` varchar(255) DEFAULT NULL,
  `company_code` varchar(8) DEFAULT NULL COMMENT '客户公司代码',
  `file_status` varchar(4) DEFAULT NULL COMMENT '状态',
  `last_upd_dt` varchar(23) DEFAULT NULL COMMENT '最后更新时间',
  `last_upd_usr` varchar(23) DEFAULT NULL COMMENT '最后更新人员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=263028 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zcsys_company
-- ----------------------------
DROP TABLE IF EXISTS `zcsys_company`;
CREATE TABLE `zcsys_company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL COMMENT '公司代码',
  `subcode` varchar(20) DEFAULT NULL,
  `sftpacc` varchar(50) DEFAULT NULL,
  `name` varchar(255) NOT NULL COMMENT '公司名称',
  `address` varchar(255) DEFAULT NULL COMMENT '公司地址',
  `sysname` varchar(200) DEFAULT NULL COMMENT '公司系统名称',
  `phone` varchar(50) DEFAULT NULL COMMENT '公司电话号码',
  `fd_name` varchar(50) DEFAULT NULL COMMENT '法定人',
  `feerate` double DEFAULT '0',
  `nickname` varchar(10) DEFAULT NULL,
  `registered_capital` varchar(50) DEFAULT NULL,
  `date_of_establishment` varchar(50) DEFAULT NULL,
  `website` varchar(50) DEFAULT NULL,
  `credit_code` varchar(50) DEFAULT NULL,
  `arbitral_id` int(11) DEFAULT NULL,
  `registration` varchar(50) DEFAULT NULL,
  `business_scope` varchar(1000) DEFAULT NULL,
  `depository_bank` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zcsys_company_ab_secretary
-- ----------------------------
DROP TABLE IF EXISTS `zcsys_company_ab_secretary`;
CREATE TABLE `zcsys_company_ab_secretary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_id` int(11) DEFAULT NULL COMMENT '仲裁客户公司ID 对应公司表',
  `ab_secretary_id` int(11) DEFAULT NULL COMMENT '仲裁秘书ID 对应仲裁秘书表',
  `op_admin` int(11) DEFAULT NULL COMMENT '分配的管理员ID 对应仲裁管理员表',
  `op_time` datetime DEFAULT NULL COMMENT '分配时间',
  `percent` int(11) DEFAULT NULL COMMENT '百分比',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_13` (`company_id`),
  KEY `FK_Reference_12` (`ab_secretary_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zcsys_company_arbitrator
-- ----------------------------
DROP TABLE IF EXISTS `zcsys_company_arbitrator`;
CREATE TABLE `zcsys_company_arbitrator` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '仲裁员ID',
  `aribitor_id` int(11) DEFAULT NULL COMMENT '仲裁员编号',
  `op_admin` int(11) DEFAULT NULL COMMENT '添加的管理员 对应仲裁管理员表',
  `op_time` datetime DEFAULT NULL COMMENT '添加时间',
  `percent` int(11) DEFAULT NULL COMMENT '百分比',
  `company_id` int(11) DEFAULT NULL COMMENT '仲裁客户公司ID 对应公司表',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_10` (`aribitor_id`),
  KEY `FK_Reference_11` (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zcsys_examination
-- ----------------------------
DROP TABLE IF EXISTS `zcsys_examination`;
CREATE TABLE `zcsys_examination` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `package_id` varchar(100) DEFAULT NULL,
  `trigger_arbtr_code` varchar(255) DEFAULT NULL COMMENT '触发的案件编号',
  `interval_time` varchar(50) DEFAULT NULL COMMENT '开始时间',
  `is_valid` int(2) DEFAULT '0' COMMENT '0表示未分配，1表示已分配',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zcsys_examiner_middle
-- ----------------------------
DROP TABLE IF EXISTS `zcsys_examiner_middle`;
CREATE TABLE `zcsys_examiner_middle` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `examiner_id` int(11) DEFAULT NULL COMMENT '审查员编号',
  `package_id` varchar(100) DEFAULT NULL,
  `total` int(11) DEFAULT '0' COMMENT '记录确认数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zcsys_menu
-- ----------------------------
DROP TABLE IF EXISTS `zcsys_menu`;
CREATE TABLE `zcsys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '菜单显示名称',
  `parentid` int(11) NOT NULL DEFAULT '0' COMMENT '父级指针，0-顶级',
  `url` varchar(200) DEFAULT NULL COMMENT '超链接',
  `menuorder` int(11) NOT NULL COMMENT '菜单顺序',
  `isdisplay` int(11) NOT NULL DEFAULT '1' COMMENT '是否显示：1-显示，0-不显示',
  `key` varchar(100) DEFAULT NULL COMMENT '菜单键值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zcsys_monthlysta
-- ----------------------------
DROP TABLE IF EXISTS `zcsys_monthlysta`;
CREATE TABLE `zcsys_monthlysta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companycode` varchar(50) DEFAULT NULL COMMENT 'HZC',
  `companysubcode` varchar(20) DEFAULT NULL COMMENT '公司部门代码',
  `month` varchar(10) DEFAULT NULL COMMENT '2017-02',
  `casecount` int(11) DEFAULT NULL,
  `caseamount` double DEFAULT NULL,
  `ispaid` varchar(1) DEFAULT NULL,
  `paidrecorder` varchar(20) DEFAULT NULL COMMENT '谁提交已付款',
  `paidtime` varchar(20) DEFAULT NULL COMMENT '谁提交已付款',
  `isinvoice` varchar(1) DEFAULT NULL,
  `invoicerecorder` varchar(20) DEFAULT NULL COMMENT '谁提交已出发票',
  `invoicetime` varchar(20) DEFAULT NULL COMMENT '提交发票时间',
  `remark` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zcsys_product_translation
-- ----------------------------
DROP TABLE IF EXISTS `zcsys_product_translation`;
CREATE TABLE `zcsys_product_translation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `model_name` varchar(120) NOT NULL,
  `model_code` varchar(30) NOT NULL,
  `company_code` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zcsys_role
-- ----------------------------
DROP TABLE IF EXISTS `zcsys_role`;
CREATE TABLE `zcsys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zcsys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `zcsys_role_menu`;
CREATE TABLE `zcsys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` int(11) NOT NULL COMMENT '角色指针',
  `menuid` int(11) NOT NULL COMMENT '菜单指针',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=388 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zcsys_users
-- ----------------------------
DROP TABLE IF EXISTS `zcsys_users`;
CREATE TABLE `zcsys_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(50) NOT NULL COMMENT '账号',
  `passwd` varchar(255) NOT NULL COMMENT '密码',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `ttype` int(11) DEFAULT '1' COMMENT '用户类型',
  `active_flag` int(11) NOT NULL DEFAULT '1' COMMENT '是否有效：1-有效，0-失效',
  `mobile_phone` varchar(30) DEFAULT NULL COMMENT '手机号',
  `addtime` varchar(23) NOT NULL COMMENT '添加时间',
  `companyid` int(11) DEFAULT NULL COMMENT '公司id',
  `arbitratorId` int(11) DEFAULT NULL,
  `secretaryId` int(11) DEFAULT NULL,
  `arbitralId` int(11) DEFAULT NULL,
  `examinerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zcsys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `zcsys_user_role`;
CREATE TABLE `zcsys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL COMMENT '用户指针',
  `roleid` int(11) NOT NULL COMMENT '角色指针',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8;

-- ----------------------------
-- View structure for 0404
-- ----------------------------
DROP VIEW IF EXISTS `0404`;
CREATE ALGORITHM=UNDEFINED DEFINER=`zc_dev`@`%` SQL SECURITY DEFINER VIEW `0404` AS select `zcsys_arbitration`.`arbtr_code` AS `arbtr_code`,`zcsys_arbitration`.`attachment` AS `attachment` from `zcsys_arbitration` where ((`zcsys_arbitration`.`client_company_code` = 'JFU') and (`zcsys_arbitration`.`rcd_created_ts` like '%2017-04-04%')) ;

-- ----------------------------
-- View structure for 0620
-- ----------------------------
DROP VIEW IF EXISTS `0620`;
CREATE ALGORITHM=UNDEFINED DEFINER=`zc_dev`@`%` SQL SECURITY DEFINER VIEW `0620` AS select `zcsys_arbitration`.`arbtr_code` AS `arbtr_code`,`zcsys_arbitration`.`attachment` AS `attachment` from `zcsys_arbitration` where ((`zcsys_arbitration`.`client_company_code` = 'JFU') and (`zcsys_arbitration`.`rcd_created_ts` like '%2017-06-20%')) ;
