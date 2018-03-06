/*
Navicat MySQL Data Transfer

Source Server         : 120.78.143.143
Source Server Version : 50716
Source Host           : 120.78.143.143:3306
Source Database       : jielin_fast

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2018-03-03 19:46:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
  `agent_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '代理商进货主键',
  `user_id` int(11) NOT NULL COMMENT '代理商id',
  `goods_id` int(11) NOT NULL COMMENT '代理商品',
  `goods_s_price` decimal(10,2) NOT NULL COMMENT '代理商商品销售价格',
  `is_on_sale` tinyint(1) NOT NULL DEFAULT '1' COMMENT '上架状态：0下架 1上架',
  `store_quantity` int(11) NOT NULL DEFAULT '0' COMMENT '库存数量',
  PRIMARY KEY (`agent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='代理商代理的商品列表';

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES ('1', '1', '1', '0.19', '1', '400');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品主键',
  `goods_type_id` int(11) NOT NULL COMMENT '商品类别id',
  `goods_name` varchar(255) NOT NULL COMMENT '商品名称',
  `goods_cost_price` decimal(10,2) NOT NULL COMMENT '商品成本价格',
  `goods_rec_price` decimal(10,2) NOT NULL COMMENT '商品推荐销售价格',
  `total_stock_quantity` int(11) NOT NULL DEFAULT '0' COMMENT '总库存数量',
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='虚拟商品表';

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '1', '快刷名片赞1000可多拍|1000', '0.16', '0.17', '5000');
INSERT INTO `goods` VALUES ('2', '2', '爱奇艺,腾讯视频,优酷,土豆,乐视,搜狐,PPTV,芒果VIP电影在线看3月', '4.50', '5.00', '500');

-- ----------------------------
-- Table structure for goods_type
-- ----------------------------
DROP TABLE IF EXISTS `goods_type`;
CREATE TABLE `goods_type` (
  `goods_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(100) NOT NULL DEFAULT '' COMMENT '商品分类',
  PRIMARY KEY (`goods_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='商品分类';

-- ----------------------------
-- Records of goods_type
-- ----------------------------
INSERT INTO `goods_type` VALUES ('1', 'QQ业务');
INSERT INTO `goods_type` VALUES ('2', '快手业务');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) NOT NULL COMMENT '客户唯一标识',
  `seller_id` int(11) NOT NULL COMMENT '卖家即代理商',
  `goods_id` int(11) NOT NULL COMMENT '商品id',
  `order_price` decimal(10,2) NOT NULL COMMENT '下单时的价格',
  `goods_quantity` int(11) NOT NULL COMMENT '下单商品数量',
  `total_sum` decimal(10,2) NOT NULL COMMENT '总金额',
  `order_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '下单时间',
  `order_status` int(4) NOT NULL DEFAULT '0' COMMENT '订单状态 0 新订单待支付  1 已支付正在处理 2 已取消 3 已完成 4 任务失败 ',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('1', '979783618', '0', '1', '0.18', '10', '1.80', '2017-12-16 20:44:25', '0', null);

-- ----------------------------
-- Table structure for storage_record
-- ----------------------------
DROP TABLE IF EXISTS `storage_record`;
CREATE TABLE `storage_record` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '入库记录主键',
  `user_id` int(11) NOT NULL,
  `goods_id` int(11) NOT NULL,
  `storage_price` decimal(10,2) NOT NULL,
  `storage_quantity` int(11) NOT NULL COMMENT '单次入库总量',
  `storage_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '入库时间',
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='入库记录表';

-- ----------------------------
-- Records of storage_record
-- ----------------------------

-- ----------------------------
-- Table structure for sys_authority
-- ----------------------------
DROP TABLE IF EXISTS `sys_authority`;
CREATE TABLE `sys_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `auth_code` varchar(255) DEFAULT NULL COMMENT '权限代码',
  `auth_name` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `url` varchar(255) DEFAULT NULL COMMENT '接口地址',
  `method` varchar(255) DEFAULT NULL COMMENT '请求方法类型:1 get,2 post,3 input,4 delete',
  `resource_name` varchar(255) DEFAULT NULL COMMENT '操作的资源名称',
  `resource_type` varchar(255) DEFAULT NULL COMMENT '资源类型：1接口资源，2服务资源',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_code` (`auth_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_authority
-- ----------------------------
INSERT INTO `sys_authority` VALUES ('2', 'user_add', '增加用户', '/user', 'post', null, '服务', null);
INSERT INTO `sys_authority` VALUES ('3', 'user_delete', '删除用户', '/user', 'delete', null, '接口', null);
INSERT INTO `sys_authority` VALUES ('4', 'sys_authority_save', '保存权限', '/sys_authority', 'post', null, '接口', null);
INSERT INTO `sys_authority` VALUES ('5', 'sys_authority_delete', '删除权限', '/sys_authority', 'delete', 'sys_authority', '接口', null);
INSERT INTO `sys_authority` VALUES ('6', 'waefaw', 'awfew', 'afew', 'awfe', 'awef', 'awef', null);
INSERT INTO `sys_authority` VALUES ('7', 'awef', 'awef', 'awef', 'awef', 'awef', 'awe', null);
INSERT INTO `sys_authority` VALUES ('8', 'awewf', 'awef', 'tqw', 'rqe', 'rqwe', 'qrwer', null);
INSERT INTO `sys_authority` VALUES ('9', 'qrwe', 'rwe', 'qrwerqw', 'rwrewrw', 'rwerq', 'htsgreg', null);
INSERT INTO `sys_authority` VALUES ('10', 'ewf', 'w', 'wr', 'fwerf', 'etrt', 'werqwe', null);
INSERT INTO `sys_authority` VALUES ('11', 'tetwe', 'etafewf', 'ew', 'awef', 'waezvdh', 'hr', null);
INSERT INTO `sys_authority` VALUES ('12', 'rhsr', 'ser', 'hdr', 'bxf', 'dnf', 'dht', null);
INSERT INTO `sys_authority` VALUES ('13', 'srht', 'hrth', 'dhrt', 'drht', 'rt', 'shrt', null);
INSERT INTO `sys_authority` VALUES ('14', 'rhst', 'hr', 'xdh', 'ddgr', 'sgr', 'hrt', null);
INSERT INTO `sys_authority` VALUES ('15', 'hjdhrgsgr', 'drhegseg', 'rthsgrse', 'rth', 'dfs', 'gsg', null);
INSERT INTO `sys_authority` VALUES ('16', 'ser', 'hrd', 'hdrt', 'xfd', 'rj', 'dj', null);
INSERT INTO `sys_authority` VALUES ('17', 'yt', 'yjd', 'rtr', 'dts', 'rt', 'hjsr', null);
INSERT INTO `sys_authority` VALUES ('18', 'jyu', 'srth', 'hrt', 'sehr', 'sht', 'rth', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色代码名称',
  `role_desc` varchar(255) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ADMIN', '超级管理员');
INSERT INTO `sys_role` VALUES ('2', 'AGENCY', '代理');
INSERT INTO `sys_role` VALUES ('6', 'USER', '普通用户角色');

-- ----------------------------
-- Table structure for sys_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_auth`;
CREATE TABLE `sys_role_auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色对应id',
  `auth_id` int(11) DEFAULT NULL COMMENT '权限对应id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_id` (`role_id`,`auth_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_auth
-- ----------------------------
INSERT INTO `sys_role_auth` VALUES ('1', '1', '1');
INSERT INTO `sys_role_auth` VALUES ('38', '1', '2');
INSERT INTO `sys_role_auth` VALUES ('36', '1', '3');
INSERT INTO `sys_role_auth` VALUES ('49', '1', '4');
INSERT INTO `sys_role_auth` VALUES ('14', '1', '5');
INSERT INTO `sys_role_auth` VALUES ('64', '1', '12');
INSERT INTO `sys_role_auth` VALUES ('63', '1', '13');
INSERT INTO `sys_role_auth` VALUES ('62', '1', '14');
INSERT INTO `sys_role_auth` VALUES ('61', '1', '15');
INSERT INTO `sys_role_auth` VALUES ('60', '1', '16');
INSERT INTO `sys_role_auth` VALUES ('59', '1', '17');
INSERT INTO `sys_role_auth` VALUES ('58', '1', '18');
INSERT INTO `sys_role_auth` VALUES ('13', '2', '1');
INSERT INTO `sys_role_auth` VALUES ('16', '2', '2');
INSERT INTO `sys_role_auth` VALUES ('19', '2', '3');
INSERT INTO `sys_role_auth` VALUES ('15', '2', '4');
INSERT INTO `sys_role_auth` VALUES ('50', '2', '5');
INSERT INTO `sys_role_auth` VALUES ('4', '2', '9');
INSERT INTO `sys_role_auth` VALUES ('5', '3', '4');
INSERT INTO `sys_role_auth` VALUES ('24', '6', '1');
INSERT INTO `sys_role_auth` VALUES ('47', '6', '2');
INSERT INTO `sys_role_auth` VALUES ('46', '6', '3');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(100) NOT NULL DEFAULT '' COMMENT '用户账号(只能是字母或者特殊字符)',
  `password` varchar(100) NOT NULL COMMENT '密码(符合一定规则)',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名称',
  `sex` int(4) DEFAULT '0' COMMENT '性别:0 不公开，1 男，2 女',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL COMMENT 'qq',
  `wechat_id` varchar(255) DEFAULT NULL COMMENT '微信号',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `status` int(4) DEFAULT NULL COMMENT '账号状态：0正常，1,"账号被锁定"，2,"账号过期"，3,"密码过期"，4,"账号未激活"',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'jack', '123456', '胡上杰', '1', '979783618@qq.com', '15579870840', null, '15579870840', '1', '1', '2018-02-24 22:54:41');
INSERT INTO `sys_user` VALUES ('2', '15579870840', '$2a$10$hVNJmIKt1U/faMmH8GuQeuWpa98ffytc8p1MY5PvZMgaaHfvd6Wza', null, null, null, null, null, null, '1', '0', '2018-02-25 14:03:30');

-- ----------------------------
-- Table structure for withdraw_record
-- ----------------------------
DROP TABLE IF EXISTS `withdraw_record`;
CREATE TABLE `withdraw_record` (
  `withdraw_rec_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '发起提现用户id',
  `withdraw_sum` decimal(10,2) NOT NULL COMMENT '提现金额',
  `status` tinyint(1) NOT NULL COMMENT '转账状态',
  `money_left` decimal(10,2) NOT NULL COMMENT '提现后剩余虚拟金额',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录时间',
  PRIMARY KEY (`withdraw_rec_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='提现记录表';

-- ----------------------------
-- Records of withdraw_record
-- ----------------------------
