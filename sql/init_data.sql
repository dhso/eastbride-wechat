-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.17 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 eastbride.eastbride_carousel 结构
DROP TABLE IF EXISTS `eastbride_carousel`;
CREATE TABLE IF NOT EXISTS `eastbride_carousel` (
  `crs_id` int(11) NOT NULL AUTO_INCREMENT,
  `crs_h2` varchar(255) DEFAULT NULL,
  `crs_p` text,
  `crs_img` text,
  `crs_href` text,
  `crs_button` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`crs_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 正在导出表  eastbride.eastbride_carousel 的数据：~2 rows (大约)
DELETE FROM `eastbride_carousel`;
/*!40000 ALTER TABLE `eastbride_carousel` DISABLE KEYS */;
INSERT INTO `eastbride_carousel` (`crs_id`, `crs_h2`, `crs_p`, `crs_img`, `crs_href`, `crs_button`) VALUES
	(1, '美轮美奂', '用心去拍，留下最美的瞬间！', 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/1.jpg', '', '关注'),
	(2, '想你所想', '想你所想，爱你所爱！', 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/10.jpg', NULL, '订阅');
/*!40000 ALTER TABLE `eastbride_carousel` ENABLE KEYS */;


-- 导出  表 eastbride.eastbride_gallery 结构
DROP TABLE IF EXISTS `eastbride_gallery`;
CREATE TABLE IF NOT EXISTS `eastbride_gallery` (
  `gry_id` int(11) NOT NULL AUTO_INCREMENT,
  `fullsize_url` varchar(255) NOT NULL DEFAULT '0',
  `smallsize_url` varchar(255) NOT NULL DEFAULT '0',
  `gry_title` varchar(255) DEFAULT '0',
  `gry_alt` varchar(255) DEFAULT '0',
  PRIMARY KEY (`gry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- 正在导出表  eastbride.eastbride_gallery 的数据：~26 rows (大约)
DELETE FROM `eastbride_gallery`;
/*!40000 ALTER TABLE `eastbride_gallery` DISABLE KEYS */;
INSERT INTO `eastbride_gallery` (`gry_id`, `fullsize_url`, `smallsize_url`, `gry_title`, `gry_alt`) VALUES
	(1, 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/1.jpg', 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/1.jpg?mageView2/1/w/100/h/100/q/75', '马场奇缘', '马场奇缘'),
	(2, 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/10.jpg', 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/10.jpg?mageView2/1/w/100/h/100/q/75', '花千骨', '花千骨'),
	(3, 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/10_调整大小.jpg', 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/10_调整大小.jpg?mageView2/1/w/100/h/100/q/75', '', ''),
	(4, 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/11_调整大小.jpg', 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/11_调整大小.jpg?mageView2/1/w/100/h/100/q/75', NULL, NULL),
	(5, 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/12.jpg', 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/12.jpg?mageView2/1/w/100/h/100/q/75', '牵你的手', '牵你的手'),
	(6, 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/12_调整大小.jpg', 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/12_调整大小.jpg?mageView2/1/w/100/h/100/q/75', '童话小镇', NULL),
	(7, 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/13.jpg', 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/13.jpg?mageView2/1/w/100/h/100/q/75', NULL, NULL),
	(8, 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/13_调整大小.jpg', 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/13_调整大小.jpg?mageView2/1/w/100/h/100/q/75', NULL, NULL),
	(9, 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/14.jpg', 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/14.jpg?mageView2/1/w/100/h/100/q/75', NULL, NULL),
	(10, 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/14_调整大小.jpg', 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/14_调整大小.jpg?mageView2/1/w/100/h/100/q/75', NULL, NULL),
	(11, 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/15.jpg', 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/15.jpg?mageView2/1/w/100/h/100/q/75', NULL, NULL),
	(12, 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/15_调整大小.jpg', 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/15_调整大小.jpg?mageView2/1/w/100/h/100/q/75', NULL, NULL),
	(13, 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/16.jpg', 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/16.jpg?mageView2/1/w/100/h/100/q/75', NULL, NULL),
	(14, 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/16_调整大小.jpg', 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/16_调整大小.jpg?mageView2/1/w/100/h/100/q/75', NULL, NULL),
	(15, 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/17.jpg', 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/17.jpg?mageView2/1/w/100/h/100/q/75', NULL, NULL),
	(16, 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/17_调整大小.jpg', 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/17_调整大小.jpg?mageView2/1/w/100/h/100/q/75', NULL, NULL),
	(17, 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/18.jpg', 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/18.jpg?mageView2/1/w/100/h/100/q/75', NULL, NULL),
	(18, 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/18调整大小.jpg', 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/18调整大小.jpg?mageView2/1/w/100/h/100/q/75', NULL, NULL),
	(19, 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/19.jpg', 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/19.jpg?mageView2/1/w/100/h/100/q/75', NULL, NULL),
	(20, 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/19调整大小.jpg', 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/19调整大小.jpg?mageView2/1/w/100/h/100/q/75', NULL, NULL),
	(21, 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/1_调整大小.jpg', 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/1_调整大小.jpg?mageView2/1/w/100/h/100/q/75', NULL, NULL),
	(22, 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/2.jpg', 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/2.jpg?mageView2/1/w/100/h/100/q/75', NULL, NULL),
	(23, 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/20.jpg', 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/20.jpg?mageView2/1/w/100/h/100/q/75', NULL, NULL),
	(24, 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/20调整大小.jpg', 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/20调整大小.jpg?mageView2/1/w/100/h/100/q/75', NULL, NULL),
	(25, 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/21.jpg', 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/21.jpg?mageView2/1/w/100/h/100/q/75', NULL, NULL),
	(26, 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/21_调整大小.jpg', 'http://7xljx2.com1.z0.glb.clouddn.com/gallery/21_调整大小.jpg?mageView2/1/w/100/h/100/q/75', NULL, NULL);
/*!40000 ALTER TABLE `eastbride_gallery` ENABLE KEYS */;


-- 导出  表 eastbride.shiro_permissions 结构
DROP TABLE IF EXISTS `shiro_permissions`;
CREATE TABLE IF NOT EXISTS `shiro_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission` varchar(100) NOT NULL,
  `permission_desc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_permissions_permission` (`permission`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 正在导出表  eastbride.shiro_permissions 的数据：~4 rows (大约)
DELETE FROM `shiro_permissions`;
/*!40000 ALTER TABLE `shiro_permissions` DISABLE KEYS */;
INSERT INTO `shiro_permissions` (`id`, `permission`, `permission_desc`) VALUES
	(1, 'crm:system:auth', '权限设置'),
	(2, 'cms:system:setting', '系统设置'),
	(3, 'wechat', '微信'),
	(4, 'eastbride', '网站');
/*!40000 ALTER TABLE `shiro_permissions` ENABLE KEYS */;


-- 导出  表 eastbride.shiro_roles 结构
DROP TABLE IF EXISTS `shiro_roles`;
CREATE TABLE IF NOT EXISTS `shiro_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(100) NOT NULL,
  `role_desc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_roles_role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 正在导出表  eastbride.shiro_roles 的数据：~4 rows (大约)
DELETE FROM `shiro_roles`;
/*!40000 ALTER TABLE `shiro_roles` DISABLE KEYS */;
INSERT INTO `shiro_roles` (`id`, `role`, `role_desc`) VALUES
	(1, 'admin', '超级管理员'),
	(2, 'wechat', '微信管理员'),
	(3, 'system', '系统管理员'),
	(4, 'eastbride', '网站管理员');
/*!40000 ALTER TABLE `shiro_roles` ENABLE KEYS */;


-- 导出  表 eastbride.shiro_roles_permissions 结构
DROP TABLE IF EXISTS `shiro_roles_permissions`;
CREATE TABLE IF NOT EXISTS `shiro_roles_permissions` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  eastbride.shiro_roles_permissions 的数据：~7 rows (大约)
DELETE FROM `shiro_roles_permissions`;
/*!40000 ALTER TABLE `shiro_roles_permissions` DISABLE KEYS */;
INSERT INTO `shiro_roles_permissions` (`role_id`, `permission_id`) VALUES
	(1, 1),
	(1, 2),
	(1, 3),
	(1, 4),
	(2, 3),
	(3, 1),
	(4, 4);
/*!40000 ALTER TABLE `shiro_roles_permissions` ENABLE KEYS */;


-- 导出  表 eastbride.shiro_urls 结构
DROP TABLE IF EXISTS `shiro_urls`;
CREATE TABLE IF NOT EXISTS `shiro_urls` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_id` int(11) NOT NULL,
  `url_type_id` int(11) NOT NULL,
  `url` varchar(255) NOT NULL,
  `text` varchar(50) NOT NULL,
  `icon` varchar(50) NOT NULL,
  `url_order` int(11) NOT NULL,
  `is_iframe` int(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- 正在导出表  eastbride.shiro_urls 的数据：~9 rows (大约)
DELETE FROM `shiro_urls`;
/*!40000 ALTER TABLE `shiro_urls` DISABLE KEYS */;
INSERT INTO `shiro_urls` (`id`, `permission_id`, `url_type_id`, `url`, `text`, `icon`, `url_order`, `is_iframe`) VALUES
	(1, 1, 1, '/system/permission', '权限管理', 'fa fa-cog', 1003, 0),
	(2, 1, 1, '/system/url', '链接管理', 'fa fa-cog', 1001, 0),
	(3, 1, 1, '/system/url_type', '链接类型', 'fa fa-cog', 1002, 0),
	(4, 1, 1, '/system/user', '用户管理', 'fa fa-cog', 1005, 0),
	(5, 1, 1, '/system/role', '角色管理', 'fa fa-cog', 1004, 0),
	(6, 2, 2, '/druid/index.htm', 'durid监控', 'fa fa-cog', 2001, 1),
	(7, 3, 3, '/weixin/customer', '微信', 'fa fa-cog', 3001, 0),
	(8, 4, 4, '/eastbride/carousel/manage', '轮播广告', 'fa fa-cog', 4001, 0),
	(9, 4, 4, '/eastbride/gallery/manage', '客片展示', 'fa fa-cog', 4002, 0);
/*!40000 ALTER TABLE `shiro_urls` ENABLE KEYS */;


-- 导出  表 eastbride.shiro_urls_type 结构
DROP TABLE IF EXISTS `shiro_urls_type`;
CREATE TABLE IF NOT EXISTS `shiro_urls_type` (
  `url_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `url_type_name` varchar(50) NOT NULL,
  `url_type_icon` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`url_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 正在导出表  eastbride.shiro_urls_type 的数据：~4 rows (大约)
DELETE FROM `shiro_urls_type`;
/*!40000 ALTER TABLE `shiro_urls_type` DISABLE KEYS */;
INSERT INTO `shiro_urls_type` (`url_type_id`, `url_type_name`, `url_type_icon`) VALUES
	(1, '权限配置', 'fa fa-cog'),
	(2, '系统配置', 'fa fa-cog'),
	(3, '微信', 'fa fa-cog'),
	(4, '网站', 'fa fa-cog');
/*!40000 ALTER TABLE `shiro_urls_type` ENABLE KEYS */;


-- 导出  表 eastbride.shiro_users 结构
DROP TABLE IF EXISTS `shiro_users`;
CREATE TABLE IF NOT EXISTS `shiro_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `locked` int(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_users_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 正在导出表  eastbride.shiro_users 的数据：~4 rows (大约)
DELETE FROM `shiro_users`;
/*!40000 ALTER TABLE `shiro_users` DISABLE KEYS */;
INSERT INTO `shiro_users` (`id`, `username`, `password`, `salt`, `locked`) VALUES
	(1, 'admin', '8e7a4a6bad4f685bd9da4f78b5f76f9f', 'shiro', 0),
	(2, 'system', 'b5435b43cca8783d13515ecaa3c28a6c', 'shiro', 0),
	(3, 'wechat', '8e7a4a6bad4f685bd9da4f78b5f76f9f', 'shiro', 0),
	(4, 'eastbride', '4a4485209a9763269ac8c2381b4dbf9f', 'shiro', 0);
/*!40000 ALTER TABLE `shiro_users` ENABLE KEYS */;


-- 导出  表 eastbride.shiro_users_roles 结构
DROP TABLE IF EXISTS `shiro_users_roles`;
CREATE TABLE IF NOT EXISTS `shiro_users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  eastbride.shiro_users_roles 的数据：~4 rows (大约)
DELETE FROM `shiro_users_roles`;
/*!40000 ALTER TABLE `shiro_users_roles` DISABLE KEYS */;
INSERT INTO `shiro_users_roles` (`user_id`, `role_id`) VALUES
	(1, 1),
	(2, 3),
	(3, 2),
	(4, 4);
/*!40000 ALTER TABLE `shiro_users_roles` ENABLE KEYS */;


-- 导出  表 eastbride.shop_goods 结构
DROP TABLE IF EXISTS `shop_goods`;
CREATE TABLE IF NOT EXISTS `shop_goods` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `image` varchar(255) NOT NULL,
  `detail` text NOT NULL,
  `price` varchar(50) NOT NULL,
  `sale` varchar(50) NOT NULL,
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  eastbride.shop_goods 的数据：~0 rows (大约)
DELETE FROM `shop_goods`;
/*!40000 ALTER TABLE `shop_goods` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_goods` ENABLE KEYS */;


-- 导出  表 eastbride.shop_menu 结构
DROP TABLE IF EXISTS `shop_menu`;
CREATE TABLE IF NOT EXISTS `shop_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  eastbride.shop_menu 的数据：~0 rows (大约)
DELETE FROM `shop_menu`;
/*!40000 ALTER TABLE `shop_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_menu` ENABLE KEYS */;


-- 导出  表 eastbride.shop_order 结构
DROP TABLE IF EXISTS `shop_order`;
CREATE TABLE IF NOT EXISTS `shop_order` (
  `order_id` varchar(100) NOT NULL,
  `open_id` varchar(100) NOT NULL,
  `total_price` varchar(50) NOT NULL,
  `pay_style` varchar(50) NOT NULL,
  `pay_status` char(1) NOT NULL,
  `order_status` char(1) NOT NULL,
  `order_note` varchar(255) NOT NULL,
  `cartdata` text NOT NULL,
  `create_id` varchar(50) NOT NULL,
  `create_dt` datetime NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  eastbride.shop_order 的数据：~0 rows (大约)
DELETE FROM `shop_order`;
/*!40000 ALTER TABLE `shop_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_order` ENABLE KEYS */;


-- 导出  表 eastbride.shop_wifi 结构
DROP TABLE IF EXISTS `shop_wifi`;
CREATE TABLE IF NOT EXISTS `shop_wifi` (
  `openId` varchar(100) NOT NULL,
  `captcha` varchar(10) NOT NULL,
  `expired_dt` datetime NOT NULL,
  PRIMARY KEY (`openId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  eastbride.shop_wifi 的数据：~0 rows (大约)
DELETE FROM `shop_wifi`;
/*!40000 ALTER TABLE `shop_wifi` DISABLE KEYS */;
INSERT INTO `shop_wifi` (`openId`, `captcha`, `expired_dt`) VALUES
	('410000100', '109591', '2015-07-07 13:15:18');
/*!40000 ALTER TABLE `shop_wifi` ENABLE KEYS */;


-- 导出  表 eastbride.sys_config 结构
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE IF NOT EXISTS `sys_config` (
  `cfg_key` varchar(50) NOT NULL,
  `cfg_value` varchar(255) NOT NULL,
  `cfg_type_id` int(11) NOT NULL,
  PRIMARY KEY (`cfg_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  eastbride.sys_config 的数据：~8 rows (大约)
DELETE FROM `sys_config`;
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;


-- 导出  表 eastbride.sys_config_type 结构
DROP TABLE IF EXISTS `sys_config_type`;
CREATE TABLE IF NOT EXISTS `sys_config_type` (
  `cfg_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `cfg_type_name` varchar(50) NOT NULL,
  PRIMARY KEY (`cfg_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 正在导出表  eastbride.sys_config_type 的数据：~3 rows (大约)
DELETE FROM `sys_config_type`;
/*!40000 ALTER TABLE `sys_config_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_config_type` ENABLE KEYS */;


-- 导出  表 eastbride.wx_config 结构
DROP TABLE IF EXISTS `wx_config`;
CREATE TABLE IF NOT EXISTS `wx_config` (
  `cfg_key` varchar(255) NOT NULL,
  `cfg_value` varchar(255) NOT NULL,
  PRIMARY KEY (`cfg_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信配置表';

-- 正在导出表  eastbride.wx_config 的数据：~5 rows (大约)
DELETE FROM `wx_config`;
/*!40000 ALTER TABLE `wx_config` DISABLE KEYS */;
INSERT INTO `wx_config` (`cfg_key`, `cfg_value`) VALUES
	('appId', 'wxbbaed5839238c4eb'),
	('appSecret', '064682c9add7e6756f9f435c904825a9'),
	('encodingAesKey', 'RBSOCvhUnTljEvosRNwwek2NB6wIuqI2B4sVNpM3Ni6'),
	('help', '帮助/n这是帮助功能。'),
	('messageEncrypt', '0'),
	('token', 'eastbride'),
	('welcome', '关注成功！');
/*!40000 ALTER TABLE `wx_config` ENABLE KEYS */;


-- 导出  表 eastbride.wx_customer 结构
DROP TABLE IF EXISTS `wx_customer`;
CREATE TABLE IF NOT EXISTS `wx_customer` (
  `openId` varchar(50) NOT NULL,
  `subscribe_flag` char(1) NOT NULL,
  `true_name` varchar(20) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `address` text,
  `money` varchar(50) DEFAULT NULL,
  `create_id` varchar(50) NOT NULL,
  `create_dt` datetime NOT NULL,
  `update_id` varchar(50) DEFAULT NULL,
  `update_dt` datetime DEFAULT NULL,
  PRIMARY KEY (`openId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  eastbride.wx_customer 的数据：~0 rows (大约)
DELETE FROM `wx_customer`;
/*!40000 ALTER TABLE `wx_customer` DISABLE KEYS */;
INSERT INTO `wx_customer` (`openId`, `subscribe_flag`, `true_name`, `mobile`, `address`, `money`, `create_id`, `create_dt`, `update_id`, `update_dt`) VALUES
	('410000100', '1', '', '', '', '', '直接关注', '2015-06-10 14:50:30', '直接关注', '2015-07-07 11:06:13');
/*!40000 ALTER TABLE `wx_customer` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
