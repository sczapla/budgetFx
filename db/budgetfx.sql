CREATE DATABASE  IF NOT EXISTS `budgetfx` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci */;
USE `budgetfx`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: budgetfx
-- ------------------------------------------------------
-- Server version	5.7.15-log

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
-- Table structure for table `resource_category`
--

DROP TABLE IF EXISTS `resource_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 NOT NULL,
  `description` varchar(256) DEFAULT NULL,
  `user_name` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `transaction_type` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_expense_type_user_idx` (`user_name`),
  CONSTRAINT `fk_expense_type_user` FOREIGN KEY (`user_name`) REFERENCES `users` (`user_name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_category`
--

LOCK TABLES `resource_category` WRITE;
/*!40000 ALTER TABLE `resource_category` DISABLE KEYS */;
INSERT INTO `resource_category` VALUES (1,'Żywność','Wydatki na jedzenie','admin',0),(2,'Zdrowie','Wydatki na leczenie','admin',0),(3,'Wypłata','zarobek','admin',1),(4,'Rozrywka','Na rozrywke','admin',0),(5,'Spadek','spadek','admin',1);
/*!40000 ALTER TABLE `resource_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resources`
--

DROP TABLE IF EXISTS `resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_polish_ci NOT NULL,
  `user_name` varchar(45) CHARACTER SET utf8 NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `date` date NOT NULL,
  `resource_category_id` int(11) NOT NULL,
  `description` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_incomes_name_idx` (`description`),
  KEY `fk_resource_user` (`user_name`),
  KEY `fk_resource_type` (`resource_category_id`),
  CONSTRAINT `fk_resource_type` FOREIGN KEY (`resource_category_id`) REFERENCES `resource_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_resource_user` FOREIGN KEY (`user_name`) REFERENCES `users` (`user_name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resources`
--

LOCK TABLES `resources` WRITE;
/*!40000 ALTER TABLE `resources` DISABLE KEYS */;
INSERT INTO `resources` VALUES (1,'Kamsoft','admin',1000.00,'2016-07-01',3,'opis wpływu'),(2,'sklep1','admin',200.98,'2016-07-03',1,'opis'),(3,'sklep2','admin',123.33,'2016-07-14',2,'apos'),(4,'sklep3','admin',123.33,'2016-07-14',2,'apos'),(5,'sklep4','admin',123.33,'2016-07-14',4,'apos'),(6,'firma','admin',123.33,'2016-07-14',5,'apos'),(7,'moj opis','admin',333.66,'2016-07-21',3,NULL);
/*!40000 ALTER TABLE `resources` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `user_name` varchar(45) CHARACTER SET utf8 NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`user_name`,`role`),
  UNIQUE KEY `uni_username_role` (`role`,`user_name`),
  KEY `fk_username_idx` (`user_name`),
  CONSTRAINT `fk_user_roles_user` FOREIGN KEY (`user_name`) REFERENCES `users` (`user_name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES ('admin','ROLE_ADMIN'),('admin','ROLE_USER'),('asd','ROLE_USER'),('tomek','ROLE_USER'),('ttt','ROLE_USER');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_name` varchar(45) CHARACTER SET utf8 NOT NULL,
  `password` varchar(128) NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  `version` int(11) NOT NULL,
  `email` varchar(64) NOT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin','$2a$06$5vb9I9I22SVWLenhS5V69OpfVnANlC0WsWuIq9gFFTlUUKULuK9Om',1,0,''),('asd','$2a$10$S4rQy/YqaT7Dy.GornMMUOXV4o522fihX/3QdfB1nPxVJPupaj9.W',1,0,'asd'),('tomek','$2a$06$c9SQGKjdBD4.ZOVi2N1USukDKuwN2SvvJ2esB.zdu3O3ux2d3um42',1,0,''),('ttt','$2a$10$dD7KvoqN2.dwy6ipZIM.O.VQ/F1gQCur3zvZqYztvMNGDwNdvGTKy',1,0,'ttt@ttt');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-03 22:15:24
