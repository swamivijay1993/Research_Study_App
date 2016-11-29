-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: nbad
-- ------------------------------------------------------
-- Server version	5.5.48

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
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `StudyID` int(20) NOT NULL,
  `QuestionID` int(20) NOT NULL,
  `UserName` varchar(40) NOT NULL DEFAULT '',
  `Choice` varchar(40) DEFAULT NULL,
  `DateSubmitted` date DEFAULT NULL,
  PRIMARY KEY (`StudyID`,`QuestionID`,`UserName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (1,1,'user@gmail.com','6','2016-03-31'),(4,3,'user@gmail.com','4','2016-04-02');
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `QuestionID` int(20) NOT NULL AUTO_INCREMENT,
  `StudyID` int(20) DEFAULT NULL,
  `Question` varchar(50) DEFAULT NULL,
  `AnswerType` varchar(10) DEFAULT NULL,
  `Option1` varchar(40) DEFAULT NULL,
  `Option2` varchar(40) DEFAULT NULL,
  `Option3` varchar(40) DEFAULT NULL,
  `Option4` varchar(40) DEFAULT NULL,
  `Option5` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`QuestionID`),
  KEY `StudyID` (`StudyID`),
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`StudyID`) REFERENCES `study` (`StudyID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,1,'how common is computers in education','text','3','4','5','6','7'),(3,4,'Industrialization and Deforestation are related','text','3','4','5','6',NULL),(4,5,'Is Computer Graphics Used Everywhere','text','3','4','5',NULL,NULL),(5,6,'All kind of trees are useful','text','3','4','5',NULL,NULL),(7,8,'testing','text','test1','TEST2','test3',NULL,NULL),(8,9,'this is a test','text','3','4','5',NULL,NULL),(9,10,'check','text','3','4','5',NULL,NULL),(10,11,'asd','text','dasf','fdas','dfg',NULL,NULL),(11,12,'check','text','1','3','3',NULL,NULL);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reported`
--

DROP TABLE IF EXISTS `reported`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reported` (
  `QuestionID` int(20) NOT NULL,
  `StudyID` int(20) NOT NULL,
  `Date` date DEFAULT NULL,
  `NumParticipants` int(15) DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL,
  `ReporterEmail` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`StudyID`,`QuestionID`),
  KEY `QuestionID` (`QuestionID`),
  CONSTRAINT `reported_ibfk_1` FOREIGN KEY (`QuestionID`) REFERENCES `question` (`QuestionID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reported_ibfk_2` FOREIGN KEY (`StudyID`) REFERENCES `study` (`StudyID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reported`
--

LOCK TABLES `reported` WRITE;
/*!40000 ALTER TABLE `reported` DISABLE KEYS */;
INSERT INTO `reported` VALUES (1,1,'2016-04-01',4,'Approved','user@gmail.com'),(3,4,'2016-04-01',4,'Under Review','user@gmail.com');
/*!40000 ALTER TABLE `reported` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `study`
--

DROP TABLE IF EXISTS `study`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `study` (
  `StudyID` int(20) NOT NULL AUTO_INCREMENT,
  `StudyName` varchar(40) DEFAULT NULL,
  `Description` varchar(50) DEFAULT NULL,
  `Username` varchar(50) DEFAULT NULL,
  `DateCreated` date DEFAULT NULL,
  `ImageURL` varchar(50) DEFAULT NULL,
  `ReqParticipants` int(15) DEFAULT NULL,
  `ActParticipants` int(15) DEFAULT '0',
  `SStatus` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`StudyID`),
  KEY `Username` (`Username`),
  CONSTRAINT `study_ibfk_1` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study`
--

LOCK TABLES `study` WRITE;
/*!40000 ALTER TABLE `study` DISABLE KEYS */;
INSERT INTO `study` VALUES (1,'Computer','This is a computer science research','swami@gmail.com','2016-03-26','computer.jpg',4,1,'start'),(4,'Environment','This is an environmental study','swami@gmail.com','2016-03-26','small_tree.jpg',4,1,'start'),(5,'Graphics','It is related to computers','swami@gmail.com','2016-03-26','computer.jpg',5,0,'start'),(6,'Trees','This is relevant to trees.','swami@gmail.com','2016-03-26','small_tree.jpg',4,0,'start'),(8,'testing','description','user@gmail.com','2016-04-02','computer.jpg',3,0,'start'),(9,'teest','testing','swami@gmail.com','2016-04-02','computer.jpg',3,0,'stop'),(10,'check','testing','swami@gmail.com','2016-04-02','computer.jpg',3,0,'start'),(11,'check','fsdf','swami@gmail.com','2016-04-02','computer.jpg',3,0,'start'),(12,'check4','SDF','swami@gmail.com','2016-04-02','computer.jpg',2,0,'start');
/*!40000 ALTER TABLE `study` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `Username` varchar(50) NOT NULL DEFAULT '',
  `Password` varchar(50) DEFAULT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `Studies` int(15) DEFAULT NULL,
  `Participation` int(15) DEFAULT NULL,
  `Coins` int(15) DEFAULT NULL,
  `Name` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('abi@gmail.com','5f4dcc3b5aa765d61d8327deb882cf99','participant',0,0,0,'abhishek'),('admin@gmail.com','21232f297a57a5a743894a0e4a801fc3','admin',0,0,0,'Admin'),('swami@gmail.com','dbb39a13b9e306a22d4f0151b0fa6b9e','participant',8,0,0,'Swami'),('user@gmail.com','5f4dcc3b5aa765d61d8327deb882cf99','participant',1,2,2,'User');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'nbad'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-02 16:25:39
