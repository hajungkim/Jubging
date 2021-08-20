-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: i5b207.p.ssafy.io    Database: jubging
-- ------------------------------------------------------
-- Server version	5.5.5-10.3.31-MariaDB-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `SPRING_SESSION`
--

DROP TABLE IF EXISTS `SPRING_SESSION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SPRING_SESSION` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint(20) NOT NULL,
  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
  `EXPIRY_TIME` bigint(20) NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`),
  UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SPRING_SESSION`
--

LOCK TABLES `SPRING_SESSION` WRITE;
/*!40000 ALTER TABLE `SPRING_SESSION` DISABLE KEYS */;
/*!40000 ALTER TABLE `SPRING_SESSION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SPRING_SESSION_ATTRIBUTES`
--

DROP TABLE IF EXISTS `SPRING_SESSION_ATTRIBUTES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SPRING_SESSION_ATTRIBUTES` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `SPRING_SESSION` (`PRIMARY_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SPRING_SESSION_ATTRIBUTES`
--

LOCK TABLES `SPRING_SESSION_ATTRIBUTES` WRITE;
/*!40000 ALTER TABLE `SPRING_SESSION_ATTRIBUTES` DISABLE KEYS */;
/*!40000 ALTER TABLE `SPRING_SESSION_ATTRIBUTES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alarm`
--

DROP TABLE IF EXISTS `alarm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alarm` (
  `alarm_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `pub_id` int(11) DEFAULT 0,
  `article_id` int(11) DEFAULT 0,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `nickname` varchar(100) DEFAULT NULL,
  `profile_path` varchar(500) DEFAULT NULL,
  `category` varchar(100) NOT NULL,
  PRIMARY KEY (`alarm_id`),
  KEY `alarm_FK` (`user_id`),
  CONSTRAINT `alarm_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alarm`
--

LOCK TABLES `alarm` WRITE;
/*!40000 ALTER TABLE `alarm` DISABLE KEYS */;
INSERT INTO `alarm` VALUES (1,4,6,NULL,'2021-08-16 01:15:51','콩지민','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/6b3424d5-d209-4cc6-9778-1ed48a0e56fbuser_default.png','follow'),(2,1,6,1,'2021-08-16 01:16:10','콩지민','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/6b3424d5-d209-4cc6-9778-1ed48a0e56fbuser_default.png','comment'),(3,5,7,NULL,'2021-08-16 01:24:38','구르미','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/5a3f5a88-daf3-419b-aefc-6e34c5228246profile.png','follow'),(4,4,6,NULL,'2021-08-16 10:30:52','콩지민','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/6b3424d5-d209-4cc6-9778-1ed48a0e56fbuser_default.png','follow'),(5,5,6,7,'2021-08-16 10:35:01','콩지민','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/6b3424d5-d209-4cc6-9778-1ed48a0e56fbuser_default.png','like'),(6,5,6,7,'2021-08-16 10:35:02','콩지민','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/6b3424d5-d209-4cc6-9778-1ed48a0e56fbuser_default.png','like'),(7,5,6,7,'2021-08-16 10:35:02','콩지민','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/6b3424d5-d209-4cc6-9778-1ed48a0e56fbuser_default.png','like'),(8,6,1,NULL,'2021-08-16 10:44:13','하죠이','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/d4919b67-c6b3-4758-ba46-489eb33446ceprofile.png','follow'),(9,1,2,6,'2021-08-16 02:35:06','에코천재','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/4bd742d2-9858-4fb8-ab5d-1efa035538f0KakaoTalk_20210816_181156612_02.jpg','comment'),(10,1,2,6,'2021-08-16 02:35:12','에코천재','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/4bd742d2-9858-4fb8-ab5d-1efa035538f0KakaoTalk_20210816_181156612_02.jpg','like'),(11,1,2,NULL,'2021-08-16 02:35:21','에코천재','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/4bd742d2-9858-4fb8-ab5d-1efa035538f0KakaoTalk_20210816_181156612_02.jpg','follow'),(12,4,1,2,'2021-08-16 02:39:56','하죠이','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/d4919b67-c6b3-4758-ba46-489eb33446ceprofile.png','comment'),(13,13,15,NULL,'2021-08-16 06:01:23','ekdms','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/6b3424d5-d209-4cc6-9778-1ed48a0e56fbuser_default.png','follow'),(14,1,2,32,'2021-08-17 15:09:57','에코천재','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/4bd742d2-9858-4fb8-ab5d-1efa035538f0KakaoTalk_20210816_181156612_02.jpg','like'),(15,1,2,31,'2021-08-17 15:10:03','에코천재','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/4bd742d2-9858-4fb8-ab5d-1efa035538f0KakaoTalk_20210816_181156612_02.jpg','like'),(16,1,14,31,'2021-08-17 15:41:00','선쥬르','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/4ea58545-d8ef-41aa-8861-c9425e027481profile.png','like'),(17,1,14,31,'2021-08-17 15:41:24','선쥬르','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/4ea58545-d8ef-41aa-8861-c9425e027481profile.png','like'),(18,1,14,32,'2021-08-17 15:41:37','선쥬르','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/4ea58545-d8ef-41aa-8861-c9425e027481profile.png','like'),(19,1,14,NULL,'2021-08-17 15:41:45','선쥬르','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/4ea58545-d8ef-41aa-8861-c9425e027481profile.png','follow'),(20,16,1,26,'2021-08-17 15:45:25','하죠이','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/d4919b67-c6b3-4758-ba46-489eb33446ceprofile.png','comment'),(21,16,1,26,'2021-08-17 15:45:31','하죠이','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/d4919b67-c6b3-4758-ba46-489eb33446ceprofile.png','like'),(22,16,1,NULL,'2021-08-17 15:45:49','하죠이','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/d4919b67-c6b3-4758-ba46-489eb33446ceprofile.png','follow'),(23,14,1,28,'2021-08-17 15:46:39','하죠이','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/d4919b67-c6b3-4758-ba46-489eb33446ceprofile.png','comment'),(24,14,2,12,'2021-08-18 17:12:19','에코천재','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/4bd742d2-9858-4fb8-ab5d-1efa035538f0KakaoTalk_20210816_181156612_02.jpg','comment'),(25,14,2,13,'2021-08-19 02:12:26','에코천재','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/4bd742d2-9858-4fb8-ab5d-1efa035538f0KakaoTalk_20210816_181156612_02.jpg','like'),(26,14,7,12,'2021-08-19 02:14:19','구르미','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/5a3f5a88-daf3-419b-aefc-6e34c5228246profile.png','like'),(27,14,7,NULL,'2021-08-19 02:14:23','구르미','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/5a3f5a88-daf3-419b-aefc-6e34c5228246profile.png','follow'),(28,14,4,8,'2021-08-19 02:15:04','yoonyoon','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/0249c42f-93ce-434b-a177-55d4b7c384aaprofile.png','like'),(29,14,4,8,'2021-08-19 02:15:27','yoonyoon','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/0249c42f-93ce-434b-a177-55d4b7c384aaprofile.png','comment'),(30,14,13,14,'2021-08-19 02:21:38','다은','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/3a65b81f-c899-48e3-ba4a-d507da054d3fprofile.png','like'),(31,14,16,14,'2021-08-19 02:22:28','기리','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/6b3424d5-d209-4cc6-9778-1ed48a0e56fbuser_default.png','like'),(32,14,16,14,'2021-08-19 02:22:32','기리','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/6b3424d5-d209-4cc6-9778-1ed48a0e56fbuser_default.png','comment'),(33,16,3,NULL,'2021-08-19 02:34:00','프로줍깅러','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/47942ddc-ecf6-4332-8219-2aade21609d0profile.png','follow'),(34,14,3,NULL,'2021-08-19 02:34:06','프로줍깅러','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/47942ddc-ecf6-4332-8219-2aade21609d0profile.png','follow'),(35,5,3,NULL,'2021-08-19 02:34:10','프로줍깅러','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/47942ddc-ecf6-4332-8219-2aade21609d0profile.png','follow'),(36,1,3,NULL,'2021-08-19 02:34:21','프로줍깅러','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/47942ddc-ecf6-4332-8219-2aade21609d0profile.png','follow'),(37,6,3,NULL,'2021-08-19 02:34:29','프로줍깅러','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/47942ddc-ecf6-4332-8219-2aade21609d0profile.png','follow'),(38,4,3,NULL,'2021-08-19 02:34:35','프로줍깅러','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/47942ddc-ecf6-4332-8219-2aade21609d0profile.png','follow'),(39,16,17,22,'2021-08-19 04:29:36','dongyun','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/6b3424d5-d209-4cc6-9778-1ed48a0e56fbuser_default.png','like'),(40,16,17,24,'2021-08-19 04:30:44','dongyun','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/6b3424d5-d209-4cc6-9778-1ed48a0e56fbuser_default.png','comment'),(41,16,17,24,'2021-08-19 04:31:05','dongyun','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/6b3424d5-d209-4cc6-9778-1ed48a0e56fbuser_default.png','comment'),(42,14,2,8,'2021-08-18 20:08:05','에코천재','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/4bd742d2-9858-4fb8-ab5d-1efa035538f0KakaoTalk_20210816_181156612_02.jpg','comment'),(43,14,18,NULL,'2021-08-18 20:29:06','줍깅하깅','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/6b3424d5-d209-4cc6-9778-1ed48a0e56fbuser_default.png','follow'),(44,1,16,10,'2021-08-18 22:47:56','기리','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/100db134-eee8-48c3-8bbf-94407759d553profile.png','like'),(45,14,16,8,'2021-08-18 22:48:01','기리','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/100db134-eee8-48c3-8bbf-94407759d553profile.png','like'),(46,1,16,9,'2021-08-18 22:48:03','기리','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/100db134-eee8-48c3-8bbf-94407759d553profile.png','like'),(47,5,16,11,'2021-08-18 22:48:06','기리','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/100db134-eee8-48c3-8bbf-94407759d553profile.png','like'),(48,14,16,13,'2021-08-18 22:48:15','기리','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/100db134-eee8-48c3-8bbf-94407759d553profile.png','like'),(49,1,16,6,'2021-08-18 22:48:22','기리','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/100db134-eee8-48c3-8bbf-94407759d553profile.png','like'),(50,1,16,1,'2021-08-18 22:48:25','기리','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/100db134-eee8-48c3-8bbf-94407759d553profile.png','like'),(51,1,16,4,'2021-08-18 22:48:30','기리','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/100db134-eee8-48c3-8bbf-94407759d553profile.png','like'),(52,4,16,2,'2021-08-18 22:48:34','기리','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/100db134-eee8-48c3-8bbf-94407759d553profile.png','like'),(53,6,16,3,'2021-08-18 22:48:38','기리','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/100db134-eee8-48c3-8bbf-94407759d553profile.png','like'),(54,5,16,11,'2021-08-18 22:50:47','기리','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/100db134-eee8-48c3-8bbf-94407759d553profile.png','comment'),(55,16,5,7,'2021-08-19 01:58:47','susu','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/a4e7417c-2a7f-4df6-9fe2-45fb9497b673profile.png','comment');
/*!40000 ALTER TABLE `alarm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article` (
  `article_id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(500) NOT NULL,
  `photos_path` varchar(500) DEFAULT NULL,
  `like_cnt` int(11) DEFAULT 0,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`article_id`),
  KEY `article_FK` (`user_id`),
  CONSTRAINT `article_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (1,'오늘은 #줍깅 하기 좋은날 ?','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/26a90343-8a9e-44c9-ac49-e09475b8e26fKakaoTalk_20210816_181637617.jpg#https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/778135e1-1be0-4e5e-82b3-d8297b10de31KakaoTalk_20210816_181642659.jpg#',3,'2021-08-16 14:53:18',1),(2,'오늘 처음으로 줍깅을 해봤다!! 조깅과 쓰레기 줍기라니! 생각보다 쓰레기가 많아서 놀랐지만,, 줍깅 재미있다?‍♂️?‍♂️?‍♂️\n#줍깅 #조깅 #환경보호','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/4c2f9c13-ae50-49f6-aeae-b0f7002586a10.png#https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/37f9d386-7ec6-4c4f-8610-2f1fc72d6f7b1.png#',3,'2021-08-16 01:12:12',4),(3,'집 주변에서 줍깅을 했다..\n담배꽁초가 많이 있다..\n열심히 주워서 깨끗해진 모습을 보니 너무 뿌듯하다!!?\n#줍깅 #나도한다 #환경보호!!','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/9e82180c-bca5-483a-8e8f-b0eb4d415fb61.png#https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/1159d23d-72e7-432d-8a29-6209242b19a90.png#',4,'2021-08-15 15:08:47',6),(4,'줍깅하는 나 .. 너무 멋진걸? 넌 너무 멋져 쓰레기가 봐도 반하겠어 #줍깅 #셀피 #선팔하면맞팔','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/52742478-5f47-4770-995b-5566c1c364a7KakaoTalk_20210816_181637617_05.jpg#https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/ea666716-2b66-48b1-8673-0e66143bc5fdKakaoTalk_20210816_181637617_06.jpg#',4,'2021-08-16 14:51:46',1),(5,'처음해 본 #줍깅\n길거리에 담배꽁초가 너무 많다!!!\n#쓰레기버리기 #멈춰\n?‍♂️???\n조금이나마 깨끗해진 거리를 보니 뿌듯하다~','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/71ebea62-971f-4478-84cf-4171d4d567440.png#https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/2de89646-98a8-4a41-9eb6-c54e7a5731522.png#https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/eba14ad3-029f-49c7-b99a-5680e9a438b11.png#',3,'2021-08-16 15:08:47',5),(6,'오늘도 줍깅 나왔다! 담배꽁초 너무 많음ㅠ 그래도 뿌듯하다 ? #담배꽁초버리지마 #줍깅','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/5bef5f4f-b5bd-4e28-8ce3-1881281868a7KakaoTalk_20210816_181637617_02.jpg#https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/35d72076-2866-456e-99aa-c1823cf10e31KakaoTalk_20210816_181637617_01.jpg#',2,'2021-08-17 14:54:42',1),(7,'오늘도 줍깅! 뿌듯하구만\n#비오는날\n','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/2dd59390-03fa-4ed0-ae63-0d53a740c02c0.png#',3,'2021-08-19 00:26:37',16),(8,'#줍깅 #핵더움 #살빠짐 #환경\n무더운 날씨 힘들긴 했지만 운동도 하고 환경개선도 하고! 뿌듯한 하루다 팔로워 환영해요~!','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/1fe0335e-469b-4774-85ff-78491b533bbe1.png#https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/bb6e77de-7c65-4618-88a2-9c4303a14f740.png#',5,'2021-08-19 00:26:37',14),(9,'오늘도 줍깅 완.료. 했습니다 ?데헷 #집가는길 \n#줍줍 #줍깅 #1일1줍깅','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/71dc0944-0df1-4963-872a-4260d68a796e0.png#',4,'2021-08-19 00:26:37',1),(10,'#쓰줍 #쓰레기줄이기 얼마나 주웠는지 세는 중.. 오늘은 5종류나 주웠다구? 랭킹1등이 될거라구?','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/a57cfca7-fd4c-46ea-ab2a-4dcd93d52f3b0.png#https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/4ea6f669-c203-4853-a0bd-5b0b51bbd83e1.png#',4,'2021-08-19 00:26:37',1),(11,'밤에 줍깅했다! 이제 저녁엔 날씨가 선선해져서 줍깅하기 딱 좋다!😆 #줍깅 #저녁운동 #환경보호','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/9a7853a7-694d-4b73-a3c5-d4e777262dcc0.png#',3,'2021-08-19 00:26:37',5),(12,'줍깅했다! 쓰레기 많아서 놀랐지만 난 줍깅한다! 끝나고 분리수거 가능한 것들은 분리수거까지!👊 \n#줍깅 #환경보호','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/0bab1844-b791-440b-8d49-3f7f57bf5e820.png#https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/6c2e9dbc-2841-4ee6-8260-390085c3129c1.png#',4,'2021-08-19 00:26:37',14),(13,'줍깅하는 나, 멋있지 않나요? #줍깅 #집근처 #쓰레기줍기 #오늘도난달린다','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/f97ae7a7-880e-46d7-a0b1-3ebc067071aa0.png#',2,'2021-08-19 00:26:37',14),(14,'오늘은 학일이랑 줍깅했다! 학일이의 첫 줍깅!! 함께하니 더 즐겁다😉   #줍깅 #선쥬르 ','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/3ee1d9f4-142f-4a66-b5cf-52bb5191cdc20.png#https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/5b412b42-fc82-49d0-9836-5d5761a49c791.png#https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/d941510f-5037-4d30-9891-6bbcbc85e8e52.png#',6,'2021-08-19 00:26:37',14),(42,'2반 화이팅','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/c7d57715-6da3-46d3-8058-7d6b851375420.png#',0,'2021-08-19 02:15:03',16),(43,'2반 화이팅','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/19f3cbaa-d769-4d36-a7db-df6dd01d45fa0.png#https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/0accfcc8-85cf-4d53-856b-28884c991e201.png#',0,'2021-08-19 02:18:26',16),(44,'2반','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/0b664fe9-f35e-466a-accd-a6e55b6eb1410.png#https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/46bd40df-7960-4363-9e22-49422b1ba30f1.png#',0,'2021-08-19 11:29:10',16),(45,'2반','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/245ed34f-7647-4461-8e6e-2f0f9ea7c1100.png#https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/a5f9ed11-6657-4ab3-a841-d8edb86a832e1.png#',0,'2021-08-19 11:43:37',16),(46,'2뱌','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/7a5f5e49-a353-4ee5-864a-384b445240640.png#https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/09c0f958-5d6a-45c2-8069-83f677b55ef21.png#',0,'2021-08-19 11:53:11',16);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authorization`
--

DROP TABLE IF EXISTS `authorization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorization` (
  `email` varchar(500) NOT NULL,
  `auth_key` varchar(5) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorization`
--

LOCK TABLES `authorization` WRITE;
/*!40000 ALTER TABLE `authorization` DISABLE KEYS */;
INSERT INTO `authorization` VALUES ('cucu9823@naver.com','yjtvi'),('hazungzz@gmail.com','h2t3c'),('lee.sujeong10111@gmail.com','wq5hx'),('mhi0118@naver.com','9yfva'),('rmlgml3@naver.com','he9j3'),('test@test.com','i0gnz'),('tlsdh1111@naver.com','7dhog'),('zerozero7bang@gmail.com','k5lkq');
/*!40000 ALTER TABLE `authorization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `article_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `comment_content` varchar(500) NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `comment_FK` (`article_id`),
  KEY `comment_FK_1` (`user_id`),
  CONSTRAINT `comment_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'2021-08-16 01:16:10',1,6,'집게 정보 알 수 있을까요?'),(3,'2021-08-16 02:34:27',6,2,'ㅎㅇㅎㅇ'),(4,'2021-08-16 02:35:06',6,2,'test'),(5,'2021-08-16 02:39:56',2,1,'실천하는 모습 멋져요 ╰(*°▽°*)╯'),(23,'2021-08-16 22:52:07',26,14,'하기릿! 오늘 줍깅 좋았따~ 담주 주말 콜~?'),(24,'2021-08-16 22:53:01',5,14,'어 여기 대전아닌가요? 같이 줍깅 하쉴~?'),(26,'2021-08-17 15:13:41',4,14,'그게 바로 인생의 진리지.'),(28,'2021-08-17 15:45:25',26,1,'하기리~하기리보이~'),(29,'2021-08-17 15:46:39',28,1,'와.. 이거 찍어주신분 사진작가신가요?'),(30,'2021-08-18 06:33:30',32,2,'줍깅 1등 가자~~'),(31,'2021-08-19 02:12:19',12,2,'줍깅 굿~~'),(33,'2021-08-19 02:20:34',14,4,'줍깅하는 모습 보기 좋아요~'),(35,'2021-08-19 02:46:19',10,14,'랭킹 1위는 나야~!'),(38,'2021-08-18 20:08:05',8,2,'잘 구경하고 갑니다~😝'),(40,'2021-08-19 05:43:36',14,18,'선쥬아 오늘 재미있었다! 덕분에 나도 줍깅 시작한다!!!'),(41,'2021-08-18 22:50:47',11,16,'저도 같이해유!!!'),(42,'2021-08-18 22:51:03',7,16,'댓글이 없넹..'),(43,'2021-08-19 09:33:54',7,14,'ㅋㅋㅋㅋㅋ내가 댓글 남기고 간다'),(44,'2021-08-19 01:58:47',7,5,'맞팔해요~');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(100) NOT NULL,
  `content` varchar(500) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,'🏃‍♂️ [행사] 런데이 어스앤런 플로깅 챌린지','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/12d2c4dd-0158-4382-b89c-7ce3a0fcdf7e20210601%EB%9F%B0%EB%8D%B0%EC%9D%B4%EC%96%B4%EC%8A%A4%EC%95%A4%EB%9F%B0%ED%94%8C%EB%A1%9C%EA%B9%85%EC%B1%8C%EB%A6%B0%EC%A7%80.jpg','2021-08-18 16:45:35'),(2,'🏃‍♂️ [행사] 아름다운 재단 두런두런 마라톤 플로깅','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/e4d69583-a25c-464e-83de-34ac88991e3320210608%EC%95%84%EB%A6%84%EB%8B%A4%EC%9A%B4%EC%9E%AC%EB%8B%A8%EB%91%90%EB%9F%B0%EB%91%90%EB%9F%B0%EB%A7%88%EB%9D%BC%ED%86%A4%ED%94%8C%EB%A1%9C%EA%B9%85.jpg','2021-08-18 16:45:35'),(3,'🏃‍♂️ [행사] 영월군 스포츠클럽 에코 영월 플로깅','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/0a66cb56-7cf5-433b-9e7b-3e60f7e0341a20210621%EC%98%81%EC%9B%94%EA%B5%B0%EC%8A%A4%ED%8F%AC%EC%B8%A0%ED%81%B4%EB%9F%BD%EC%97%90%EC%BD%94%EC%98%81%EC%9B%94%ED%94%8C%EB%A1%9C%EA%B9%85.jpg','2021-08-18 16:45:35'),(4,'🏃‍♂️ [행사] 군산시 금강미래체험관 플로깅 캠페인','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/594e5a41-466c-48ae-be01-babab6faea3620210630%EA%B5%B0%EC%82%B0%EC%8B%9C%EA%B8%88%EA%B0%95%EB%AF%B8%EB%9E%98%EC%B2%B4%ED%97%98%EA%B4%80%ED%94%8C%EB%A1%9C%EA%B9%85%EC%BA%A0%ED%8E%98%EC%9D%B8.png','2021-08-18 16:45:35'),(5,'🏃‍♂️ [행사] 의정부 에코 서포터즈 플로깅단 모집','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/edbd49b8-2206-4632-a8f6-9675951a467320210701%EC%9D%98%EC%A0%95%EB%B6%80%EC%97%90%EC%BD%94%EC%84%9C%ED%8F%AC%ED%84%B0%EC%A6%88%ED%94%8C%EB%A1%9C%EA%B9%85%EB%8B%A8%EB%AA%A8%EC%A7%91.jpg','2021-08-18 16:45:35'),(6,'🏃‍♂️ [행사] 한빛 소프트 대한민국 구석구석 플로깅','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/06d2122d-bdfb-481d-bdc0-c8edc98169b920210719%ED%95%9C%EB%B9%9B%EC%86%8C%ED%94%84%ED%8A%B8%EA%B0%9C%ED%95%9C%EB%AF%BC%EA%B5%AD%EA%B5%AC%EC%84%9D%EA%B5%AC%EC%84%9D%ED%94%8C%EB%A1%9C%EA%B9%85.jpg','2021-08-18 16:45:35'),(7,'🏃‍♂️ [행사] 하남시 여름방학 에코 플로깅','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/e1b39b2a-f5d9-41d4-94a0-3e3ed51f3b6920210721%ED%95%98%EB%82%A8%EC%8B%9C%EC%97%AC%EB%A6%84%EB%B0%A9%ED%95%99%EC%97%90%EC%BD%94%ED%94%8C%EB%A1%9C%EA%B9%85.png','2021-08-18 16:45:35'),(8,'🏃‍♂️ [행사] 시흥시 정왕청소년문화의집 만보 플로깅','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/cd569488-422a-4ba8-b18b-82f6d439b63b20210725%EC%8B%9C%ED%9D%A5%EC%8B%9C%EC%A0%95%EC%99%95%EC%B2%AD%EC%86%8C%EB%85%84%EB%AC%B8%ED%99%94%EC%9D%98%EC%A7%91%EB%A7%8C%EB%B3%B4%ED%94%8C%EB%A1%9C%EA%B9%85.png','2021-08-18 16:45:35'),(9,'🏃‍♂️ [행사] 영천시 우리가족 플로깅 캠페인','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/a49ff360-608f-4a52-8672-a0bdc085a00e20210730%EC%98%81%EC%B2%9C%EC%8B%9C%EC%9A%B0%EB%A6%AC%EA%B0%80%EC%A1%B1%ED%94%8C%EB%A1%9C%EA%B9%85%EC%BA%A0%ED%8E%98%EC%9D%B8.jpg','2021-08-18 16:45:35'),(10,'🏃‍♂️ [행사] 이천시 마장도서관 그린 워킹 환경 캠페인','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/9babe159-b851-40b7-ade5-c97c013fa0fa20210802%EC%9D%B4%EC%B2%9C%EC%8B%9C%EB%A7%88%EC%9E%A5%EB%8F%84%EC%84%9C%EA%B4%80%EA%B7%B8%EB%A6%B0%EC%9B%8C%ED%82%B9%ED%99%98%EA%B2%BD%EC%BA%A0%ED%8E%98%EC%9D%B8.png','2021-08-18 16:45:35'),(11,'🏃‍♂️ [행사] 안산시 가족 플로깅 Day','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/8335d37f-b49e-48d8-bae1-e40e18c17bb620210804%EC%95%88%EC%82%B0%EC%8B%9C%EA%B0%80%EC%A1%B1%ED%94%8C%EB%A1%9C%EA%B9%85Day.jpg','2021-08-18 16:45:35'),(12,'🏃‍♂️ [행사] 부산 항만공사 일석삼조 플로깅 이벤트','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/65ff7d2d-2f7f-402e-aca9-61d3784dd51020210813%EB%B6%80%EC%82%B0%ED%95%AD%EB%A7%8C%EA%B3%B5%EC%82%AC%EC%9D%BC%EC%84%9D%EC%82%BC%EC%A1%B0%ED%94%8C%EB%A1%9C%EA%B9%85%EC%9D%B4%EB%B2%A4%ED%8A%B8.jpg','2021-08-18 16:45:35'),(13,'🏃‍♂️ [행사] 인천 서구문화재단 선셋 줍깅 캠페인','https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/e6448a7c-782b-44fb-a8b7-5b5b091f51f820210828%EC%9D%B8%EC%B2%9C%EC%84%9C%EA%B5%AC%EB%AC%B8%ED%99%94%EC%9E%AC%EB%8B%A8%EC%84%A0%EC%85%8B%EC%A4%8D%EA%B9%85%EC%BA%A0%ED%8E%98%EC%9D%B8.jpg','2021-08-18 16:45:35');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `follow_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `follow_user_id` int(11) NOT NULL,
  `follow_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`follow_id`),
  KEY `follow_FK` (`user_id`),
  CONSTRAINT `follow_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (2,6,1,'2021-08-16 01:15:57'),(3,7,5,'2021-08-16 01:24:38'),(5,6,4,'2021-08-16 10:30:52'),(11,1,5,'2021-08-16 10:45:26'),(13,2,1,'2021-08-16 02:41:58'),(23,1,6,'2021-08-16 04:46:59'),(29,13,6,'2021-08-16 05:56:54'),(30,15,13,'2021-08-16 06:01:23'),(36,14,1,'2021-08-17 15:41:45'),(37,1,16,'2021-08-17 15:45:49'),(39,2,5,'2021-08-19 00:53:28'),(40,2,16,'2021-08-19 00:54:35'),(42,3,16,'2021-08-19 02:34:00'),(44,3,5,'2021-08-19 02:34:10'),(45,3,1,'2021-08-19 02:34:21'),(46,3,6,'2021-08-19 02:34:29'),(47,3,4,'2021-08-19 02:34:35'),(49,14,5,'2021-08-19 02:35:38'),(52,1,14,'2021-08-19 04:22:29'),(53,2,14,'2021-08-19 04:22:31'),(54,3,14,'2021-08-19 04:22:32'),(55,4,14,'2021-08-19 04:22:33'),(56,5,14,'2021-08-19 04:22:35'),(57,6,14,'2021-08-19 04:22:37'),(58,7,14,'2021-08-19 04:22:39'),(59,8,14,'2021-08-19 04:22:41'),(60,9,14,'2021-08-19 04:22:43'),(61,10,14,'2021-08-19 04:22:45'),(62,11,14,'2021-08-19 04:22:46'),(63,12,14,'2021-08-19 04:22:47'),(64,13,14,'2021-08-19 04:22:49'),(65,15,14,'2021-08-19 04:22:52'),(66,16,14,'2021-08-19 04:22:55'),(73,18,14,'2021-08-19 05:43:12'),(76,1,3,'2021-08-18 23:23:37'),(77,2,3,'2021-08-18 23:23:39'),(78,4,3,'2021-08-18 23:23:41'),(79,5,3,'2021-08-18 23:23:42'),(80,6,3,'2021-08-18 23:23:43'),(81,7,3,'2021-08-18 23:23:47'),(82,8,3,'2021-08-18 23:23:49'),(83,9,3,'2021-08-19 08:23:50'),(86,14,3,'2021-08-19 08:27:07'),(87,14,16,'2021-08-19 08:27:13');
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hashtag`
--

DROP TABLE IF EXISTS `hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hashtag` (
  `hash_id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(100) NOT NULL,
  `article_id` int(11) NOT NULL,
  PRIMARY KEY (`hash_id`),
  KEY `hashtag_FK` (`article_id`),
  CONSTRAINT `hashtag_FK` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hashtag`
--

LOCK TABLES `hashtag` WRITE;
/*!40000 ALTER TABLE `hashtag` DISABLE KEYS */;
INSERT INTO `hashtag` VALUES (1,'줍깅',1),(5,'줍깅',2),(6,'조깅',2),(7,'환경보호',2),(12,'줍깅',3),(13,'나도한다',3),(14,'환경보호',3),(19,'줍깅',5),(20,'쓰레기버리기',5),(21,'멈춰',5),(22,'담배꽁초버리지마',6),(23,'줍깅',6),(24,'줍깅',4),(25,'셀피',4),(26,'선팔하면맞팔',4),(31,'비오는날',7),(36,'줍깅',8),(37,'핵더움',8),(38,'살빠짐',8),(39,'환경',8),(54,'집가는길',9),(55,'줍줍',9),(56,'줍깅',9),(57,'1일1줍깅',9),(58,'쓰줍',10),(59,'쓰레기줄이기',10),(70,'줍깅',11),(71,'저녁운동',11),(72,'환경보호',11),(73,'줍깅',12),(74,'환경보호',12),(75,'줍깅',13),(76,'집근처',13),(77,'쓰레기줍기',13),(78,'오늘도난달린다',13),(79,'줍깅',14),(80,'선쥬르',14);
/*!40000 ALTER TABLE `hashtag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jubging_log`
--

DROP TABLE IF EXISTS `jubging_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jubging_log` (
  `jubging_id` int(11) NOT NULL AUTO_INCREMENT,
  `total_time` varchar(100) NOT NULL DEFAULT '0',
  `distance` varchar(100) NOT NULL DEFAULT '0',
  `created_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `user_id` int(11) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`jubging_id`),
  KEY `jubging_log_FK` (`user_id`),
  CONSTRAINT `jubging_log_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jubging_log`
--

LOCK TABLES `jubging_log` WRITE;
/*!40000 ALTER TABLE `jubging_log` DISABLE KEYS */;
INSERT INTO `jubging_log` VALUES (1,'0:00:04','0.00','2021-08-16 16:57:22',8,'대전시 유성구'),(2,'0:00:04','0.00','2021-08-16 16:57:33',8,'대전시 유성구'),(3,'0:00:01','0.00','2021-08-16 16:57:33',8,'대전시 유성구'),(4,'0:00:11','0.00','2021-08-16 16:57:33',8,'대전시 유성구'),(5,'0:00:02','0.00','2021-08-16 16:57:33',8,'대전시 유성구'),(6,'0:00:08','0.00','2021-08-16 16:57:33',8,'대전시 유성구'),(7,'0:00:03','0.00','2021-08-16 16:57:33',8,'대전시 유성구'),(8,'0:00:12','0.00','2021-08-16 16:57:33',8,'대전시 유성구'),(9,'0:00:14','0.00','2021-08-16 16:57:33',8,'대전시 유성구'),(10,'0:00:14','0.00','2021-08-16 16:57:33',8,'대전시 유성구'),(11,'0:00:04','0.00','2021-08-16 16:57:33',16,'대전시 유성구'),(12,'0:00:18','0.00','2021-08-16 16:57:33',16,'대전시 유성구'),(13,'0:18:04','1.2','2021-08-12 22:41:35',14,'대전 중구'),(14,'0:40:04','2.3','2021-08-13 23:10:13',14,'대전 중구'),(15,'0:20:30','0.9','2021-08-14 23:21:19',14,'대전 중구'),(16,'0:00:12','0.00','2021-08-16 23:22:58',2,'대전 중구'),(17,'0:00:12','0.00','2021-08-16 23:23:42',2,'대전 중구'),(18,'0:00:15','0.00','2021-08-16 23:31:49',16,'대전 중구'),(19,'0:00:15','0.00','2021-08-16 23:36:02',16,'대전 중구'),(20,'0:00:01','0.00','2021-08-16 23:36:11',16,'대전 중구'),(21,'0:00:12','0.00','2021-08-16 23:45:19',2,'대전 중구'),(22,'0:00:12','0.00','2021-08-16 23:45:22',2,'대전 중구'),(23,'0:00:05','0.00','2021-08-17 09:20:42',2,'대전 중구'),(24,'0:00:06','0.00','2021-08-17 12:35:10',16,'대전 중구'),(25,'0:00:03','0.00','2021-08-17 06:59:46',16,'대전 중구'),(26,'0:00:03','0.00','2021-08-17 16:30:43',16,'대전 중구'),(27,'0:00:04','0.00','2021-08-17 16:32:34',16,'대전 중구'),(28,'0:00:12','0.00','2021-08-17 16:34:01',16,'대전 중구'),(29,'0:00:05','0.00','2021-08-17 07:58:50',16,'대전 중구'),(30,'0:00:05','0.00','2021-08-17 07:58:57',16,'대전 중구'),(31,'0:00:01','0.00','2021-08-17 17:58:49',16,'대전 중구'),(32,'0:16:03','1.09','2021-08-18 01:09:11',16,'대전 중구'),(33,'0:00:04','0.00','2021-08-18 06:38:19',5,'대전 유성구'),(34,'0:40:45','3.2','2021-08-16 06:45:17',14,'대전 유성구'),(35,'0:30:06','2.4','2021-08-17 06:47:22',14,'대전 유성구'),(36,'0:20:06','1.6','2021-08-18 04:26:33',14,'대전 유성구'),(37,'0:08:06','0.7','2021-08-19 04:25:45',14,'대전 유성구'),(38,'0:00:02','0.00','2021-08-19 04:27:54',2,'대전 유성구'),(39,'0:00:02','0.00','2021-08-19 04:27:54',2,'대전 유성구'),(40,'0:00:02','0.00','2021-08-19 04:27:54',2,'대전 유성구'),(41,'0:00:02','0.00','2021-08-19 04:27:54',2,'대전 유성구'),(42,'0:00:04','0.00','2021-08-19 04:27:54',2,'대전 유성구'),(43,'0:00:04','0.00','2021-08-19 04:27:54',2,'대전 유성구'),(44,'0:00:14','0.00','2021-08-19 04:27:54',2,'대전 유성구'),(45,'0:00:14','0.00','2021-08-19 04:27:54',2,'대전 유성구'),(46,'0:02:40','0.00','2021-08-19 04:27:54',16,'충북 청주시'),(47,'0:00:07','0.00','2021-08-19 04:27:54',2,'대전 유성구'),(48,'0:00:07','0.00','2021-08-19 04:27:54',2,'대전 유성구'),(49,'0:02:40','0.00','2021-08-19 04:27:54',16,'충북 청주시'),(50,'0:00:06','0.00','2021-08-19 04:27:54',16,'충북 청주시'),(51,'0:00:06','0.00','2021-08-19 04:27:54',16,'충북 청주시'),(52,'0:00:01','0.00','2021-08-19 04:27:54',16,'충북 청주시'),(53,'0:00:01','0.00','2021-08-19 04:27:54',16,'충북 청주시'),(54,'0:00:01','0.00','2021-08-19 04:27:54',16,'충북 청주시'),(55,'0:00:03','0.00','2021-08-19 04:27:54',16,'충북 청주시'),(56,'0:00:03','0.00','2021-08-19 04:27:54',16,'충북 청주시'),(57,'0:00:19','0.00','2021-08-19 04:27:54',16,'충북 청주시'),(58,'0:00:19','0.00','2021-08-19 04:27:54',16,'충북 청주시'),(59,'0:00:19','0.00','2021-08-19 04:27:54',16,'충북 청주시'),(60,'0:00:03','0.00','2021-08-19 04:27:54',16,'충북 청주시'),(61,'0:00:03','0.00','2021-08-19 04:27:54',16,'충북 청주시'),(62,'0:00:03','0.00','2021-08-19 04:27:54',16,'충북 청주시'),(63,'0:00:03','0.00','2021-08-19 04:27:54',16,'충북 청주시'),(64,'0:00:03','0.00','2021-08-19 04:27:54',16,'충북 청주시'),(65,'0:00:39','0.01','2021-08-19 04:27:54',16,'충북 청주시'),(66,'0:00:37','0.03','2021-08-19 04:27:54',16,'충북 청주시'),(67,'0:00:34','0.01','2021-08-19 04:27:54',16,'충북 청주시'),(75,'0:00:03','0.00','2021-08-19 04:33:19',16,'충북 청주시'),(76,'0:00:03','0.00','2021-08-19 04:34:03',16,'충북 청주시'),(77,'0:00:03','0.00','2021-08-19 04:36:07',16,'충북 청주시'),(78,'0:00:03','0.00','2021-08-19 04:36:07',16,'충북 청주시'),(79,'0:00:03','0.00','2021-08-19 04:36:09',16,'충북 청주시'),(80,'0:00:03','0.00','2021-08-19 04:36:45',16,'충북 청주시'),(81,'0:00:02','0.00','2021-08-18 20:19:30',16,'충북 청주시'),(82,'0:00:03','0.00','2021-08-18 20:23:33',16,'충북 청주시'),(83,'0:00:03','0.00','2021-08-18 20:23:39',16,'충북 청주시'),(84,'5:05:05','0.00','2021-08-19 05:37:10',16,'충북 청주시'),(85,'9:09:04','0.00','2021-08-19 05:41:26',16,'충북 청주시'),(86,'9:09:04','0.00','2021-08-19 05:41:55',16,'충북 청주시'),(87,'9:09:04','0.00','2021-08-19 05:42:00',16,'충북 청주시'),(88,'21:21:12','0.00','2021-08-19 05:44:39',16,'충북 청주시'),(89,'0:01:46','0.01','2021-08-18 20:58:07',16,'충북 청주시'),(90,'0:01:39','0.02','2021-08-18 21:10:44',16,'충북 청주시'),(91,'0:00:37','0.00','2021-08-18 21:19:30',16,'충북 청주시'),(92,'0:00:03','0.00','2021-08-18 21:21:16',16,'충북 청주시'),(93,'0:00:03','0.00','2021-08-18 21:21:50',16,'충북 청주시'),(94,'0:00:03','0.00','2021-08-19 06:48:40',16,'충북 청주시'),(95,'0:01:44','0.02','2021-08-19 07:04:50',16,'충북 청주시'),(96,'0:01:44','0.02','2021-08-19 07:10:15',16,'충북 청주시'),(97,'0:00:03','0.00','2021-08-19 07:10:35',16,'충북 청주시'),(98,'0:00:03','0.00','2021-08-19 07:12:20',16,'충북 청주시'),(99,'0:00:03','0.00','2021-08-19 07:13:24',16,'충북 청주시'),(100,'0:00:03','0.00','2021-08-19 07:14:52',16,'충북 청주시'),(101,'0:00:03','0.00','2021-08-19 07:14:53',16,'충북 청주시'),(102,'0:00:10','0.00','2021-08-18 22:16:10',1,'충북 청주시'),(103,'0:00:03','0.00','2021-08-18 22:16:30',1,'충북 청주시'),(104,'0:00:03','0.00','2021-08-18 22:17:35',1,'충북 청주시'),(105,'0:00:38','0.00','2021-08-18 22:52:07',16,'충북 청주시'),(106,'0:00:38','0.00','2021-08-18 22:52:13',16,'충북 청주시'),(107,'0:00:56','0.07','2021-08-19 02:10:12',16,'충북 청주시'),(108,'0:03:07','0.19','2021-08-19 02:14:31',16,'충북 청주시'),(109,'0:01:25','0.07','2021-08-19 02:17:46',16,'충북 청주시'),(110,'0:00:40','0.10','2021-08-19 11:28:38',16,'충북 청주시'),(111,'0:00:31','0.09','2021-08-19 11:43:04',16,'충북 청주시'),(112,'0:02:09','0.17','2021-08-19 11:52:22',16,'충북 청주시');
/*!40000 ALTER TABLE `jubging_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like_log`
--

DROP TABLE IF EXISTS `like_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `like_log` (
  `like_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `article_id` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`like_id`),
  KEY `like_log_FK` (`user_id`),
  KEY `like_log_FK_1` (`article_id`),
  CONSTRAINT `like_log_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `like_log_FK_1` FOREIGN KEY (`article_id`) REFERENCES `article` (`article_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like_log`
--

LOCK TABLES `like_log` WRITE;
/*!40000 ALTER TABLE `like_log` DISABLE KEYS */;
INSERT INTO `like_log` VALUES (2,6,5,'2021-08-16 10:35:01'),(3,6,3,'2021-08-16 10:35:09'),(4,2,4,'2021-08-16 02:35:12'),(8,1,6,'2021-08-16 05:34:55'),(10,14,8,'2021-08-16 22:45:42'),(11,14,4,'2021-08-16 22:48:09'),(12,14,7,'2021-08-16 22:52:09'),(13,14,5,'2021-08-16 22:52:36'),(14,1,8,'2021-08-17 15:06:08'),(16,1,1,'2021-08-17 15:07:34'),(17,1,2,'2021-08-17 15:07:38'),(18,1,3,'2021-08-17 15:07:41'),(19,1,4,'2021-08-17 15:07:45'),(20,1,5,'2021-08-17 15:07:51'),(21,2,10,'2021-08-17 15:09:57'),(22,2,9,'2021-08-17 15:10:03'),(25,14,9,'2021-08-17 15:41:24'),(26,14,10,'2021-08-17 15:41:37'),(27,1,7,'2021-08-17 15:45:31'),(28,16,12,'2021-08-19 00:47:27'),(29,2,8,'2021-08-18 17:10:37'),(30,2,12,'2021-08-18 17:11:38'),(31,2,13,'2021-08-19 02:12:26'),(32,7,12,'2021-08-19 02:14:19'),(33,4,8,'2021-08-19 02:15:04'),(34,4,14,'2021-08-19 02:20:19'),(35,13,14,'2021-08-19 02:21:38'),(36,16,14,'2021-08-19 02:22:28'),(37,14,14,'2021-08-19 02:23:30'),(38,14,3,'2021-08-19 02:46:36'),(39,14,2,'2021-08-19 02:46:39'),(40,14,11,'2021-08-19 02:46:45'),(41,14,1,'2021-08-19 02:46:49'),(42,1,9,'2021-08-19 04:06:51'),(43,1,10,'2021-08-19 04:06:56'),(44,1,11,'2021-08-19 04:07:11'),(45,1,12,'2021-08-19 04:07:14'),(46,1,14,'2021-08-19 04:07:23'),(52,18,14,'2021-08-19 05:43:23'),(53,16,10,'2021-08-18 22:47:56'),(54,16,8,'2021-08-18 22:48:01'),(55,16,9,'2021-08-18 22:48:03'),(56,16,11,'2021-08-18 22:48:06'),(57,16,13,'2021-08-18 22:48:15'),(58,16,6,'2021-08-18 22:48:22'),(59,16,1,'2021-08-18 22:48:25'),(60,16,4,'2021-08-18 22:48:30'),(61,16,2,'2021-08-18 22:48:34'),(62,16,3,'2021-08-18 22:48:38'),(64,2,7,'2021-08-19 01:58:11');
/*!40000 ALTER TABLE `like_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `map`
--

DROP TABLE IF EXISTS `map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `map` (
  `code` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `count` varchar(100) DEFAULT '0',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `map`
--

LOCK TABLES `map` WRITE;
/*!40000 ALTER TABLE `map` DISABLE KEYS */;
INSERT INTO `map` VALUES ('00','전국','0'),('11','서울','0'),('11010','종로구','80'),('11020','중구','86'),('11030','용산구','94'),('11040','성동구','92'),('11050','광진구','91'),('11060','동대문구','83'),('11070','중랑구','70'),('11080','성북구','57'),('11090','강북구','70'),('11100','도봉구','90'),('11110','노원구','82'),('11120','은평구','89'),('11130','서대문구','80'),('11140','마포구','93'),('11150','양천구','32'),('11160','강서구','66'),('11170','구로구','80'),('11180','금천구','80'),('11190','영등포구','70'),('11200','동작구','70'),('11210','관악구','70'),('11220','서초구','75'),('11230','강남구','79'),('11240','송파구','57'),('11250','강동구','34'),('21','부산','0'),('21010','중구','69'),('21020','서구','47'),('21030','동구','84'),('21040','영도구','74'),('21050','부산진구','65'),('21060','동래구','85'),('21070','남구','0'),('21080','북구','0'),('21090','해운대구','0'),('21100','사하구','0'),('21110','금정구','0'),('21120','강서구','0'),('21130','연제구','0'),('21140','수영구','0'),('21150','사상구','0'),('21310','기장군','0'),('22','대구','0'),('22010','중구','0'),('22020','동구','0'),('22030','서구','0'),('22040','남구','0'),('22050','북구','0'),('22060','수성구','0'),('22070','달서구','0'),('22310','달성군','0'),('23','인천','0'),('23010','중구','0'),('23020','동구','0'),('23030','남구','0'),('23040','연수구','0'),('23050','남동구','0'),('23060','부평구','0'),('23070','계양구','0'),('23080','서구','0'),('23310','강화군','0'),('23320','옹진군','0'),('24','광주','0'),('24010','동구','0'),('24020','서구','0'),('24030','남구','0'),('24040','북구','0'),('24050','광산구','0'),('25','대전','0'),('25010','동구','0'),('25020','중구','0'),('25030','서구','0'),('25040','유성구','0'),('25050','대덕구','0'),('26','울산','0'),('26010','중구','0'),('26020','남구','0'),('26030','동구','0'),('26040','북구','0'),('26310','울주군','0'),('29','세종특별자치시','0'),('29010','세종시','0'),('31','경기','0'),('31011','수원시','85'),('31021','성남시','89'),('31030','의정부시','72'),('31041','안양시','60'),('31051','부천시','68'),('31060','광명시','44'),('31070','평택시','66'),('31080','동두천시','76'),('31091','안산시','84'),('31101','고양시','27'),('31110','과천시','84'),('31120','구리시','59'),('31130','남양주시','73'),('31140','오산시','73'),('31150','시흥시','63'),('31160','군포시','46'),('31170','의왕시','35'),('31180','하남시','57'),('31191','용인시','93'),('31200','파주시','35'),('31210','이천시','52'),('31220','안성시','74'),('31230','김포시','32'),('31240','화성시','45'),('31250','광주시','58'),('31260','양주시','46'),('31270','포천시','35'),('31320','여주군','68'),('31350','연천군','34'),('31370','가평군','67'),('31380','양평군','45'),('32','강원',''),('32010','춘천시','76'),('32020','원주시','0'),('32030','강릉시','86'),('32040','동해시','93'),('32050','태백시','0'),('32060','속초시','57'),('32070','삼척시','0'),('32310','홍천군','0'),('32320','횡성군','0'),('32330','영월군','0'),('32340','평창군','70'),('32350','정선군','0'),('32360','철원군','0'),('32370','화천군','0'),('32380','양구군','0'),('32390','인제군','12'),('32400','고성군','0'),('32410','양양군','0'),('33','충북','0'),('33011','청주시','81'),('33020','충주시','68'),('33030','제천시','0'),('33310','청원군','32'),('33320','보은군','0'),('33330','옥천군','32'),('33340','영동군','0'),('33350','진천군','32'),('33360','괴산군','0'),('33370','음성군','32'),('33380','단양군','32'),('33390','증평군','0'),('34','충남','0'),('34011','천안시','53'),('34020','공주시','47'),('34030','보령시','98'),('34040','아산시','66'),('34050','서산시','0'),('34060','논산시','66'),('34070','계룡시','0'),('34080','당진시','0'),('34310','금산군','0'),('34330','부여군','26'),('34340','서천군','0'),('34350','청양군','0'),('34360','홍성군','48'),('34370','예산군','0'),('34380','태안군','85'),('35','전북','0'),('35011','전주시','86'),('35020','군산시','69'),('35030','익산시','43'),('35040','정읍시','0'),('35050','남원시','0'),('35060','김제시','0'),('35310','완주군','0'),('35320','진안군','0'),('35330','무주군','0'),('35340','장수군','0'),('35350','임실군','0'),('35360','순창군','0'),('35370','고창군','0'),('35380','부안군','0'),('36','전남','0'),('36010','목포시','0'),('36020','여수시','85'),('36030','순천시','58'),('36040','나주시','47'),('36060','광양시','36'),('36310','담양군','47'),('36320','곡성군','27'),('36330','구례군','0'),('36350','고흥군','0'),('36360','보성군','0'),('36370','화순군','0'),('36380','장흥군','0'),('36390','강진군','38'),('36400','해남군','26'),('36410','영암군','37'),('36420','무안군','48'),('36430','함평군','0'),('36440','영광군','0'),('36450','장성군','0'),('36460','완도군','48'),('36470','진도군','0'),('36480','신안군','0'),('37','경북','0'),('37011','포항시','94'),('37020','경주시','94'),('37030','김천시','58'),('37040','안동시','0'),('37050','구미시','48'),('37060','영주시','0'),('37070','영천시','0'),('37080','상주시','0'),('37090','문경시','0'),('37100','경산시','0'),('37310','군위군','37'),('37320','의성군','48'),('37330','청송군','0'),('37340','영양군','0'),('37350','영덕군','54'),('37360','청도군','0'),('37370','고령군','63'),('37380','성주군','0'),('37390','칠곡군','0'),('37400','예천군','59'),('37410','봉화군','0'),('37420','울진군','0'),('37430','울릉군','0'),('38','경남','0'),('38030','진주시','47'),('38050','통영시','37'),('38060','사천시','38'),('38070','김해시','84'),('38080','밀양시','77'),('38090','거제시','45'),('38100','양산시','0'),('38111','창원시','40'),('38310','의령군','0'),('38320','함안군','59'),('38330','창녕군','0'),('38340','고성군','0'),('38350','남해군','0'),('38360','하동군','66'),('38370','산청군','0'),('38380','함양군','47'),('38390','거창군','0'),('38400','합천군','0'),('39','제주특별자치도','0'),('39010','제주시','72'),('39020','서귀포시','48');
/*!40000 ALTER TABLE `map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mission`
--

DROP TABLE IF EXISTS `mission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mission` (
  `user_id` int(11) NOT NULL,
  `can_cnt` int(11) DEFAULT 0,
  `plastic_cnt` int(11) DEFAULT 0,
  `bottle_cnt` int(11) DEFAULT 0,
  `paper_cnt` int(11) DEFAULT 0,
  `styroform_cnt` int(11) DEFAULT 0,
  `metal_cnt` int(11) DEFAULT 0,
  `vinyl_cnt` int(11) DEFAULT 0,
  `trash_cnt` int(11) DEFAULT 0,
  `mountain_cnt` int(11) DEFAULT 0,
  `ocean_cnt` int(11) DEFAULT 0,
  `river_cnt` int(11) DEFAULT 0,
  `arround_cnt` int(11) DEFAULT 0,
  `like_cnt` int(11) DEFAULT 0,
  `follow_cnt` int(11) DEFAULT 0,
  `comment_cnt` int(11) DEFAULT 0,
  `article_cnt` int(11) DEFAULT 0,
  `jubging_cnt` int(11) DEFAULT 0,
  `total_distance` double DEFAULT 0,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `mission_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mission`
--

LOCK TABLES `mission` WRITE;
/*!40000 ALTER TABLE `mission` DISABLE KEYS */;
INSERT INTO `mission` VALUES (1,10,1,0,0,0,2,0,5,0,0,0,7,14,7,3,12,8,21),(2,0,0,0,0,0,0,0,0,0,2,1,1,7,6,6,5,4,6.6000000000000005),(3,0,0,0,0,0,0,0,0,0,0,0,0,0,7,0,0,0,0),(4,1,0,0,0,1,0,0,1,0,0,0,1,2,2,2,1,1,2.2),(5,2,2,0,0,1,0,2,3,0,0,2,1,0,2,1,2,3,4.4),(6,1,1,0,0,1,0,1,2,0,0,1,1,2,4,1,1,2,4.4),(7,0,0,0,0,0,0,0,0,0,0,0,0,1,4,0,0,0,0),(8,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0),(9,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0),(10,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0),(11,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0),(12,0,1,0,0,1,0,0,0,0,0,1,0,0,1,0,1,1,0),(13,0,1,0,0,0,0,0,0,0,1,0,0,1,2,12,2,6,11),(14,3,14,22,20,0,1,0,7,0,0,0,7,11,4,5,7,30,13.4),(15,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0),(16,14,2,0,8,3,1,2,16,1,0,3,20,13,1,2,22,24,0.68),(18,2,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0),(19,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
/*!40000 ALTER TABLE `mission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mission_success`
--

DROP TABLE IF EXISTS `mission_success`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mission_success` (
  `user_id` int(11) NOT NULL,
  `distance_bronze` int(11) DEFAULT 0,
  `distance_silver` int(11) DEFAULT 0,
  `distance_gold` int(11) DEFAULT 0,
  `plastic_bronze` int(11) DEFAULT 0,
  `plastic_silver` int(11) DEFAULT 0,
  `plastic_gold` int(11) DEFAULT 0,
  `can_bronze` int(11) DEFAULT 0,
  `can_silver` int(11) DEFAULT 0,
  `can_gold` int(11) DEFAULT 0,
  PRIMARY KEY (`user_id`),
  KEY `mission_success_FK` (`user_id`),
  CONSTRAINT `mission_success_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mission_success`
--

LOCK TABLES `mission_success` WRITE;
/*!40000 ALTER TABLE `mission_success` DISABLE KEYS */;
INSERT INTO `mission_success` VALUES (1,1,0,0,0,0,0,1,1,0),(2,0,0,0,0,0,0,0,0,0),(3,0,0,0,0,0,0,0,0,0),(4,0,0,0,0,0,0,0,0,0),(5,0,0,0,0,0,0,0,0,0),(6,0,0,0,0,0,0,0,0,0),(7,0,0,0,0,0,0,0,0,0),(8,0,0,0,0,0,0,0,0,0),(9,0,0,0,0,0,0,0,0,0),(10,0,0,0,0,0,0,0,0,0),(11,0,0,0,0,0,0,0,0,0),(12,0,0,0,0,0,0,0,0,0),(13,1,0,0,0,0,0,0,0,0),(14,1,0,0,1,1,0,1,0,0),(15,0,0,0,0,0,0,0,0,0),(16,0,0,0,0,0,0,1,1,0),(18,0,0,0,0,0,0,0,0,0),(19,0,0,0,0,0,0,0,0,0);
/*!40000 ALTER TABLE `mission_success` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL,
  `score` int(11) DEFAULT 0,
  `profile_path` varchar(500) DEFAULT NULL,
  `article_count` int(11) DEFAULT 0,
  `created_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `follower` int(11) DEFAULT 0,
  `following` int(11) DEFAULT 0,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'hazungzz@gmail.com','하죠이','hazung12',1970,'https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/d4919b67-c6b3-4758-ba46-489eb33446ceprofile.png',0,'2021-08-16 14:57:56',4,6),(2,'ssafy@naver.com','에코천재','ssafy1234',2970,'https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/4bd742d2-9858-4fb8-ab5d-1efa035538f0KakaoTalk_20210816_181156612_02.jpg',0,'2021-08-16 14:59:18',0,6),(3,'ssafy1@naver.com','프로줍깅러','ssafy1234',960,'https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/47942ddc-ecf6-4332-8219-2aade21609d0profile.png',0,'2021-08-16 15:27:45',9,7),(4,'ssafy2@naver.com','yoonyoon','ssafy1234',860,'https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/0249c42f-93ce-434b-a177-55d4b7c384aaprofile.png',0,'2021-08-16 14:59:18',3,2),(5,'ssafy3@naver.com','susu','ssafy1234',1530,'https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/a4e7417c-2a7f-4df6-9fe2-45fb9497b673profile.png',0,'2021-08-16 14:58:04',5,2),(6,'ssafy4@naver.com','콩지민','ssafy1234',320,'https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/0cda667a-3fa3-4711-a780-584c2563a9ccprofile.png',0,'2021-08-16 14:59:58',2,4),(7,'ssafy5@naver.com','구르미','ssafy1234',980,'https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/5a3f5a88-daf3-419b-aefc-6e34c5228246profile.png',0,'2021-08-16 14:58:44',0,4),(8,'ssafy6@naver.com','오늘도난달린다','ssafy1234',1000,'https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/06759765-fdde-4207-95ad-6320c0d91baeprofile.png',0,'2021-08-16 11:06:58',0,2),(9,'ssafy7@naver.com','earthlove','ssafy1234',130,'https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/678cd9a8-00d5-40fd-9adf-db83df8b5f8fprofile.png',0,'2021-08-16 14:59:58',0,2),(10,'ssafy8@naver.com','고먐미집사','ssafy1234',780,'https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/192b5260-3c93-4bb2-8ee8-5172dd5f9dd7profile.png',0,'2021-08-16 14:59:58',0,1),(11,'ssafy9@naver.com','하이하이','ssafy1234',150,'https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/4affdefb-4432-4efd-bf89-2cf064f86074profile.png',0,'2021-08-16 14:59:58',0,1),(12,'ssafy10@naver.com','줍깅2','ssafy1234',600,'https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/1001dd0b-80ad-4d83-82de-d7b140d1f459profile.png',0,'2021-08-16 14:59:58',0,1),(13,'rmlgml@naver.com','다은','ekdmsekdms20',1080,'https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/3a65b81f-c899-48e3-ba4a-d507da054d3fprofile.png',0,'2021-08-16 14:58:23',1,1),(14,'cucu9823@naver.com','선쥬르','a123789456',3160,'https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/4ea58545-d8ef-41aa-8861-c9425e027481profile.png',0,'2021-08-19 04:21:49',16,4),(15,'rmlgml2@naver.com','ekdms','ekdmsekdms20',0,'https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/6b3424d5-d209-4cc6-9778-1ed48a0e56fbuser_default.png',0,'2021-08-16 06:01:17',0,2),(16,'mhi0118@naver.com','기리','octopus1',10759,'https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/100db134-eee8-48c3-8bbf-94407759d553profile.png',0,'2021-08-16 16:18:40',4,1),(18,'lee.sujeong10111@gmail.com','학일이','ssafy1234',0,'https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/6b3424d5-d209-4cc6-9778-1ed48a0e56fbuser_default.png',0,'2021-08-19 05:40:41',0,1),(19,'zerozero7bang@gmail.com','ekdmsekdms','ekdmsekdms20',0,'https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/6b3424d5-d209-4cc6-9778-1ed48a0e56fbuser_default.png',0,'2021-08-19 02:02:08',0,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'jubging'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-19 21:01:38
