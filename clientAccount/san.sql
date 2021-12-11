-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: san
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `IDclient` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(50) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `height` int unsigned DEFAULT NULL,
  `weight` int unsigned DEFAULT NULL,
  `bloodtype` int unsigned NOT NULL,
  `rezus` char(1) NOT NULL,
  `illness` varchar(50) NOT NULL,
  PRIMARY KEY (`IDclient`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'John Smith','151 Stone, Hannover','2011-06-29','M',191,80,1,'P','Leukamia'),(2,'Maya Christensen','121 Street, Helsinki','2020-12-12','F',167,60,4,'N','Bronchitis'),(3,'Ebrahim Mitchell','111 Strasse, Berlin','1984-04-13','M',170,70,2,'N','Bulimia'),(4,'Alanna Bernard','161 Street, Denver','1996-08-13','F',168,50,1,'P','Chets pain'),(5,'Martine Francis','171 Avenue, New York','1945-08-18','M',181,79,4,'P','Pancreatitis'),(6,'Danial Blevins','78 Street, Stockholm','1985-09-11','M',179,75,3,'P','Common cold'),(7,'Taliah Harding','46 Avenue, Dubai','1969-10-12','M',175,77,1,'N','Cough'),(8,'Ephraim Sadler','33 Street, Baku','1999-12-19','M',178,74,2,'P','Depression'),(9,'Tom King','89 Stone, London','1977-03-15','M',195,85,3,'P','Diabetes'),(10,'Alissia Robbins','79 Strasse, Viena','1991-01-02','F',170,65,1,'P','Diarrhoea');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_login`
--

DROP TABLE IF EXISTS `client_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client_login` (
  `ClientID` int DEFAULT NULL,
  `Login` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  UNIQUE KEY `Login` (`Login`),
  KEY `fk_clientIDlogin` (`ClientID`),
  CONSTRAINT `fk_clientIDlogin` FOREIGN KEY (`ClientID`) REFERENCES `client` (`IDclient`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_login`
--

