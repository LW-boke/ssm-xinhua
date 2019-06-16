/*
Navicat MySQL Data Transfer

Source Server         : mysql_LW
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : scoa

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-06-16 17:18:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for clas_cou_rel
-- ----------------------------
DROP TABLE IF EXISTS `clas_cou_rel`;
CREATE TABLE `clas_cou_rel` (
  `cla_id` bigint(20) NOT NULL,
  `cou_id` bigint(20) NOT NULL,
  `tea_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`cla_id`,`cou_id`),
  KEY `cou_id` (`cou_id`),
  KEY `tea_id` (`tea_id`),
  CONSTRAINT `clas_cou_rel_ibfk_1` FOREIGN KEY (`cla_id`) REFERENCES `clazz` (`cla_id`),
  CONSTRAINT `clas_cou_rel_ibfk_2` FOREIGN KEY (`cou_id`) REFERENCES `course` (`cou_id`),
  CONSTRAINT `clas_cou_rel_ibfk_3` FOREIGN KEY (`tea_id`) REFERENCES `teacher` (`tea_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clas_cou_rel
-- ----------------------------
INSERT INTO `clas_cou_rel` VALUES ('1', '30', null);
INSERT INTO `clas_cou_rel` VALUES ('2', '32', null);
INSERT INTO `clas_cou_rel` VALUES ('2', '33', null);
INSERT INTO `clas_cou_rel` VALUES ('3', '7', null);
INSERT INTO `clas_cou_rel` VALUES ('5', '5', null);
INSERT INTO `clas_cou_rel` VALUES ('13', '27', null);
INSERT INTO `clas_cou_rel` VALUES ('3', '8', '2');
INSERT INTO `clas_cou_rel` VALUES ('13', '24', '2');
INSERT INTO `clas_cou_rel` VALUES ('1', '2', '3');
INSERT INTO `clas_cou_rel` VALUES ('2', '7', '15');
INSERT INTO `clas_cou_rel` VALUES ('1', '29', '18');
INSERT INTO `clas_cou_rel` VALUES ('1', '32', '18');

-- ----------------------------
-- Table structure for clazz
-- ----------------------------
DROP TABLE IF EXISTS `clazz`;
CREATE TABLE `clazz` (
  `cla_id` bigint(10) NOT NULL AUTO_INCREMENT,
  `cla_name` varchar(20) DEFAULT NULL,
  `cla_open_date` date DEFAULT NULL,
  `cla_number` int(11) DEFAULT NULL,
  `gra_num` int(10) NOT NULL,
  `tea_id` bigint(20) DEFAULT NULL,
  `pro_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`cla_id`),
  KEY `gar_num` (`gra_num`),
  KEY `tea_id` (`tea_id`),
  KEY `pro_id` (`pro_id`),
  CONSTRAINT `clazz_ibfk_1` FOREIGN KEY (`gra_num`) REFERENCES `grade` (`gra_num`),
  CONSTRAINT `clazz_ibfk_2` FOREIGN KEY (`tea_id`) REFERENCES `teacher` (`tea_id`),
  CONSTRAINT `clazz_ibfk_3` FOREIGN KEY (`pro_id`) REFERENCES `profession` (`pro_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clazz
-- ----------------------------
INSERT INTO `clazz` VALUES ('1', '软件开发1804', '2019-03-14', '24', '1002', '16', '2');
INSERT INTO `clazz` VALUES ('2', '软件开发1806', '2019-03-13', '26', '1001', '15', '2');
INSERT INTO `clazz` VALUES ('3', 'TC-环境艺术1702', '2019-03-13', '32', '1001', null, '4');
INSERT INTO `clazz` VALUES ('4', 'TC-环境艺术1703', '2018-05-06', '24', '1001', '4', '4');
INSERT INTO `clazz` VALUES ('5', 'TC-环境艺术1801', '2019-05-05', '16', '1001', '5', '4');
INSERT INTO `clazz` VALUES ('7', 'TC-环境艺术1802', '2019-03-04', '15', '1001', '30', '4');
INSERT INTO `clazz` VALUES ('8', 'TC-软件开发1704', '2017-08-06', '22', '1002', '16', '2');
INSERT INTO `clazz` VALUES ('9', 'TC-影视动漫1801', '2018-05-23', '15', '1002', null, '5');
INSERT INTO `clazz` VALUES ('10', '3Y-电子商务1701', '2017-05-06', '16', '1002', '21', '1');
INSERT INTO `clazz` VALUES ('11', 'TC-互联网+新零售1701', '2018-05-03', '24', '1002', '29', '1');
INSERT INTO `clazz` VALUES ('12', 'TC-互联网+新零售1702', '2019-03-21', '25', '1002', '18', '1');
INSERT INTO `clazz` VALUES ('13', 'TC-VR数字媒体1701', '2019-06-04', '19', '1002', '16', '3');
INSERT INTO `clazz` VALUES ('14', 'TC-电子竞技1801', '2017-05-05', '36', '1003', null, '6');
INSERT INTO `clazz` VALUES ('15', 'TC-电子竞技1802', '2017-08-06', '22', '1003', null, '6');
INSERT INTO `clazz` VALUES ('16', 'TC-电子竞技1803', '2017-10-02', '15', '1003', '29', '6');
INSERT INTO `clazz` VALUES ('17', 'TC-人工智能1801', '2017-12-05', '16', '1003', '28', '2');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `cou_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cou_name` varchar(20) DEFAULT NULL,
  `cou_open_date` date DEFAULT NULL,
  `cou_content` varchar(255) DEFAULT NULL,
  `pro_id` bigint(20) NOT NULL,
  PRIMARY KEY (`cou_id`),
  KEY `pro_id` (`pro_id`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`pro_id`) REFERENCES `profession` (`pro_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', 'SQl2008 CMS网站建设实践', '2019-03-13', 'SQlServer2008数据库基础 SQl2008+CMS网站建设实践 英语+计算机词汇 JQuery框架+AJAX JavaScript DOM编程 Bootstrap企业级UI库 Java OOP面向对象编程 Oracle...', '2');
INSERT INTO `course` VALUES ('2', 'JAVA OOP编程', '2019-03-12', '面向对象编程（OOP）具有多方面的吸引力。对管理人员，它实现了更快和更廉价的开发与维护过程。对分析与设计人员，建模处理变得更加简单，能生成清晰、易于维护的设计方案。对程序员，对象模型显得如此高雅和浅显。此外，面向对象工具以及库的巨大威力使编程成为一项更使人愉悦的任务。每个人都可从中获益，至少表面如此。', '2');
INSERT INTO `course` VALUES ('3', 'Photoshop图像处理', '2019-03-01', 'Photoshop主要处理以像素所构成的数字图像。使用其众多的编修与绘图工具，可以有效地进行图片编辑工作。ps有很多功能，在图像、图形、文字、视频、出版等各方面都有涉及。', '1');
INSERT INTO `course` VALUES ('4', '3dsmax建筑效果图表现技法', '2019-03-16', '《视觉:3ds Max/VRay照片级建筑效果图表现技法》主要讲解了如何在3ds Max软件环境下使用VRay渲染器制作建筑效果图的技巧。', '4');
INSERT INTO `course` VALUES ('5', '手绘效果图技法', '2019-03-04', '手绘效果图是从事各种设计专业，比如建筑设计、园林设计、室内设计、景观设计（见插图）、服装设计、工业设计等专业学习的一门重要的专业必修课程。前期必须有素描、色彩、钢笔画、透视这些基础课程。', '4');
INSERT INTO `course` VALUES ('6', '素描、色彩、美术写生', null, '素描、色彩、美术写生', '4');
INSERT INTO `course` VALUES ('7', 'Photoshop图像处理', null, 'Photoshop主要处理以像素所构成的数字图像。使用其众多的编修与绘图工具，可以有效地进行图片编辑工作。ps有很多功能，在图像、图形、文字、视频、出版等各方面都有涉及。', '4');
INSERT INTO `course` VALUES ('8', 'AutoCAD和建筑环境艺术设计', '2019-03-05', '计算机CAD技术的研究与应用已经进入了成熟、普及的阶段,设计师可以利用CAD技术完成设计与绘制图纸;进行环境内、外空间三维效果的预览;三维环境演示动画及虚拟现实技术等更为复杂的设计工作。', '4');
INSERT INTO `course` VALUES ('9', 'Office办公自动化', null, 'Microsoft Office 2003产品家族中Access 2003、InfoPath 2003、Outlook Express 2003和Publisher 2003的产品概述。', '4');
INSERT INTO `course` VALUES ('10', 'CRM客户关系管理', null, 'CRM客户关系管理是一种“以客户为中心”的经营管理策略', '1');
INSERT INTO `course` VALUES ('11', 'SEO搜索引擎优化', null, 'SEO（Search Engine Optimization）：汉译为搜索引擎优化。是一种方式：利用搜索引擎的规则提高网站在有关搜索引擎内的自然排名。', '1');
INSERT INTO `course` VALUES ('12', 'Flash动画设计与制作', null, '《Flash动画设计与制作》 [1]  内容组织力求与教学各环节配套，做到实用、符合教学规律。不论Flash是作为相关专业的专业课，还是作为公选课，《Flash动画设计与制作》 [1]  都能极大地方便教师在教学过程中组织教学活动。', '1');
INSERT INTO `course` VALUES ('13', 'Dreamweaver网页设计', null, 'Dreamweaver是Macromedia公司推出的网页制作三剑客之一，数以万计的用户通过它制作出各类精美的网页。', '1');
INSERT INTO `course` VALUES ('14', 'WEB界面设计', null, '在Web已经进入崭新的时代的今天，界面的设计显得非常重要，本书就是基于独一无二的Web环境下、在创建丰富体验的过程中设计Web界面的最佳实践、模式和原理。本书既是一本Web界面设计指南，又是一本Web界面实例参考，适合Web界面设计、开发、研究人员、爱好者，以及Web项目管理人员阅读。', '5');
INSERT INTO `course` VALUES ('15', '版式与图形设计', null, '版式设计是现代设计艺术的重要组成部分,是视觉传达的重要手段。表面上看,它是一种关于编排的学问;实际上,它...', '5');
INSERT INTO `course` VALUES ('16', 'CorelDRAW图形设计', null, '《CorelDRAW图形设计》运用快速表现设计构思的手绘草图和电脑设计软件相结合的方法，深入到CorelDraw实际运用的精髓。精选各种典型的设计实例，系统地讲解了从设计构思、手绘设计图到CorelDraw设计与制作的全过程。', '5');
INSERT INTO `course` VALUES ('17', '素描、色彩、美术写生', null, '《色彩写生》中选用了较多的图片,以期实现方便实用...之所以被称为设计意识,是因为素描、色彩是绘画训练,...', '5');
INSERT INTO `course` VALUES ('19', 'MOBA项目基础', null, 'MOBA项目基础', '6');
INSERT INTO `course` VALUES ('20', '实训', null, '实训', '6');
INSERT INTO `course` VALUES ('21', 'FPS项目基础', null, '只有当FPS游戏的多人对战环境的成熟,电子竞技才有了作为一个有别于其他电子游戏的、独立的发展的项目基础。...', '6');
INSERT INTO `course` VALUES ('22', '计算机操作基础', null, '内容主要包括Windows XP、Word 2003、Excel 2003、PowerPoint 2003、Access 2003等，每一部分既有理论知识，又有实践内容。全书注重计算机实践训练与应用技能的培养，内容丰富，语言简洁，通俗易懂。《计算机操作基础》可作为高等专科学校、高等职业学校、成人高校的“计算机公共基础”课程教材，也可以作为各类计算机应用基础培训的教材，还可以作为广大计算机爱好者的自学指导书。', '6');
INSERT INTO `course` VALUES ('23', '电竞概论', null, '电竞专业即电子竞技运动与管理专业,是2016年9月中国教育部职业教育与成人教育司公布的《关于做好2017年高等职...', '6');
INSERT INTO `course` VALUES ('24', '引擎蓝图架构设计', null, '本书详细阐述了与3D游戏引擎设计相关的高效解决方案及相应的数据结构和算法,主要包括图形系统、渲染器、场景图、控制器动画、空间排序、细节级别、碰撞检测、物理学、...', '3');
INSERT INTO `course` VALUES ('25', 'Video Studio 非线性编辑', null, '目前在非线性编辑中使用了6种不同的压缩方法:小波...此外,Video Studio会声会影,Corel® Digital ...', '3');
INSERT INTO `course` VALUES ('26', 'AutoCAD和建筑环境艺术设计', null, 'AUTO CAD和建筑环境艺术设计', '3');
INSERT INTO `course` VALUES ('27', 'Photoshop图像处理', null, 'Photoshop主要处理以像素所构成的数字图像。使用其众多的编修与绘图工具，可以有效地进行图片编辑工作。ps有很多功能，在图像、图形、文字、视频、出版等各方面都有涉及。', '3');
INSERT INTO `course` VALUES ('28', '网页图形图像设计与制作', null, '《图形图像设计与制作》是2008年清华大学出版社出版的图书,作者是陈伟、林燕。...... 与制作,数码照片的处理,广告设计与制作,封面与包装的设计与制作和网页设计与制...', '2');
INSERT INTO `course` VALUES ('29', 'MySql数据库', '2019-03-28', 'MySQL是一种开放源代码的关系型数据库管理系统（RDBMS），使用最常用的数据库管理语言--结构化查询语言（SQL）进行数据库管理。', '2');
INSERT INTO `course` VALUES ('30', 'Office办公自动化', null, 'Microsoft Office 2003产品家族中Access 2003、InfoPath 2003、Outlook Express 2003和Publisher 2003的产品概述。', '2');
INSERT INTO `course` VALUES ('32', 'HTML', '2019-03-05', '超文本标记语言，标准通用标记语言下的一个应用。是 网页制作必备的编程语言', '2');
INSERT INTO `course` VALUES ('33', 'JS', '2019-03-04', 'JavaScript一种直译式脚本语言，是一种动态类型、弱类型、基于原型的语言，内置支持类型。它的解释器被称为JavaScript引擎，为浏览器的一部分，广泛用于客户端的脚本语言，最早是在HTML（标准通用标记语言下的一个应用）网页上使用，用来给HTML网页增加动态功能。', '2');

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `gra_num` int(11) NOT NULL AUTO_INCREMENT,
  `gra_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`gra_num`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES ('1001', '大一');
INSERT INTO `grade` VALUES ('1002', '大二');
INSERT INTO `grade` VALUES ('1003', '大三');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(20) DEFAULT NULL,
  `url` varchar(30) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  KEY `permission_id` (`permission_id`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `menu` (`id`),
  CONSTRAINT `menu_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '学校管理', null, null, null);
INSERT INTO `menu` VALUES ('2', '年级列表', '/grade', '1', '4');
INSERT INTO `menu` VALUES ('3', '学生列表', '/student', '1', '2');
INSERT INTO `menu` VALUES ('4', '老师列表', '/teacher', '1', '3');
INSERT INTO `menu` VALUES ('5', '班级列表', '/clazz', '1', '1');
INSERT INTO `menu` VALUES ('6', '课程列表', '/course', '1', '5');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `p_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `p_name` varchar(20) DEFAULT NULL,
  `p_resource` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '查看班级', '/clazz');
INSERT INTO `permission` VALUES ('2', '查看学生', '/student');
INSERT INTO `permission` VALUES ('3', '查看老师', '/teacher');
INSERT INTO `permission` VALUES ('4', '年级列表', '/grade');
INSERT INTO `permission` VALUES ('5', '课程列表', '/course');
INSERT INTO `permission` VALUES ('6', '添加学生', 'addStudent');
INSERT INTO `permission` VALUES ('7', '编辑学生', 'editStudent');
INSERT INTO `permission` VALUES ('8', '删除学生', 'deleStudent');
INSERT INTO `permission` VALUES ('9', '添加老师', 'addTeacher');
INSERT INTO `permission` VALUES ('10', '编辑老师', 'editTeacher');
INSERT INTO `permission` VALUES ('11', '删除老师', 'deleTeacher');
INSERT INTO `permission` VALUES ('12', '添加班级', 'addClass');
INSERT INTO `permission` VALUES ('13', '编辑班级', 'editCalss');
INSERT INTO `permission` VALUES ('14', '删除班级', 'deleCalss');
INSERT INTO `permission` VALUES ('15', '添加课程', 'addCourse');
INSERT INTO `permission` VALUES ('16', '编辑课程', 'editCourse');
INSERT INTO `permission` VALUES ('17', '删除课程', 'deleCourse');
INSERT INTO `permission` VALUES ('18', '添加和编辑班级课程', 'addAndeditClassCourse');
INSERT INTO `permission` VALUES ('19', '添加或编辑课程老师', 'addAndeditCourseTeacher');

-- ----------------------------
-- Table structure for profession
-- ----------------------------
DROP TABLE IF EXISTS `profession`;
CREATE TABLE `profession` (
  `pro_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pro_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`pro_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of profession
-- ----------------------------
INSERT INTO `profession` VALUES ('1', '互联网电商');
INSERT INTO `profession` VALUES ('2', '软件开发');
INSERT INTO `profession` VALUES ('3', '新媒体');
INSERT INTO `profession` VALUES ('4', '环境艺术');
INSERT INTO `profession` VALUES ('5', 'UI设计');
INSERT INTO `profession` VALUES ('6', '电竞与影视');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `r_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `r_num` varchar(20) DEFAULT NULL,
  `r_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '1001', '校长');
INSERT INTO `role` VALUES ('2', '1002', '副校长');
INSERT INTO `role` VALUES ('3', '1003', '主任');
INSERT INTO `role` VALUES ('4', '1004', '老师');
INSERT INTO `role` VALUES ('5', '1005', '班主任');

-- ----------------------------
-- Table structure for role_permission_rel
-- ----------------------------
DROP TABLE IF EXISTS `role_permission_rel`;
CREATE TABLE `role_permission_rel` (
  `r_id` bigint(20) NOT NULL,
  `p_id` bigint(20) NOT NULL,
  PRIMARY KEY (`r_id`,`p_id`),
  KEY `p_id` (`p_id`),
  CONSTRAINT `role_permission_rel_ibfk_1` FOREIGN KEY (`r_id`) REFERENCES `role` (`r_id`),
  CONSTRAINT `role_permission_rel_ibfk_2` FOREIGN KEY (`p_id`) REFERENCES `permission` (`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission_rel
-- ----------------------------
INSERT INTO `role_permission_rel` VALUES ('1', '1');
INSERT INTO `role_permission_rel` VALUES ('2', '1');
INSERT INTO `role_permission_rel` VALUES ('1', '2');
INSERT INTO `role_permission_rel` VALUES ('2', '2');
INSERT INTO `role_permission_rel` VALUES ('3', '2');
INSERT INTO `role_permission_rel` VALUES ('4', '2');
INSERT INTO `role_permission_rel` VALUES ('1', '3');
INSERT INTO `role_permission_rel` VALUES ('4', '3');
INSERT INTO `role_permission_rel` VALUES ('1', '4');
INSERT INTO `role_permission_rel` VALUES ('2', '4');
INSERT INTO `role_permission_rel` VALUES ('1', '5');
INSERT INTO `role_permission_rel` VALUES ('2', '5');
INSERT INTO `role_permission_rel` VALUES ('4', '5');
INSERT INTO `role_permission_rel` VALUES ('1', '6');
INSERT INTO `role_permission_rel` VALUES ('2', '6');
INSERT INTO `role_permission_rel` VALUES ('1', '7');
INSERT INTO `role_permission_rel` VALUES ('2', '7');
INSERT INTO `role_permission_rel` VALUES ('1', '8');
INSERT INTO `role_permission_rel` VALUES ('2', '8');
INSERT INTO `role_permission_rel` VALUES ('1', '9');
INSERT INTO `role_permission_rel` VALUES ('2', '9');
INSERT INTO `role_permission_rel` VALUES ('1', '10');
INSERT INTO `role_permission_rel` VALUES ('2', '10');
INSERT INTO `role_permission_rel` VALUES ('1', '11');
INSERT INTO `role_permission_rel` VALUES ('2', '11');
INSERT INTO `role_permission_rel` VALUES ('1', '12');
INSERT INTO `role_permission_rel` VALUES ('2', '12');
INSERT INTO `role_permission_rel` VALUES ('1', '13');
INSERT INTO `role_permission_rel` VALUES ('2', '13');
INSERT INTO `role_permission_rel` VALUES ('1', '14');
INSERT INTO `role_permission_rel` VALUES ('2', '14');
INSERT INTO `role_permission_rel` VALUES ('1', '15');
INSERT INTO `role_permission_rel` VALUES ('2', '15');
INSERT INTO `role_permission_rel` VALUES ('1', '16');
INSERT INTO `role_permission_rel` VALUES ('2', '16');
INSERT INTO `role_permission_rel` VALUES ('1', '17');
INSERT INTO `role_permission_rel` VALUES ('2', '17');
INSERT INTO `role_permission_rel` VALUES ('1', '18');
INSERT INTO `role_permission_rel` VALUES ('2', '18');
INSERT INTO `role_permission_rel` VALUES ('1', '19');
INSERT INTO `role_permission_rel` VALUES ('2', '19');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `stu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stu_name` varchar(10) DEFAULT NULL,
  `stu_sex` tinyint(1) DEFAULT NULL,
  `stu_age` date DEFAULT NULL,
  `stu_enrol` date DEFAULT NULL,
  `stu_phone` varchar(30) DEFAULT NULL,
  `stu_site` varchar(100) DEFAULT NULL,
  `stu_progress` tinyint(1) DEFAULT NULL,
  `stu_pro` bigint(20) DEFAULT NULL,
  `stu_clas` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`stu_id`),
  KEY `stu_pro` (`stu_pro`),
  KEY `stu_clas` (`stu_clas`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`stu_pro`) REFERENCES `profession` (`pro_id`),
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`stu_clas`) REFERENCES `clazz` (`cla_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', 'zs', '0', '2002-08-22', '2017-07-13', '18374719203', '湖南省衡阳市', '1', '2', '1');
INSERT INTO `student` VALUES ('2', 'xs', '1', '2000-02-02', '2016-04-02', '18873476625', '湖南的长沙市', '1', '2', '1');
INSERT INTO `student` VALUES ('3', '廖伟', '1', '2002-08-22', '2019-03-18', '18374719203', '湖南省', '1', '2', '1');
INSERT INTO `student` VALUES ('4', '张三', '1', '2000-08-08', '2019-03-13', '18394918657', '北京市天安门', '1', '3', '13');
INSERT INTO `student` VALUES ('5', '王强', '1', '2001-11-10', '2019-03-27', '14739964934', '湖南省常德市', '1', '4', '3');
INSERT INTO `student` VALUES ('6', '唐三', '1', '2002-08-05', '2019-03-01', '18779646494', '香港澳门', '1', '2', '1');
INSERT INTO `student` VALUES ('7', 'zs123', '0', '2001-02-05', '2019-05-03', '14789399309', '湖南省衡阳市', '1', null, null);

-- ----------------------------
-- Table structure for student_teacher_rel
-- ----------------------------
DROP TABLE IF EXISTS `student_teacher_rel`;
CREATE TABLE `student_teacher_rel` (
  `stu_id` bigint(20) NOT NULL,
  `tea_id` bigint(20) NOT NULL,
  PRIMARY KEY (`stu_id`,`tea_id`),
  KEY `tea_id` (`tea_id`),
  CONSTRAINT `student_teacher_rel_ibfk_1` FOREIGN KEY (`stu_id`) REFERENCES `student` (`stu_id`),
  CONSTRAINT `student_teacher_rel_ibfk_2` FOREIGN KEY (`tea_id`) REFERENCES `teacher` (`tea_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_teacher_rel
-- ----------------------------
INSERT INTO `student_teacher_rel` VALUES ('1', '1');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `tea_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tea_name` varchar(10) DEFAULT NULL,
  `tea_sex` tinyint(1) DEFAULT NULL,
  `tea_age` date DEFAULT NULL,
  `tea_about` varchar(255) DEFAULT NULL,
  `tea_enrol` date DEFAULT NULL,
  `tea_phone` varchar(20) DEFAULT NULL,
  `tea_site` varchar(100) DEFAULT NULL,
  `pro_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`tea_id`),
  KEY `pro_id` (`pro_id`),
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`pro_id`) REFERENCES `profession` (`pro_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', '唐老师', '1', '1996-08-02', '很好', '2017-07-06', '1234585251', '湖南省衡阳耒阳', '1');
INSERT INTO `teacher` VALUES ('2', '张老师', '1', '1984-05-02', '优秀班主任', '2000-10-05', '1837495515', '湖南省长沙市', '3');
INSERT INTO `teacher` VALUES ('3', '高老师', '1', '1985-01-02', '软件开发', '2015-05-08', '1837475926', '湖南衡阳市', '2');
INSERT INTO `teacher` VALUES ('4', '袁兵', '1', '1995-02-20', '主授：AutoCAD建筑装饰施工图、3dsmax建筑装饰效果图表现、建筑装饰材料、室内设计原理、环境艺术专业导训系列等课程', '2012-03-19', '1863457244', '湖南省衡山', '5');
INSERT INTO `teacher` VALUES ('5', '周科峰', '1', '1985-08-01', '主授：3ds max建筑效果图表现技法、VRay室内渲染技法、SketchUp草图大师高级技法、VRay for SU渲染技法', '2019-01-16', '1847812524', '湖南省长沙天心区', '4');
INSERT INTO `teacher` VALUES ('6', '王林海', '1', '1985-04-04', '主授：OFFICE、AUTOCAD、SEKTCHUP。14年AUTOCAD 、OFFICE 课程教学经验。', '2015-05-06', null, null, '4');
INSERT INTO `teacher` VALUES ('7', '仇怡程', '0', '1998-02-14', '主授：Adobe Photoshop、AutoCAD、Google Sketchup、Lumion等设计软件.', '2013-02-04', null, null, '4');
INSERT INTO `teacher` VALUES ('8', '符旦红', '0', '1994-01-05', '主授：AutoCAD、3D max、photoshop等软件。八年的行业从业及教学经验', '2014-05-06', null, null, '4');
INSERT INTO `teacher` VALUES ('9', '何朗', '0', '1997-04-04', '主授：版式设计, Photoshop,CorelDRAW', '2017-03-05', null, null, '5');
INSERT INTO `teacher` VALUES ('10', '舒琴', '0', '1999-02-04', '主授： Photoshop软件,CoreIDRAW软件,平面设计,排版设计,VI设计,三大构成,美术设计', '2016-05-05', null, null, '5');
INSERT INTO `teacher` VALUES ('11', '董文慧', '0', '1997-05-06', '主授： Photoshop、Adobe Illustrator、corelDRAW。从业数年，拥有较丰富的实践经验', null, null, null, '5');
INSERT INTO `teacher` VALUES ('12', '陈曦', '0', '1992-05-05', '主授： web界面设计，移动端APP界面设计、DW网页设计，Photoshop图像处理', null, null, null, '5');
INSERT INTO `teacher` VALUES ('13', '贺湘', '0', '1998-04-06', '主授： 主授ps实操，cdr商业案例板式设计，ai软件课,很强的美学鉴赏能力，七年平面设计师的从业经验', null, null, null, '5');
INSERT INTO `teacher` VALUES ('14', '周垚', '1', '1997-04-02', '主授：Photoshop、CorelDRAW、Indesign。 十年专业IT教育工作经验；全国十佳讲师、CIW认证教师', null, null, null, '5');
INSERT INTO `teacher` VALUES ('15', '李雪林', '1', '1994-04-09', '主授： Javaweb，JavaScript，servlet/jsp等框架，MySQL/oracle数据库。', null, null, null, '2');
INSERT INTO `teacher` VALUES ('16', '熊伟', '1', '1990-04-02', '主授： HTML5+CSS3、javascript/JQUERY、MySQL/SQL Server数据库、SQL+CMS网站建设、就业导训等', null, null, null, '2');
INSERT INTO `teacher` VALUES ('17', '肖莱', '1', '1992-04-02', '精通：andriod所有控件。精通Eclipse开发工具，熟悉Android Studio、Ideal开发工具', null, null, null, '2');
INSERT INTO `teacher` VALUES ('18', '唐美华', '0', '1998-02-05', '主授：Java基础，javaoop,javaScript,Jquery,数据库，html...等,具有多年教学经验', null, null, null, '2');
INSERT INTO `teacher` VALUES ('19', '谭鑫', '0', '1995-05-09', '主授： HTML5+CSS3，JavaScript/jQuery，MySQL，SQL+CMS网站建设 项目经验', null, null, null, '2');
INSERT INTO `teacher` VALUES ('20', '杨建峰', '1', '1990-12-07', '主授： JavaSE,JavaEE,SSH框架,SSM框架,Oracle,Mysql，Maven,HTML,CSS,JavaScript等', null, null, null, '2');
INSERT INTO `teacher` VALUES ('21', '宋彬', '1', '1897-10-07', '主授： 计算机操作基础、计算机组装与维护、office办公自动化、网络工程管理、网络营销等', null, null, null, '2');
INSERT INTO `teacher` VALUES ('22', '徐邵阳', '1', '1999-10-07', '主授： 淘宝开店，淘宝精细化运营，天猫全案营销， 淘宝设计摄影等，10年电商运营经验，五年电商带领团队经验', null, null, null, '1');
INSERT INTO `teacher` VALUES ('23', '刘晓玲', '0', '1997-05-06', '主授：淘宝开店；淘宝天猫运营；电子商务数据化运营；电子商务客户关系管理；成考英语、成考语文', null, null, null, '1');
INSERT INTO `teacher` VALUES ('24', '董雪凤', '0', '1994-02-04', '主授： 微信营销、营销方案展示、office办公自动化、应用文写作、成考语文', null, null, null, '1');
INSERT INTO `teacher` VALUES ('25', '陈亮', '1', '1998-01-07', '熟练使用photoshop、Dreamweaver软件，从事天猫和京东运营工作三年，丰富的网店店铺运营经验', null, null, null, '1');
INSERT INTO `teacher` VALUES ('26', '龚叶', '0', '1999-01-08', '精通SEO搜索引擎优化、淘宝开店装修、店铺运营、网站策划及活动策划，5年的电商运营管理带团经验', '2019-05-08', '', '', '1');
INSERT INTO `teacher` VALUES ('27', '段玲', '0', '1997-01-05', '精通淘宝开店、店铺运营装修，seo搜索引擎优化及网络营销，熟悉Office、Photoshop、CAD等软件', null, null, null, '1');
INSERT INTO `teacher` VALUES ('28', '蒋俊杰', '1', '1994-02-07', '香蕉计划游戏签约解说艺人湖南汇玩、网易合作解说，任职解说三年时间。曾受邀解说金鹰电竞、百事杯、英特尔杯、雪碧杯等大型赛事', null, null, null, '6');
INSERT INTO `teacher` VALUES ('29', '申阳', '1', '1999-10-07', '前EP电子竞技俱乐部二队英雄联盟职业选手；多项全国比赛冠军、英雄联盟导师，五年丰富电竞教学、培训经验', null, null, null, '6');
INSERT INTO `teacher` VALUES ('30', '方小兵', '1', '1990-05-08', '主授：计算机网络技术基础、网络设计与管理、SEO、SEM、数据库管理、CEAC网络工程师认证讲师', null, null, null, '6');
INSERT INTO `teacher` VALUES ('31', '谢莹婷', '0', '1999-10-12', '主授：photoshop/aotocad/手绘/三大构成/素描色彩/等课程。主任多个家装设计案例', '2018-05-03', '1849276464', '湖南省衡阳', '6');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `admin` tinyint(1) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `role` (`role_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '123', '297254e9bfe0b8f39c682eda30bb9be0', '张三', '1', '1');
INSERT INTO `user` VALUES ('2', '1314', '0a9a058a1a7a66abc33b5307c3055f98', '张飞', '0', '3');
INSERT INTO `user` VALUES ('3', '3344', '80512d82e87e93282eda59620302e1d9', '唐老师', '0', '4');
INSERT INTO `user` VALUES ('4', '2291621571', 'b97239297e38b6e871e2a61d0217ab15', '廖伟', '0', '1');
INSERT INTO `user` VALUES ('5', '147', 'ba10912c61a84e5c99ccf5eca851ef74', '唐美华', '0', '4');
