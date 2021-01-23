-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: eobrazovanje5
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Dumping data for table `admin`
--

--
-- Dumping data for table `registracija_zahtev`
--

LOCK TABLES `registracija_zahtev` WRITE;
/*!40000 ALTER TABLE `registracija_zahtev` DISABLE KEYS */;
INSERT INTO `registracija_zahtev` VALUES (2,'Krilova 9','SF-17-2019','069123321','Jasmin','1234112341123',_binary '\0','$2a$10$ltGWumfvBdua.UVoSqyA8eB8c/TajAtylt/Q1/BTPvGFTxuZ9iEla','Jasminovic','STUDENT',0,'44322-123-11','jasmin'),(3,'Mise Dimitrijevica 23','SF-18-2019','069123333','Neven','1234112341129',_binary '\0','$2a$10$V/CyzkQ5o9bkwqnmmDfLuunmzCoR8GGZu/jNs9Ws3cRUpO7sYCnJa','Nevenic','STUDENT',0,'44322-123-99','neven'),(4,'Mise Dimitrijevica 67','SF-19-2019','069123336','Milan','1234112342229',_binary '\0','$2a$10$EAY.ijY.OiG3YapSqP2kb.Vnsg/8u1bKygaD7drgYGrlW6iWRJoj.','Milanic','STUDENT',0,'44312-123-91','milan'),(5,'Branislava Nusica 9','SF-20-2019','069123334','Marko','1235192342229',_binary '\0','$2a$10$fWqJ6enPSVPF1Wt8.RGrmeAQ9aFjOdyVaeCJ8VE8wQ5tdklds67aW','Markovic','STUDENT',0,'44112-123-91','marko');
/*!40000 ALTER TABLE `registracija_zahtev` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'Gagarinova 22','Branimir','3424423444121','$2a$10$k3Ff7motUSPhCXP3j4Qege9zx7ziZiELjz4Vv3U0AfMUNGSUOe8NK','Peric','../../assets/student.png','NASTAVNIK','Branimir'),(4,'Adminovska 43','Admin','1234512345124','$2a$10$INil3D7UdclonE/L3P.OUevRLho/QMXjHX5A9Rm2NqChH9c/RM1e6','Adminovic','../../assets/student.png','ADMINISTRATOR','Admin'),(6,'Hilandarska 23','Dragan','1234512345777','$2a$10$Ahb/59f170FeWekr/h7NjeNZnyHL/BxFQ6xGH2TGloqLaD2fyAf7S','Dragic','../../assets/student.png','STUDENT','Dragan'),(7,'Krilova 3','Zoran','1234512345111','$2a$10$d2utu.tLMfT/r6hH8EhuH.52PLMtGtLrIE1Zi5sc6CT8p0FiDtV3e','Zoric','../../assets/student.png','STUDENT','Zoran'),(8,'Petra Drapsina 63','Goran','1234512345222','$2a$10$D3E5WNjkOt4vZ8W9LxrCTuzbmznM2IL5W5wWD22C7eqH7piXp55ri','Goric','../../assets/student.png','STUDENT','Goran'),(9,'Novopazarska 99','Dragana','1234512345322','$2a$10$F0Iiod8O8IJAdxqAu5wdeeoY9WT8fYX6g18n2sMwZH5uloZM8jPGm','Dragic','../../assets/student.png','STUDENT','Dragana'),(10,'Danila Kisa 22','Zorana','1234512345999','$2a$10$fvLNPDqP7iHgfxV0k.K5PuHolYorGOYDVOEW8IIaD3NWvxW/C08hW','Zoric','../../assets/student.png','STUDENT','Zorana'),(11,'Majevicka 35','Gordana','1234512345222','$2a$10$aohmdx0k13uBnaLMVtMhFOB.GmmEbQJD48by5TN4E1ZZKjXD17epO','Goric','../../assets/student.png','STUDENT','Gordana'),(12,'Mitrovicka 29','Sara','1234512345890','$2a$10$a/x.Z5HBo3BTwkpO28sfQupg3758Mg3SKAIWQSkRVG2TPns0hy1K.','Sarcic','../../assets/student.png','STUDENT','Sara'),(13,'Jurija Gagarina 95','Mihajlo','1234512345998','$2a$10$b5L2nlvUBhSkE82x5dbfMO0M3MRm6f9H2ea4ZihnmCVmpTUE4qZhS','Mihic','../../assets/student.png','STUDENT','Mihajlo'),(14,'Petrogradska 98','Danilo','1234512345643','$2a$10$qo4JHdL8TE9ajAdH3flqlOk2IlxXN68bqOjf6jycvF4PpXZmvNfWK','Danilovic','../../assets/student.png','STUDENT','Danilo'),(15,'Gagarinova 33','Kesnija','1234512345554','$2a$10$NdWZ6BL5zpF3WZDgtjhHXOUstqiG2cZ.rQmhYkJEzJ3thgYkv6.O2','Lovric','../../assets/student.png','STUDENT','Ksenija'),(16,'Brace Ribnikar 92','Djordje','2134512345123','$2a$10$tGujjcMYLQ2XLnkbly4oIea46Q72eLkKB7zacMTAnjHeNlabTSWKG','Djordjevic','../../assets/student.png','STUDENT','Djordje'),(17,'Zeleznicka 45','Lidija','1234543211231','$2a$10$rniHK/dlIrmpdLyby9DJOuKZICVjrGys6YSMz/MUjXf.zrClnJlfm','Prokic','../../assets/student.png','STUDENT','Lidija'),(18,'Dunavksa 98','Zeljana','2345604322332','$2a$10$fUnhZ2Yr5xWpm5rHze8BG.KjIZAeidGksoAW2Ge6EJkU.3weof6x6','Zivkovic','../../assets/student.png','STUDENT','Zeljana'),(19,'Kisacka 34','Nebojsa','2134512354342','$2a$10$aX8JovpT1uaiQYYk80eS..pLARlMg/EdRpy1a2FbwVvLhaurmsFga','Nastasic','../../assets/student.png','STUDENT','Nebojsa'),(20,'Tremerinska 78','Jeremija','4535651234212','$2a$10$4.zaloFVXZ2ax0cBXxYiz.i7OES59y2SSq4YC02s6.S/Rw8efDBFC','Krstic ','../../assets/student.png','STUDENT','Jeremija'),(21,'Beogradska 23','Jozef','1234421345123','$2a$10$azOi3sZ2xgaciN.FjbIL.uhs8j.21f47hLbqWTnbJM45F.EoR33Mm','Broz','../../assets/student.png','STUDENT','Jozef'),(22,'Krilova 2','Pero','1234512345332','$2a$10$Yrf9Ag9m6yqXOOeppBpwOuk5I9LVvI444oDGIVlENTxt4R4X5lSZe','Peric','../../assets/student.png','NASTAVNIK','Pero'),(23,'Zeleznicka 24','Djuro','5432123456234','$2a$10$i3Qwo/tz3UsJWctdR.WpAeHVwYltaI5/Csfdi8NUs93n2hG5ThYKK','Djuric','../../assets/student.png','NASTAVNIK','Djuro'),(24,'Gogoljeva 67','Danijel','4532243256123','$2a$10$oCzJYcZd4ihnD9atT9vRPedrtyMlm1Hn4pI2YcTvEZZPTqqwv6daG','Danilovic','../../assets/student.png','NASTAVNIK','Danijel'),(25,'Temerinska 54','Petar','3245561324234','$2a$10$tCtt6ZtAaeGI9IW1y24wLec5ufeUTesZ11MKFVwV4sb2yz5HkGEvy','Petrovic','../../assets/student.png','NASTAVNIK','Petar'),(26,'Profesora Profesorovica 35','Profesor','1234512345555','$2a$10$UX1Yn.46PsioCelNzXv1PuR0U/Y3/F5z4K6gHivGBJUnSo6yVJPB6','Profesorovic','../../assets/student.png','NASTAVNIK','profa'),(27,'Petra Drpasina 22','Bojan','1235511234534','$2a$10$CSTYSvKhn2y4tRpGt4JdxOAAttIUETj8VeFoWOBxrZEKBbF1rdpFe','Bojovic','../../assets/student.png','NASTAVNIK','Bojan'),(28,'Maksima Gorkog 55','Stevo','4532454352123','$2a$10$YZDw0rLtuGHi4zxMya3be./66z9aceQsDBml6WUCZM7vHkBwGyPeW','Stevic','../../assets/student.png','STUDENT','Stevo');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;


LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (4);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `nastavnik`
--

LOCK TABLES `nastavnik` WRITE;
/*!40000 ALTER TABLE `nastavnik` DISABLE KEYS */;
INSERT INTO `nastavnik` VALUES (2),(22),(23),(24),(25),(26),(27);
/*!40000 ALTER TABLE `nastavnik` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `predmet`
--

LOCK TABLES `predmet` WRITE;
/*!40000 ALTER TABLE `predmet` DISABLE KEYS */;
INSERT INTO `predmet` VALUES (1,9,'Osnove Programiranja','OP'),(2,9,'Uvod u Objektno Programiranje','OOP'),(3,8,'Osnove Web Programiranja','OWP'),(4,8,'Osnove Softverskih Arhitektura','OSA');
/*!40000 ALTER TABLE `predmet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('SF-1-2019','069123322',800,'12345678912',6),('SF-2-2019','069123323',1000,'12345678911',7),('SF-17-2019','069123324',800,'12345678913',8),('SF-4-2019','069123325',1000,'12345678914',9),('SF-5-2019','069123326',800,'12345678915',10),('SF-6-2019','069123327',800,'12345678916',11),('SF-7-2019','069123328',1000,'12345678917',12),('SF-8-2019','069123329',1000,'12345678918',13),('SF-9-2019','069123311',800,'12345678919',14),('Sf-11-2019','069123312',1000,'12345678921',15),('SF-12-2019','069123313',1000,'12345678922',16),('SF-10-2019','069123314',1000,'12345678923',17),('Sf-13-2019','0691233215',1000,'12345678924',18),('SF-14-2019','069123316',1000,'12345678926',19),('SF-15-2019','069123317',1000,'12345678927',20),('SF-16-2019','069123319',1000,'12345678928',21),('Sf-43-2019','069123217',7800,'12345678929',28);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;




--
-- Dumping data for table `dokument`
--

LOCK TABLES `dokument` WRITE;
/*!40000 ALTER TABLE `dokument` DISABLE KEYS */;
INSERT INTO `dokument` VALUES (1,'2021-01-18 15:58:19.950000','http://localhost:8080/studenti/documents/Stevo/Pdf%20Fajl%201.pdf','Pdf Fajl 1',28),(2,'2021-01-18 15:58:29.290000','http://localhost:8080/studenti/documents/Stevo/Pdf%20Fajl%202.pdf','Pdf Fajl 2',28),(3,'2021-01-18 15:58:46.797000','http://localhost:8080/studenti/documents/Stevo/Img%20Fajl.jpg','Img Fajl',28);
/*!40000 ALTER TABLE `dokument` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ispit`
--

LOCK TABLES `ispit` WRITE;
/*!40000 ALTER TABLE `ispit` DISABLE KEYS */;
INSERT INTO `ispit` VALUES (1,'2021-03-20 01:00:00.000000','2021-03-05 01:00:00.000000',25,50,23,1),(2,'2021-03-23 01:00:00.000000','2021-03-05 01:00:00.000000',25,50,2,2),(3,'2021-03-20 01:00:00.000000','2021-03-01 01:00:00.000000',25,50,24,3),(4,'2021-02-28 01:00:00.000000','2021-01-25 01:00:00.000000',25,50,26,4);
/*!40000 ALTER TABLE `ispit` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `nastavnik_predmeti`
--

LOCK TABLES `nastavnik_predmeti` WRITE;
/*!40000 ALTER TABLE `nastavnik_predmeti` DISABLE KEYS */;
INSERT INTO `nastavnik_predmeti` VALUES (23,1),(25,2),(22,3),(25,4),(26,4);
/*!40000 ALTER TABLE `nastavnik_predmeti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `predispitne_obaveze`
--

LOCK TABLES `predispitne_obaveze` WRITE;
/*!40000 ALTER TABLE `predispitne_obaveze` DISABLE KEYS */;
INSERT INTO `predispitne_obaveze` VALUES (1,10,'Kontrolna Tacka 1 ',20,1),(2,10,'Kontrolna Tacka 2',20,1),(3,10,'Kontrolna Tacka 1',20,2),(4,10,'Kontrolna Tacka 2',20,2),(5,10,'Kontrolna Tacka 1',20,3),(6,10,'Kontrolna Tacka 2',20,3),(7,10,'Kontrolna Tacka 1',20,4),(8,10,'Kontrolna Tacka 2',20,4);
/*!40000 ALTER TABLE `predispitne_obaveze` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `predispitne_obaveze_polaganje`
--

LOCK TABLES `predispitne_obaveze_polaganje` WRITE;
/*!40000 ALTER TABLE `predispitne_obaveze_polaganje` DISABLE KEYS */;
INSERT INTO `predispitne_obaveze_polaganje` VALUES (1,'2020-09-09 00:00:00.000000',11,_binary '',1,1,6),(2,'2020-10-10 00:00:00.000000',15,_binary '',1,2,6),(3,'2020-09-09 00:00:00.000000',14,_binary '',2,3,6),(4,'2020-09-09 00:00:00.000000',14,_binary '',2,4,6),(5,'2021-01-03 18:52:16.940000',12,_binary '',1,2,9),(6,'2021-01-03 19:03:36.041000',17,_binary '',1,1,9),(7,'2021-01-04 12:12:35.110000',13,_binary '',1,2,15),(8,'2021-01-04 12:12:37.333000',7,_binary '\0',1,1,15),(9,'2021-01-04 12:22:08.863000',16,_binary '',1,2,14),(10,'2021-01-04 12:22:10.715000',17,_binary '',1,1,14),(11,'2021-01-04 12:22:14.788000',14,_binary '',1,1,18),(12,'2021-01-04 12:22:17.349000',18,_binary '',1,2,18),(13,'2021-01-04 12:22:21.985000',9,_binary '\0',1,2,13),(14,'2021-01-04 12:22:26.085000',2,_binary '\0',1,1,13),(15,'2021-01-04 12:22:30.177000',12,_binary '',1,2,17),(16,'2021-01-04 12:22:32.679000',11,_binary '',1,1,17),(17,'2021-01-04 12:22:35.736000',20,_binary '',1,2,11),(18,'2021-01-04 12:22:37.553000',20,_binary '',1,1,11),(19,'2021-01-04 12:22:43.396000',4,_binary '\0',1,1,7),(20,'2021-01-04 12:22:45.611000',7,_binary '\0',1,2,7),(21,'2021-01-04 12:22:49.060000',7,_binary '\0',1,2,19),(22,'2021-01-04 12:22:50.618000',12,_binary '',1,1,19),(23,'2021-01-04 12:22:54.596000',12,_binary '',1,1,21),(24,'2021-01-04 12:22:56.460000',18,_binary '',1,2,21),(25,'2021-01-04 12:23:04.080000',11,_binary '',1,1,20),(26,'2021-01-04 12:23:06.787000',11,_binary '',1,2,20),(27,'2021-01-04 12:23:09.449000',14,_binary '',1,2,9),(28,'2021-01-04 12:23:11.863000',15,_binary '',1,1,9),(29,'2021-01-04 12:23:15.663000',3,_binary '\0',1,1,12),(30,'2021-01-04 12:23:17.438000',8,_binary '\0',1,2,12),(31,'2021-01-04 12:23:20.397000',11,_binary '',1,1,10),(32,'2021-01-04 12:23:22.448000',15,_binary '',1,2,10),(33,'2021-01-04 12:23:25.785000',15,_binary '',1,1,8),(34,'2021-01-04 12:23:27.252000',15,_binary '',1,2,8),(35,'2021-01-04 12:23:30.466000',11,_binary '',1,2,16),(36,'2021-01-04 12:23:32.033000',5,_binary '\0',1,1,16),(37,'2021-01-18 14:51:33.801000',11,_binary '',4,8,19),(38,'2021-01-18 14:51:35.867000',11,_binary '',4,7,19),(39,'2021-01-18 15:04:39.274000',7,_binary '\0',4,7,14),(40,'2021-01-18 15:04:41.047000',11,_binary '',4,8,14),(41,'2021-01-18 15:59:22.175000',11,_binary '',1,2,28),(42,'2021-01-18 15:59:24.157000',11,_binary '',1,1,28),(43,'2021-01-18 15:59:50.822000',11,_binary '',3,6,28),(44,'2021-01-18 15:59:53.129000',11,_binary '',3,5,28),(45,'2021-01-18 16:00:32.270000',15,_binary '',4,8,28),(46,'2021-01-18 16:00:34.007000',18,_binary '',4,7,28);
/*!40000 ALTER TABLE `predispitne_obaveze_polaganje` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `predmet_studenti`
--

LOCK TABLES `predmet_studenti` WRITE;
/*!40000 ALTER TABLE `predmet_studenti` DISABLE KEYS */;
INSERT INTO `predmet_studenti` VALUES (6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(18,1),(19,1),(20,1),(21,1),(28,1),(6,2),(7,2),(8,2),(9,2),(10,2),(11,2),(12,2),(13,2),(14,2),(15,2),(16,2),(17,2),(18,2),(19,2),(20,2),(21,2),(28,2),(6,3),(7,3),(8,3),(9,3),(10,3),(11,3),(12,3),(13,3),(14,3),(15,3),(16,3),(17,3),(18,3),(19,3),(20,3),(21,3),(28,3),(6,4),(7,4),(8,4),(9,4),(10,4),(11,4),(12,4),(13,4),(14,4),(15,4),(16,4),(17,4),(18,4),(19,4),(20,4),(21,4),(28,4);
/*!40000 ALTER TABLE `predmet_studenti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `prijava`
--

LOCK TABLES `prijava` WRITE;
/*!40000 ALTER TABLE `prijava` DISABLE KEYS */;
INSERT INTO `prijava` VALUES (1,'2021-01-03 18:48:36.338000',_binary '',47,21,_binary '\0',26,1,6),(2,'2021-01-04 12:31:12.344000',_binary '',55,25,_binary '',30,1,8),(3,'2021-01-04 12:31:32.232000',_binary '',52,26,_binary '',26,1,10),(4,'2021-01-04 12:31:45.557000',_binary '',85,45,_binary '',40,1,11),(5,'2021-01-04 12:32:00.063000',_binary '',76,43,_binary '',33,1,14);
/*!40000 ALTER TABLE `prijava` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `student`
--


--
-- Dumping data for table `uplata`
--

LOCK TABLES `uplata` WRITE;
/*!40000 ALTER TABLE `uplata` DISABLE KEYS */;
INSERT INTO `uplata` VALUES (1,'2020-07-07 00:00:00.000000',1000,'974325234444123','34256-1235-2342','Uplata Na Racun',6),(3,'2020-07-07 00:00:00.000000',1000,'9743235645624332','34256-1235-2342','Uplata Na Racun',7),(4,'2020-07-07 00:00:00.000000',1000,'9753235645624331','34256-1235-2342','Uplata Na Racun',8),(5,'2020-07-07 00:00:00.000000',1000,'9723235645624333','34256-1235-2342','Uplata Na Racun',9),(6,'2020-07-07 00:00:00.000000',1000,'9713235645624335','34256-1235-2342','Uplata Na Racun',10),(7,'2020-07-07 00:00:00.000000',1000,'9713235645624336','34256-1235-2342','Uplata Na Racun',11),(8,'2020-07-07 00:00:00.000000',1000,'9763235645624337','34256-1235-2342','Uplata Na Racun',12),(9,'2020-07-07 00:00:00.000000',1000,'9773235645624338','34256-1235-2342','Uplata Na Racun',13),(10,'2020-07-07 00:00:00.000000',1000,'9743235645624339','34256-1235-2342','Uplata Na Racun',14),(11,'2020-07-07 00:00:00.000000',1000,'9733235645624324','34256-1235-2342','Uplata Na Racun',15),(12,'2020-07-07 00:00:00.000000',1000,'9733235645624335','34256-1235-2342','Uplata Na Racun',16),(13,'2020-07-07 00:00:00.000000',1000,'9763235645624345','34256-1235-2342','Uplata Na Racun',17),(14,'2020-07-07 00:00:00.000000',1000,'9783235645624736','34256-1235-2342','Uplata Na Racun',18),(15,'2020-07-07 00:00:00.000000',1000,'9793235645624387','34256-1235-2342','Uplata Na Racun',19),(16,'2020-07-07 00:00:00.000000',1000,'9783235645624348','34256-1235-2342','Uplata Na Racun',20),(29,'2021-01-18 15:56:29.164000',2000,'9783235645624340','12345-123-31','Uplata Na Racun',28),(30,'2021-01-18 15:56:35.743000',1000,'9783235645624340','12345-123-31','Uplata Na Racun',28),(31,'2021-01-18 15:56:43.992000',1500,'9783235645624340','12345-123-31','Uplata Na Racun',28),(32,'2021-01-18 15:57:55.702000',300,'9783235645624340','12345-123-31','Uplata Ispita',28);
/*!40000 ALTER TABLE `uplata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--


--
-- Dumping events for database 'eobrazovanje5'
--

--
-- Dumping routines for database 'eobrazovanje5'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-18 16:01:30
