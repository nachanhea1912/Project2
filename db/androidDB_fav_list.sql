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
-- Table structure for table `fav_list`
--

DROP TABLE IF EXISTS `fav_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fav_list` (
  `fav_list_idx` int NOT NULL AUTO_INCREMENT COMMENT '관심목록 번호',
  `fav_list_user_id` varchar(45) NOT NULL COMMENT '관심목록-유저아이디',
  `fav_list_list_idx` int NOT NULL COMMENT '관심목록-글번호',
  PRIMARY KEY (`fav_list_idx`),
  KEY `fav_list_list_idx_idx` (`fav_list_list_idx`),
  KEY `fav_list_user_id_idx` (`fav_list_user_id`),
  CONSTRAINT `fav_list_list_idx` FOREIGN KEY (`fav_list_list_idx`) REFERENCES `list` (`list_idx`),
  CONSTRAINT `fav_list_user_id` FOREIGN KEY (`fav_list_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='관심목록';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fav_list`
--

LOCK TABLES `fav_list` WRITE;
/*!40000 ALTER TABLE `fav_list` DISABLE KEYS */;
INSERT INTO `fav_list` VALUES (1,'test',114),(2,'test',147),(3,'test',149),(4,'test',127),(5,'nch',153),(6,'nch',154),(7,'nch',149),(8,'suyeon',155),(9,'suyeon',148),(10,'rlgh',161);
/*!40000 ALTER TABLE `fav_list` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-31  9:16:23
