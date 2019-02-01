/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : lxt_db

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 22/01/2019 09:04:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for act_ge_bytearray
-- ----------------------------
DROP TABLE IF EXISTS `act_ge_bytearray`;
CREATE TABLE `act_ge_bytearray` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTES_` longblob,
  `GENERATED_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  KEY `ACT_FK_BYTEARR_DEPL` (`DEPLOYMENT_ID_`) USING BTREE,
  CONSTRAINT `act_ge_bytearray_ibfk_1` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for act_ge_property
-- ----------------------------
DROP TABLE IF EXISTS `act_ge_property`;
CREATE TABLE `act_ge_property` (
  `NAME_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `VALUE_` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of act_ge_property
-- ----------------------------
BEGIN;
INSERT INTO `act_ge_property` VALUES ('next.dbid', '1', 1);
INSERT INTO `act_ge_property` VALUES ('schema.history', 'create(5.14)', 1);
INSERT INTO `act_ge_property` VALUES ('schema.version', '5.14', 1);
COMMIT;

-- ----------------------------
-- Table structure for act_hi_actinst
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_actinst`;
CREATE TABLE `act_hi_actinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin NOT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CALL_PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ACT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime NOT NULL,
  `END_TIME_` datetime DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  KEY `ACT_IDX_HI_ACT_INST_START` (`START_TIME_`) USING BTREE,
  KEY `ACT_IDX_HI_ACT_INST_END` (`END_TIME_`) USING BTREE,
  KEY `ACT_IDX_HI_ACT_INST_PROCINST` (`PROC_INST_ID_`,`ACT_ID_`) USING BTREE,
  KEY `ACT_IDX_HI_ACT_INST_EXEC` (`EXECUTION_ID_`,`ACT_ID_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for act_hi_attachment
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_attachment`;
CREATE TABLE `act_hi_attachment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `URL_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `CONTENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for act_hi_comment
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_comment`;
CREATE TABLE `act_hi_comment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TIME_` datetime NOT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `MESSAGE_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `FULL_MSG_` longblob,
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for act_hi_detail
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_detail`;
CREATE TABLE `act_hi_detail` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TIME_` datetime NOT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  KEY `ACT_IDX_HI_DETAIL_PROC_INST` (`PROC_INST_ID_`) USING BTREE,
  KEY `ACT_IDX_HI_DETAIL_ACT_INST` (`ACT_INST_ID_`) USING BTREE,
  KEY `ACT_IDX_HI_DETAIL_TIME` (`TIME_`) USING BTREE,
  KEY `ACT_IDX_HI_DETAIL_NAME` (`NAME_`) USING BTREE,
  KEY `ACT_IDX_HI_DETAIL_TASK_ID` (`TASK_ID_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for act_hi_identitylink
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_identitylink`;
CREATE TABLE `act_hi_identitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  KEY `ACT_IDX_HI_IDENT_LNK_USER` (`USER_ID_`) USING BTREE,
  KEY `ACT_IDX_HI_IDENT_LNK_TASK` (`TASK_ID_`) USING BTREE,
  KEY `ACT_IDX_HI_IDENT_LNK_PROCINST` (`PROC_INST_ID_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for act_hi_procinst
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_procinst`;
CREATE TABLE `act_hi_procinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `START_TIME_` datetime NOT NULL,
  `END_TIME_` datetime DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `START_USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `END_ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  UNIQUE KEY `PROC_INST_ID_` (`PROC_INST_ID_`) USING BTREE,
  UNIQUE KEY `ACT_UNIQ_HI_BUS_KEY` (`PROC_DEF_ID_`,`BUSINESS_KEY_`) USING BTREE,
  KEY `ACT_IDX_HI_PRO_INST_END` (`END_TIME_`) USING BTREE,
  KEY `ACT_IDX_HI_PRO_I_BUSKEY` (`BUSINESS_KEY_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for act_hi_taskinst
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_taskinst`;
CREATE TABLE `act_hi_taskinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_TIME_` datetime NOT NULL,
  `CLAIM_TIME_` datetime DEFAULT NULL,
  `END_TIME_` datetime DEFAULT NULL,
  `DURATION_` bigint(20) DEFAULT NULL,
  `DELETE_REASON_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `DUE_DATE_` datetime DEFAULT NULL,
  `FORM_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for act_hi_varinst
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_varinst`;
CREATE TABLE `act_hi_varinst` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VAR_TYPE_` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `REV_` int(11) DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  KEY `ACT_IDX_HI_PROCVAR_PROC_INST` (`PROC_INST_ID_`) USING BTREE,
  KEY `ACT_IDX_HI_PROCVAR_NAME_TYPE` (`NAME_`,`VAR_TYPE_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for act_id_group
-- ----------------------------
DROP TABLE IF EXISTS `act_id_group`;
CREATE TABLE `act_id_group` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for act_id_info
-- ----------------------------
DROP TABLE IF EXISTS `act_id_info`;
CREATE TABLE `act_id_info` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `USER_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `VALUE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD_` longblob,
  `PARENT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for act_id_membership
-- ----------------------------
DROP TABLE IF EXISTS `act_id_membership`;
CREATE TABLE `act_id_membership` (
  `USER_ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `GROUP_ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  PRIMARY KEY (`USER_ID_`,`GROUP_ID_`) USING BTREE,
  KEY `ACT_FK_MEMB_GROUP` (`GROUP_ID_`) USING BTREE,
  CONSTRAINT `act_id_membership_ibfk_1` FOREIGN KEY (`GROUP_ID_`) REFERENCES `act_id_group` (`ID_`),
  CONSTRAINT `act_id_membership_ibfk_2` FOREIGN KEY (`USER_ID_`) REFERENCES `act_id_user` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for act_id_user
-- ----------------------------
DROP TABLE IF EXISTS `act_id_user`;
CREATE TABLE `act_id_user` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `FIRST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LAST_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EMAIL_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PWD_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PICTURE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for act_re_deployment
-- ----------------------------
DROP TABLE IF EXISTS `act_re_deployment`;
CREATE TABLE `act_re_deployment` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOY_TIME_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for act_re_model
-- ----------------------------
DROP TABLE IF EXISTS `act_re_model`;
CREATE TABLE `act_re_model` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATE_TIME_` timestamp NULL DEFAULT NULL,
  `LAST_UPDATE_TIME_` timestamp NULL DEFAULT NULL,
  `VERSION_` int(11) DEFAULT NULL,
  `META_INFO_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EDITOR_SOURCE_EXTRA_VALUE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  KEY `ACT_FK_MODEL_SOURCE` (`EDITOR_SOURCE_VALUE_ID_`) USING BTREE,
  KEY `ACT_FK_MODEL_SOURCE_EXTRA` (`EDITOR_SOURCE_EXTRA_VALUE_ID_`) USING BTREE,
  KEY `ACT_FK_MODEL_DEPLOYMENT` (`DEPLOYMENT_ID_`) USING BTREE,
  CONSTRAINT `act_re_model_ibfk_1` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`ID_`),
  CONSTRAINT `act_re_model_ibfk_2` FOREIGN KEY (`EDITOR_SOURCE_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `act_re_model_ibfk_3` FOREIGN KEY (`EDITOR_SOURCE_EXTRA_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for act_re_procdef
-- ----------------------------
DROP TABLE IF EXISTS `act_re_procdef`;
CREATE TABLE `act_re_procdef` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `CATEGORY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `KEY_` varchar(255) COLLATE utf8_bin NOT NULL,
  `VERSION_` int(11) NOT NULL,
  `DEPLOYMENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DGRM_RESOURCE_NAME_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `HAS_START_FORM_KEY_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  UNIQUE KEY `ACT_UNIQ_PROCDEF` (`KEY_`,`VERSION_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for act_ru_event_subscr
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_event_subscr`;
CREATE TABLE `act_ru_event_subscr` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `EVENT_TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EVENT_NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACTIVITY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `CONFIGURATION_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `CREATED_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID_`) USING BTREE,
  KEY `ACT_IDX_EVENT_SUBSCR_CONFIG_` (`CONFIGURATION_`) USING BTREE,
  KEY `ACT_FK_EVENT_EXEC` (`EXECUTION_ID_`) USING BTREE,
  CONSTRAINT `act_ru_event_subscr_ibfk_1` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for act_ru_execution
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_execution`;
CREATE TABLE `act_ru_execution` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BUSINESS_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `SUPER_EXEC_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `ACT_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `IS_ACTIVE_` tinyint(4) DEFAULT NULL,
  `IS_CONCURRENT_` tinyint(4) DEFAULT NULL,
  `IS_SCOPE_` tinyint(4) DEFAULT NULL,
  `IS_EVENT_SCOPE_` tinyint(4) DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  `CACHED_ENT_STATE_` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  UNIQUE KEY `ACT_UNIQ_RU_BUS_KEY` (`PROC_DEF_ID_`,`BUSINESS_KEY_`) USING BTREE,
  KEY `ACT_IDX_EXEC_BUSKEY` (`BUSINESS_KEY_`) USING BTREE,
  KEY `ACT_FK_EXE_PROCINST` (`PROC_INST_ID_`) USING BTREE,
  KEY `ACT_FK_EXE_PARENT` (`PARENT_ID_`) USING BTREE,
  KEY `ACT_FK_EXE_SUPER` (`SUPER_EXEC_`) USING BTREE,
  CONSTRAINT `act_ru_execution_ibfk_1` FOREIGN KEY (`PARENT_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `act_ru_execution_ibfk_2` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `act_ru_execution_ibfk_3` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `act_ru_execution_ibfk_4` FOREIGN KEY (`SUPER_EXEC_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for act_ru_identitylink
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_identitylink`;
CREATE TABLE `act_ru_identitylink` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `GROUP_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ID_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  KEY `ACT_IDX_IDENT_LNK_USER` (`USER_ID_`) USING BTREE,
  KEY `ACT_IDX_IDENT_LNK_GROUP` (`GROUP_ID_`) USING BTREE,
  KEY `ACT_IDX_ATHRZ_PROCEDEF` (`PROC_DEF_ID_`) USING BTREE,
  KEY `ACT_FK_TSKASS_TASK` (`TASK_ID_`) USING BTREE,
  KEY `ACT_FK_IDL_PROCINST` (`PROC_INST_ID_`) USING BTREE,
  CONSTRAINT `act_ru_identitylink_ibfk_1` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `act_ru_identitylink_ibfk_2` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `act_ru_identitylink_ibfk_3` FOREIGN KEY (`TASK_ID_`) REFERENCES `act_ru_task` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for act_ru_job
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_job`;
CREATE TABLE `act_ru_job` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `LOCK_EXP_TIME_` timestamp NULL DEFAULT NULL,
  `LOCK_OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EXCLUSIVE_` tinyint(1) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROCESS_INSTANCE_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `RETRIES_` int(11) DEFAULT NULL,
  `EXCEPTION_STACK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `EXCEPTION_MSG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `DUEDATE_` timestamp NULL DEFAULT NULL,
  `REPEAT_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_TYPE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `HANDLER_CFG_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  KEY `ACT_FK_JOB_EXCEPTION` (`EXCEPTION_STACK_ID_`) USING BTREE,
  CONSTRAINT `act_ru_job_ibfk_1` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for act_ru_task
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_task`;
CREATE TABLE `act_ru_task` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `REV_` int(11) DEFAULT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_DEF_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PARENT_TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TASK_DEF_KEY_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `OWNER_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ASSIGNEE_` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DELEGATION_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY_` int(11) DEFAULT NULL,
  `CREATE_TIME_` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `DUE_DATE_` datetime DEFAULT NULL,
  `SUSPENSION_STATE_` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  KEY `ACT_IDX_TASK_CREATE` (`CREATE_TIME_`) USING BTREE,
  KEY `ACT_FK_TASK_EXE` (`EXECUTION_ID_`) USING BTREE,
  KEY `ACT_FK_TASK_PROCINST` (`PROC_INST_ID_`) USING BTREE,
  KEY `ACT_FK_TASK_PROCDEF` (`PROC_DEF_ID_`) USING BTREE,
  CONSTRAINT `act_ru_task_ibfk_1` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `act_ru_task_ibfk_2` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`),
  CONSTRAINT `act_ru_task_ibfk_3` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for act_ru_variable
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_variable`;
CREATE TABLE `act_ru_variable` (
  `ID_` varchar(64) COLLATE utf8_bin NOT NULL,
  `REV_` int(11) DEFAULT NULL,
  `TYPE_` varchar(255) COLLATE utf8_bin NOT NULL,
  `NAME_` varchar(255) COLLATE utf8_bin NOT NULL,
  `EXECUTION_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `PROC_INST_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `TASK_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `BYTEARRAY_ID_` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `DOUBLE_` double DEFAULT NULL,
  `LONG_` bigint(20) DEFAULT NULL,
  `TEXT_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  `TEXT2_` varchar(4000) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_`) USING BTREE,
  KEY `ACT_IDX_VARIABLE_TASK_ID` (`TASK_ID_`) USING BTREE,
  KEY `ACT_FK_VAR_EXE` (`EXECUTION_ID_`) USING BTREE,
  KEY `ACT_FK_VAR_PROCINST` (`PROC_INST_ID_`) USING BTREE,
  KEY `ACT_FK_VAR_BYTEARRAY` (`BYTEARRAY_ID_`) USING BTREE,
  CONSTRAINT `act_ru_variable_ibfk_1` FOREIGN KEY (`BYTEARRAY_ID_`) REFERENCES `act_ge_bytearray` (`ID_`),
  CONSTRAINT `act_ru_variable_ibfk_2` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`),
  CONSTRAINT `act_ru_variable_ibfk_3` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for lxt_chat_friend
-- ----------------------------
DROP TABLE IF EXISTS `lxt_chat_friend`;
CREATE TABLE `lxt_chat_friend` (
  `ID` varchar(32) NOT NULL DEFAULT '',
  `USER_ID` varchar(32) DEFAULT NULL,
  `FRIEND_ID` varchar(32) DEFAULT NULL,
  `FRIEND_CATEGORY_ID` varchar(255) DEFAULT NULL,
  `MEMO` varchar(64) DEFAULT NULL,
  `INSERT_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `SORT_SEQ` int(255) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lxt_chat_friend
-- ----------------------------
BEGIN;
INSERT INTO `lxt_chat_friend` VALUES ('1', 'admin', 'xiaoting', '1', '肖太郎', NULL, NULL, 1);
INSERT INTO `lxt_chat_friend` VALUES ('2', 'admin', 'admin', '2', '管理员', NULL, NULL, NULL);
INSERT INTO `lxt_chat_friend` VALUES ('3', 'xiaoting', 'admin', '3', '管理员', NULL, NULL, NULL);
INSERT INTO `lxt_chat_friend` VALUES ('4', 'xiaoting', 'admin', '11', '管理员', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for lxt_chat_friend_category
-- ----------------------------
DROP TABLE IF EXISTS `lxt_chat_friend_category`;
CREATE TABLE `lxt_chat_friend_category` (
  `FRIEND_CATEGORY_ID` varchar(32) NOT NULL,
  `USER_ID` varchar(32) DEFAULT NULL,
  `NAME` varchar(64) DEFAULT NULL,
  `SORT_SEQ` int(255) DEFAULT NULL,
  `INSERT_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`FRIEND_CATEGORY_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lxt_chat_friend_category
-- ----------------------------
BEGIN;
INSERT INTO `lxt_chat_friend_category` VALUES ('1', 'admin', 'forever love', 1, NULL, NULL);
INSERT INTO `lxt_chat_friend_category` VALUES ('11', 'admin', 'system', 1, NULL, NULL);
INSERT INTO `lxt_chat_friend_category` VALUES ('2', 'admin', 'self', 2, NULL, NULL);
INSERT INTO `lxt_chat_friend_category` VALUES ('3', 'xiaoting', '唯一', 1, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for lxt_chat_group
-- ----------------------------
DROP TABLE IF EXISTS `lxt_chat_group`;
CREATE TABLE `lxt_chat_group` (
  `GROUP_ID` varchar(11) NOT NULL,
  `USER_ID` varchar(32) DEFAULT NULL,
  `GROUP_NAME` varchar(64) DEFAULT NULL,
  `SORT_SEQ` int(255) DEFAULT NULL,
  `INSERT_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`GROUP_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lxt_chat_group
-- ----------------------------
BEGIN;
INSERT INTO `lxt_chat_group` VALUES ('0', '0', '未分组', 99999, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for lxt_chat_group_user
-- ----------------------------
DROP TABLE IF EXISTS `lxt_chat_group_user`;
CREATE TABLE `lxt_chat_group_user` (
  `GROUP_ID` varchar(32) NOT NULL,
  `USER_ID` varchar(32) NOT NULL,
  PRIMARY KEY (`GROUP_ID`,`USER_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lxt_chat_record
-- ----------------------------
DROP TABLE IF EXISTS `lxt_chat_record`;
CREATE TABLE `lxt_chat_record` (
  `RECORD_ID` varchar(255) NOT NULL,
  `SEND_ID` varchar(32) DEFAULT NULL,
  `RECEIVE_ID` varchar(32) DEFAULT NULL,
  `MSG_TYPE` int(255) DEFAULT NULL,
  `CONTENT` varchar(1024) DEFAULT NULL,
  `INSERT_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`RECORD_ID`) USING BTREE,
  KEY `FK_chatrecord_user` (`SEND_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lxt_chat_record
-- ----------------------------
BEGIN;
INSERT INTO `lxt_chat_record` VALUES ('103F2636D72D4E66ACFBAF325B735719', 'admin', 'xiaoting', 0, 'app上面测试的，也能发消息，就是头像地址有问题，不过都是小意思', '2017-10-20 16:08:46');
INSERT INTO `lxt_chat_record` VALUES ('1148751FF3034F0D9BEE932B503413DA', 'xiaoting', 'admin', 0, '好像可以了', '2017-10-20 15:33:48');
INSERT INTO `lxt_chat_record` VALUES ('17EF37A5EB524C2F809DAAE90069ABE4', 'xiaoting', 'admin', 0, '哦', '2017-10-20 16:08:04');
INSERT INTO `lxt_chat_record` VALUES ('2C0EF39CF7764343B2D9CA61D569572B', 'admin', 'admin', 0, '自己测试着玩', '2017-10-20 15:26:00');
INSERT INTO `lxt_chat_record` VALUES ('36100F17FE8F4FA98C3A805F6927ED8D', 'admin', 'xiaoting', 0, 'aaaa', '2017-10-20 14:24:47');
INSERT INTO `lxt_chat_record` VALUES ('3E5021E30C4A4166A5F9B47F231955FD', 'admin', 'xiaoting', 0, '今天先测试到这里，后面再做表情和图片', '2017-10-20 15:21:54');
INSERT INTO `lxt_chat_record` VALUES ('483A152808B9427192281B914898E59E', 'admin', 'xiaoting', 0, 'vhjhffcc', '2017-12-13 18:30:16');
INSERT INTO `lxt_chat_record` VALUES ('4C39C1DDB53E4BBAB140B0286DBCF0CE', 'admin', 'xiaoting', 0, '今天星期五了，月底项目要上线，是喜是忧呢', '2017-10-20 15:35:30');
INSERT INTO `lxt_chat_record` VALUES ('511030A7D601436A80D504C3A554EA7A', 'admin', 'xiaoting', 0, '小样，还整不了你了', '2017-10-20 15:20:37');
INSERT INTO `lxt_chat_record` VALUES ('57015BB866254E40B7031DE13BE8D37C', 'admin', 'xiaoting', 0, 'cvbbvvvvv', '2017-12-13 18:30:22');
INSERT INTO `lxt_chat_record` VALUES ('69E1D5E29EE743C591C6F9E5BD95A484', 'xiaoting', 'admin', 0, '好好做  其他是事情别多想', '2017-10-20 15:40:17');
INSERT INTO `lxt_chat_record` VALUES ('6B94CBD5691B40A3B1E7E7A2B7E99194', 'xiaoting', 'admin', 0, '图片支持不好做，需要用到ueditor插件，手机上面兼容性不是很好', '2017-10-20 15:25:03');
INSERT INTO `lxt_chat_record` VALUES ('8956C2C2351B41D599EDBB978CF337DA', 'admin', 'xiaoting', 0, '嘿嘿', '2017-10-20 15:33:38');
INSERT INTO `lxt_chat_record` VALUES ('8C155C9BB0AC4F1E8377CE805C475855', 'xiaoting', 'admin', 0, 'bbbb', '2017-10-20 15:17:31');
INSERT INTO `lxt_chat_record` VALUES ('8F915BCE17BE4B75BF5E43ABA9C50973', 'xiaoting', 'admin', 0, '88888888888888888888888888888888888', '2017-10-20 15:18:31');
INSERT INTO `lxt_chat_record` VALUES ('921724DC28DD4015A91A71D24CA093E7', 'admin', 'xiaoting', 0, '能收到吗', '2017-10-20 15:17:58');
INSERT INTO `lxt_chat_record` VALUES ('93A719EDF6584460BE9B1E5A69E98B28', 'admin', 'xiaoting', 0, '可能需要自己定UI', '2017-10-20 15:25:23');
INSERT INTO `lxt_chat_record` VALUES ('A4A67C8F3C9F46AABAECFF544C350426', 'xiaoting', 'admin', 0, 'bbbbb', '2017-12-13 18:30:11');
INSERT INTO `lxt_chat_record` VALUES ('A911B1B050014F10AD46F3F3DCA9D8EE', 'xiaoting', 'admin', 0, '调试了半天，总算发出去了，我日', '2017-10-20 15:18:17');
INSERT INTO `lxt_chat_record` VALUES ('B85F940ACDF840F9955083E4FE3D39BD', 'xiaoting', 'admin', 0, '消息发送速度还挺快的，达到了毫秒级', '2017-10-20 15:20:59');
INSERT INTO `lxt_chat_record` VALUES ('C5589EF8B9F3451D899CEB74F16FF385', 'xiaoting', 'admin', 0, 'aaa', '2017-12-13 18:29:48');
INSERT INTO `lxt_chat_record` VALUES ('DE2E3B0CD40C49549CF3B801250D5E7B', 'admin', 'xiaoting', 0, 'hhffbbvf', '2017-12-13 18:30:05');
COMMIT;

-- ----------------------------
-- Table structure for lxt_department
-- ----------------------------
DROP TABLE IF EXISTS `lxt_department`;
CREATE TABLE `lxt_department` (
  `DEPT_ID` varchar(32) NOT NULL,
  `DEPT_NAME` varchar(64) DEFAULT NULL,
  `DEPT_CODE` varchar(32) DEFAULT NULL,
  `PARENT_ID` varchar(32) DEFAULT NULL,
  `LAST_UPDATE_TIME` datetime DEFAULT NULL,
  `LAST_UPDATE_USER` varchar(32) DEFAULT NULL,
  `DEPT_MANAGER` varchar(32) DEFAULT NULL,
  `DEPT_ORDER` int(11) DEFAULT NULL,
  `ORG_ID` varchar(32) DEFAULT NULL,
  `PHONE` varchar(32) DEFAULT NULL,
  `MOBILE` varchar(32) DEFAULT NULL,
  `FAX` varchar(64) DEFAULT NULL,
  `QQ` varchar(32) DEFAULT NULL,
  `EMAIL` varchar(64) DEFAULT NULL,
  `DEPT_MEMO` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`DEPT_ID`) USING BTREE,
  KEY `FK_DEPARMENT` (`PARENT_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of lxt_department
-- ----------------------------
BEGIN;
INSERT INTO `lxt_department` VALUES ('1', '财务部', 'CWB', NULL, '2013-07-23 18:44:53', '1', '1', 1, '1', '123', '321', '1321', '3214214', '43214@21fds.com', 'fwsfewfewfw');
INSERT INTO `lxt_department` VALUES ('2', '人事部', 'RSB', NULL, '2013-08-23 18:45:45', '1', '1', 5, '2', '423423', '2141', '11fdwf', '432432', 'fdwfw@fewf.com', 'fwegfwegwe');
INSERT INTO `lxt_department` VALUES ('3', '采购部', 'CGB', NULL, '2013-07-31 11:15:23', '1', '1', 1, '1', '213', '2132', '132', '132', '1321312@edwq.com', 'fewfgwgwgfw');
INSERT INTO `lxt_department` VALUES ('4', '应用系统一部', 'APP', NULL, '2013-07-09 11:16:30', '1', '1', 1, '1', '123', '12', '312', '3213', '13fdsf@ffwef.com', 'hbregrwege');
INSERT INTO `lxt_department` VALUES ('5', '电子商务部', 'ES', NULL, '2013-07-10 11:19:20', '1', '1', 1, '1', '132', '1321', '321', '321', '321321fe@fwef.com', 'gehregfew');
COMMIT;

-- ----------------------------
-- Table structure for lxt_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `lxt_dictionary`;
CREATE TABLE `lxt_dictionary` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `VALUE` int(11) DEFAULT '0',
  `TEXT` varchar(255) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT '',
  `CODE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lxt_dictionary
-- ----------------------------
BEGIN;
INSERT INTO `lxt_dictionary` VALUES (4, 0, '关闭', 'COMMON', 'CLOSED');
INSERT INTO `lxt_dictionary` VALUES (5, 1, '开启', 'COMMON', 'OPEN');
INSERT INTO `lxt_dictionary` VALUES (6, 0, '未发布', 'PROCESS', 'UNPUBLISH');
INSERT INTO `lxt_dictionary` VALUES (7, 1, '已发布', 'PROCESS', 'PUBLISHED');
INSERT INTO `lxt_dictionary` VALUES (8, -1, '未知', 'SEX', 'UNKNOW');
INSERT INTO `lxt_dictionary` VALUES (9, 0, '女', 'SEX', 'FEMALE');
INSERT INTO `lxt_dictionary` VALUES (10, 1, '男', 'SEX', 'MALE');
INSERT INTO `lxt_dictionary` VALUES (11, 1, '激活', 'COMMON', 'ACTIVE');
INSERT INTO `lxt_dictionary` VALUES (12, 0, '禁用', 'COMMON', 'FORBIDDEN');
INSERT INTO `lxt_dictionary` VALUES (13, 0, '未修改', 'PWDSTATUS', 'UNCHANGE');
INSERT INTO `lxt_dictionary` VALUES (14, 1, '已修改', 'PWDSTATUS', 'CHANGED');
INSERT INTO `lxt_dictionary` VALUES (15, 0, '未设置', 'QUESTION', 'UNSET');
INSERT INTO `lxt_dictionary` VALUES (16, 1, '已设置', 'QUESTION', 'SET');
INSERT INTO `lxt_dictionary` VALUES (17, 0, '不允许退回', 'SENDBACK', 'FORBIDDEN');
INSERT INTO `lxt_dictionary` VALUES (18, 1, '退回至来源', 'SENDBACK', 'SOURCE');
INSERT INTO `lxt_dictionary` VALUES (19, 2, '退回至上一节点', 'SENDBACK', 'PREVIOUS');
INSERT INTO `lxt_dictionary` VALUES (20, 3, '退回历史节点', 'SENDBACK', 'HISTORY');
INSERT INTO `lxt_dictionary` VALUES (21, 4, '退回至指定节点', 'SENDBACK', 'ASSIGNED');
COMMIT;

-- ----------------------------
-- Table structure for lxt_dictionary_type
-- ----------------------------
DROP TABLE IF EXISTS `lxt_dictionary_type`;
CREATE TABLE `lxt_dictionary_type` (
  `TYPE` int(11) NOT NULL DEFAULT '0',
  `NAME` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`TYPE`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lxt_dictionary_type
-- ----------------------------
BEGIN;
INSERT INTO `lxt_dictionary_type` VALUES (0, 'COMMON');
INSERT INTO `lxt_dictionary_type` VALUES (1, NULL);
COMMIT;

-- ----------------------------
-- Table structure for lxt_node
-- ----------------------------
DROP TABLE IF EXISTS `lxt_node`;
CREATE TABLE `lxt_node` (
  `NODE_ID` varchar(32) NOT NULL,
  `CELL_ID` varchar(32) DEFAULT NULL,
  `WORKFLOW_ID` varchar(32) DEFAULT NULL,
  `NAME` varchar(64) DEFAULT NULL,
  `FORM` varchar(32) DEFAULT NULL,
  `ASSIGNER_TYPE` int(11) DEFAULT NULL,
  `ASSIGNER_ID` varchar(255) DEFAULT NULL,
  `MEMO` varchar(1024) DEFAULT NULL,
  `NODE_TYPE` varchar(32) DEFAULT NULL,
  `NODE_XML` text,
  PRIMARY KEY (`NODE_ID`) USING BTREE,
  KEY `FK_Reference_4` (`WORKFLOW_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程节点表';

-- ----------------------------
-- Records of lxt_node
-- ----------------------------
BEGIN;
INSERT INTO `lxt_node` VALUES ('4028b8814587beb8014587d1f6c7000f', '3', '4028b8814587beb8014587d1f6c6000e', '任务1', '', 0, '0', NULL, 'task', NULL);
INSERT INTO `lxt_node` VALUES ('4028b8814587beb8014587d1f6c70010', '5', '4028b8814587beb8014587d1f6c6000e', '结束', NULL, NULL, NULL, NULL, 'end', NULL);
INSERT INTO `lxt_node` VALUES ('4028b8814587beb8014587d1f6c70011', '2', '4028b8814587beb8014587d1f6c6000e', '开始', NULL, NULL, NULL, NULL, 'start', NULL);
INSERT INTO `lxt_node` VALUES ('4028b8814587beb8014587d1f6c70012', '4', '4028b8814587beb8014587d1f6c6000e', '任务2', '', 0, '0', NULL, 'task', NULL);
INSERT INTO `lxt_node` VALUES ('4028b881458c16db01458cfae3440002', '4', '4028b881458c16db01458cfae3440001', '结束', NULL, NULL, NULL, NULL, 'end', NULL);
INSERT INTO `lxt_node` VALUES ('4028b881458c16db01458cfae3440003', '3', '4028b881458c16db01458cfae3440001', '任务', '', 0, '0', NULL, 'task', NULL);
INSERT INTO `lxt_node` VALUES ('4028b881458c16db01458cfae3440004', '2', '4028b881458c16db01458cfae3440001', '开始', NULL, NULL, NULL, NULL, 'start', NULL);
COMMIT;

-- ----------------------------
-- Table structure for lxt_organization
-- ----------------------------
DROP TABLE IF EXISTS `lxt_organization`;
CREATE TABLE `lxt_organization` (
  `ORG_ID` varchar(32) NOT NULL,
  `ORG_NAME` varchar(64) DEFAULT NULL,
  `ORG_CODE` varchar(64) DEFAULT NULL,
  `PARENT_ID` varchar(32) DEFAULT NULL,
  `LAST_UPDATE_TIME` datetime DEFAULT NULL,
  `LAST_UPDATE_USER` varchar(32) DEFAULT NULL,
  `ORG_MANAGER` varchar(32) DEFAULT NULL,
  `ORG_ORDER` int(11) DEFAULT NULL,
  `ORG_LEVEL` int(11) DEFAULT NULL,
  `ORG_PATH` varchar(255) DEFAULT NULL,
  `ORG_MEMO` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`ORG_ID`) USING BTREE,
  KEY `FK_ORGANIZATION` (`PARENT_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构表';

-- ----------------------------
-- Records of lxt_organization
-- ----------------------------
BEGIN;
INSERT INTO `lxt_organization` VALUES ('1', '总公司', '1', '0', '2015-01-17 17:55:36', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `lxt_organization` VALUES ('101', '上海分公司', '101', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `lxt_organization` VALUES ('10101', '浦东新区', '10101', '101', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `lxt_organization` VALUES ('10102', '徐汇区', '10102', '101', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `lxt_organization` VALUES ('102', '北京分公司', '102', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `lxt_organization` VALUES ('10201', '朝阳区', '10201', '102', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `lxt_organization` VALUES ('115', '湖南分公司', '115', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `lxt_organization` VALUES ('11501', '长沙', '11501', '115', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `lxt_organization` VALUES ('11502', '株洲', '11502', '115', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for lxt_process
-- ----------------------------
DROP TABLE IF EXISTS `lxt_process`;
CREATE TABLE `lxt_process` (
  `PROCESS_ID` varchar(32) NOT NULL,
  `PROCESS_CATEGORY_ID` varchar(32) DEFAULT NULL,
  `NAME` varchar(64) DEFAULT NULL,
  `PROCESS_KEY` varchar(32) DEFAULT NULL,
  `VERSION_NO` varchar(32) DEFAULT NULL,
  `MEMO` varchar(1024) DEFAULT NULL,
  `GRAPH_XML` text,
  `BPMN_XML` text,
  `STATUS` int(11) DEFAULT NULL,
  `PUBLISH_STATUS` int(11) DEFAULT NULL,
  `PUBLISH_TIME` datetime DEFAULT NULL,
  `INSERT_USER_ID` varchar(32) DEFAULT NULL,
  `INSERT_TIME` datetime DEFAULT NULL,
  `UPDATE_USER_ID` varchar(32) DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `DEPLOYE_ID` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`PROCESS_ID`) USING BTREE,
  KEY `FK_WORKFLOW_CATEGORY_ID` (`PROCESS_CATEGORY_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程主表';

-- ----------------------------
-- Records of lxt_process
-- ----------------------------
BEGIN;
INSERT INTO `lxt_process` VALUES ('4028b8814587beb8014587d1f6c6000e', NULL, '新建流程1', '流程标识', '1.0', '', '<mxGraphModel><root><Workflow label=\"新建流程\" propertyTitle=\"流程配置\" nodeType=\"workflow\" workflowKey=\"流程标识\" version=\"1.0\" status=\"1\" memo=\"\" id=\"0\"><mxCell/></Workflow><Layer label=\"Default Layer\" id=\"1\"><mxCell parent=\"0\"/></Layer><Start label=\"开始\" propertyTitle=\"开始节点\" nodeType=\"start\" isSymbol=\"true\" id=\"2\"><mxCell style=\"start\" vertex=\"1\" parent=\"1\"><mxGeometry x=\"44\" y=\"68\" width=\"48\" height=\"48\" as=\"geometry\"/></mxCell></Start><Task label=\"任务1\" propertyTitle=\"任务节点\" nodeType=\"task\" form=\"\" formText=\"无表单\" remindRuleId=\"\" remindRuleText=\"从不提醒\" assignerType=\"0\" assignerId=\"0\" handlerText=\"流程发起人\" allowTakeback=\"1\" allowDelegate=\"1\" allowSendback=\"0\" sendbackWay=\"1\" sendbackTargetId=\"\" sendbackTargetText=\"\" allowCheck=\"1\" checkTargetId=\"\" checkTargetText=\"\" allowUpload=\"1\" needAdvice=\"0\" memo=\"\" id=\"3\"><mxCell style=\"task\" vertex=\"1\" parent=\"1\"><mxGeometry x=\"206\" y=\"75\" width=\"96\" height=\"48\" as=\"geometry\"/></mxCell></Task><Task label=\"任务2\" propertyTitle=\"任务节点\" nodeType=\"task\" form=\"\" formText=\"无表单\" remindRuleId=\"\" remindRuleText=\"从不提醒\" assignerType=\"0\" assignerId=\"0\" handlerText=\"流程发起人\" allowTakeback=\"1\" allowDelegate=\"1\" allowSendback=\"0\" sendbackWay=\"1\" sendbackTargetId=\"\" sendbackTargetText=\"\" allowCheck=\"1\" checkTargetId=\"\" checkTargetText=\"\" allowUpload=\"1\" needAdvice=\"0\" memo=\"\" id=\"4\"><mxCell style=\"task\" vertex=\"1\" parent=\"1\"><mxGeometry x=\"414\" y=\"82\" width=\"96\" height=\"48\" as=\"geometry\"/></mxCell></Task><End label=\"结束\" propertyTitle=\"结束节点\" nodeType=\"end\" isSymbol=\"true\" id=\"5\"><mxCell style=\"end\" vertex=\"1\" parent=\"1\"><mxGeometry x=\"646\" y=\"85\" width=\"48\" height=\"48\" as=\"geometry\"/></mxCell></End><Edge label=\"to 任务2\" description=\"\" propertyTitle=\"连接线\" nodeType=\"edge\" id=\"6\"><mxCell edge=\"1\" parent=\"1\" source=\"2\" target=\"3\"><mxGeometry relative=\"1\" as=\"geometry\"/></mxCell></Edge><Edge label=\"to 任务2\" description=\"\" propertyTitle=\"连接线\" nodeType=\"edge\" id=\"7\"><mxCell edge=\"1\" parent=\"1\" source=\"3\" target=\"4\"><mxGeometry relative=\"1\" as=\"geometry\"/></mxCell></Edge><Edge label=\"to 结束\" description=\"\" propertyTitle=\"连接线\" nodeType=\"edge\" id=\"8\"><mxCell edge=\"1\" parent=\"1\" source=\"4\" target=\"5\"><mxGeometry relative=\"1\" as=\"geometry\"/></mxCell></Edge></root></mxGraphModel>', '<process-definition name=\"新建流程\" key=\"流程标识\" xmlns=\"http://jbpm.org/4.4/jpdl\" description=\"\">\n  <task id=\"3\" name=\"任务1\">\n    <description>4028b8814587beb8014587d1f6c7000f</description>\n    <transition name=\"to 任务2\" to=\"任务2\"/>\n    <form></form>\n    <assignment-handler class=\"com.lxt.workflow.assigner.TaskAssigneeHandler\">\n      <field name=\"nodeId\">\n        <string value=\"4028b8814587beb8014587d1f6c7000f\"/>\n      </field>\n    </assignment-handler>\n  </task>\n  <start id=\"2\" name=\"开始\">\n    <description>4028b8814587beb8014587d1f6c70011</description>\n    <transition name=\"to 任务2\" to=\"任务1\"/>\n  </start>\n  <end id=\"5\" name=\"结束\">\n    <description>4028b8814587beb8014587d1f6c70010</description>\n  </end>\n  <task id=\"4\" name=\"任务2\">\n    <description>4028b8814587beb8014587d1f6c70012</description>\n    <transition name=\"to 结束\" to=\"结束\"/>\n    <form></form>\n    <assignment-handler class=\"com.lxt.workflow.assigner.TaskAssigneeHandler\">\n      <field name=\"nodeId\">\n        <string value=\"4028b8814587beb8014587d1f6c70012\"/>\n      </field>\n    </assignment-handler>\n  </task>\n</process-definition>', 1, 1, NULL, NULL, '2014-04-22 13:04:15', NULL, '2014-04-22 13:04:15', '2040061');
INSERT INTO `lxt_process` VALUES ('4028b881458c16db01458cfae3440001', NULL, '新建流程2', '流程标识', '1.1', '', '<mxGraphModel><root><Workflow label=\"新建流程\" propertyTitle=\"流程配置\" nodeType=\"workflow\" workflowKey=\"流程标识\" version=\"1.0\" status=\"1\" memo=\"\" id=\"0\"><mxCell/></Workflow><Layer label=\"Default Layer\" id=\"1\"><mxCell parent=\"0\"/></Layer><Start label=\"开始\" propertyTitle=\"开始节点\" nodeType=\"start\" isSymbol=\"true\" id=\"2\"><mxCell style=\"start\" vertex=\"1\" parent=\"1\"><mxGeometry x=\"72\" y=\"75\" width=\"48\" height=\"48\" as=\"geometry\"/></mxCell></Start><Task label=\"任务\" propertyTitle=\"任务节点\" nodeType=\"task\" form=\"\" formText=\"无表单\" remindRuleId=\"\" remindRuleText=\"从不提醒\" assignerType=\"0\" assignerId=\"0\" handlerText=\"流程发起人\" allowTakeback=\"1\" allowDelegate=\"1\" allowSendback=\"0\" sendbackWay=\"1\" sendbackTargetId=\"\" sendbackTargetText=\"\" allowCheck=\"1\" checkTargetId=\"\" checkTargetText=\"\" allowUpload=\"1\" needAdvice=\"0\" memo=\"\" id=\"3\"><mxCell style=\"task\" vertex=\"1\" parent=\"1\"><mxGeometry x=\"216\" y=\"104\" width=\"96\" height=\"48\" as=\"geometry\"/></mxCell></Task><End label=\"结束\" propertyTitle=\"结束节点\" nodeType=\"end\" isSymbol=\"true\" id=\"4\"><mxCell style=\"end\" vertex=\"1\" parent=\"1\"><mxGeometry x=\"303\" y=\"262\" width=\"48\" height=\"48\" as=\"geometry\"/></mxCell></End><Edge label=\"to 任务\" description=\"\" propertyTitle=\"连接线\" nodeType=\"edge\" id=\"5\"><mxCell edge=\"1\" parent=\"1\" source=\"2\" target=\"3\"><mxGeometry relative=\"1\" as=\"geometry\"/></mxCell></Edge><Edge label=\"to 结束\" description=\"\" propertyTitle=\"连接线\" nodeType=\"edge\" id=\"6\"><mxCell edge=\"1\" parent=\"1\" source=\"3\" target=\"4\"><mxGeometry relative=\"1\" as=\"geometry\"/></mxCell></Edge></root></mxGraphModel>', '<process-definition name=\"新建流程\" key=\"流程标识\" xmlns=\"http://jbpm.org/4.4/jpdl\" description=\"\">\n  <task id=\"3\" name=\"任务\">\n    <description>4028b881458c16db01458cfae3440003</description>\n    <transition name=\"to 结束\" to=\"结束\"/>\n    <form></form>\n    <assignment-handler class=\"com.lxt.workflow.assigner.TaskAssigneeHandler\">\n      <field name=\"nodeId\">\n        <string value=\"4028b881458c16db01458cfae3440003\"/>\n      </field>\n    </assignment-handler>\n  </task>\n  <start id=\"2\" name=\"开始\">\n    <description>4028b881458c16db01458cfae3440004</description>\n    <transition name=\"to 任务\" to=\"任务\"/>\n  </start>\n  <end id=\"4\" name=\"结束\">\n    <description>4028b881458c16db01458cfae3440002</description>\n  </end>\n</process-definition>', 1, 0, NULL, NULL, '2014-04-23 13:07:03', NULL, '2014-04-23 13:07:03', '2050008');
INSERT INTO `lxt_process` VALUES ('4028b881458c16db01458cfae3440002', NULL, '新建流程3', '流程标识', '1.0', NULL, NULL, NULL, 1, 1, NULL, NULL, '2015-08-25 13:43:44', NULL, '2015-08-25 13:43:47', NULL);
INSERT INTO `lxt_process` VALUES ('4028b881458c16db01458cfae3440003', NULL, '新建流程4', '流程标识', '1.0', NULL, NULL, NULL, 0, 1, NULL, NULL, '2015-08-25 13:44:24', NULL, '2015-08-25 13:44:26', NULL);
INSERT INTO `lxt_process` VALUES ('4028b881458c16db01458cfae3440004', NULL, '新建流程5', '流程标识', '1.0', NULL, NULL, NULL, 1, 1, NULL, NULL, '2015-08-25 13:44:51', NULL, '2015-08-25 13:44:55', NULL);
INSERT INTO `lxt_process` VALUES ('4028b881458c16db01458cfae3440005', NULL, '新建流程6', '流程标识', '1.0', NULL, NULL, NULL, 1, 0, NULL, NULL, '2015-08-25 13:46:23', NULL, '2015-08-25 13:46:23', NULL);
INSERT INTO `lxt_process` VALUES ('4028b881458c16db01458cfae3440006', NULL, '新建流程7', '流程标识', '1.0', NULL, NULL, NULL, 1, 1, NULL, NULL, '2015-08-25 13:46:23', NULL, '2015-08-25 13:46:23', NULL);
INSERT INTO `lxt_process` VALUES ('4028b881458c16db01458cfae3440007', NULL, '新建流程8', '流程标识', '1.0', NULL, NULL, NULL, 0, 1, NULL, NULL, '2015-08-25 13:46:23', NULL, '2015-08-25 13:46:23', NULL);
INSERT INTO `lxt_process` VALUES ('4028b881458c16db01458cfae3440008', NULL, '新建流程9', '流程标识', '1.0', NULL, NULL, NULL, 1, 1, NULL, NULL, '2015-08-25 13:46:23', NULL, '2015-08-25 13:46:23', NULL);
INSERT INTO `lxt_process` VALUES ('4028b881458c16db01458cfae3440009', NULL, '新建流程10', '流程标识', '1.0', NULL, NULL, NULL, 1, 0, NULL, NULL, '2015-08-25 13:46:23', NULL, '2015-08-25 13:46:23', NULL);
INSERT INTO `lxt_process` VALUES ('4028b881458c16db01458cfae3440010', NULL, '新建流程11', '流程标识', '1.0', NULL, NULL, NULL, 1, 1, NULL, NULL, '2015-08-25 13:46:23', NULL, '2015-08-25 13:46:23', NULL);
INSERT INTO `lxt_process` VALUES ('4028b881458c16db01458cfae3440011', NULL, '新建流程12', '流程标识', '1.0', NULL, NULL, NULL, 0, 1, NULL, NULL, '2015-08-25 13:46:23', NULL, '2015-08-25 13:46:23', NULL);
INSERT INTO `lxt_process` VALUES ('4028b881458c16db01458cfae3440012', NULL, '新建流程13', '流程标识', '1.1', NULL, NULL, NULL, 1, 0, NULL, NULL, '2015-08-26 09:32:15', NULL, '2015-08-25 13:46:23', NULL);
INSERT INTO `lxt_process` VALUES ('4028b881458c16db01458cfae3440013', NULL, '新建流程14', '流程标识', '1.1', NULL, NULL, NULL, 1, 1, NULL, NULL, '2015-08-26 09:31:49', NULL, '2015-08-25 13:46:23', NULL);
INSERT INTO `lxt_process` VALUES ('4028b881458c16db01458cfae3440014', NULL, '新建流程15', '流程标识', '1.1', NULL, NULL, NULL, 1, 0, NULL, NULL, '2015-08-26 09:31:47', NULL, '2015-08-25 13:46:23', NULL);
INSERT INTO `lxt_process` VALUES ('4028b881458c16db01458cfae3440015', NULL, '新建流程16', '流程标识', '1.1', NULL, NULL, NULL, 0, 1, NULL, NULL, '2015-08-26 09:31:44', NULL, '2015-08-25 13:46:23', NULL);
INSERT INTO `lxt_process` VALUES ('4028b881458c16db01458cfae3440016', NULL, '新建流程17', '流程标识', '1.1', NULL, NULL, NULL, 1, 1, NULL, NULL, '2015-08-26 09:31:41', NULL, '2015-08-25 13:46:23', NULL);
INSERT INTO `lxt_process` VALUES ('4028b881458c16db01458cfae3440017', NULL, '新建流程18', '流程标识', '1.1', NULL, NULL, NULL, 1, 0, NULL, NULL, '2015-08-26 09:31:39', NULL, '2015-08-25 13:46:23', NULL);
INSERT INTO `lxt_process` VALUES ('4028b881458c16db01458cfae3440018', NULL, '新建流程19', '流程标识', '1.1', NULL, NULL, NULL, 1, 1, NULL, NULL, '2015-08-26 09:31:36', NULL, '2015-08-25 13:46:23', NULL);
INSERT INTO `lxt_process` VALUES ('4028b881458c16db01458cfae3440019', NULL, '新建流程20', '流程标识', '1.1', NULL, NULL, NULL, 1, 1, NULL, NULL, '2015-08-26 09:31:33', NULL, '2015-08-25 13:46:23', NULL);
INSERT INTO `lxt_process` VALUES ('4028b881458c16db01458cfae3440020', NULL, '新建流程21', '流程标识', '1.1', NULL, NULL, NULL, 0, 0, NULL, NULL, '2015-08-26 09:31:36', NULL, '2015-08-26 09:31:36', NULL);
COMMIT;

-- ----------------------------
-- Table structure for lxt_process_category
-- ----------------------------
DROP TABLE IF EXISTS `lxt_process_category`;
CREATE TABLE `lxt_process_category` (
  `ID` varchar(32) NOT NULL,
  `PARENT_ID` varchar(32) DEFAULT NULL,
  `NAME` varchar(64) DEFAULT NULL,
  `MEMO` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程分类表';

-- ----------------------------
-- Table structure for lxt_user
-- ----------------------------
DROP TABLE IF EXISTS `lxt_user`;
CREATE TABLE `lxt_user` (
  `USER_ID` varchar(32) NOT NULL COMMENT '用户ID',
  `USERNAME` varchar(64) DEFAULT NULL COMMENT '用户名',
  `PASSWORD` varchar(64) DEFAULT NULL COMMENT '密码',
  `REALNAME` varchar(64) DEFAULT NULL COMMENT '真实姓名',
  `ORG_ID` varchar(32) DEFAULT NULL COMMENT '机构ID',
  `DEPT_ID` varchar(32) DEFAULT NULL COMMENT '部门ID',
  `STATUS` int(11) DEFAULT '0' COMMENT '状态',
  `INSERT_USER_ID` varchar(64) DEFAULT NULL COMMENT '创建人ID',
  `INSERT_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '最后更新时间',
  `LOGIN_TIME` datetime DEFAULT NULL COMMENT '最后登录时间',
  `SET_QUESTION` int(11) DEFAULT NULL COMMENT '是否设置密保',
  `UPDATE_QUESTION_TIME` datetime DEFAULT NULL COMMENT '最后更新密保时间',
  `UPDATE_PWD_TIME` datetime DEFAULT NULL COMMENT '最后更新密码时间',
  `PWD_STATUS` int(11) DEFAULT NULL COMMENT '是否修改密码',
  `MOBILE` varchar(32) DEFAULT NULL COMMENT '手机',
  `EMAIL` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `QQ` varchar(32) DEFAULT NULL COMMENT 'QQ号码',
  `BIRTHDAY` datetime DEFAULT NULL COMMENT '出生日期',
  `GENDER` int(11) DEFAULT NULL COMMENT '性别',
  `IDENTITY_CARD` varchar(32) DEFAULT NULL COMMENT '身份证号',
  PRIMARY KEY (`USER_ID`) USING BTREE,
  KEY `FK_USER_DEPARTMENT` (`DEPT_ID`) USING BTREE,
  KEY `FK_USER_ORGANIZATION` (`ORG_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of lxt_user
-- ----------------------------
BEGIN;
INSERT INTO `lxt_user` VALUES ('admin', 'admin', '21232f297a57a5a743894a0e4a801fc3', '管理员', '11501', '1', 1, NULL, '2013-08-30 10:58:15', NULL, NULL, 1, NULL, NULL, 1, '1', '1', '1', '2013-08-30 10:58:30', 1, '1');
INSERT INTO `lxt_user` VALUES ('chenjia', 'chenjia', '6e92acacda1404464a7c04bedbe62bdf', '陈佳', '11502', '1', 1, NULL, '2013-07-21 10:52:05', NULL, NULL, 1, NULL, NULL, 1, '18702189255', 'chenjias1@sina.com', '251746034', '1989-05-28 00:00:00', 1, '43028198905282010');
INSERT INTO `lxt_user` VALUES ('xiaoting', 'xiaoting', 'bb909cda0c3f92bbf8666e0f2f22c330', '肖婷', '11502', '2', 1, NULL, '2013-07-21 20:30:28', NULL, NULL, 1, NULL, NULL, 1, '15021245307', '625520091@qq.com', '625520091', '1989-05-28 00:00:00', 0, '43028198909252142');
COMMIT;

-- ----------------------------
-- Table structure for lxt_user_setting
-- ----------------------------
DROP TABLE IF EXISTS `lxt_user_setting`;
CREATE TABLE `lxt_user_setting` (
  `USER_ID` varchar(32) NOT NULL DEFAULT '',
  `NICK_NAME` varchar(64) DEFAULT NULL,
  `CHAT_MOOD` varchar(255) DEFAULT NULL,
  `CHAT_STATUS` int(255) DEFAULT NULL,
  `HEAD_PIC` varchar(32) DEFAULT NULL,
  `MENU_CONFIG` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lxt_user_setting
-- ----------------------------
BEGIN;
INSERT INTO `lxt_user_setting` VALUES ('admin', '管理员', '英雄有泪不曾拭，放纵过往风凝之~', 1, 'admin.jpg', '{\"desktopMenus\":[{\"createTime\":1422965318194,\"href\":\"../../pages/system/userList.jsp\",\"icon\":\"../../images/icon32/user.png\",\"iconCls\":\"icon-user\",\"left\":60,\"name\":\"userList\",\"text\":\"用户列表\",\"top\":0},{\"createTime\":1423659417963,\"handler\":\"showChatWindow()\",\"icon\":\"../../images/icon32/user_comment.png\",\"iconCls\":\"icon-portal\",\"left\":0,\"name\":\"portals\",\"text\":\"好友管理\",\"top\":0}],\"taskMenus\":[{\"box\":{\"0\":{\"fcount\":1,\"jQuery18008647085735975053\":158},\"context\":{\"jQuery18008647085735975053\":9,\"location\":{\"hash\":\"\",\"host\":\"localhost\",\"hostname\":\"localhost\",\"href\":\"http://localhost/lxt1/pages/homepage/homepage.jsp?mode=desktop\",\"origin\":\"http://localhost\",\"password\":\"\",\"pathname\":\"/lxt1/pages/homepage/homepage.jsp\",\"port\":\"\",\"protocol\":\"http:\",\"search\":\"?mode=desktop\",\"username\":\"\"},\"validCodeImg\":{}},\"length\":1,\"selector\":\"#1422881459202\"},\"createTime\":1422800292845,\"dom\":{\"0\":{\"jQuery180009196721442845479\":156},\"length\":1},\"href\":\"../../pages/system/userList.jsp\",\"icon\":\"../../images/icon32/user.png\",\"iconCls\":\"icon-user\",\"left\":240,\"lock\":true,\"name\":\"userList\",\"text\":\"用户列表\",\"top\":120}]}');
INSERT INTO `lxt_user_setting` VALUES ('xiaoting', '婷婷', '遵从内心的选择！', 1, 'xiaoting.jpg', '{\"desktopMenus\":[{\"createTime\":1423021287538,\"handler\":\"showChatWindow()\",\"icon\":\"../../images/icon32/user_comment.png\",\"iconCls\":\"icon-portal\",\"left\":0,\"name\":\"portals\",\"text\":\"好友管理\",\"top\":0}],\"taskMenus\":[]}');
COMMIT;

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `aaa` float(255,8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of test
-- ----------------------------
BEGIN;
INSERT INTO `test` VALUES (32.42343140);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
