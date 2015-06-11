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

-- 导出  表 wechat.config 结构
CREATE TABLE IF NOT EXISTS `config` (
  `cfg_key` varchar(50) NOT NULL,
  `cfg_value` varchar(255) NOT NULL,
  PRIMARY KEY (`cfg_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  wechat.config 的数据：~3 rows (大约)
DELETE FROM `config`;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` (`cfg_key`, `cfg_value`) VALUES
	('wx.shop.name', '店名'),
	('wx.shop.notification', '店铺公告'),
	('wx.welcome', '关注成功！');
/*!40000 ALTER TABLE `config` ENABLE KEYS */;


-- 导出  表 wechat.customer 结构
CREATE TABLE IF NOT EXISTS `customer` (
  `open_id` varchar(100) NOT NULL,
  `subscribe_flag` char(1) NOT NULL,
  `true_name` varchar(20) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `address` text NOT NULL,
  `money` varchar(50) NOT NULL,
  `create_id` varchar(50) NOT NULL,
  `create_dt` datetime NOT NULL,
  PRIMARY KEY (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  wechat.customer 的数据：~1 rows (大约)
DELETE FROM `customer`;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`open_id`, `subscribe_flag`, `true_name`, `mobile`, `address`, `money`, `create_id`, `create_dt`) VALUES
	('410000100', '0', '', '', '', '', '直接关注', '2015-06-10 14:50:30');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;


-- 导出  表 wechat.customer_wifi 结构
CREATE TABLE IF NOT EXISTS `customer_wifi` (
  `open_id` varchar(100) NOT NULL,
  `captcha` varchar(10) NOT NULL,
  `expired_dt` datetime NOT NULL,
  PRIMARY KEY (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  wechat.customer_wifi 的数据：~0 rows (大约)
DELETE FROM `customer_wifi`;
/*!40000 ALTER TABLE `customer_wifi` DISABLE KEYS */;
INSERT INTO `customer_wifi` (`open_id`, `captcha`, `expired_dt`) VALUES
	('410000100', '125979', '2015-06-11 16:53:42');
/*!40000 ALTER TABLE `customer_wifi` ENABLE KEYS */;


-- 导出  表 wechat.shop_goods 结构
CREATE TABLE IF NOT EXISTS `shop_goods` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `image` varchar(255) NOT NULL,
  `detail` text NOT NULL,
  `price` varchar(50) NOT NULL,
  `old_price` varchar(50) NOT NULL,
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  wechat.shop_goods 的数据：~0 rows (大约)
DELETE FROM `shop_goods`;
/*!40000 ALTER TABLE `shop_goods` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_goods` ENABLE KEYS */;


-- 导出  表 wechat.shop_history 结构
CREATE TABLE IF NOT EXISTS `shop_history` (
  `history_id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(100) NOT NULL,
  `event_desc` varchar(255) NOT NULL,
  `create_id` varchar(50) NOT NULL,
  `create_dt` datetime NOT NULL,
  PRIMARY KEY (`history_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  wechat.shop_history 的数据：~0 rows (大约)
DELETE FROM `shop_history`;
/*!40000 ALTER TABLE `shop_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_history` ENABLE KEYS */;


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
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
