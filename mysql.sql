-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 106.52.87.235    Database: mydb
-- ------------------------------------------------------
-- Server version	5.7.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'10001','123456');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `audit`
--

DROP TABLE IF EXISTS `audit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `audit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sex` tinyint(1) NOT NULL,
  `real_name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `province` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `area` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `message` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `background` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `hospital` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `department` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `deputy_deparment` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `professor` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `skills` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `morder_price` float DEFAULT NULL,
  `porder_price` float DEFAULT NULL,
  `commit_time` datetime DEFAULT NULL,
  `email` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=501 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audit`
--

LOCK TABLES `audit` WRITE;
/*!40000 ALTER TABLE `audit` DISABLE KEYS */;
INSERT INTO `audit` VALUES (5,1,'123','吉林省','松原市','前郭尔罗斯蒙古族自治县','321','123','123','321',NULL,'123','321',123,321,'2020-10-09 14:31:37','321321@qq.com'),(7,1,'陈踩踩','天津市','天津市','河东区','好好学习','天津大学博士毕业','天津市人民医院','骨科',NULL,'主治医师','骨科',20,40,'2020-10-09 08:08:49','asc@qq.com'),(10,1,'123','北京市','北京市','东城区','321','123','123','321',NULL,'123','321',123,321,'2020-10-09 08:18:16','123s1@qq.com'),(13,1,'刘23','山西省','太原市','尖草坪区','救人','北大医学院','第一医院','脑科',NULL,'主任','开颅手术',50,60,'2020-10-10 14:15:37','1234223@qq.com'),(14,1,'刘23','内蒙古自治区','乌海市','海南区','救人','北大','第一医院','脑科',NULL,'张主任','开颅',50,60,'2020-10-10 14:16:52','32112414@qq.com'),(17,1,'123','天津市','天津市','河西区','321','213','123','321',NULL,'123','321',12,32,'2020-10-10 15:52:36','123213@qq.com'),(18,1,'刘惠城','天津市','天津市','和平区','救人','北大医学院毕业','第一医院','脑科',NULL,'主任','开颅手术',30,50,'2020-10-10 15:54:29','10536195082@qq.com'),(500,0,'123','广东省','东莞市','大岭山镇','救人','医学世家','第一医院','脑科','内科','主任','开颅手术',50,100,'2020-10-10 16:04:09','123518@qq.com');
/*!40000 ALTER TABLE `audit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commont`
--

DROP TABLE IF EXISTS `commont`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commont` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderid` int(11) NOT NULL,
  `content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `star` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `doctorid` int(11) NOT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commont`
--

LOCK TABLES `commont` WRITE;
/*!40000 ALTER TABLE `commont` DISABLE KEYS */;
INSERT INTO `commont` VALUES (1,1,'doctoer is nice',5,1,3,'2020-09-01 00:00:00'),(2,1,'doctoer is nice',4,1,3,'2020-09-02 10:01:52'),(3,1,'doctoer is nice',3,1,3,'2020-09-04 10:01:52'),(26,1,'doctoer is nice',5,1,3,'2020-09-05 10:01:52'),(27,1,'doctoer is nice',5,1,3,'2020-09-06 10:01:52'),(28,1,'doctoer is nice',5,1,3,'2020-09-07 10:01:52'),(29,1,'doctoer is nice',5,1,3,'2020-09-08 10:01:52'),(30,1,'doctoer is nice',5,1,3,'2020-09-09 10:01:52'),(31,1,'doctoer is nice',5,1,3,'2020-09-10 10:01:52'),(32,1,'doctoer is nice',5,1,3,'2020-09-11 10:01:52'),(33,1,'doctoer is nice',5,1,3,'2020-09-12 10:01:52'),(34,1,'doctoer is nice',5,1,3,'2020-09-13 10:01:52'),(35,1,'doctoer is nice',5,1,3,'2020-09-14 10:01:52'),(36,1,'doctoer is nice',5,1,3,'2020-09-15 10:01:52'),(37,1,'doctoer is nice',5,1,3,'2020-09-16 10:01:52'),(38,1,'doctoer is nice',5,1,3,'2020-09-17 10:01:52'),(39,1,'doctoer is nice',5,1,3,'2020-09-18 10:01:52'),(40,1,'doctoer is nice',5,1,3,'2020-09-19 10:01:52'),(41,12,'医生人很好，很尽责',5,1,2,'2020-10-04 14:35:29'),(42,14,'test',4,1,3,'2020-10-05 22:31:19'),(43,3,'医生很尽责',5,2,4,'2020-10-06 15:42:55'),(44,4,'医生的解答很详细，对我很有帮助',5,2,2,'2020-10-06 20:18:27'),(45,9,'123test',3,2,3,'2020-10-08 21:00:51'),(46,10,'123456',5,2,3,'2020-10-08 21:08:31'),(47,16,'123123',5,1,3,'2020-10-09 22:37:59'),(48,15,'123123',4,1,3,'2020-10-09 22:38:28'),(49,11,'123',5,1,3,'2020-10-09 22:39:02'),(50,17,'123123',5,2,3,'2020-10-09 22:39:19'),(51,16,'123123',5,2,3,'2020-10-09 22:39:55'),(52,15,'12313',5,2,3,'2020-10-09 22:40:35'),(53,14,'113',5,2,3,'2020-10-09 22:41:47'),(54,18,'good',5,1,3,'2020-10-10 15:24:18'),(55,19,'good',4,2,3,'2020-10-10 15:27:57'),(56,10,'good',5,1,3,'2020-10-10 15:29:48'),(57,10,'good',5,1,3,'2020-10-10 15:29:55'),(58,10,'good',5,1,3,'2020-10-10 15:30:02'),(59,10,'good',5,1,3,'2020-10-10 15:30:16'),(60,20,'12313',5,2,3,'2020-10-10 15:53:29'),(61,4,'1233',5,1,2,'2020-10-10 15:53:43'),(62,18,'',5,2,3,'2020-10-10 18:49:36'),(63,22,'这医生不可靠',3,1,18,'2020-10-10 19:43:03'),(64,35,'这个医生很可靠',5,2,13,'2020-10-10 19:45:48'),(65,23,'医生你真好',5,1,18,'2020-10-10 21:31:07'),(66,25,'医生人很好',5,1,18,'2020-10-10 22:07:53'),(67,36,'医生人很好',4,2,18,'2020-10-10 22:09:03'),(68,27,'医生人真好',5,1,16,'2020-10-10 22:31:06'),(69,26,'医生人不错',4,1,16,'2020-10-10 22:34:20'),(75,31,'good',4,1,3,'2020-10-11 01:01:09'),(76,39,'good',5,2,3,'2020-10-11 01:02:15');
/*!40000 ALTER TABLE `commont` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sex` tinyint(1) NOT NULL,
  `real_name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `province` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `area` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `message` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `background` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `hospital` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `department` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `deputy_deparment` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `professor` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `skills` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `morder_price` float DEFAULT NULL,
  `porder_price` float DEFAULT NULL,
  `services` int(11) DEFAULT '0',
  `allcommonts` int(11) DEFAULT '0',
  `goodcommonts` int(11) DEFAULT '0',
  `balance` float DEFAULT '0',
  `state` int(11) DEFAULT '0',
  `email` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=502 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES (2,0,'杨无忧','湖南','','','妙手回春','中山大学医学院','解放军医院','内科','','主治医生','开脑 截肢',1,1,28,23,16,2,0,'23233@qq.com','23233'),(3,1,'林斯敏','安徽','','','妙手回春','中山大学医学院','民生医院','骨科','','主治医生','开脑 截肢',1,1,52,43,34,2,1,'17129@qq.com','17129'),(4,0,'黄无极','河南','','','妙手回春','中山大学医学院','市中医院','妇产科','','主治医生','开脑 截肢',1,1,22,20,13,2,1,'83638@qq.com','83638'),(5,1,'梁思乐','海南','','','妙手回春','中山大学医学院','民生医院','妇产科','','主治医生','开脑 截肢',1,1,21,19,12,2,1,'90527@qq.com','90527'),(6,0,'汪继伟','江西','','','妙手回春','中山大学医学院','民生医院','外科','','主治医生','开脑 截肢',1,1,21,19,12,2,1,'91031@qq.com','91031'),(7,1,'杨毛毛','河南','','','妙手回春','中山大学医学院','市中医院','儿科','','主治医生','开脑 截肢',1,1,21,19,12,2,1,'91956@qq.com','91956'),(8,0,'杜丝丝','北京','','','妙手回春','中山大学医学院','解放军医院','骨科','','主治医生','开脑 截肢',1,1,21,19,12,2,0,'39986@qq.com','39986'),(9,1,'汪富于','河北','','','妙手回春','中山大学医学院','市中医院','外科','','主治医生','开脑 截肢',1,1,21,19,12,2,1,'88933@qq.com','88933'),(10,0,'郑丝丝','安徽','','','妙手回春','中山大学医学院','平安医院','男科','','主治医生','开脑 截肢',1,1,21,19,12,2,1,'56594@qq.com','56594'),(11,1,'梁番禺','安徽','','','妙手回春','中山大学医学院','人民医院','五官科','','主治医生','开脑 截肢',1,1,21,19,12,2,1,'90332@qq.com','90332'),(12,1,'刘启明','辽宁省','丹东市','元宝区','治疗一人死9人','北大青鸟毕业','第二医院','脑科',NULL,'兼职','开颅',32,45,0,0,0,0,0,'a123@qq.com','[C@76099e07'),(13,1,'陈思宝','辽宁','','','妙手回春','中山大学医学院','人民医院','心理科','','主治医生','开脑 截肢',1,1,22,20,13,2,1,'90214@qq.com','90214'),(14,1,'杜斯敏','广东','','','妙手回春','中山大学医学院','民生医院','妇产科','','主治医生','开脑 截肢',1,1,21,19,12,2,1,'17896@qq.com','17896'),(15,1,'杜继伟','海南','','','妙手回春','中山大学医学院','市中医院','心理科','','主治医生','开脑 截肢',1,1,21,19,12,2,1,'76932@qq.com','76932'),(16,1,'刘慧慧','河北省','邢台市','临城县','救人','北大医学院毕业','第一医院','心血管科',NULL,'主任','接骨',40,80,2,2,2,0,1,'123@qq.com','[C@298a3e54'),(17,1,'肖毛毛','福建','','','妙手回春','中山大学医学院','民生医院','皮肤科','','主治医生','开脑 截肢',1,1,21,19,12,2,1,'79754@qq.com','79754'),(18,0,'黄毛毛','上海','','','妙手回春','中山大学医学院','人民医院','骨科','','主治医生','开脑 截肢',1,1,25,23,15,2,1,'87968@qq.com','87968'),(19,1,'刘国栋','安徽','','','妙手回春','中山大学医学院','人民医院','皮肤科','','主治医生','开脑 截肢',1,1,21,19,12,2,1,'52855@qq.com','52855'),(20,1,'刘钿储','四川','','','妙手回春','中山大学医学院','人民医院','男科','','主治医生','开脑 截肢',1,1,21,19,12,2,1,'41763@qq.com','41763'),(21,0,'郑丝丝','浙江','','','妙手回春','中山大学医学院','民生医院','脑科','','主治医生','开脑 截肢',1,1,21,19,12,2,1,'43746@qq.com','43746'),(22,0,'范乐天','山东','','','妙手回春','中山大学医学院','平安医院','内科','','主治医生','开脑 截肢',1,1,21,19,12,2,1,'19736@qq.com','19736'),(23,1,'杜亮丽','江西','','','妙手回春','中山大学医学院','市中医院','内科','','主治医生','开脑 截肢',1,1,21,19,12,2,1,'64764@qq.com','64764'),(63,1,'林斯敏','北京市','北京市',NULL,'妙手回春','中山大学医学院','民生医院','骨科','','主治医生','开脑 截肢',13,13,13,13,13,2,1,'17129123@qq.com','17129'),(65,1,'林斯敏','安徽','','','妙手回春','中山大学医学院','民生医院','骨科','','主治医生','开脑 截肢',11,11,11,11,11,2,1,'17119113@qq.com','17119'),(67,1,'林斯敏','安徽','','','妙手回春','中山大学医学院','民生医院','骨科','','主治医生','开脑 截肢',12,12,12,12,12,2,1,'1712229123@qq.com','17129'),(68,1,'林斯敏','安徽','','','妙手回春','中山大学医学院','民生医院','骨科','','主治医生','开脑 截肢',10,10,10,10,10,2,1,'171029103@qq.com','17109'),(69,1,'林斯敏','安徽','','','妙手回春','中山大学医学院','民生医院','骨科','','主治医生','开脑 截肢',9,9,9,9,9,2,1,'17929393@qq.com','1799'),(70,1,'林斯敏','安徽','','','妙手回春','中山大学医学院','民生医院','骨科','','主治医生','开脑 截肢',8,8,8,8,8,2,1,'17831228383@qq.com','1788'),(71,1,'林斯敏','安徽','','','妙手回春','中山大学医学院','民生医院','骨科','','主治医生','开脑 截肢',7,7,7,7,7,2,1,'177317373@qq.com','1777'),(72,1,'林斯敏','安徽','','','妙手回春','中山大学医学院','民生医院','骨科','','主治医生','开脑 截肢',6,6,6,6,6,2,1,'166316421363@qq.com','1666'),(73,1,'林斯敏','安徽','','','妙手回春','中山大学医学院','民生医院','骨科','','主治医生','开脑 截肢',5,5,5,5,5,2,1,'155315421353@qq.com','1555'),(74,1,'林斯敏','安徽','','','妙手回春','中山大学医学院','民生医院','骨科','','主治医生','开脑 截肢',4,4,4,4,4,2,1,'144314421343@qq.com','1444'),(75,1,'林斯敏','安徽','','','妙手回春','中山大学医学院','民生医院','骨科','','主治医生','开脑 截肢',3,3,3,3,3,2,1,'133313321333@qq.com','1333'),(76,1,'林斯敏','安徽','','','妙手回春','中山大学医学院','民生医院','骨科','','主治医生','开脑 截肢',2,2,2,2,2,2,1,'122212221222@qq.com','1222'),(77,1,'林斯敏','安徽','','','妙手回春','中山大学医学院','民生医院','骨科','','主治医生','开脑 截肢',1,1,1,1,1,1,1,'111111111111@qq.com','1111'),(80,1,'林嘻嘻','广东省','汕头市','潮阳区','没啥好说的啦','毕业于北京大学','汕头中心医院','儿科',NULL,'主治医师','儿科',100,200,0,0,0,NULL,0,'123@xixi.com','[C@79998d0b'),(84,1,'黄丝锥','四川','','','妙手回春','中山大学医学院','民生医院','外科','','主治医生','开脑 截肢',1,1,21,19,12,2,1,'62183@qq.com','62183'),(99,1,'黄丝丝','山西','','','妙手回春','中山大学医学院','民生医院','皮肤科','','主治医生','开脑 截肢',1,1,21,19,12,2,1,'90629@qq.com','90629'),(501,1,'刘惠城','天津市','天津市','河东区','救人','北大医学院毕业','第一医院','脑科',NULL,'主任','开颅手术',50,100,1,0,0,NULL,1,'1053619508@qq.com','14725836');
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `healthrecord`
--

DROP TABLE IF EXISTS `healthrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `healthrecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `username` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `birthdate` datetime DEFAULT NULL,
  `height` float DEFAULT NULL,
  `issmoke` tinyint(1) DEFAULT NULL,
  `isdrink` tinyint(1) DEFAULT NULL,
  `liver_state` tinyint(1) DEFAULT NULL,
  `ismarry` tinyint(1) DEFAULT NULL,
  `chronic_disease` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `other` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `allergy` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `history_disease` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `weight` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `healthrecord`
--

LOCK TABLES `healthrecord` WRITE;
/*!40000 ALTER TABLE `healthrecord` DISABLE KEYS */;
INSERT INTO `healthrecord` VALUES (22,2,'林垦',NULL,'2020-09-28 12:28:06',172,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,40),(23,4,'林垦',1,'1998-09-22 00:00:00',171,0,0,0,0,'无','无','对药粉过敏','无',55),(24,7,'林西',0,'1999-10-10 00:00:00',162,0,0,0,0,'','','','',52),(25,5,'魏欣欣',0,'2020-10-10 19:47:36',NULL,NULL,NULL,NULL,NULL,'','','','',NULL),(26,6,'刘大大',1,'2020-10-10 19:49:34',NULL,NULL,NULL,NULL,NULL,'','','','',NULL),(27,8,'可乐',0,'2020-10-10 20:04:39',NULL,NULL,NULL,NULL,NULL,'','','','',NULL),(28,3,'梓豪',1,'1999-05-19 00:00:00',178,0,0,0,0,'','','','',60),(29,9,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(30,10,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `healthrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `healthyabout`
--

