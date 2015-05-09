-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.21-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2015-05-09 08:13:23
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for shooting_stars_database
DROP DATABASE IF EXISTS `shooting_stars_database`;
CREATE DATABASE IF NOT EXISTS `shooting_stars_database` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `shooting_stars_database`;


-- Dumping structure for table shooting_stars_database.chat
DROP TABLE IF EXISTS `chat`;
CREATE TABLE IF NOT EXISTS `chat` (
  `chatId` int(10) NOT NULL AUTO_INCREMENT,
  `user1Id` int(10) DEFAULT NULL,
  `user2Id` int(10) DEFAULT NULL,
  PRIMARY KEY (`chatId`),
  KEY `FK_chat_user` (`user1Id`),
  KEY `FK_chat_user_2` (`user2Id`),
  CONSTRAINT `FK_chat_user` FOREIGN KEY (`user1Id`) REFERENCES `user` (`userId`),
  CONSTRAINT `FK_chat_user_2` FOREIGN KEY (`user2Id`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.chat: ~5 rows (approximately)
DELETE FROM `chat`;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` (`chatId`, `user1Id`, `user2Id`) VALUES
	(1, 14, 2),
	(2, 2, 11),
	(3, 2, 15),
	(13, 18, 2),
	(14, 2, 17),
	(17, 18, 14);
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;


-- Dumping structure for table shooting_stars_database.considered_wish
DROP TABLE IF EXISTS `considered_wish`;
CREATE TABLE IF NOT EXISTS `considered_wish` (
  `wishId` int(10) NOT NULL,
  `userId` int(10) NOT NULL,
  PRIMARY KEY (`wishId`,`userId`),
  KEY `FK_considered_wish_user` (`userId`),
  CONSTRAINT `FK_considered_wish_user` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`),
  CONSTRAINT `FK_considered_wish_wish` FOREIGN KEY (`wishId`) REFERENCES `wish` (`wishId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.considered_wish: ~2 rows (approximately)
DELETE FROM `considered_wish`;
/*!40000 ALTER TABLE `considered_wish` DISABLE KEYS */;
INSERT INTO `considered_wish` (`wishId`, `userId`) VALUES
	(44, 2),
	(44, 18);
/*!40000 ALTER TABLE `considered_wish` ENABLE KEYS */;


-- Dumping structure for table shooting_stars_database.fulfilled_wish
DROP TABLE IF EXISTS `fulfilled_wish`;
CREATE TABLE IF NOT EXISTS `fulfilled_wish` (
  `wishId` int(10) NOT NULL DEFAULT '0',
  `userId` int(10) DEFAULT NULL,
  `wishStatusId` int(10) DEFAULT '1',
  `date` date DEFAULT NULL,
  PRIMARY KEY (`wishId`),
  KEY `FK_fulfilled_wish_user` (`userId`),
  KEY `FK_fulfilled_wish_wish_status` (`wishStatusId`),
  CONSTRAINT `FK_fulfilled_wish_user` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`),
  CONSTRAINT `FK_fulfilled_wish_wish` FOREIGN KEY (`wishId`) REFERENCES `wish` (`wishId`) ON DELETE CASCADE,
  CONSTRAINT `FK_fulfilled_wish_wish_status` FOREIGN KEY (`wishStatusId`) REFERENCES `wish_status` (`wishStatusId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.fulfilled_wish: ~7 rows (approximately)
DELETE FROM `fulfilled_wish`;
/*!40000 ALTER TABLE `fulfilled_wish` DISABLE KEYS */;
INSERT INTO `fulfilled_wish` (`wishId`, `userId`, `wishStatusId`, `date`) VALUES
	(7, 14, 2, '2015-03-19'),
	(17, 14, 2, '2015-03-19'),
	(20, 2, 2, '2015-04-09'),
	(21, 2, 2, '2015-04-28'),
	(27, 14, 2, '2015-04-29'),
	(36, 2, 2, '2015-04-12'),
	(42, 14, 2, '2015-04-29');
/*!40000 ALTER TABLE `fulfilled_wish` ENABLE KEYS */;


-- Dumping structure for table shooting_stars_database.message
DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
  `messageId` int(10) NOT NULL AUTO_INCREMENT,
  `chatId` int(10) DEFAULT NULL,
  `message` longtext,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `sender` int(11) DEFAULT NULL,
  `isRead` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`messageId`),
  KEY `FK_message_chat` (`chatId`),
  KEY `FK_message_user` (`sender`),
  CONSTRAINT `FK_message_chat` FOREIGN KEY (`chatId`) REFERENCES `chat` (`chatId`),
  CONSTRAINT `FK_message_user` FOREIGN KEY (`sender`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=181 DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.message: ~136 rows (approximately)
DELETE FROM `message`;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` (`messageId`, `chatId`, `message`, `date`, `time`, `sender`, `isRead`) VALUES
	(1, 1, 'bbjj', '2015-04-12', '11:02:26', 2, 1),
	(2, 1, 'hjghdjghdhkg', '2015-04-12', '18:13:11', 2, 1),
	(3, 1, 'fjgfjgjg', '2015-04-12', '18:13:27', 2, 1),
	(4, 1, 'hgjkfdhgfdg', '2015-04-12', '18:14:15', 14, 1),
	(5, 1, 'hgghfgkfdg', '2015-04-12', '18:17:06', 2, 1),
	(6, 1, 'jghjghjgj', '2015-04-12', '18:17:19', 2, 1),
	(7, 1, 'gjhgjhgjyj', '2015-04-12', '18:19:22', 14, 1),
	(8, 1, 'ghgb', '2015-04-12', '18:20:54', 2, 1),
	(9, 1, 'cancel application', '2015-04-12', '21:07:08', 2, 1),
	(16, 2, 'application was made', '2015-04-12', '21:11:13', 2, 0),
	(17, 1, 'qq', '2015-04-12', '21:41:19', 2, 1),
	(18, 1, 'hgfhfh', '2015-04-12', '21:43:35', 2, 1),
	(19, 1, 'ghjhhjg', '2015-04-13', '09:58:37', 2, 1),
	(20, 3, 'application was made', '2015-04-13', '12:14:54', 2, 0),
	(21, 1, 'application was accepted', '2015-04-13', '17:58:52', 14, 1),
	(22, 1, 'gffhfh', '2015-04-13', '18:22:54', 2, 1),
	(23, 1, 'gvhghg', '2015-04-14', '07:27:05', 2, 1),
	(24, 1, 'dhhdfh', '2015-04-14', '07:27:08', 2, 1),
	(25, 1, 'ghgfhf', '2015-04-14', '07:27:11', 2, 1),
	(26, 1, 'fggf', '2015-04-14', '07:27:14', 2, 1),
	(66, 1, 'hjhjhj', '2015-04-23', '10:06:20', 14, 1),
	(67, 1, 'ghjhgj', '2015-04-23', '10:09:11', 14, 1),
	(68, 1, 'hgjhjhg', '2015-04-23', '10:10:02', 14, 1),
	(69, 1, 'hjgj', '2015-04-23', '10:10:29', 2, 1),
	(70, 1, 'gfdgfdg', '2015-04-23', '10:13:50', 2, 1),
	(71, 1, 'gfhfgh', '2015-04-23', '10:16:33', 14, 1),
	(72, 1, 'yrtyry', '2015-04-23', '10:38:46', 14, 1),
	(73, 1, 'fyfy', '2015-04-23', '10:40:35', 2, 1),
	(74, 1, 'jhjh', '2015-04-23', '10:43:54', 14, 1),
	(75, 1, 'ghgj', '2015-04-23', '10:44:52', 14, 1),
	(76, 1, 'hjhjk', '2015-04-23', '10:51:11', 2, 1),
	(77, 1, 'hmhm', '2015-04-23', '11:00:25', 14, 1),
	(79, 1, 'gjgfjf', '2015-04-23', '11:15:21', 14, 1),
	(80, 1, 'dfjdfd', '2015-04-23', '11:16:10', 14, 1),
	(81, 1, 'gfdg', '2015-04-23', '11:19:57', 14, 1),
	(82, 1, 'fggfg', '2015-04-23', '11:31:21', 14, 1),
	(83, 1, 'fhfh', '2015-04-23', '11:33:46', 2, 1),
	(84, 1, 'jffjj', '2015-04-23', '11:34:51', 14, 1),
	(85, 1, 'fhghf', '2015-04-23', '11:38:36', 14, 1),
	(86, 1, 'jhgjgh', '2015-04-23', '11:39:44', 14, 1),
	(87, 1, 'fhhf', '2015-04-23', '11:44:17', 14, 1),
	(88, 1, 'nghf', '2015-04-23', '11:45:15', 14, 1),
	(89, 1, 'mmgfg', '2015-04-23', '11:45:40', 14, 1),
	(90, 1, 'gffgfgfg', '2015-04-23', '17:20:07', 14, 1),
	(91, 1, 'fgfdgfdgh', '2015-04-23', '17:20:33', 14, 1),
	(92, 1, 'gfggf', '2015-04-24', '07:48:09', 14, 1),
	(93, 1, 'application was made', '2015-04-27', '13:21:58', 14, 1),
	(95, 1, 'application was made', '2015-04-28', '11:47:33', 2, 1),
	(96, 1, 'cancel making wish', '2015-04-28', '11:49:34', 2, 1),
	(97, 1, 'wish was made', '2015-04-28', '11:49:48', 14, 1),
	(98, 1, 'application was made', '2015-04-28', '11:58:21', 14, 1),
	(99, 1, 'application was made', '2015-04-28', '12:12:12', 2, 1),
	(101, 1, 'application was made', '2015-04-28', '12:28:11', 2, 1),
	(104, 1, 'cancel application', '2015-04-28', '12:36:23', 2, 1),
	(105, 3, 'cancel application', '2015-04-28', '12:36:35', 2, 0),
	(106, 2, 'cancel application', '2015-04-28', '12:38:45', 2, 0),
	(107, 1, 'application was made', '2015-04-28', '12:42:25', 2, 1),
	(108, 1, 'application was made', '2015-04-28', '12:42:28', 2, 1),
	(109, 1, 'cancel application', '2015-04-28', '12:42:41', 2, 1),
	(110, 1, 'application was made', '2015-04-28', '12:43:09', 2, 1),
	(111, 1, 'cancel application', '2015-04-28', '12:44:27', 2, 1),
	(112, 1, 'application was accepted', '2015-04-28', '12:44:33', 14, 1),
	(113, 1, 'application was made', '2015-04-29', '11:53:04', 2, 1),
	(114, 1, 'cancel application', '2015-04-29', '12:06:23', 2, 1),
	(115, 1, 'application was made', '2015-04-29', '12:32:08', 2, 1),
	(116, 1, 'cancel application', '2015-04-29', '12:32:23', 2, 1),
	(117, 1, 'application was made', '2015-04-29', '12:52:36', 2, 1),
	(118, 1, 'cancel application', '2015-04-29', '12:52:44', 2, 1),
	(119, 1, 'application was made', '2015-04-29', '13:06:29', 2, 1),
	(120, 1, 'cancel application', '2015-04-29', '13:11:54', 14, 1),
	(121, 1, 'application was made', '2015-04-29', '13:11:59', 2, 1),
	(122, 1, 'application was made', '2015-04-29', '14:59:46', 14, 1),
	(123, 1, 'application was accepted', '2015-04-29', '14:59:50', 2, 1),
	(124, 1, 'wish was made', '2015-04-29', '15:00:43', 2, 1),
	(125, 1, 'application was made', '2015-04-29', '15:01:15', 14, 1),
	(126, 1, 'application was accepted', '2015-04-29', '15:01:28', 2, 1),
	(127, 1, 'wish was made', '2015-04-29', '15:04:08', 2, 1),
	(128, 1, 'application was made', '2015-04-29', '15:04:54', 14, 1),
	(129, 1, 'application was made', '2015-04-29', '15:05:15', 2, 1),
	(130, 1, 'application was made', '2015-04-29', '15:05:27', 14, 1),
	(131, 1, 'cancel making wish', '2015-04-29', '15:06:46', 14, 1),
	(132, 1, 'fhfhf', '2015-05-01', '08:33:26', 14, 1),
	(133, 1, 'hghg', '2015-05-01', '08:41:17', 14, 1),
	(134, 1, 'bnvnv', '2015-05-01', '08:41:31', 2, 1),
	(135, 1, 'hghfg', '2015-05-01', '08:42:21', 14, 1),
	(136, 1, 'fdbff', '2015-05-01', '08:44:15', 2, 1),
	(137, 1, 'application was made', '2015-05-01', '08:55:48', 14, 1),
	(138, 1, 'application was accepted', '2015-05-01', '08:55:59', 2, 0),
	(139, 1, 'cancel making wish', '2015-05-01', '09:00:34', 2, 0),
	(140, 1, 'cancel application', '2015-05-01', '09:00:46', 14, 1),
	(141, 1, 'application was made', '2015-05-01', '09:00:56', 14, 1),
	(142, 1, 'cancel application', '2015-05-01', '09:02:26', 14, 1),
	(143, 14, 'application was made', '2015-05-06', '08:11:50', 2, 0),
	(144, 14, 'cancel application', '2015-05-06', '08:56:04', 2, 0),
	(180, 17, 'application was made', '2015-05-09', '07:56:31', 18, 0);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;


-- Dumping structure for table shooting_stars_database.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `userId` int(10) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `userStatusId` int(10) DEFAULT '1',
  PRIMARY KEY (`userId`),
  KEY `FK_user_user_status` (`userStatusId`),
  CONSTRAINT `FK_user_user_status` FOREIGN KEY (`userStatusId`) REFERENCES `user_status` (`userStatusId`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.user: ~15 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`userId`, `login`, `password`, `userStatusId`) VALUES
	(2, 'v', 'c4ca4238a0b923820dcc509a6f75849b', 1),
	(8, 'bbbv', 'c4ca4238a0b923820dcc509a6f75849b', 0),
	(9, 'ghg', 'c4ca4238a0b923820dcc509a6f75849b', 0),
	(10, 'gdfgd', 'c4ca4238a0b923820dcc509a6f75849b', 0),
	(11, 'hfhfh', 'c4ca4238a0b923820dcc509a6f75849b', 0),
	(12, 'fg', 'c4ca4238a0b923820dcc509a6f75849b', 0),
	(13, 'fdgfg', 'c4ca4238a0b923820dcc509a6f75849b', 0),
	(14, 'h', 'c4ca4238a0b923820dcc509a6f75849b', 0),
	(15, 'htbh', 'c4ca4238a0b923820dcc509a6f75849b', 0),
	(16, '1', 'c4ca4238a0b923820dcc509a6f75849b', 0),
	(17, 's', '202cb962ac59075b964b07152d234b70', 0),
	(18, '123', 'e10adc3949ba59abbe56e057f20f883e', 1),
	(19, '1234', 'e10adc3949ba59abbe56e057f20f883e', 0),
	(20, 'fhfdhhfdhfd', 'e10adc3949ba59abbe56e057f20f883e', 0),
	(21, 'sjckscscj', 'e10adc3949ba59abbe56e057f20f883e', 1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


-- Dumping structure for table shooting_stars_database.user_info
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE IF NOT EXISTS `user_info` (
  `userId` int(10) NOT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `surname` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `dateOfBirth` date DEFAULT NULL,
  `abilities` varchar(50) DEFAULT NULL,
  `photoName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  FULLTEXT KEY `user_name` (`user_name`),
  FULLTEXT KEY `surname` (`surname`),
  CONSTRAINT `FK_user_info_user` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.user_info: ~15 rows (approximately)
DELETE FROM `user_info`;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` (`userId`, `user_name`, `surname`, `email`, `country`, `city`, `dateOfBirth`, `abilities`, `photoName`) VALUES
	(2, 'veronica', 'haritonova', 'veronica_haritonova@mail.ru', 'belarus', 'Minsk', '2015-04-27', 'jhjhj', '2.jpg'),
	(8, 'gfg', 'gfg', '', '', '', NULL, '', NULL),
	(9, 'wj', 'wqh', '', '', '', NULL, 'gff', NULL),
	(10, 'tr', 'gfgf', '', '', '', NULL, '', NULL),
	(11, 'ffs', 'fsd', '', '', '', NULL, '', NULL),
	(12, 'fgf', 'fg', '', '', '', NULL, '', NULL),
	(13, 'gfg', '', '', '', '', NULL, 'dgfg', NULL),
	(14, 'Patrick', 'Weirauch', '', '', 'Munchen', NULL, '', NULL),
	(15, 'vdvdfv', '', '', '', '', NULL, '', NULL),
	(16, '1', '', '', '', '', NULL, '', NULL),
	(17, 'Sasha', 'M', '', 'Australia', 'Sydney', '2013-05-07', 'sjsksl, skksl, sk', '17.jpg'),
	(18, 'fff', '', '', '', '', NULL, '', NULL),
	(19, 'ver', 'har', '', '', '', NULL, '', NULL),
	(20, 'hgfhgh', '', 'veronica48@mail.ru', '', '', NULL, '', NULL),
	(21, 'hgh', '', 'veronica_haritonova@mail.ru', '', '', NULL, '', NULL);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;


-- Dumping structure for table shooting_stars_database.user_status
DROP TABLE IF EXISTS `user_status`;
CREATE TABLE IF NOT EXISTS `user_status` (
  `userStatusId` int(10) NOT NULL DEFAULT '0',
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`userStatusId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.user_status: ~2 rows (approximately)
DELETE FROM `user_status`;
/*!40000 ALTER TABLE `user_status` DISABLE KEYS */;
INSERT INTO `user_status` (`userStatusId`, `status`) VALUES
	(0, 'offline'),
	(1, 'online');
/*!40000 ALTER TABLE `user_status` ENABLE KEYS */;


-- Dumping structure for table shooting_stars_database.wish
DROP TABLE IF EXISTS `wish`;
CREATE TABLE IF NOT EXISTS `wish` (
  `wishId` int(10) NOT NULL AUTO_INCREMENT,
  `userId` int(10) DEFAULT NULL,
  `wish` tinytext,
  `wishStatusId` int(10) DEFAULT '0',
  `creationTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`wishId`),
  KEY `FK_wish_user` (`userId`),
  KEY `FK_wish_wish_status` (`wishStatusId`),
  CONSTRAINT `FK_wish_user` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`),
  CONSTRAINT `FK_wish_wish_status` FOREIGN KEY (`wishStatusId`) REFERENCES `wish_status` (`wishStatusId`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.wish: ~22 rows (approximately)
DELETE FROM `wish`;
/*!40000 ALTER TABLE `wish` DISABLE KEYS */;
INSERT INTO `wish` (`wishId`, `userId`, `wish`, `wishStatusId`, `creationTime`) VALUES
	(1, 11, 'gfg', 0, '2015-05-07 16:10:09'),
	(2, 11, 'fgdfg', 0, '2015-05-07 16:10:09'),
	(3, 12, 'bggfb', 0, '2015-05-07 16:10:09'),
	(7, 2, 'dgdgd', 2, '2015-05-07 16:10:09'),
	(14, 13, 'dgdgd', 0, '2015-05-07 16:10:09'),
	(15, 13, 'dgdgdfg', 0, '2015-05-07 16:10:09'),
	(17, 2, 'fsdfsdf', 2, '2015-05-07 16:10:09'),
	(20, 14, 'gsdgdsg', 2, '2015-05-07 16:10:09'),
	(21, 14, 'gdfgfdgf', 2, '2015-05-07 16:10:09'),
	(27, 2, 'vfvrfv', 2, '2015-05-07 16:10:09'),
	(29, 15, 'yuryfh', 0, '2015-05-07 16:10:09'),
	(30, 15, 'hghgg', 0, '2015-05-07 16:10:09'),
	(31, 15, 'jk', 0, '2015-05-07 16:10:09'),
	(32, 16, 'hi', 0, '2015-05-07 16:10:09'),
	(36, 14, 'sss', 2, '2015-05-07 16:10:09'),
	(38, 17, 'Parachute jump', 0, '2015-05-07 16:10:09'),
	(39, 17, 'Drums', 0, '2015-05-07 16:10:09'),
	(42, 2, 'ggh', 2, '2015-05-07 16:10:09'),
	(44, 14, 'ghgh', 0, '2015-05-07 16:10:09'),
	(47, 14, 'ytyty', 0, '2015-05-07 16:10:09'),
	(49, 20, 'htthfhd', 0, '2015-05-07 16:10:09'),
	(50, 2, 'jhjjhjj', 0, '2015-05-07 16:10:09'),
	(51, 2, 'fdgfdgfdgd', 0, '2015-05-07 16:10:59'),
	(52, 2, 'hghgjfghf', 0, '2015-05-07 20:30:22'),
	(53, 14, 'dfhhh', 0, '2015-05-08 22:37:03');
/*!40000 ALTER TABLE `wish` ENABLE KEYS */;


-- Dumping structure for table shooting_stars_database.wish_status
DROP TABLE IF EXISTS `wish_status`;
CREATE TABLE IF NOT EXISTS `wish_status` (
  `wishStatusId` int(10) NOT NULL DEFAULT '0',
  `wishStatus` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`wishStatusId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.wish_status: ~2 rows (approximately)
DELETE FROM `wish_status`;
/*!40000 ALTER TABLE `wish_status` DISABLE KEYS */;
INSERT INTO `wish_status` (`wishStatusId`, `wishStatus`) VALUES
	(0, 'New'),
	(1, 'In progress'),
	(2, 'Fulfilled');
/*!40000 ALTER TABLE `wish_status` ENABLE KEYS */;
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
