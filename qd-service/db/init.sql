-- MySQL dump 10.13  Distrib 5.7.22, for osx10.11 (x86_64)
--
-- Host: 127.0.0.1    Database: qd_admin
-- ------------------------------------------------------
-- Server version	5.7.22

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
-- Table structure for table `sale_custom`
--

DROP TABLE IF EXISTS `sale_custom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sale_custom` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `custom_name` varchar(30) NOT NULL DEFAULT '' COMMENT '姓名',
  `phone` varchar(20) DEFAULT '' COMMENT '手机',
  `owner_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '所属人id',
  `owner_dept_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '所属部门id',
  `create_user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '修改人id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='客户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale_custom`
--

LOCK TABLES `sale_custom` WRITE;
/*!40000 ALTER TABLE `sale_custom` DISABLE KEYS */;
INSERT INTO `sale_custom` VALUES (1,'北京销售部1','133333333',2,2,2,'2019-07-25 14:20:45',0,NULL,0),(2,'北京销售一部1','13333323',3,3,3,'2019-07-25 14:21:13',0,NULL,0),(3,'北京销售二部1','132323',4,4,4,'2019-07-25 14:21:36',0,NULL,0),(4,'上海销售部1','133423',5,5,5,'2019-07-25 14:21:53',0,NULL,0),(5,'上海销售一部1','123342234',6,6,6,'2019-07-25 14:22:26',0,NULL,0),(6,'集团销售部1','13324234',7,1,7,'2019-07-25 14:25:55',0,NULL,0);
/*!40000 ALTER TABLE `sale_custom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_company`
--

DROP TABLE IF EXISTS `sys_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_company` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '上级id',
  `company_name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `create_user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '修改人id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='公司';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_company`
--

LOCK TABLES `sys_company` WRITE;
/*!40000 ALTER TABLE `sys_company` DISABLE KEYS */;
INSERT INTO `sys_company` VALUES (1,0,'启迪集团',1,'2019-07-13 09:58:33',0,NULL,0),(2,1,'启迪北京分公司',1,'2019-07-13 09:58:33',0,NULL,0),(3,1,'启迪上海分公司',1,'2019-07-13 09:58:33',0,NULL,0);
/*!40000 ALTER TABLE `sys_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_data_obj`
--

DROP TABLE IF EXISTS `sys_data_obj`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_data_obj` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `obj_name` varchar(30) NOT NULL DEFAULT '' COMMENT '对象名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='数据对象';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_data_obj`
--

LOCK TABLES `sys_data_obj` WRITE;
/*!40000 ALTER TABLE `sys_data_obj` DISABLE KEYS */;
INSERT INTO `sys_data_obj` VALUES (1,'用户'),(2,'客户');
/*!40000 ALTER TABLE `sys_data_obj` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_data_rule`
--

DROP TABLE IF EXISTS `sys_data_rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_data_rule` (
  `data_obj_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '数据对象id',
  `role_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '角色id',
  `permission_type` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '权限类型',
  `custom_dept_ids` text COMMENT '自定义部门id',
  PRIMARY KEY (`data_obj_id`,`role_id`,`permission_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据规则';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_data_rule`
--

LOCK TABLES `sys_data_rule` WRITE;
/*!40000 ALTER TABLE `sys_data_rule` DISABLE KEYS */;
INSERT INTO `sys_data_rule` VALUES (1,2,500,NULL),(1,3,300,NULL),(1,4,200,NULL),(2,3,300,NULL),(2,4,100,NULL),(2,5,3,'6');
/*!40000 ALTER TABLE `sys_data_rule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dept`
--

DROP TABLE IF EXISTS `sys_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dept` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `company_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '所属公司',
  `parent_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '上级id',
  `dept_name` varchar(50) NOT NULL DEFAULT '' COMMENT '名称',
  `create_user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '修改人id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='部门';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dept`
--

LOCK TABLES `sys_dept` WRITE;
/*!40000 ALTER TABLE `sys_dept` DISABLE KEYS */;
INSERT INTO `sys_dept` VALUES (1,1,0,'销售部',1,'2019-07-13 09:58:33',0,NULL,0),(2,2,0,'销售部',1,'2019-07-13 09:58:50',0,NULL,0),(3,2,2,'销售一部',1,'2019-07-13 10:02:12',0,NULL,0),(4,2,2,'销售二部',1,'2019-07-13 10:02:25',0,NULL,0),(5,3,0,'销售部',1,'2019-07-13 10:02:38',0,NULL,0),(6,3,5,'销售一部',1,'2019-07-19 10:53:19',0,NULL,0);
/*!40000 ALTER TABLE `sys_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict`
--

DROP TABLE IF EXISTS `sys_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dict` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type_code` varchar(32) NOT NULL DEFAULT '' COMMENT '类型',
  `dict_value` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '值',
  `dict_desc` varchar(30) NOT NULL DEFAULT '' COMMENT '描述',
  `sort_num` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '顺序',
  `create_user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '修改人id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='字典';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict`
--

LOCK TABLES `sys_dict` WRITE;
/*!40000 ALTER TABLE `sys_dict` DISABLE KEYS */;
INSERT INTO `sys_dict` VALUES (1,'gender',1,'男',0,1,'2019-06-12 03:59:18',0,NULL,0),(2,'gender',2,'女',0,1,'2019-06-12 03:59:18',0,NULL,0),(3,'menuType',1,'目录',0,1,'2019-06-12 03:59:18',0,NULL,0),(4,'menuType',2,'页面',0,1,'2019-06-12 03:59:18',0,NULL,0),(5,'pageElementMethod',1,'GET',0,1,'2019-06-12 03:59:18',0,NULL,0),(6,'pageElementMethod',2,'POST',0,1,'2019-06-12 03:59:18',0,NULL,0),(7,'pageElementMethod',3,'PUT',0,1,'2019-06-12 03:59:18',0,NULL,0),(8,'pageElementMethod',4,'DELETE',0,1,'2019-06-12 03:59:18',0,NULL,0),(9,'resourceType',1,'菜单',0,1,'2019-06-12 03:59:18',0,NULL,0),(10,'resourceType',2,'页面元素',0,1,'2019-06-12 03:59:18',0,NULL,0),(11,'pageElementType',1,'按钮',0,1,'2019-06-12 03:59:18',0,NULL,0),(12,'pageElementType',2,'页签',0,1,'2019-06-12 03:59:18',0,NULL,0),(13,'dataPermissionType',1,'无权',0,1,'2019-06-12 03:59:18',0,NULL,0),(14,'dataPermissionType',2,'所有权限',0,1,'2019-06-12 03:59:18',0,NULL,0),(15,'dataPermissionType',3,'自定义部门',0,1,'2019-06-12 03:59:18',0,NULL,0),(16,'dataPermissionType',100,'本人',0,1,'2019-06-12 03:59:18',0,NULL,0),(17,'dataPermissionType',200,'本部门',0,1,'2019-06-12 03:59:18',0,NULL,0),(18,'dataPermissionType',300,'本部门及子部门',0,1,'2019-06-12 03:59:18',0,NULL,0),(19,'dataPermissionType',400,'本公司',0,1,'2019-06-12 03:59:18',0,NULL,0),(20,'dataPermissionType',500,'本公司及子公司',0,1,'2019-06-12 03:59:18',0,NULL,0),(21,'pageElementType',3,'弹窗',0,1,'2019-07-23 16:25:28',0,NULL,0);
/*!40000 ALTER TABLE `sys_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict_type`
--

DROP TABLE IF EXISTS `sys_dict_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dict_type` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type_code` varchar(32) NOT NULL DEFAULT '' COMMENT '编码',
  `type_desc` varchar(30) NOT NULL DEFAULT '' COMMENT '描述',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '修改人id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='字典类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict_type`
--

LOCK TABLES `sys_dict_type` WRITE;
/*!40000 ALTER TABLE `sys_dict_type` DISABLE KEYS */;
INSERT INTO `sys_dict_type` VALUES (1,'gender','性别','',1,'2019-06-12 03:59:18',0,NULL,0),(2,'menuType','菜单类型',NULL,1,'2019-06-12 03:59:18',0,NULL,0),(3,'pageElementMethod','页面元素请求方法',NULL,1,'2019-06-12 03:59:18',0,NULL,0),(4,'resourceType','资源类型',NULL,1,'2019-06-12 03:59:18',0,NULL,0),(5,'pageElementType','页面元素类型','',1,'2019-06-12 03:59:18',0,NULL,0),(6,'dataPermissionType','数据权限类型',NULL,1,'2019-06-12 03:59:18',0,NULL,0);
/*!40000 ALTER TABLE `sys_dict_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '父级id',
  `menu_name` varchar(10) NOT NULL DEFAULT '' COMMENT '菜单名称',
  `type` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '类型',
  `page_router_name` varchar(30) NOT NULL DEFAULT '' COMMENT '前端路由名称',
  `page_path` varchar(255) NOT NULL DEFAULT '' COMMENT '前端页面位置',
  `sort_num` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `index_url` varchar(255) NOT NULL DEFAULT '' COMMENT '首页请求',
  `icon_name` varchar(30) NOT NULL DEFAULT '' COMMENT '图标名称',
  `create_user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '修改人id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='菜单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,0,'系统管理',1,'sys','',0,'','md-settings',1,'2019-06-12 03:59:18',0,NULL,0),(2,1,'用户',2,'user','/sys/user',0,'/user','',1,'2019-06-12 03:59:18',0,NULL,0),(3,1,'角色',2,'role','/sys/role',0,'/role','',1,'2019-06-12 03:59:18',0,NULL,0),(4,1,'资源',2,'menu','/sys/menu',0,'/menu','',1,'2019-06-12 03:59:18',0,NULL,0),(5,1,'字典',2,'dict-type','/sys/dict',0,'/dict','',1,'2019-06-12 03:59:18',0,NULL,0),(6,1,'组织',2,'org','/sys/org',0,'/org','',1,'2019-06-12 03:59:18',0,NULL,0),(7,0,'销售管理',1,'sale','',0,'','',1,'2019-07-19 11:06:28',0,NULL,0),(8,7,'客户',2,'custom','/sale/custom',0,'/custom','',1,'2019-07-19 11:07:13',1,'2019-07-19 11:10:17',0);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_page_element`
--

DROP TABLE IF EXISTS `sys_page_element`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_page_element` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '菜单id',
  `element_name` varchar(10) NOT NULL DEFAULT '' COMMENT '元素名称',
  `element_type` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '元素类型',
  `element_code` varchar(30) NOT NULL DEFAULT '' COMMENT '元素编码',
  `url` varchar(255) NOT NULL DEFAULT '' COMMENT '请求url',
  `method` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '请求方法',
  `create_user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '修改人id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='页面元素';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_page_element`
--

LOCK TABLES `sys_page_element` WRITE;
/*!40000 ALTER TABLE `sys_page_element` DISABLE KEYS */;
INSERT INTO `sys_page_element` VALUES (1,2,'新建',1,'saveBtn','/user',2,1,'2019-06-12 03:59:18',0,NULL,0),(2,2,'编辑',1,'updateBtn','/user/{id}',3,1,'2019-06-12 03:59:18',0,NULL,0),(3,2,'删除',1,'removeBtn','/user/{id}',4,1,'2019-06-12 03:59:18',0,NULL,0),(4,2,'批量删除',1,'batchRemoveBtn','/user/batch',4,1,'2019-06-12 03:59:18',0,NULL,0),(5,2,'分配角色',1,'assignRoleBtn','/user/{id}/role/assign',3,1,'2019-06-12 03:59:18',0,NULL,0),(6,3,'新建',1,'saveBtn','/role',2,1,'2019-06-12 03:59:18',0,NULL,0),(7,3,'编辑',1,'updateBtn','/role/{id}',3,1,'2019-06-12 03:59:18',0,NULL,0),(8,3,'删除',1,'removeBtn','/role/{id}',4,1,'2019-06-12 03:59:18',0,NULL,0),(9,3,'分配功能权限',1,'assignResourceBtn','/role/{id}/resource',2,0,'2019-06-12 03:59:18',0,NULL,0),(10,3,'分配数据权限',1,'assignDataBtn','/role/{id}/data',2,1,'2019-06-12 03:59:18',1,'2019-07-25 10:37:32',0),(11,4,'新建顶级',1,'saveRootBtn','/menu',2,1,'2019-06-12 03:59:18',0,NULL,0),(12,4,'新建',1,'saveBtn','/menu',2,1,'2019-06-12 03:59:18',0,NULL,0),(13,4,'编辑',1,'updateBtn','/menu/{id}',3,1,'2019-06-12 03:59:18',0,NULL,0),(14,4,'删除',1,'removeBtn','/menu/{id}',4,1,'2019-06-12 03:59:18',0,NULL,0),(15,4,'新建页面元素',1,'savePageElementBtn','/menu/{menuId}/page-element',2,1,'2019-06-12 03:59:18',0,NULL,0),(16,4,'编辑页面元素',1,'updatePageElementBtn','/menu/page-element/{id}',3,1,'2019-06-12 03:59:18',0,NULL,0),(17,4,'删除页面元素',1,'removePageElementBtn','/menu/page-element/{id}',4,1,'2019-06-12 03:59:18',0,NULL,0),(18,5,'新建类型',1,'saveTypeBtn','/dict/type',2,1,'2019-06-12 03:59:18',0,NULL,0),(19,5,'编辑类型',1,'editTypeBtn','/dict/type/{id}',3,1,'2019-06-12 03:59:18',0,NULL,0),(20,5,'删除类型',1,'removeTypeBtn','/dict/type/{id}',4,1,'2019-06-12 03:59:18',0,NULL,0),(21,5,'新建字典',1,'saveDict','/dict',2,1,'2019-06-12 03:59:18',0,NULL,0),(22,5,'编辑字典',1,'updateDictBtn','/dict/{id}',3,1,'2019-06-12 03:59:18',0,NULL,0),(23,5,'删除字典',1,'removeDictBtn','/dict/{id}',4,1,'2019-06-12 03:59:18',0,NULL,0),(24,5,'更新字典缓存',1,'updateCacheBtn','/dict/code/{typeCode}/cache',3,1,'2019-06-12 03:59:18',0,NULL,0),(25,6,'新建顶级公司',1,'saveCompanyRootBtn','/org/company',2,1,'2019-07-13 08:49:09',0,NULL,0),(26,6,'新建公司',1,'saveCompanyBtn','/org/company',2,1,'2019-07-13 08:49:49',0,NULL,0),(27,6,'编辑公司',1,'updateCompanyBtn','/org/company/{id}',3,1,'2019-07-13 08:50:29',0,NULL,0),(28,6,'删除公司',1,'removeCompanyBtn','/org/company/{id}',4,1,'2019-07-13 08:57:59',0,NULL,0),(29,6,'新建顶级部门',1,'saveDeptRootBtn','/org/dept',2,1,'2019-07-13 09:49:27',0,NULL,0),(30,6,'新建部门',1,'saveDeptBtn','/org/dept',2,1,'2019-07-13 09:49:50',0,NULL,0),(31,6,'编辑部门',1,'updateDeptBtn','/org/dept/{id}',3,1,'2019-07-13 09:50:10',0,NULL,0),(32,6,'删除部门',1,'removeDeptBtn','/org/dept/{id}',4,1,'2019-07-13 09:50:29',0,NULL,0),(33,8,'新增',1,'saveBtn','/custom',2,1,'2019-07-19 11:08:58',0,NULL,0),(34,8,'批量删除',1,'batchRemoveBtn','/custom/batch',4,1,'2019-07-22 13:59:39',0,NULL,0),(35,8,'编辑',1,'updateBtn','/custom/{id}',3,1,'2019-07-22 14:01:34',1,'2019-07-22 14:03:39',0),(36,8,'删除',1,'removeBtn','/custom/{id}',4,1,'2019-07-22 14:01:58',0,NULL,0),(37,2,'用户参照',3,'userRef','/user/ref',1,1,'2019-07-23 16:26:52',0,NULL,0);
/*!40000 ALTER TABLE `sys_page_element` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(30) NOT NULL DEFAULT '' COMMENT '角色名称',
  `create_user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '修改人id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'超级管理员',0,'2019-06-12 03:59:18',0,NULL,0),(2,'公司管理员',1,'2019-06-12 03:59:18',0,NULL,0),(3,'销售主管',1,'2019-06-12 03:59:18',0,NULL,0),(4,'销售员',1,'2019-06-12 03:59:18',0,NULL,0),(5,'临时协助人员',1,'2019-07-25 14:29:32',0,NULL,0);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_resource`
--

DROP TABLE IF EXISTS `sys_role_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_resource` (
  `role_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '角色id',
  `resource_type` int(10) unsigned NOT NULL DEFAULT '0',
  `resource_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '资源id',
  PRIMARY KEY (`role_id`,`resource_type`,`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_resource`
--

LOCK TABLES `sys_role_resource` WRITE;
/*!40000 ALTER TABLE `sys_role_resource` DISABLE KEYS */;
INSERT INTO `sys_role_resource` VALUES (1,1,1),(1,1,2),(1,1,3),(1,1,4),(1,1,5),(1,1,6),(1,1,7),(1,1,8),(1,2,1),(1,2,2),(1,2,3),(1,2,4),(1,2,5),(1,2,6),(1,2,7),(1,2,8),(1,2,9),(1,2,10),(1,2,11),(1,2,12),(1,2,13),(1,2,14),(1,2,15),(1,2,16),(1,2,17),(1,2,18),(1,2,19),(1,2,20),(1,2,21),(1,2,22),(1,2,23),(1,2,24),(1,2,25),(1,2,26),(1,2,27),(1,2,28),(1,2,29),(1,2,30),(1,2,31),(1,2,32),(1,2,33),(1,2,34),(1,2,35),(1,2,36),(2,1,1),(2,1,2),(2,1,6),(2,2,1),(2,2,2),(2,2,3),(2,2,4),(2,2,5),(2,2,26),(2,2,27),(2,2,28),(2,2,29),(2,2,30),(2,2,31),(2,2,32),(3,1,7),(3,1,8),(3,2,33),(3,2,34),(3,2,35),(3,2,36),(3,2,37),(4,1,7),(4,1,8),(4,2,33),(4,2,35),(4,2,36),(4,2,37);
/*!40000 ALTER TABLE `sys_role_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(30) NOT NULL DEFAULT '' COMMENT '用户名',
  `full_name` varchar(30) NOT NULL DEFAULT '' COMMENT '姓名',
  `gender` int(10) unsigned DEFAULT NULL COMMENT '性别',
  `company_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '公司id',
  `dept_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '部门id',
  `pwd` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(32) NOT NULL DEFAULT '' COMMENT '盐',
  `avatar` varchar(100) NOT NULL DEFAULT '' COMMENT '头像',
  `create_user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '修改人id',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='用户 ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'admin','管理员',1,1,1,'b1c3770c404e49710c22196986415490','258f528c64f2423c9bff976c7159f916','',0,'2019-06-12 03:59:18',1,'2019-07-23 11:49:50',0),(2,'bjxs','北京销售部主管',2,2,2,'b1c3770c404e49710c22196986415490','258f528c64f2423c9bff976c7159f916','',1,'2019-06-12 03:59:18',1,'2019-07-23 11:32:33',0),(3,'bjxs01','北京销售一部组员',1,2,3,'3d3ced7386b6eccaff6776808b73152e','c95b5a1fe57845b086b3097ee0ac5e16','',1,'2019-07-19 10:55:13',1,'2019-07-23 11:32:38',0),(4,'bjxs02','北京销售二部组员',1,2,4,'d0865bf12fe8f8edec03a9ff050f7f61','16953dbf66854e1ba1a6d713a097c963','',1,'2019-07-19 10:55:33',1,'2019-07-23 11:32:42',0),(5,'shxs','上海销售部主管',1,3,5,'290eaa3d77ae331aae60ef55c8fde64a','1b4c9e59cb0c448ca7bdcd5b8aac0282','',1,'2019-07-19 10:55:55',1,'2019-07-23 11:32:47',0),(6,'shxs01','上海销售一部组员',1,3,6,'39f6808aca3e17405f4fd8592a88800c','318af02f21694c62bb165d84658d0aa2','',1,'2019-07-19 10:56:14',1,'2019-07-23 11:32:50',0),(7,'jtxs','集团销售主管',1,1,1,'cfd240fc8214c323f537d0ed49968779','3ebce18b544944c4bdb6f4ea0e0a01ae','',1,'2019-07-19 10:56:38',1,'2019-07-25 14:25:39',0),(8,'bjgly','北京管理员',1,2,2,'549ed52a3205b9c031a3202e8ec2ac62','f3560789c2f74586b7f73b283428de82','',1,'2019-07-23 11:36:08',1,'2019-07-23 11:36:43',0),(9,'shgly','上海管理员',1,3,5,'dc65a3049ec94ec4cd1c22f7c771de64','9716e19a08b5436387e4ff8416f65694','',1,'2019-07-23 11:36:37',0,NULL,0);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '用户id',
  `role_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '角色id',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (1,1),(2,4),(2,5),(3,4),(4,4),(5,3),(6,4),(7,3),(8,2),(9,2);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-25 14:31:02