DROP TABLE IF EXISTS `healthyabout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `healthyabout` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `adminid` int(11) NOT NULL,
  `time` datetime DEFAULT NULL,
  `title` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000116020575487100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `healthyabout`
--

LOCK TABLES `healthyabout` WRITE;
/*!40000 ALTER TABLE `healthyabout` DISABLE KEYS */;
INSERT INTO `healthyabout` VALUES (1000116020575487080,'<img style=\"display:block;max-width:100%;height:auto;\" src=\"https://www.qnm.green:8080/article/1000116023535593490/759349.jpg\" alt=\"article/1000116023535593490/759349.jpg\">','儿科咨询',10001,'2020-10-07 15:59:21','就试试'),(1000116020575487081,'<p><img style=\"display:block;max-width:100%;height:auto;\" src=\"https://www.qnm.green:8080/article/1000116023535333300/733330.jpg\" alt=\"article/1000116023535333300/733330.jpg\"><br></p>','针灸',10001,'2020-10-08 13:23:26','中医针灸'),(1000116020575487083,'<p><img style=\"display:block;max-width:100%;height:auto;\" src=\"https://www.qnm.green:8080/article/1000116023535197760/719776.jpg\" alt=\"article/1000116023535197760/719776.jpg\"><br></p>','医疗健康',10001,'2020-10-09 04:59:48','测试测试文章'),(1000116020575487084,'<p><img style=\"display:block;max-width:100%;height:auto;\" src=\"https://www.qnm.green:8080/article/1000116023535090110/709011.jpg\" alt=\"article/1000116023535090110/709011.jpg\"><br></p>','健康',10001,'2020-10-09 05:05:20','测试一下下'),(1000116020575487085,'<p><img style=\"display:block;max-width:100%;height:auto;\" src=\"https://www.qnm.green:8080/article/1000116023534998550/699855.jpg\" alt=\"article/1000116023534998550/699855.jpg\"><br></p>','健康',10001,'2020-10-09 06:38:04','如何预防高血压'),(1000116020575487086,'<img style=\"display:block;max-width:100%;height:auto;\" src=\"https://www.qnm.green:8080/article/1000116023534841360/684136.jpg\" alt=\"article/1000116023534841360/684136.jpg\">','健康',10001,'2020-10-09 07:15:17','测试图片'),(1000116020575487088,'<img style=\"display:block;max-width:100%;height:auto;\" src=\"https://www.qnm.green:8080/article/1000116023534677680/667768.jpg\" alt=\"article/1000116023534677680/667768.jpg\">','健康',10001,'2020-10-09 08:12:43','测试'),(1000116020575487089,'<p><img style=\"display:block;max-width:100%;height:auto;\" src=\"https://www.qnm.green:8080/article/1000116023534406410/640641.jpg\" alt=\"article/1000116023534406410/640641.jpg\"><br></p>','健康资讯',10001,'2020-10-09 08:47:09','健康资讯'),(1000116020575487091,'<p><img style=\"display:block;max-width:100%;height:auto;\" src=\"http://123.207.95.77:8080/article/216023059160080/7116008.jpg\" alt=\"article/216023059160080/7116008.jpg\"></p><p><img style=\"display:block;max-width:100%;height:auto;\" src=\"http://123.207.95.77:8080/article/216023059233820/7123382.jpg\" alt=\"article/216023059233820/7123382.jpg\"><br></p><p><img style=\"display:block;max-width:100%;height:auto;\" src=\"http://123.207.95.77:8080/article/216023059361400/7136140.jpg\" alt=\"article/216023059361400/7136140.jpg\"><br></p><p><br></p>','健康',2,'2020-10-10 04:59:03','新1'),(1000116020575487093,'<p></p><p><img style=\"display:block;max-width:100%;height:auto;\" src=\"https://www.qnm.green:8080/article/1000116023534275270/627527.jpg\" alt=\"article/1000116023534275270/627527.jpg\"><br></p>','健康',10001,'2020-10-10 16:03:30','高血压的危害'),(1000116020575487094,'<p></p><p><img style=\"display:block;max-width:100%;height:auto;\" src=\"https://www.qnm.green:8080/article/1000116023534150410/615041.jpg\" alt=\"article/1000116023534150410/615041.jpg\"><br></p>','科普',10001,'2020-10-10 16:04:39','如何保护牙齿'),(1000116020575487095,'<p></p><p><img style=\"display:block;max-width:100%;height:auto;\" src=\"https://www.qnm.green:8080/article/1000116023534014580/601458.jpg\" alt=\"article/1000116023534014580/601458.jpg\"><br></p>','资讯',10001,'2020-10-10 16:06:19','熬夜的危害？'),(1000116020575487096,'<p></p><p><img style=\"display:block;max-width:100%;height:auto;\" src=\"https://www.qnm.green:8080/article/1000116023533865960/586596.jpg\" alt=\"article/1000116023533865960/586596.jpg\"><br></p>','科普',10001,'2020-10-10 16:07:03','程序员如何防止脱发'),(1000116020575487097,'<p></p><p><img style=\"display:block;max-width:100%;height:auto;\" src=\"https://www.qnm.green:8080/article/1000116023533652600/565260.jpg\" alt=\"article/1000116023533652600/565260.jpg\"><br></p>','百科',10001,'2020-10-10 16:07:57','颈椎护养方法'),(1000116020575487098,'<p></p><p><img style=\"display:block;max-width:100%;height:auto;\" src=\"https://www.qnm.green:8080/article/1000116023533420820/542082.jpg\" alt=\"article/1000116023533420820/542082.jpg\"><br></p>','健康',10001,'2020-10-10 16:28:04','高血压的危害'),(1000116020575487099,'<p><img style=\"display:block;max-width:100%;height:auto;\" src=\"https://www.qnm.green:8080/article/1000116023531760090/376011.jpg\" alt=\"article/1000116023531760090/376011.jpg\"></p><p><img style=\"display:block;max-width:100%;height:auto;\" src=\"https://www.qnm.green:8080/article/1000116023531818160/381816.jpg\" alt=\"article/1000116023531818160/381816.jpg\"><br></p>','健康',10001,'2020-10-11 02:06:42','脊椎护理');
/*!40000 ALTER TABLE `healthyabout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `morder`
--

