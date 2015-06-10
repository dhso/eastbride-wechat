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

-- 正在导出表  wechat.config 的数据：~0 rows (大约)
DELETE FROM `config`;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` (`cfg_key`, `cfg_value`) VALUES
	('wx.welcome', '关注成功！');
/*!40000 ALTER TABLE `config` ENABLE KEYS */;


-- 导出  表 wechat.customer 结构
CREATE TABLE IF NOT EXISTS `customer` (
  `open_id` varchar(50) NOT NULL,
  `subscribe_flag` char(1) NOT NULL,
  `create_id` varchar(50) NOT NULL,
  `create_dt` datetime NOT NULL,
  PRIMARY KEY (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  wechat.customer 的数据：~1 rows (大约)
DELETE FROM `customer`;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`open_id`, `subscribe_flag`, `create_id`, `create_dt`) VALUES
	('410000100', '1', '直接关注', '2015-06-10 14:50:30');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;


-- 导出  表 wechat.customer_wifi 结构
CREATE TABLE IF NOT EXISTS `customer_wifi` (
  `open_id` varchar(50) NOT NULL,
  `captcha` varchar(6) NOT NULL,
  `expired_dt` datetime NOT NULL,
  PRIMARY KEY (`open_id`),
  CONSTRAINT `FK_CST.OPENID` FOREIGN KEY (`open_id`) REFERENCES `customer` (`open_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  wechat.customer_wifi 的数据：~1 rows (大约)
DELETE FROM `customer_wifi`;
/*!40000 ALTER TABLE `customer_wifi` DISABLE KEYS */;
INSERT INTO `customer_wifi` (`open_id`, `captcha`, `expired_dt`) VALUES
	('410000100', '190708', '2015-06-10 17:57:02');
/*!40000 ALTER TABLE `customer_wifi` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
