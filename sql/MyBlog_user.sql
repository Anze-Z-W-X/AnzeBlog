﻿CREATE DATABASE /*!32312 IF NOT EXISTS*/`az_blog` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `az_blog`;

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '用户名',
  `nick_name` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '昵称',
  `password` varchar(64) NOT NULL DEFAULT 'NULL' COMMENT '密码',
  `type` char(1) DEFAULT '0' COMMENT '用户类型：0代表普通用户，1代表管理员',
  `status` char(1) DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `phonenumber` varchar(32) DEFAULT NULL COMMENT '手机号',
  `sex` char(1) DEFAULT NULL COMMENT '用户性别（0男，1女，2未知）',
  `avatar` varchar(128) DEFAULT NULL COMMENT '头像',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人的用户id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `del_flag` int(11) DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`user_name`,`nick_name`,`password`,`type`,`status`,`email`,`phonenumber`,`sex`,`avatar`,`create_by`,`create_time`,`update_by`,`update_time`,`del_flag`) values (1,'sg','sg333','$2a$10$Jnq31rRkNV3RNzXe0REsEOSKaYK8UgVZZqlNlNXqn.JeVcj2NdeZy','1','0','23412332@qq.com','18888888888','1','https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farticle%2F3bf9c263bc0f2ac5c3a7feb9e218d07475573ec8.gi',NULL,'2022-01-05 09:01:56',1,'2022-01-30 15:37:03',0),(3,'sg3','weqe','$2a$10$ydv3rLkteFnRx9xelQ7elOiVhFvXOooA98xCqk/omh7G94R.K/E3O','1','0',NULL,NULL,'0',NULL,NULL,'2022-01-05 13:28:43',NULL,'2022-01-05 13:28:43',0),(4,'sg2','dsadd','$2a$10$kY4T3SN7i4muBccZppd2OOkhxMN6yt8tND1sF89hXOaFylhY2T3he','1','0','23412332@qq.com','19098790742','0',NULL,NULL,NULL,NULL,NULL,0),(5,'sg2233','tteqe','','1','0',NULL,'18246845873','1',NULL,NULL,'2022-01-06 03:51:13',NULL,'2022-01-06 07:00:50',0),(6,'sangeng','sangeng','$2a$10$Jnq31rRkNV3RNzXe0REsEOSKaYK8UgVZZqlNlNXqn.JeVcj2NdeZy','1','0','2312321','17777777777','0',NULL,NULL,'2022-01-16 06:54:26',NULL,'2022-01-16 07:06:34',0);