CREATE DATABASE  IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mydb`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	5.7.16-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actions`
--

DROP TABLE IF EXISTS `actions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actions` (
  `idActions` int(11) NOT NULL AUTO_INCREMENT,
  `idServicesActions` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `url` varchar(500) NOT NULL,
  PRIMARY KEY (`idActions`),
  UNIQUE KEY `Service_name_Unique` (`idServicesActions`,`name`),
  UNIQUE KEY `url_UNIQUE` (`url`),
  CONSTRAINT `FK_ActionsServices` FOREIGN KEY (`idServicesActions`) REFERENCES `services` (`idServices`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actions`
--

LOCK TABLES `actions` WRITE;
/*!40000 ALTER TABLE `actions` DISABLE KEYS */;
INSERT INTO `actions` VALUES (2,3,'alert','http://127.0.0.1:40000/tick'),(4,3,'shutdown',' 	http://127.0.0.1:40000/shutdown'),(5,7,'shutdown','http://10.2.1.10/shutdown'),(6,8,'shutdown','http://10.2.1.12/shutdown'),(7,9,'shutdown','http://10.2.2.2/shutdown'),(8,13,'shutdown','http://10.2.2.10/shutdown');
/*!40000 ALTER TABLE `actions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conections`
--

DROP TABLE IF EXISTS `conections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conections` (
  `idConnections` int(11) NOT NULL AUTO_INCREMENT,
  `idSignals` int(11) NOT NULL,
  `idActions` int(11) NOT NULL,
  PRIMARY KEY (`idConnections`),
  UNIQUE KEY `idSignals_UNIQUE` (`idSignals`,`idActions`),
  KEY `FK_Actions_idx` (`idActions`),
  KEY `FK_Signals_idx` (`idSignals`),
  CONSTRAINT `FK_Actions` FOREIGN KEY (`idActions`) REFERENCES `actions` (`idActions`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Signals` FOREIGN KEY (`idSignals`) REFERENCES `signals` (`idSignals`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conections`
--

LOCK TABLES `conections` WRITE;
/*!40000 ALTER TABLE `conections` DISABLE KEYS */;
INSERT INTO `conections` VALUES (4,3,2),(6,4,2),(7,11,5),(8,11,6),(9,11,7),(10,11,8);
/*!40000 ALTER TABLE `conections` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `idRoles` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`idRoles`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Admin'),(5,'Service'),(2,'Standard');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `services` (
  `idServices` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(244) DEFAULT NULL,
  `credential` char(32) NOT NULL,
  PRIMARY KEY (`idServices`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `credential_UNIQUE` (`credential`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (2,'ROR 12','Rotative machine #12','ror.12'),(3,'Alarm','A standard alarm','al'),(6,'ROR 10','Rotative Machine #10','ror.10'),(7,'PC 01.10','PC, Office 1, Table 10','pc.01.10'),(8,'PC 01.12','PC, Office 1, Table 12','pc.01.12'),(9,'PC 02.02','PC, Office 2, Table 2','pc.02.02'),(10,'Clock','A Clock','clock'),(11,'ROR 06','Rotative Machine #6','ror.06'),(12,'ROR 15','Rotative Machine #15','ror.15'),(13,'PC 02.10','PC, Office 2, Table 10','pc.02.10'),(14,'S-01','Tests Services','s.01');
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `signals`
--

DROP TABLE IF EXISTS `signals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `signals` (
  `idSignals` int(11) NOT NULL AUTO_INCREMENT,
  `idServicesSignals` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idSignals`),
  UNIQUE KEY `Services_Signale_Unique` (`idServicesSignals`,`name`),
  CONSTRAINT `FK_SignalsServices` FOREIGN KEY (`idServicesSignals`) REFERENCES `services` (`idServices`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `signals`
--

LOCK TABLES `signals` WRITE;
/*!40000 ALTER TABLE `signals` DISABLE KEYS */;
INSERT INTO `signals` VALUES (3,2,'alert'),(4,2,'down'),(5,3,'power.off'),(6,6,'alert'),(7,6,'down'),(10,10,'days.tick'),(9,10,'hours.tick'),(8,10,'minutes.tick'),(11,10,'weekends.tick');
/*!40000 ALTER TABLE `signals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `rolename` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_roles_idx` (`rolename`),
  KEY `fk_user_idx` (`username`),
  CONSTRAINT `fk_roles` FOREIGN KEY (`rolename`) REFERENCES `roles` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user` FOREIGN KEY (`username`) REFERENCES `users` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (4,'service','Service'),(5,'user','Standard'),(6,'admin','Admin');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `idUsers` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `password` char(64) NOT NULL,
  PRIMARY KEY (`idUsers`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'service','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4'),(2,'admin','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4'),(3,'user','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_connections`
--

DROP TABLE IF EXISTS `users_connections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_connections` (
  `idUsers` int(11) NOT NULL,
  `idConnections` int(11) NOT NULL,
  `public` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idUsers`,`idConnections`),
  KEY `FK_Connections_idx` (`idConnections`),
  CONSTRAINT `FK_Connections` FOREIGN KEY (`idConnections`) REFERENCES `conections` (`idConnections`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Users` FOREIGN KEY (`idUsers`) REFERENCES `users` (`idUsers`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_connections`
--

LOCK TABLES `users_connections` WRITE;
/*!40000 ALTER TABLE `users_connections` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_connections` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-26 10:10:21
