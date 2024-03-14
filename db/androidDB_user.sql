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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_idx` int NOT NULL AUTO_INCREMENT COMMENT '사용자 번호',
  `user_id` varchar(45) NOT NULL COMMENT '사용자 아이디',
  `user_pw` varchar(45) NOT NULL COMMENT '사용자 비밀번호',
  `user_nick` varchar(100) NOT NULL COMMENT '사용자 닉네임',
  `user_email` varchar(100) NOT NULL COMMENT '사용자 이메일 주소',
  `user_adress` varchar(100) DEFAULT NULL COMMENT '사용자 사는 곳',
  `user_phone_num` varchar(45) DEFAULT NULL COMMENT '사용자 전화번호',
  `user_deleted_yn` varchar(1) NOT NULL DEFAULT 'N' COMMENT '사용자 탈퇴 여부',
  `user_favlist` varchar(45) DEFAULT NULL COMMENT '사용자 찜목록',
  `user_rating` int DEFAULT '50' COMMENT '사용자 평점',
  `user_profile` varchar(100) DEFAULT NULL,
  `user_comment` varchar(1000) DEFAULT '반갑습니다',
  PRIMARY KEY (`user_idx`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `user_email_UNIQUE` (`user_email`),
  UNIQUE KEY `user_nick_UNIQUE` (`user_nick`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='회원정보';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'master','1234','마스터','master','',NULL,'N',NULL,50,'','매매를실제로'),(2,'test','0000','테스터','test@naver.com','부산진구가야','01012345678','N',NULL,56,'profiletest202401290102.png','매매를실제로'),(3,'test2','1234','testes','tttt',NULL,NULL,'N',NULL,50,'','매매를실제로'),(4,'test3','1234','test3','tttttttttt',NULL,NULL,'N',NULL,50,NULL,'매매를실제로'),(6,'tes5','12345','sssss','sssssss',NULL,NULL,'N',NULL,50,NULL,'매매를실제로'),(7,'test12','12345','aaaaaaa','aaa',NULL,NULL,'N',NULL,50,NULL,'매매를실제로'),(8,'','','','',NULL,NULL,'N',NULL,50,NULL,'매매를실제로'),(9,'beatch','abc123','배덕두','test1@naver.com',NULL,NULL,'N',NULL,50,NULL,'매매를실제로'),(10,'rhkr','1234','가야동 곽형','rhkr@naver.com','부산진구동 가야','010-1234-4567','N',NULL,49,'profilerhkr202401282337.png','가야이마트24'),(11,'vkvkdia','1234','익스','vkvkdia@naver.com',NULL,NULL,'N',NULL,50,NULL,'반갑습니다'),(12,'nch','123','qwer','qwer@naver.com',NULL,NULL,'N',NULL,50,'profilench202401290202.png','반갑습니다'),(13,'rlgh','1234','기호이기호기호','rlgh@test.com',NULL,NULL,'N',NULL,50,NULL,'반갑습니다'),(14,'suyeon','0000','psy','psy@gmail.com',NULL,NULL,'N',NULL,50,'profilesuyeon202401290203.png','반갑습니다'),(15,'rbth','1234','블랙핑크 구소룡	','rnth@naver.com',NULL,NULL,'N',NULL,50,'profilerbth202401290310.png','반갑습니다');
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

-- Dump completed on 2024-01-31  9:16:23
