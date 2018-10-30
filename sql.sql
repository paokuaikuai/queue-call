-- --------------------------------------------------------
-- 主机:                           192.168.7.154
-- 服务器版本:                        5.7.19-0ubuntu0.16.04.1 - (Ubuntu)
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 zq_queue.sys_business_type 结构
CREATE TABLE IF NOT EXISTS `sys_business_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '0' COMMENT '业务类型',
  `departId` int(11) DEFAULT NULL COMMENT '科室ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COMMENT='业务类型';

-- 正在导出表  zq_queue.sys_business_type 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `sys_business_type` DISABLE KEYS */;
INSERT INTO `sys_business_type` (`id`, `name`, `departId`) VALUES
	(1, '常规心电图', 1);
/*!40000 ALTER TABLE `sys_business_type` ENABLE KEYS */;

-- 导出  表 zq_queue.sys_department 结构
CREATE TABLE IF NOT EXISTS `sys_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `departCode` varchar(50) DEFAULT NULL COMMENT '科室编号',
  `departName` varchar(100) DEFAULT NULL COMMENT '科室名称',
  `departType` int(11) DEFAULT NULL COMMENT '科室类型',
  `firstNum` varchar(11) DEFAULT NULL COMMENT '号码第一个数',
  `screenNum` varchar(50) DEFAULT NULL COMMENT '屏号',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='科室表';

-- 正在导出表  zq_queue.sys_department 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `sys_department` DISABLE KEYS */;
INSERT INTO `sys_department` (`id`, `departCode`, `departName`, `departType`, `firstNum`, `screenNum`, `remark`, `createTime`, `updateTime`) VALUES
	(1, NULL, '心功能室', 1, 'A', NULL, '', '2018-09-25 08:55:08', '2018-09-25 08:55:08');
/*!40000 ALTER TABLE `sys_department` ENABLE KEYS */;

-- 导出  表 zq_queue.sys_func_queue 结构
CREATE TABLE IF NOT EXISTS `sys_func_queue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL DEFAULT '0' COMMENT '业务类型',
  `number` varchar(50) DEFAULT NULL COMMENT '排队号',
  `firstNum` varchar(11) DEFAULT NULL COMMENT '首字母',
  `patientCard` varchar(50) DEFAULT NULL COMMENT '病人卡号',
  `patientName` varchar(50) DEFAULT NULL COMMENT '病人名字',
  `doctorName` varchar(50) DEFAULT NULL COMMENT '医生名字',
  `departId` int(11) DEFAULT NULL COMMENT '科室ID',
  `roomId` int(11) DEFAULT NULL COMMENT '房号ID',
  `isVip` int(1) DEFAULT '0' COMMENT '是否VIP',
  `isFirst` int(1) DEFAULT '0' COMMENT '是否优先',
  `status` int(1) DEFAULT '1' COMMENT '就诊状态',
  `createTime` datetime DEFAULT NULL COMMENT '排号时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  zq_queue.sys_func_queue 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_func_queue` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_func_queue` ENABLE KEYS */;

-- 导出  表 zq_queue.sys_func_queue_history 结构
CREATE TABLE IF NOT EXISTS `sys_func_queue_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(50) DEFAULT NULL COMMENT '排队号',
  `patientCard` varchar(50) DEFAULT NULL COMMENT '病人卡号',
  `patientName` varchar(50) DEFAULT NULL COMMENT '病人名字',
  `doctorName` varchar(50) DEFAULT NULL COMMENT '医生名字',
  `departId` int(11) DEFAULT NULL COMMENT '科室ID',
  `roomId` int(11) DEFAULT NULL COMMENT '房号ID',
  `isVip` int(1) DEFAULT NULL COMMENT '是否VIP',
  `isFirst` int(1) DEFAULT NULL COMMENT '是否优先',
  `status` int(1) DEFAULT '0' COMMENT '就诊状态',
  `createTime` datetime DEFAULT NULL COMMENT '排号时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  zq_queue.sys_func_queue_history 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_func_queue_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_func_queue_history` ENABLE KEYS */;

