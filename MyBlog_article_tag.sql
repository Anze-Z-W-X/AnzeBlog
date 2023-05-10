DROP TABLE IF EXISTS `az_article_tag`;

CREATE TABLE `az_article_tag` (
  `article_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `tag_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '标签id',
  PRIMARY KEY (`article_id`,`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='文章标签关联表';

/*Data for the table `sg_article_tag` */

insert  into `az_article_tag`(`article_id`,`tag_id`) values (1,4),(2,1),(2,4),(3,4),(3,5);

