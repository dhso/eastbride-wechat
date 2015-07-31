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

-- 导出  表 wechat.shiro_permissions 结构
DROP TABLE IF EXISTS `shiro_permissions`;
CREATE TABLE IF NOT EXISTS `shiro_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission` varchar(100) NOT NULL,
  `permission_desc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_permissions_permission` (`permission`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 正在导出表  wechat.shiro_permissions 的数据：~3 rows (大约)
DELETE FROM `shiro_permissions`;
/*!40000 ALTER TABLE `shiro_permissions` DISABLE KEYS */;
INSERT INTO `shiro_permissions` (`id`, `permission`, `permission_desc`) VALUES
	(1, 'crm:system:auth', '权限设置'),
	(2, 'cms:system:setting', '系统设置');
/*!40000 ALTER TABLE `shiro_permissions` ENABLE KEYS */;


-- 导出  表 wechat.shiro_roles 结构
DROP TABLE IF EXISTS `shiro_roles`;
CREATE TABLE IF NOT EXISTS `shiro_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(100) NOT NULL,
  `role_desc` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_roles_role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- 正在导出表  wechat.shiro_roles 的数据：~3 rows (大约)
DELETE FROM `shiro_roles`;
/*!40000 ALTER TABLE `shiro_roles` DISABLE KEYS */;
INSERT INTO `shiro_roles` (`id`, `role`, `role_desc`) VALUES
	(1, 'admin', '超级管理员'),
	(2, 'wechat', '微信管理员'),
	(3, 'system', '系统管理员');
/*!40000 ALTER TABLE `shiro_roles` ENABLE KEYS */;


-- 导出  表 wechat.shiro_roles_permissions 结构
DROP TABLE IF EXISTS `shiro_roles_permissions`;
CREATE TABLE IF NOT EXISTS `shiro_roles_permissions` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  wechat.shiro_roles_permissions 的数据：~4 rows (大约)
DELETE FROM `shiro_roles_permissions`;
/*!40000 ALTER TABLE `shiro_roles_permissions` DISABLE KEYS */;
INSERT INTO `shiro_roles_permissions` (`role_id`, `permission_id`) VALUES
	(1, 1),
	(1, 2),
	(2, 2),
	(3, 1);
/*!40000 ALTER TABLE `shiro_roles_permissions` ENABLE KEYS */;


-- 导出  表 wechat.shiro_urls 结构
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- 正在导出表  wechat.shiro_urls 的数据：~5 rows (大约)
DELETE FROM `shiro_urls`;
/*!40000 ALTER TABLE `shiro_urls` DISABLE KEYS */;
INSERT INTO `shiro_urls` (`id`, `permission_id`, `url_type_id`, `url`, `text`, `icon`, `url_order`, `is_iframe`) VALUES
	(1, 1, 1, '/system/permission', '权限管理', 'fa fa-cog', 1003, 0),
	(2, 1, 1, '/system/url', '链接管理', 'fa fa-cog', 1001, 0),
	(3, 1, 1, '/system/url_type', '链接类型', 'fa fa-cog', 1002, 0),
	(4, 1, 1, '/system/user', '用户管理', 'fa fa-cog', 1005, 0),
	(5, 1, 1, '/system/role', '角色管理', 'fa fa-cog', 1004, 0),
	(6, 2, 2, '/druid/index.htm', 'durid监控', 'fa fa-cog', 2001, 1);
/*!40000 ALTER TABLE `shiro_urls` ENABLE KEYS */;


-- 导出  表 wechat.shiro_urls_type 结构
DROP TABLE IF EXISTS `shiro_urls_type`;
CREATE TABLE IF NOT EXISTS `shiro_urls_type` (
  `url_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `url_type_name` varchar(50) NOT NULL,
  `url_type_icon` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`url_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 正在导出表  wechat.shiro_urls_type 的数据：~2 rows (大约)
DELETE FROM `shiro_urls_type`;
/*!40000 ALTER TABLE `shiro_urls_type` DISABLE KEYS */;
INSERT INTO `shiro_urls_type` (`url_type_id`, `url_type_name`, `url_type_icon`) VALUES
	(1, '权限配置', 'fa fa-cog'),
	(2, '系统配置', 'fa fa-cog');
/*!40000 ALTER TABLE `shiro_urls_type` ENABLE KEYS */;


-- 导出  表 wechat.shiro_users 结构
DROP TABLE IF EXISTS `shiro_users`;
CREATE TABLE IF NOT EXISTS `shiro_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `locked` int(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_users_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 正在导出表  wechat.shiro_users 的数据：~3 rows (大约)
DELETE FROM `shiro_users`;
/*!40000 ALTER TABLE `shiro_users` DISABLE KEYS */;
INSERT INTO `shiro_users` (`id`, `username`, `password`, `salt`, `locked`) VALUES
	(1, 'admin', '8e7a4a6bad4f685bd9da4f78b5f76f9f', 'shiro', 0),
	(2, 'system', 'b5435b43cca8783d13515ecaa3c28a6c', 'shiro', 0),
	(3, 'wechat', '8e7a4a6bad4f685bd9da4f78b5f76f9f', 'shiro', 0);
/*!40000 ALTER TABLE `shiro_users` ENABLE KEYS */;


-- 导出  表 wechat.shiro_users_roles 结构
DROP TABLE IF EXISTS `shiro_users_roles`;
CREATE TABLE IF NOT EXISTS `shiro_users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  wechat.shiro_users_roles 的数据：~3 rows (大约)
DELETE FROM `shiro_users_roles`;
/*!40000 ALTER TABLE `shiro_users_roles` DISABLE KEYS */;
INSERT INTO `shiro_users_roles` (`user_id`, `role_id`) VALUES
	(1, 1),
	(2, 3),
	(3, 2);
/*!40000 ALTER TABLE `shiro_users_roles` ENABLE KEYS */;


-- 导出  表 wechat.shop_goods 结构
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

-- 正在导出表  wechat.shop_goods 的数据：~0 rows (大约)
DELETE FROM `shop_goods`;
/*!40000 ALTER TABLE `shop_goods` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_goods` ENABLE KEYS */;


-- 导出  表 wechat.shop_menu 结构
DROP TABLE IF EXISTS `shop_menu`;
CREATE TABLE IF NOT EXISTS `shop_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  wechat.shop_menu 的数据：~0 rows (大约)
DELETE FROM `shop_menu`;
/*!40000 ALTER TABLE `shop_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_menu` ENABLE KEYS */;


-- 导出  表 wechat.shop_order 结构
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

-- 正在导出表  wechat.shop_order 的数据：~0 rows (大约)
DELETE FROM `shop_order`;
/*!40000 ALTER TABLE `shop_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_order` ENABLE KEYS */;


-- 导出  表 wechat.shop_wifi 结构
DROP TABLE IF EXISTS `shop_wifi`;
CREATE TABLE IF NOT EXISTS `shop_wifi` (
  `openId` varchar(100) NOT NULL,
  `captcha` varchar(10) NOT NULL,
  `expired_dt` datetime NOT NULL,
  PRIMARY KEY (`openId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  wechat.shop_wifi 的数据：~0 rows (大约)
DELETE FROM `shop_wifi`;
/*!40000 ALTER TABLE `shop_wifi` DISABLE KEYS */;
INSERT INTO `shop_wifi` (`openId`, `captcha`, `expired_dt`) VALUES
	('410000100', '109591', '2015-07-07 13:15:18');
/*!40000 ALTER TABLE `shop_wifi` ENABLE KEYS */;


-- 导出  表 wechat.sys_config 结构
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE IF NOT EXISTS `sys_config` (
  `cfg_key` varchar(50) NOT NULL,
  `cfg_value` varchar(255) NOT NULL,
  `cfg_type_id` int(11) NOT NULL,
  PRIMARY KEY (`cfg_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  wechat.sys_config 的数据：~8 rows (大约)
DELETE FROM `sys_config`;
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
INSERT INTO `sys_config` (`cfg_key`, `cfg_value`, `cfg_type_id`) VALUES
	('wx.appId', 'wxbbaed5839238c4eb', 3),
	('wx.appSecret', '064682c9add7e6756f9f435c904825a9', 3),
	('wx.encodingAesKey', 'RBSOCvhUnTljEvosRNwwek2NB6wIuqI2B4sVNpM3Ni6', 3),
	('wx.messageEncrypt', '0', 3),
	('wx.shop.name', '店名', 2),
	('wx.shop.notification', '店铺公告', 2),
	('wx.token', 'eastbride', 3),
	('wx.welcome', '关注成功！', 1);
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;


-- 导出  表 wechat.sys_config_type 结构
DROP TABLE IF EXISTS `sys_config_type`;
CREATE TABLE IF NOT EXISTS `sys_config_type` (
  `cfg_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `cfg_type_name` varchar(50) NOT NULL,
  PRIMARY KEY (`cfg_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 正在导出表  wechat.sys_config_type 的数据：~3 rows (大约)
DELETE FROM `sys_config_type`;
/*!40000 ALTER TABLE `sys_config_type` DISABLE KEYS */;
INSERT INTO `sys_config_type` (`cfg_type_id`, `cfg_type_name`) VALUES
	(1, '微信配置'),
	(2, '商城配置'),
	(3, '微信基础配置');
/*!40000 ALTER TABLE `sys_config_type` ENABLE KEYS */;


-- 导出  表 wechat.wx_customer 结构
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

-- 正在导出表  wechat.wx_customer 的数据：~1 rows (大约)
DELETE FROM `wx_customer`;
/*!40000 ALTER TABLE `wx_customer` DISABLE KEYS */;
INSERT INTO `wx_customer` (`openId`, `subscribe_flag`, `true_name`, `mobile`, `address`, `money`, `create_id`, `create_dt`, `update_id`, `update_dt`) VALUES
	('410000100', '1', '', '', '', '', '直接关注', '2015-06-10 14:50:30', '直接关注', '2015-07-07 11:06:13');
/*!40000 ALTER TABLE `wx_customer` ENABLE KEYS */;


-- 导出  表 wechat.wx_props 结构
DROP TABLE IF EXISTS `wx_props`;
CREATE TABLE IF NOT EXISTS `wx_props` (
  `appId` varchar(50) NOT NULL,
  `appSecret` varchar(50) NOT NULL,
  `token` varchar(100) NOT NULL,
  `messageEncrypt` bit(1) NOT NULL,
  `encodingAesKey` varchar(255) NOT NULL,
  `userId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信配置表';

-- 正在导出表  wechat.wx_props 的数据：~1 rows (大约)
DELETE FROM `wx_props`;
/*!40000 ALTER TABLE `wx_props` DISABLE KEYS */;
INSERT INTO `wx_props` (`appId`, `appSecret`, `token`, `messageEncrypt`, `encodingAesKey`, `userId`) VALUES
	('wxbbaed5839238c4eb', '064682c9add7e6756f9f435c904825a9', 'eastbride', b'0', 'RBSOCvhUnTljEvosRNwwek2NB6wIuqI2B4sVNpM3Ni6', 1);
/*!40000 ALTER TABLE `wx_props` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
