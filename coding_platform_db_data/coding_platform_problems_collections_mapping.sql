-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: coding_platform
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `problems_collections_mapping`
--

DROP TABLE IF EXISTS `problems_collections_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `problems_collections_mapping` (
  `pc_id` varchar(255) NOT NULL,
  `collection_id` varchar(255) NOT NULL,
  `problem_id` varchar(255) NOT NULL,
  PRIMARY KEY (`pc_id`),
  KEY `FKsrf9b0olm2u34e8x26l668h3e` (`problem_id`),
  KEY `FKnm02466ueu3slvtafn9lman03` (`collection_id`),
  CONSTRAINT `FKnm02466ueu3slvtafn9lman03` FOREIGN KEY (`collection_id`) REFERENCES `problem_collections` (`collection_id`),
  CONSTRAINT `FKsrf9b0olm2u34e8x26l668h3e` FOREIGN KEY (`problem_id`) REFERENCES `problems` (`problem_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `problems_collections_mapping`
--

LOCK TABLES `problems_collections_mapping` WRITE;
/*!40000 ALTER TABLE `problems_collections_mapping` DISABLE KEYS */;
INSERT INTO `problems_collections_mapping` VALUES ('065d3d5c-2f91-4852-85eb-1b6cc2af9143','s6b2e19d-9c0c-48ea-aa61-9za636f7a953','5b39f36a-9623-423c-9205-2e909f47f702'),('182f3c92-3cbe-4a80-a712-8ae8b3c7e9fc','d5a0d17d-9c0c-48ea-aa61-5ca636f7a883','5b39f36a-9623-423c-9205-2e909f47f702'),('33314bec-1b54-4fb0-a1d0-69d9973e89db','s6b2e19d-9c0c-48ea-aa61-9za636f7a953','573600a6-baaf-40c4-a329-0d7580a11f20'),('3a1e485a-f145-4788-8852-2b6ae4d83b99','d5a0d17d-9c0c-48ea-aa61-5ca636f7a883','b0651c66-34dc-443f-808e-71d7929dedce'),('3d89b4eb-f58a-4d89-b36f-e3ed4515d4eb','d5a0d17d-9c0c-48ea-aa61-5ca636f7a883','f0cf4010-023f-4abe-865c-26daacf81729'),('57d53094-b4cc-4bde-a08b-0cca33ac9eb6','d5a0d17d-9c0c-48ea-aa61-5ca636f7a883','573600a6-baaf-40c4-a329-0d7580a11f20'),('a1f1103f-04c6-4c62-9d75-aa7274e61cb7','s6b2e19d-9c0c-48ea-aa61-9za636f7a953','b0651c66-34dc-443f-808e-71d7929dedce'),('b7276e19-5a46-468b-b8ff-d6352717c0f3','s6b2e19d-9c0c-48ea-aa61-9za636f7a953','f0cf4010-023f-4abe-865c-26daacf81729'),('c84f7f21-52c5-4ab0-95ef-fd74a7f01e4d','s6b2e19d-9c0c-48ea-aa61-9za636f7a953','f8853914-8cac-4975-b198-780eed531dfa'),('d5a0d17d-9c0c-48ea-aa61-5ca636f7a883','s6b2e19d-9c0c-48ea-aa61-9za636f7a953','b171dac1-b9ab-424a-9529-89a350d76716'),('d96f0517-3681-4c91-819e-7b9f227cccd4','s6b2e19d-9c0c-48ea-aa61-9za636f7a953','263b6598-1fbe-4e46-80df-9fee1a0e684f'),('e1ac803d-87f2-4cd5-966f-44faf320cd86','d5a0d17d-9c0c-48ea-aa61-5ca636f7a883','f8853914-8cac-4975-b198-780eed531dfa'),('eab234f2-6374-492f-8f4c-4e987afbf85c','s6b2e19d-9c0c-48ea-aa61-9za636f7a953','99cb8a25-00e9-4a3c-b829-40b5c2c655be');
/*!40000 ALTER TABLE `problems_collections_mapping` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-28 23:22:34