LOCK TABLES `client_login` WRITE;
/*!40000 ALTER TABLE `client_login` DISABLE KEYS */;
INSERT INTO `client_login` VALUES (4,'alanna','bernard'),(10,'alissia','robbins'),(6,'danial','blevins'),(3,'ebrahim','mitchell'),(8,'ephraim','sadler'),(1,'john','smith'),(5,'martine','francis'),(2,'maya','christensen'),(7,'taliah','harding'),(9,'tom','king');
/*!40000 ALTER TABLE `client_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procedures`
--

DROP TABLE IF EXISTS `procedures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `procedures` (
  `ProcedureID` int NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Price` int NOT NULL,
  `Start_at` time DEFAULT NULL,
  `End_at` time DEFAULT NULL,
  `Schedule` int NOT NULL,
  PRIMARY KEY (`ProcedureID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procedures`
--

LOCK TABLES `procedures` WRITE;
/*!40000 ALTER TABLE `procedures` DISABLE KEYS */;
INSERT INTO `procedures` VALUES (123,'Acupuncture',150,'08:00:00','09:00:00',21),(124,'Herbal medicine',120,'10:00:00','10:30:00',63),(125,'Phototherapy',110,'10:30:00','12:00:00',42),(126,'Physiotherapy',120,'13:00:00','14:00:00',17),(127,'Massage',140,'14:00:00','15:00:00',96),(128,'Mud therapy',130,'15:00:00','16:00:00',10),(129,'Hydromassage',150,'18:00:00','19:00:00',96),(130,'Baths',100,'19:00:00','21:00:00',127);
/*!40000 ALTER TABLE `procedures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase` (
  `PurchaseID` int NOT NULL AUTO_INCREMENT,
  `ClientID` int NOT NULL,
  `ProcedureID` int NOT NULL,
  `Date` date DEFAULT NULL,
  `Time` time DEFAULT NULL,
  `procedure_taken` tinyint DEFAULT NULL,
  PRIMARY KEY (`PurchaseID`),
  UNIQUE KEY `TreatmentID_UNIQUE` (`PurchaseID`),
  KEY `fk_procedure` (`ProcedureID`),
  KEY `fk_client_purchase` (`ClientID`),
  CONSTRAINT `fk_client_purchase` FOREIGN KEY (`ClientID`) REFERENCES `client` (`IDclient`),
  CONSTRAINT `fk_proc_purchase` FOREIGN KEY (`ProcedureID`) REFERENCES `procedures` (`ProcedureID`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (101,1,123,'2021-10-20','08:05:00',1),(102,3,123,'2021-10-21','08:10:00',1),(103,6,123,'2021-10-22','08:15:00',1),(104,2,124,'2021-10-22','10:01:00',1),(105,3,124,'2021-10-23','10:15:00',1),(106,5,124,'2021-10-28','10:20:00',1),(107,7,124,'2021-10-29','10:27:00',1),(108,9,124,'2021-10-29','10:08:00',1),(109,10,124,'2021-10-29','10:10:00',1),(110,5,125,'2021-10-29','11:00:00',1),(111,1,126,'2021-10-30','13:30:00',1),(112,2,126,'2021-10-30','13:45:00',1),(113,7,126,'2021-10-31','13:07:00',1),(114,9,126,'2021-10-31','13:50:00',1),(115,4,127,'2021-11-01','14:20:00',1),(116,8,127,'2021-11-02','14:15:00',1),(117,6,128,'2021-11-02','15:50:00',1),(118,8,128,'2021-11-02','08:00:00',1),(119,4,129,'2021-11-02','18:32:00',1),(120,10,129,'2021-11-02','18:05:00',1),(121,2,130,'2021-11-02','18:00:00',1),(122,4,130,'2021-11-02','20:08:00',1),(123,8,130,'2021-11-03','20:30:00',1),(124,10,130,'2021-11-04','19:20:00',1),(125,1,127,'2021-11-01','09:00:00',0),(127,2,123,'2021-11-09','22:20:00',0),(128,3,126,'2021-11-09','22:28:00',0),(129,4,123,'2021-11-10','00:30:00',0),(130,4,123,'2021-11-10','00:30:00',0),(131,5,124,'2021-11-10','00:31:00',0),(132,10,127,'2021-11-10','01:14:00',0),(133,9,129,'2021-11-10','01:29:00',0),(134,7,130,'2021-11-10','13:58:00',0),(135,6,130,'2021-11-11','12:40:00',0),(136,8,130,'2021-11-11','13:27:00',0);
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserved`
--

DROP TABLE IF EXISTS `reserved`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserved` (
  `ReservationN` int NOT NULL,
  `ClientID` int DEFAULT NULL,
  `RoomID` int NOT NULL,
  `Arrived` date DEFAULT NULL,
  `Moved_out` date DEFAULT NULL,
  PRIMARY KEY (`ReservationN`),
  KEY `fk_roomID` (`RoomID`),
  KEY `fk_clientID` (`ClientID`),
  CONSTRAINT `fk_clientID` FOREIGN KEY (`ClientID`) REFERENCES `client` (`IDclient`),
  CONSTRAINT `fk_roomID` FOREIGN KEY (`RoomID`) REFERENCES `room` (`RoomID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserved`
--

LOCK TABLES `reserved` WRITE;
/*!40000 ALTER TABLE `reserved` DISABLE KEYS */;
INSERT INTO `reserved` VALUES (1,1,101,'2021-11-01',NULL),(2,3,101,'2021-11-02',NULL),(3,2,107,'2021-10-31',NULL),(4,4,105,'2021-11-03',NULL),(5,10,105,'2021-11-03',NULL),(6,5,102,'2021-10-01',NULL),(7,6,102,'2021-10-19',NULL),(8,8,103,'2021-10-27',NULL),(9,7,109,'2021-11-07',NULL),(10,9,109,'2021-10-17',NULL);
/*!40000 ALTER TABLE `reserved` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `RoomID` int NOT NULL,
  `Persons` int DEFAULT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `Available_places` int DEFAULT NULL,
  PRIMARY KEY (`RoomID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (101,2,'standart',0),(102,2,'standart',0),(103,1,'standart',0),(104,1,'standart',1),(105,2,'deluxe',0),(106,2,'deluxe',2),(107,1,'deluxe',0),(108,1,'deluxe',1),(109,3,'standart',2),(110,3,'standart',3);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treatment`
--

DROP TABLE IF EXISTS `treatment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `treatment` (
  `TreatmentID` int NOT NULL AUTO_INCREMENT,
  `ClientID` int DEFAULT NULL,
  `ProcedureID` int NOT NULL,
  `Date` date DEFAULT NULL,
  `Time` time DEFAULT NULL,
  PRIMARY KEY (`TreatmentID`),
  UNIQUE KEY `TreatmentID_UNIQUE` (`TreatmentID`),
  KEY `fk_procedure` (`ProcedureID`),
  KEY `fk_client` (`ClientID`),
  CONSTRAINT `fk_client` FOREIGN KEY (`ClientID`) REFERENCES `client` (`IDclient`),
  CONSTRAINT `fk_procedure` FOREIGN KEY (`ProcedureID`) REFERENCES `procedures` (`ProcedureID`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treatment`
--

LOCK TABLES `treatment` WRITE;
/*!40000 ALTER TABLE `treatment` DISABLE KEYS */;
INSERT INTO `treatment` VALUES (101,1,123,'2021-11-01','08:05:00'),(102,3,123,'2021-11-01','08:10:00'),(103,6,123,'2021-11-01','08:15:00'),(104,2,124,'2021-11-01','10:01:00'),(105,3,124,'2021-11-02','10:15:00'),(106,5,124,'2021-11-02','10:20:00'),(107,7,124,'2021-11-02','10:27:00'),(108,9,124,'2021-11-02','10:08:00'),(109,10,124,'2021-11-02','10:10:00'),(110,5,125,'2021-11-02','11:00:00'),(111,1,126,'2021-11-05','13:30:00'),(112,2,126,'2021-11-05','13:45:00'),(113,7,126,'2021-11-01','13:07:00'),(114,9,126,'2021-11-05','13:50:00'),(115,4,127,'2021-11-06','14:20:00'),(116,8,127,'2021-11-07','14:15:00'),(117,6,128,'2021-11-04','15:50:00'),(118,8,128,'2021-11-02','15:03:00'),(119,4,129,'2021-11-06','18:32:00'),(120,10,129,'2021-11-07','18:05:00'),(121,2,130,'2021-11-02','19:28:00'),(122,4,130,'2021-11-03','20:08:00'),(123,8,130,'2021-11-04','20:30:00'),(124,10,130,'2021-11-05','19:20:00'),(125,1,124,'2021-11-06','20:05:00');
/*!40000 ALTER TABLE `treatment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `worker`
--

DROP TABLE IF EXISTS `worker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `worker` (
  `WorkerID` char(3) NOT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `Login` varchar(50) DEFAULT NULL,
  `Password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`WorkerID`),
  UNIQUE KEY `Login` (`Login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worker`
--

LOCK TABLES `worker` WRITE;
/*!40000 ALTER TABLE `worker` DISABLE KEYS */;
INSERT INTO `worker` VALUES ('001','Adis','youha','12345678'),('002','Bekzhan','bekzhansan','87654321');
/*!40000 ALTER TABLE `worker` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-11 19:36:30
