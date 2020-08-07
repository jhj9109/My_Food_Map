-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: ssafysk
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `review`
--
use ssafysk;
DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `no` int NOT NULL AUTO_INCREMENT,
  `userid` int DEFAULT NULL,
  `resid` int NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `rank` int NOT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `image` varchar(255) DEFAULT NULL,
  `like_cnt` int DEFAULT 0,
  PRIMARY KEY (`no`),
  KEY `userid` (`userid`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`),
  KEY `resid` (`resid`),
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`resid`) REFERENCES `restaurants` (`idrestaurants`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,1,2,'맛있는데 비싸서 1점 깎았습니다.',4,'2020-07-30 00:00:00',NULL,0),(2,2,2,'웨이팅 1시간 해서 짜증났는데 음식 먹는 순간 다 풀림',5,'2020-07-30 00:00:00',NULL,0),(3,3,4,'jmt,, 사장님이 쫌 불친절하시지만 너무 맛있어서 다시 갈 거예요,,,',4,'2020-07-15 00:00:00',NULL,0),(4,5,5,'음식에서 머리카락 나오고 진짜 최악;;; 절대 가지 마세요ㅜㅜㅜ',1,'2020-07-21 00:00:00',NULL,0),(5,7,3,'그냥저냥 평범ㅇㅇ 재방문 하진 않을 듯 ?',3,'2020-07-08 00:00:00',NULL,0);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-04 14:47:06
