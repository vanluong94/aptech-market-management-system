-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 35.247.137.54    Database: aptech_java_project
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
-- Temporary view structure for view `v_import_product`
--

DROP TABLE IF EXISTS `v_import_product`;
/*!50001 DROP VIEW IF EXISTS `v_import_product`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `v_import_product` AS SELECT 
 1 AS `import_product_id`,
 1 AS `supplier_id`,
 1 AS `product_id`,
 1 AS `employee_id`,
 1 AS `product_quantity`,
 1 AS `product_price`,
 1 AS `date_added`,
 1 AS `supplier_name`,
 1 AS `employee_name`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `v_import_product`
--

/*!50001 DROP VIEW IF EXISTS `v_import_product`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`aptech_participant`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `v_import_product` AS select `import_products`.`import_product_id` AS `import_product_id`,`import_products`.`supplier_id` AS `supplier_id`,`import_products`.`product_id` AS `product_id`,`import_products`.`employee_id` AS `employee_id`,`import_products`.`product_quantity` AS `product_quantity`,`import_products`.`product_price` AS `product_price`,`import_products`.`date_added` AS `date_added`,`suppliers`.`supplier_name` AS `supplier_name`,`employees`.`employee_name` AS `employee_name` from ((`import_products` left join `suppliers` on((`suppliers`.`supplier_id` = `import_products`.`supplier_id`))) left join `employees` on((`employees`.`employee_id` = `import_products`.`employee_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-09-22  7:38:02