DROP TABLE IF EXISTS `morder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `morder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `begin_time` datetime NOT NULL,
  `active_time` datetime NOT NULL,
  `content` varchar(600) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `has_picture` tinyint(1) DEFAULT NULL,
  `price` float NOT NULL,
  `user_confirm` tinyint(1) NOT NULL,
  `user_ctime` datetime DEFAULT NULL,
  `userid` int(11) NOT NULL,
  `doctorid` int(11) NOT NULL,
  `iscommont` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `morder`
--

LOCK TABLES `morder` WRITE;
/*!40000 ALTER TABLE `morder` DISABLE KEYS */;
INSERT INTO `morder` VALUES (13,'2020-10-03 20:46:14','2020-10-04 20:46:14','医生我又病了',NULL,2,1,'2020-10-03 20:47:52',2,2,0),(17,'2020-10-09 23:32:17','2020-10-10 23:32:17','<p><br></p>',NULL,1,1,'2020-10-10 15:32:17',4,3,0),(20,'2020-10-10 19:03:38','2020-10-11 19:03:38','<h2>富文本测试</h2><p><br></p><p><em>测试   </em><u>测试</u>   <strong>测试</strong></p><p><br></p><p><img src=\"https://www.qnm.green:8080/morder/3/16023278176885.jpg\" data-local=\"wxfile://tmp_6777fd0ce13d452201eb846d4264ffa0cb9ce506287843dd.jpg\" width=\"80%\"></p><p><br></p><p><img src=\"https://www.qnm.green:8080/morder/3/16023278176148.jpg\" data-local=\"wxfile://tmp_b34b1a224ac5248728d4c5b12cb125f3d5ecf5c03b0418d9.jpg\" width=\"80%\"></p><p><br></p><p><br></p><p><br></p>',NULL,1,1,'2020-10-11 19:03:38',3,3,0),(21,'2020-10-10 19:17:36','2020-10-11 19:17:36','<p><img src=\"https://www.qnm.green:8080/morder/5/16023286561577.jpg\" data-local=\"wxfile://tmp_13939ad9a32c1931cd1f2caa3db7e3a8666ac0917ffd516b.jpg\" width=\"80%\"></p><p><br></p>',NULL,1,1,'2020-10-11 19:17:36',5,3,0),(22,'2020-10-10 19:40:03','2020-10-11 19:40:03','<p><img src=\"https://www.qnm.green:8080/morder/7/16023300029468.jpg\" data-local=\"wxfile://tmp_e6f51652eacb87b4c8747358afbbf4f507b5473c211f6b5b.jpg\" width=\"80%\"></p><p>医生你看看我的肚子怎么了</p>',NULL,1,1,'2020-10-10 19:42:43',7,18,1),(23,'2020-10-10 21:22:38','2020-10-11 21:22:38','<p><img src=\"https://www.qnm.green:8080/morder/7/16023361584792.jpg\" data-local=\"wxfile://tmp_1e8e05bb4226a991cae2c8f911687838235cdf0b73e5940d.jpg\" width=\"80%\"></p><p>医生我为什么木有腹肌</p>',NULL,1,1,NULL,7,18,1),(24,'2020-10-10 21:31:41','2020-10-11 21:31:41','<p><img src=\"https://www.qnm.green:8080/morder/7/16023367010945.jpg\" data-local=\"wxfile://tmp_022463519115b06438284e7f301e1784ccb70825ade29102.jpg\" width=\"80%\"></p><p>医生我腹肌又没了</p>',NULL,1,1,'2020-10-11 21:31:41',7,18,0),(25,'2020-10-10 22:04:38','2020-10-11 22:04:38','<p><img src=\"https://www.qnm.green:8080/morder/7/16023386784809.jpg\" data-local=\"wxfile://tmp_a74265af02abb1be0485fa73a33dea0359a312e5bdd7af40.jpg\" width=\"80%\"></p><p>医生我的腹肌怎么没了</p>',NULL,1,1,'2020-10-10 22:07:37',7,18,1),(26,'2020-10-10 22:29:00','2020-10-11 22:29:00','<p><img src=\"https://www.qnm.green:8080/morder/4/16023401408671.jpg\" data-local=\"wxfile://tmp_fb22ba0cc02095755dd92362a3f68aada9d8154ed6865b85.jpg\" width=\"80%\"></p><p>医生我为什么没有腹肌</p>',NULL,40,1,'2020-10-10 22:34:06',4,16,1),(27,'2020-10-10 22:30:44','2020-10-11 22:30:44','<p>你你你</p>',NULL,40,1,'2020-10-10 22:30:50',4,16,1),(28,'2020-10-11 00:15:04','2020-10-12 00:15:04','<p><img src=\"https://www.qnm.green:8080/morder/6/16023465046309.jpg\" data-local=\"wxfile://tmp_260e6476c6a0e8078d5f494b84c36c27a69f1c7ec4810587.jpg\" width=\"80%\"></p><p>出来打球啊医生</p>',NULL,50,1,'2020-10-12 00:15:04',6,501,0),(31,'2020-10-11 01:00:07','2020-10-12 01:00:07','<h2>测试</h2><p><img src=\"https://www.qnm.green:8080/morder/3/16023492077987.jpg\" data-local=\"wxfile://tmp_7050fed2f3414dd871165116b997484897a4a09a7a4f29d9.jpg\" width=\"80%\"></p><p><br></p>',NULL,1,1,'2020-10-11 01:00:58',3,3,1);
/*!40000 ALTER TABLE `morder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `morderMessages`
--

DROP TABLE IF EXISTS `morderMessages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `morderMessages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderid` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `content` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` smallint(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `morderMessages`
--

LOCK TABLES `morderMessages` WRITE;
/*!40000 ALTER TABLE `morderMessages` DISABLE KEYS */;
INSERT INTO `morderMessages` VALUES (1,2,'2018-09-10 00:00:00','hhhhh',1),(2,4,'2020-10-03 20:31:49','我没有过敏症状',1),(3,4,'2020-10-03 20:36:48','我没有吸烟史',1),(4,12,'2020-10-03 20:46:45','吃开瑞坦',1),(5,4,'2020-10-05 12:21:21','好的我明白了',1),(6,14,'2020-10-05 20:55:11','<p><img src=\"https://www.qnm.green:8080/morder/3/16019025111614.jpg\" width=\"80%\"></p><p>test</p><p><br></p><p><br></p>',1),(7,14,'2020-10-05 20:55:18','<p><img src=\"https://www.qnm.green:8080/morder/3/16019025111614.jpg\" width=\"80%\"></p><p>test</p><p><br></p><p><br></p>',1),(8,14,'2020-10-05 20:57:32','<p><img src=\"https://www.qnm.green:8080/morder/3/16019025111614.jpg\" width=\"80%\"></p><p>test</p><p><br></p><p><br></p>',1),(9,14,'2020-10-05 21:04:36','<p><img src=\"https://www.qnm.green:8080/morder/3/16019025111614.jpg\" width=\"80%\"></p><p>test</p><p><br></p><p><br></p>',1),(10,14,'2020-10-05 21:59:33','<p><img src=\"https://106.52.87.235:8080/morder/3/16019063730970.jpg\" data-local=\"wxfile://tmp_3acfba32a62bb5d2bc4011770e2f1110743b3a6dabf1feb1.jpg\" width=\"80%\"></p><p><br></p>',1),(11,16,'2020-10-09 22:33:50','<p><br></p>',1),(12,16,'2020-10-09 22:33:55','<p><br></p>',1),(13,15,'2020-10-09 22:34:08','<p>12313</p>',1),(14,15,'2020-10-09 22:37:12','<p>123123</p>',1),(15,17,'2020-10-10 12:58:34','<p wx:nodeid=\"9\">医生你好</p>',1),(16,18,'2020-10-10 15:23:26','<p><img src=\"https://www.qnm.green:8080/morder/3/16023146058884.jpg\" data-local=\"wxfile://tmp_da3bcbf021bd298ea1605270c787d6deae140347006ccac0.jpg\" width=\"80%\"></p><p><br></p>',1),(17,2,'2020-10-11 00:00:00','aaaaaaa',2),(18,2,'2020-10-10 18:36:37','ababababab',2),(19,17,'2020-10-11 00:00:00','<p wx:nodeid=\"9\">你好</p>',0),(20,22,'2020-10-10 19:40:55','<p>我是不是凉了</p>',1),(21,22,'2020-10-10 19:42:11',' <p>没救了，准备后事吧</p>',0),(22,12,'2020-10-10 20:41:45','多喝热水',0),(23,12,'2020-10-10 21:21:43','随便侧测',0),(24,23,'2020-10-10 21:22:47','<p>医生救救我</p>',1),(25,24,'2020-10-10 21:31:52','<p>医生在吗</p>',1),(26,24,'2020-10-10 21:32:06','不在，滚',0),(27,25,'2020-10-10 22:06:33','没有腹肌正常',0),(28,25,'2020-10-10 22:06:55','<p>为什么</p>',1),(29,26,'2020-10-10 22:29:13','<p>医生快回我</p>',1),(30,26,'2020-10-10 14:33:26','没有就没有',0),(31,26,'2020-10-10 22:34:00','<p>好吧</p>',1),(32,28,'2020-10-10 16:15:48','好的',0),(33,28,'2020-10-11 00:16:26','<p>今晚八点</p>',1),(34,20,'2020-10-11 00:16:26','建议服用开瑞坦',0),(35,29,'2020-10-11 00:51:17','<p><img src=\"https://www.qnm.green:8080/morder/3/16023486778734.jpg\" data-local=\"wxfile://tmp_df70d3634183ed69e2f6418693b0d42b253cbef4e59369ae.jpg\" width=\"80%\"></p><p><br></p>',1),(36,31,'2020-10-11 01:00:40','<p><img src=\"https://www.qnm.green:8080/morder/3/16023492403201.jpg\" data-local=\"wxfile://tmp_ea3ab7ac7011e5fd0cdaa93afd992d4bb0d08f867daa84af.jpg\" width=\"80%\"></p><p><br></p>',1);
/*!40000 ALTER TABLE `morderMessages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `porder`
--

DROP TABLE IF EXISTS `porder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `porder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_time` datetime NOT NULL,
  `user_phone` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `freed_time` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_confirm` tinyint(1) NOT NULL,
  `user_ctime` datetime DEFAULT NULL,
  `price` float NOT NULL,
  `userid` int(11) NOT NULL,
  `doctorid` int(11) NOT NULL,
  `iscommont` tinyint(1) NOT NULL,
  `doctor_confirm` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `porder`
--

LOCK TABLES `porder` WRITE;
/*!40000 ALTER TABLE `porder` DISABLE KEYS */;
INSERT INTO `porder` VALUES (1,'2020-10-04 22:22:54','13718990923','2020-10-09 22:15:08',0,NULL,3,2,2,0,1),(3,'2020-10-06 15:23:02','13670520774','2020-10-11到2020-10-12',1,'2020-10-06 15:26:11',1,2,4,1,0),(4,'2020-10-06 19:51:03','13715622918','2020.10.10-2020.10.12',1,'2020-10-06 20:15:01',2,2,2,1,1),(5,'2020-10-07 00:18:41','13715622918','2020-10-07 00:18:23',0,NULL,1,2,2,0,1),(6,'2020-10-04 00:00:00','12345287334','2020.10.11-2020.10.12',0,NULL,2,2,2,0,0),(18,'2020-10-10 12:59:15','','2020-10-10 12:51:42',1,'2020-10-10 18:41:08',1,4,3,1,0),(24,'2020-10-10 19:02:03','13690857332','2020-10-11 19:01:00',0,NULL,1,4,3,0,0),(34,'2020-10-10 19:27:43','12585638571','2020-10-10 19:26:54',0,NULL,1,4,3,0,0),(35,'2020-10-10 19:44:30','13640853116','2020-10-11 19:35:00',1,'2020-10-10 19:45:37',1,7,13,1,0),(36,'2020-10-10 22:08:25','13245963668','2020-10-10 22:00:30',1,'2020-10-10 22:08:49',1,7,18,1,1),(37,'2020-10-10 22:36:05','13670520446','2020-10-11 22:20:00',0,NULL,80,4,16,0,1),(38,'2020-10-11 00:17:32','18024476205','2020-11-11 01:14:00',0,NULL,100,6,501,0,1),(39,'2020-10-11 01:01:41','18126733258','2020-11-11 00:34:00',1,'2020-10-11 01:02:09',1,3,3,1,0),(40,'2020-10-11 10:18:50','15211','2020-10-11 09:38:08',0,NULL,1,3,3,0,0),(41,'2020-10-11 10:36:52','11144','2020-10-11 09:38:08',0,NULL,1,3,3,0,0);
/*!40000 ALTER TABLE `porder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `wx_open_id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
  `phone` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'潇洒哥','ogq0d5CvDML1uxt9pacz_D3wFdrE',NULL),(3,'小小','odyGY5HaokYBdL2eEWs0KxbnkR9E',NULL),(4,'某某','odyGY5PWZvuRoHfxjeCnaHD2wIGg',NULL),(5,'某某','odyGY5Mab9Pla3fNgSyqC6EGLYEU',NULL),(6,'某某','odyGY5B3GNivFynVB9IoEFHRmpi8',NULL),(7,'某某','odyGY5GLT2kspkHXyfc-Z9xmBdeM',NULL),(8,'某某','odyGY5FHF1bKbvyd52LtNYuvTRGM',NULL),(9,NULL,'odyGY5OrBE7U2usEO-GuuYquT_g8',NULL),(10,NULL,'odyGY5EqRLRH8BE8TGtVuEig6jU4',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-05  3:34:27
