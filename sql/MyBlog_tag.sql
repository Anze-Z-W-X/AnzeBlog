CREATE DATABASE /*!32312 IF NOT EXISTS*/`az_blog` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `az_blog`;

/*Table structure for table `az_tag` */

DROP TABLE IF EXISTS `az_tag`;

CREATE TABLE `az_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL COMMENT '标签名',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` int(1) DEFAULT '0' COMMENT '删除标志（0代表未删除，1代表已删除）',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='标签';

/*Data for the table `az_tag` */

insert  into `az_tag`(`id`,`name`,`create_by`,`create_time`,`update_by`,`update_time`,`del_flag`,`remark`) values (1,'Mybatis',NULL,NULL,NULL,'2022-01-11 09:20:50',0,'weqwe'),(2,'asdas',NULL,'2022-01-11 09:20:55',NULL,'2022-01-11 09:20:55',1,'weqw'),(3,'weqw',NULL,'2022-01-11 09:21:07',NULL,'2022-01-11 09:21:07',1,'qweqwe'),(4,'Java',NULL,'2022-01-13 15:22:43',NULL,'2022-01-13 15:22:43',0,'sdad'),(5,'WAD',NULL,'2022-01-13 15:22:47',NULL,'2022-01-13 15:22:47',0,'ASDAD');
