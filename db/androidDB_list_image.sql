-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 3.39.239.11    Database: androidDB
-- ------------------------------------------------------
-- Server version	8.0.35-0ubuntu0.22.04.1

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
-- Table structure for table `list_image`
--

DROP TABLE IF EXISTS `list_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `list_image` (
  `list_image_idx` int NOT NULL AUTO_INCREMENT COMMENT '게시글 이미지 저장 번호',
  `list_image_name` varchar(100) NOT NULL DEFAULT 'NULL' COMMENT '사진이름',
  `list_image_list_idx` int DEFAULT NULL COMMENT '사진이 보여질 게시글 번호',
  PRIMARY KEY (`list_image_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='게시글 이미지 저장';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `list_image`
--

LOCK TABLES `list_image` WRITE;
/*!40000 ALTER TABLE `list_image` DISABLE KEYS */;
INSERT INTO `list_image` VALUES (9,'listtest202401251015i1.png',147),(10,'listtest202401251015i1.png',147),(11,'listtest202401251015i0.png',147),(12,'listrhkr202401282340i0.png',148),(13,'listrhkr202401282340i0.png',148),(14,'listrhkr202401282340i1.png',148),(15,'listrhkr202401282347i0.png',149),(16,'listvkvkdia202401290108i1.png',152),(17,'listvkvkdia202401290108i0.png',152),(18,'listtest202401290114i0.png',153),(19,'listtest202401290114i1.png',153),(20,'listnch202401290145i0.png',154),(21,'listsuyeon202401290153i0.png',155),(22,'listrlgh202401290154i0.png',156),(23,'listnch202401290154i0.png',157),(24,'listtest202401290222i0.png',160),(25,'listrbth202401290311i1.png',161),(26,'listrbth202401290311i0.png',161),(27,'listrbth202401290311i2.png',161);
/*!40000 ALTER TABLE `list_image` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-31  9:16:22