-- 导出  表 zq_queue.sys_log 结构
CREATE TABLE IF NOT EXISTS `sys_log` (
  `logId` varchar(50) NOT NULL COMMENT '主键',
  `type` varchar(20) DEFAULT '0' COMMENT '类型',
  `title` varchar(200) DEFAULT 'CURRENT_TIMESTAMP' COMMENT '标题',
  `remoteAddr` varchar(200) DEFAULT 'CURRENT_TIMESTAMP' COMMENT '地址',
  `requestUri` varchar(256) DEFAULT '0' COMMENT 'url',
  `method` varchar(256) DEFAULT '0' COMMENT '方法',
  `params` varchar(256) DEFAULT '1' COMMENT '参数',
  `exception` text COMMENT '异常',
  `operateDate` datetime DEFAULT NULL COMMENT '操作时间',
  `timeout` varchar(25) DEFAULT NULL COMMENT '时常',
  `userId` varchar(50) DEFAULT NULL COMMENT '用户id',
  `result` text COMMENT '请求结果',
  `duration` bigint(20) DEFAULT NULL COMMENT '持续时间',
  `url` varchar(512) DEFAULT NULL COMMENT '请求url',
  `user_agent` varchar(512) DEFAULT NULL COMMENT '请求ua',
  PRIMARY KEY (`logId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  zq_queue.sys_log 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;

-- 导出  表 zq_queue.sys_permission 结构
CREATE TABLE IF NOT EXISTS `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) DEFAULT NULL COMMENT '父ID',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `url` varchar(255) NOT NULL COMMENT '权限URL',
  `sort` int(11) NOT NULL COMMENT '排序',
  `isMenu` bit(1) NOT NULL COMMENT '是否菜单',
  `isEnable` bit(1) NOT NULL COMMENT '是否启用',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- 正在导出表  zq_queue.sys_permission 的数据：~47 rows (大约)
/*!40000 ALTER TABLE `sys_permission` DISABLE KEYS */;
INSERT INTO `sys_permission` (`id`, `parentId`, `name`, `url`, `sort`, `isMenu`, `isEnable`, `icon`) VALUES
	(39, NULL, '导航栏', '/admin/admin/menu', 99, b'0', b'1', ''),
	(41, NULL, '个人中心', '/admin/profile', 89, b'1', b'1', 'fa fa-user'),
	(42, 41, '修改密码', '/admin/profile/savePassword', 1, b'0', b'1', ''),
	(58, NULL, '系统管理', 'w', 43, b'1', b'1', 'fa fa-align-justify black'),
	(59, 58, '用户管理', '/admin/user', 4, b'1', b'1', 'fa fa-users blue'),
	(60, 58, '角色管理', '/admin/role', 3, b'1', b'1', 'fa fa-sitemap'),
	(61, 58, '模块管理', '/admin/permission', 2, b'1', b'1', 'fa fa-tree'),
	(62, 59, '管理员列表', '/admin/user/list', 7, b'0', b'1', ''),
	(63, 59, '管理员新增', '/admin/user/edit', 6, b'0', b'1', 'fa-plus-circle blue'),
	(64, 60, '角色列表', '/admin/role/list', 6, b'0', b'1', ''),
	(65, 61, '权限树列表', '/admin/permission/nodes', 1, b'0', b'1', ''),
	(66, 59, '管理员禁用', '/admin/user/enable', 5, b'0', b'1', 'fa-lock orange'),
	(67, 59, '管理员启用', '/admin/user/enable', 4, b'0', b'1', '/admin/user/enable'),
	(68, 59, '管理员删除', '/admin/user/delete', 3, b'0', b'1', 'fa-trash-o red'),
	(69, 59, '重置密码', '/admin/user/resetPassword', 2, b'0', b'1', 'fa-key grey'),
	(70, 59, '分配角色', '/admin/user/allocate', 1, b'0', b'1', 'fa-cog grey'),
	(71, 59, '管理员保存', '/admin/user/save', 1, b'0', b'1', ''),
	(72, 60, '角色新增', '/admin/role/edit', 5, b'0', b'1', 'fa-plus-circle blue'),
	(73, 60, '角色禁用', '/admin/role/enable', 4, b'0', b'1', 'fa-lock orange'),
	(74, 60, '角色启用', '/admin/role/enable', 3, b'0', b'1', 'fa-unlock green'),
	(75, 60, '角色删除', '/admin/role/delete', 2, b'0', b'1', 'fa-trash-o red'),
	(76, 60, '角色授权', '/admin/role/allocate', 1, b'0', b'1', 'fa-cog grey'),
	(77, 60, '角色保存', '/admin/role/save', 1, b'0', b'1', ''),
	(78, 61, '权限保存', '/admin/permission/save', 1, b'0', b'1', ''),
	(79, 61, '权限删除', '/admin/permission/delete', 1, b'0', b'1', ''),
	(83, 58, '日志管理', '/admin/log', 1, b'1', b'1', ''),
	(84, 83, '日志列表', '/admin/log/list', 1, b'0', b'1', ''),
	(86, NULL, '功能科室排队', '/queue/list', 60, b'1', b'1', 'fa fa-ellipsis-h'),
	(87, NULL, '功能科室呼号', '/queue/call', 59, b'1', b'1', 'fa fa-bullhorn'),
	(88, NULL, '功能科室屏幕', '/queue/screen', 58, b'1', b'1', 'fa fa-laptop'),
	(89, NULL, '系统设置', '1', 40, b'1', b'1', 'fa fa-cog'),
	(90, 89, '科室管理', '/admin/department', 6, b'1', b'1', ''),
	(91, 89, '诊室管理', '/admin/room', 5, b'1', b'1', ''),
	(92, 89, '其它设置', '1', 4, b'1', b'1', ''),
	(93, 89, '模板管理', '1', 3, b'1', b'1', ''),
	(94, NULL, '系统对接', '1', 1, b'1', b'1', 'fa fa-exchange'),
	(95, 90, '科室新增', '/admin/department/edit', 1, b'0', b'1', 'fa-plus-circle blue'),
	(96, 90, '科室列表', '/admin/department/list', 13, b'0', b'1', ''),
	(97, 90, '科室删除', '/admin/department/delete', 1, b'0', b'1', 'fa-trash-o red'),
	(98, 91, '诊室列表', '/admin/room/list', 1, b'0', b'1', ''),
	(99, 91, '诊室新增', '/admin/room/edit', 1, b'0', b'1', 'fa-plus-circle blue'),
	(100, 91, '诊室删除', '/admin/room/delete', 1, b'0', b'1', 'fa-trash-o red'),
	(101, 89, '叫号管理', '/queue/maneger', 1, b'1', b'1', 'fa fa-cog'),
	(102, 89, '业务管理', '/admin/business', 1, b'1', b'1', ''),
	(103, 102, '业务列表', '/admin/business/list', 1, b'0', b'1', ''),
	(104, 102, '业务新增', '/admin/business/add', 1, b'0', b'1', ''),
	(105, 102, '业务删除', '/admin/business/delete', 1, b'0', b'1', '');
/*!40000 ALTER TABLE `sys_permission` ENABLE KEYS */;

-- 导出  表 zq_queue.sys_queuedata 结构
CREATE TABLE IF NOT EXISTS `sys_queuedata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data` longtext COMMENT '队列数据',
  `departId` int(11) DEFAULT NULL COMMENT '科室Id',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='队列表';

-- 正在导出表  zq_queue.sys_queuedata 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_queuedata` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_queuedata` ENABLE KEYS */;

-- 导出  表 zq_queue.sys_queuelog 结构
CREATE TABLE IF NOT EXISTS `sys_queuelog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(50) DEFAULT NULL COMMENT '排队号',
  `firstNum` varchar(11) DEFAULT NULL COMMENT '首字母',
  `patientCard` varchar(50) DEFAULT NULL COMMENT '病人卡号',
  `patientName` varchar(50) DEFAULT NULL COMMENT '病人名字',
  `doctorName` varchar(50) DEFAULT NULL COMMENT '医生名字',
  `departId` int(11) DEFAULT NULL COMMENT '科室ID',
  `roomId` int(11) DEFAULT NULL COMMENT '房号ID',
  `isVip` int(1) DEFAULT '0' COMMENT '是否VIP',
  `isFirst` int(1) DEFAULT '0' COMMENT '是否优先',
  `status` int(1) DEFAULT '1' COMMENT '就诊状态',
  `createTime` datetime DEFAULT NULL COMMENT '排号时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;

-- 正在导出表  zq_queue.sys_queuelog 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_queuelog` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_queuelog` ENABLE KEYS */;

-- 导出  表 zq_queue.sys_re_role_permission 结构
CREATE TABLE IF NOT EXISTS `sys_re_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) NOT NULL COMMENT '角色ID',
  `permissionId` int(11) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`),
  KEY `FK_SYS_RE_R_REFERENCE_SYS_PERM` (`permissionId`),
  KEY `FK_SYS_RE_R_REFERENCE_SYS_ROLE` (`roleId`),
  CONSTRAINT `FK_SYS_RE_R_REFERENCE_SYS_PERM` FOREIGN KEY (`permissionId`) REFERENCES `sys_permission` (`id`),
  CONSTRAINT `FK_SYS_RE_R_REFERENCE_SYS_ROLE` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=946 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- 正在导出表  zq_queue.sys_re_role_permission 的数据：~47 rows (大约)
/*!40000 ALTER TABLE `sys_re_role_permission` DISABLE KEYS */;
INSERT INTO `sys_re_role_permission` (`id`, `roleId`, `permissionId`) VALUES
	(899, 1, 39),
	(900, 1, 41),
	(901, 1, 42),
	(902, 1, 86),
	(903, 1, 87),
	(904, 1, 88),
	(905, 1, 58),
	(906, 1, 59),
	(907, 1, 62),
	(908, 1, 63),
	(909, 1, 66),
	(910, 1, 67),
	(911, 1, 68),
	(912, 1, 69),
	(913, 1, 70),
	(914, 1, 71),
	(915, 1, 60),
	(916, 1, 64),
	(917, 1, 72),
	(918, 1, 73),
	(919, 1, 74),
	(920, 1, 75),
	(921, 1, 76),
	(922, 1, 77),
	(923, 1, 61),
	(924, 1, 65),
	(925, 1, 78),
	(926, 1, 79),
	(927, 1, 83),
	(928, 1, 84),
	(929, 1, 89),
	(930, 1, 90),
	(931, 1, 96),
	(932, 1, 95),
	(933, 1, 97),
	(934, 1, 91),
	(935, 1, 98),
	(936, 1, 99),
	(937, 1, 100),
	(938, 1, 92),
	(939, 1, 93),
	(940, 1, 101),
	(941, 1, 102),
	(942, 1, 103),
	(943, 1, 104),
	(944, 1, 105),
	(945, 1, 94);
/*!40000 ALTER TABLE `sys_re_role_permission` ENABLE KEYS */;

-- 导出  表 zq_queue.sys_re_user_role 结构
CREATE TABLE IF NOT EXISTS `sys_re_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL COMMENT '用户ID ',
  `roleId` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`),
  KEY `FK_SYS_RE_U_REFERENCE_SYS_USER` (`userId`),
  KEY `FK_SYS_RE_U_REFERENCE_SYS_ROLE` (`roleId`),
  CONSTRAINT `FK_SYS_RE_U_REFERENCE_SYS_ROLE` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FK_SYS_RE_U_REFERENCE_SYS_USER` FOREIGN KEY (`userId`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- 正在导出表  zq_queue.sys_re_user_role 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `sys_re_user_role` DISABLE KEYS */;
INSERT INTO `sys_re_user_role` (`id`, `userId`, `roleId`) VALUES
	(11, 2, 1);
/*!40000 ALTER TABLE `sys_re_user_role` ENABLE KEYS */;

-- 导出  表 zq_queue.sys_role 结构
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '名称',
  `sort` int(11) NOT NULL COMMENT '排序',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `isEnable` bit(1) NOT NULL COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- 正在导出表  zq_queue.sys_role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` (`id`, `name`, `sort`, `description`, `isEnable`) VALUES
	(1, '系统管理员', 1, '', b'1');
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;

-- 导出  表 zq_queue.sys_room 结构
CREATE TABLE IF NOT EXISTS `sys_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `departId` int(11) DEFAULT NULL COMMENT '科室Id',
  `roomCode` varchar(50) DEFAULT NULL COMMENT '诊室编码',
  `roomName` varchar(100) DEFAULT NULL COMMENT '诊室名称',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- 正在导出表  zq_queue.sys_room 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `sys_room` DISABLE KEYS */;
INSERT INTO `sys_room` (`id`, `departId`, `roomCode`, `roomName`, `createTime`, `updateTime`) VALUES
	(1, 1, '001', '001', '2018-09-25 08:55:29', '2018-09-25 11:39:41');
/*!40000 ALTER TABLE `sys_room` ENABLE KEYS */;

-- 导出  表 zq_queue.sys_user 结构
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(50) NOT NULL COMMENT '登录名',
  `password` varchar(100) NOT NULL COMMENT '密码(加密)',
  `realname` varchar(100) DEFAULT NULL COMMENT '真名',
  `position` varchar(100) DEFAULT NULL COMMENT '职位',
  `telephone` varchar(20) DEFAULT NULL COMMENT '电话',
  `picture` varchar(100) DEFAULT NULL COMMENT '头像',
  `departId` int(11) DEFAULT NULL COMMENT '科室ID',
  `roomId` int(11) DEFAULT NULL,
  `lastLoginIp` varchar(20) DEFAULT NULL COMMENT '最后登录IP',
  `lastLoginTime` datetime DEFAULT NULL COMMENT '最后登录时间',
  `loginCount` int(11) DEFAULT NULL COMMENT '登录总次数',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `isEnable` bit(1) NOT NULL COMMENT '是否启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 正在导出表  zq_queue.sys_user 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`id`, `account`, `password`, `realname`, `position`, `telephone`, `picture`, `departId`, `roomId`, `lastLoginIp`, `lastLoginTime`, `loginCount`, `createTime`, `isEnable`) VALUES
	(2, 'admin', '26524bdf4ea266f131566a89e8f4972c', '1', '1', '1', NULL, 1, 1, NULL, NULL, 0, '2015-06-02 11:31:56', b'1'),
	(7, 'test4', '26524bdf4ea266f131566a89e8f4972c', '12', '22', '3123', NULL, 1, 6, NULL, NULL, 0, '2018-10-22 12:24:40', b'1');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;

-- 导出  表 zq_queue.t_ecg 结构
CREATE TABLE IF NOT EXISTS `t_ecg` (
  `reqId` varchar(11) NOT NULL,
  `patientId` varchar(11) DEFAULT NULL,
  `ecgPic` blob,
  `ecgResult` varchar(100) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `hr` varchar(10) DEFAULT NULL,
  `pr` varchar(10) DEFAULT NULL,
  `qrs` varchar(10) DEFAULT NULL,
  `qt` varchar(11) DEFAULT NULL,
  `qtc` varchar(11) DEFAULT NULL,
  `p_` varchar(11) DEFAULT NULL,
  `qrs_` varchar(11) DEFAULT NULL,
  `t_` varchar(11) DEFAULT NULL,
  `rv5` varchar(100) DEFAULT NULL,
  `sv1` varchar(100) DEFAULT NULL,
  `reportDoctor` varchar(100) DEFAULT NULL,
  `reviewDoctor` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  zq_queue.t_ecg 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_ecg` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_ecg` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
