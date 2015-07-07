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

-- 导出  表 wechat.shop_goods 结构
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
CREATE TABLE IF NOT EXISTS `sys_config` (
  `cfg_key` varchar(50) NOT NULL,
  `cfg_value` varchar(255) NOT NULL,
  PRIMARY KEY (`cfg_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  wechat.sys_config 的数据：~3 rows (大约)
DELETE FROM `sys_config`;
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
INSERT INTO `sys_config` (`cfg_key`, `cfg_value`) VALUES
	('wx.shop.name', '店名'),
	('wx.shop.notification', '店铺公告'),
	('wx.welcome', '关注成功！');
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;


-- 导出  表 wechat.wx_customer 结构
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
CREATE TABLE IF NOT EXISTS `wx_props` (
  `appId` varchar(50) NOT NULL,
  `appSecret` varchar(50) NOT NULL,
  `token` varchar(100) NOT NULL,
  `messageEncrypt` bit(1) NOT NULL,
  `encodingAesKey` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信配置表';

-- 正在导出表  wechat.wx_props 的数据：~0 rows (大约)
DELETE FROM `wx_props`;
/*!40000 ALTER TABLE `wx_props` DISABLE KEYS */;
INSERT INTO `wx_props` (`appId`, `appSecret`, `token`, `messageEncrypt`, `encodingAesKey`) VALUES
	('wxbbaed5839238c4eb', '064682c9add7e6756f9f435c904825a9', 'eastbride', b'0', 'RBSOCvhUnTljEvosRNwwek2NB6wIuqI2B4sVNpM3Ni6');
/*!40000 ALTER TABLE `wx_props` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
