/*
 Navicat Premium Dump SQL

 Source Server         : Mysql90
 Source Server Type    : MySQL
 Source Server Version : 90000 (9.0.0)
 Source Host           : localhost:3308
 Source Schema         : codegen_java

 Target Server Type    : MySQL
 Target Server Version : 90000 (9.0.0)
 File Encoding         : 65001

 Date: 14/07/2025 03:46:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_product_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_info`;
CREATE TABLE `tb_product_info`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `company_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '公司ID',
  `code` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品编号',
  `product_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品名称',
  `price` decimal(15, 2) NULL DEFAULT NULL COMMENT '价格',
  `sku_type` tinyint NULL DEFAULT NULL COMMENT '类型',
  `color_type` tinyint NULL DEFAULT NULL COMMENT '颜色类型',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_date` date NULL DEFAULT NULL COMMENT '创建日期',
  `stock` bigint NULL DEFAULT NULL COMMENT '库存',
  `status` tinyint NULL DEFAULT NULL COMMENT '状态:0:未上架 1:已上架',
  `is_del` tinyint NULL DEFAULT NULL COMMENT '0:删除 1:正常',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_code`(`code` ASC) USING BTREE,
  UNIQUE INDEX `idx_sku_color`(`sku_type` ASC, `color_type` ASC) USING BTREE,
  INDEX `idx_name`(`product_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_product_info
-- ----------------------------
INSERT INTO `tb_product_info` VALUES (4, '100000', '10002', '苹果12_02', 5001.10, 1, 0, '2022-08-27 10:41:52', '2022-08-27', 110, 1, NULL);
INSERT INTO `tb_product_info` VALUES (9, '100000', '10004', 'update by 9', 5001.10, 4, 2, '2022-08-27 10:41:52', '2022-08-27', 110, 1, NULL);
INSERT INTO `tb_product_info` VALUES (10, '100000', '10007', 'update by code', 4000.00, 5, 3, '2022-08-28 10:41:52', '2022-08-28', 1000, 0, NULL);
INSERT INTO `tb_product_info` VALUES (12, NULL, '10006', '测试1', NULL, 6, 0, '2025-07-11 01:20:17', '2025-07-11', NULL, NULL, NULL);
INSERT INTO `tb_product_info` VALUES (14, NULL, NULL, '测试2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_product_info` VALUES (21, NULL, '00000002', 'name002', NULL, NULL, NULL, '2025-07-11 20:55:30', '2025-07-11', NULL, NULL, NULL);
INSERT INTO `tb_product_info` VALUES (23, NULL, '10008', 'iPhone 13 Pro', 7999.00, 1, 2, '2022-08-27 10:42:52', '2022-08-27', 200, NULL, NULL);
INSERT INTO `tb_product_info` VALUES (24, NULL, '10009', 'Samsung Galaxy S22', 6999.00, 2, 1, '2022-08-27 10:43:52', '2022-08-27', 150, NULL, NULL);
INSERT INTO `tb_product_info` VALUES (25, NULL, '10010', 'Huawei Mate 50', 5999.00, 3, 0, '2022-08-27 10:44:52', '2022-08-27', 180, NULL, NULL);
INSERT INTO `tb_product_info` VALUES (26, NULL, '10011', 'Xiaomi 12', 3999.00, 4, 3, '2022-08-27 10:45:52', '2022-08-27', 250, NULL, NULL);
INSERT INTO `tb_product_info` VALUES (27, NULL, '10012', 'OnePlus 10', 4999.00, 5, 1, '2022-08-27 10:46:52', '2022-08-27', 130, NULL, NULL);
INSERT INTO `tb_product_info` VALUES (28, NULL, '10013', 'Oppo Find X5', 5499.00, 6, 2, '2022-08-27 10:47:52', '2022-08-27', 170, NULL, NULL);
INSERT INTO `tb_product_info` VALUES (29, NULL, '10014', 'Google Pixel 6', 6499.00, 7, 0, '2022-08-27 10:48:52', '2022-08-27', 120, NULL, NULL);
INSERT INTO `tb_product_info` VALUES (30, NULL, '10015', 'Sony Xperia 1 IV', 7299.00, 8, 3, '2022-08-27 10:49:52', '2022-08-27', 90, NULL, NULL);
INSERT INTO `tb_product_info` VALUES (31, NULL, '10016', 'Nokia G50', 2499.00, 9, 1, '2022-08-27 10:50:52', '2022-08-27', 300, NULL, NULL);
INSERT INTO `tb_product_info` VALUES (32, NULL, '10017', 'ASUS ROG Phone 6', 5999.00, 10, 2, '2022-08-27 10:51:52', '2022-08-27', 140, NULL, NULL);
INSERT INTO `tb_product_info` VALUES (33, NULL, '10018', 'Motorola Edge 30', 3299.00, 11, 0, '2022-08-27 10:52:52', '2022-08-27', 220, NULL, NULL);
INSERT INTO `tb_product_info` VALUES (34, NULL, '10019', 'LG Velvet 2', 4199.00, 12, 3, '2022-08-27 10:53:52', '2022-08-27', 160, NULL, NULL);
INSERT INTO `tb_product_info` VALUES (35, NULL, '10020', 'HTC U22', 3899.00, 13, 1, '2022-08-27 10:54:52', '2022-08-27', 190, NULL, NULL);
INSERT INTO `tb_product_info` VALUES (36, NULL, '10021', 'BlackBerry KEY5', 6299.00, 14, 2, '2022-08-27 10:55:52', '2022-08-27', 80, NULL, NULL);
INSERT INTO `tb_product_info` VALUES (37, NULL, '10022', '折叠手机 ZTE Axon Fold', 8999.00, 15, 0, '2022-08-27 10:56:52', '2022-08-27', 50, NULL, NULL);
INSERT INTO `tb_product_info` VALUES (38, NULL, '10023', 'Realme GT 2 Pro', 4699.00, 16, 3, '2022-08-27 10:57:52', '2022-08-27', 210, NULL, NULL);
INSERT INTO `tb_product_info` VALUES (39, NULL, '10024', 'Vivo X80', 5299.00, 17, 1, '2022-08-27 10:58:52', '2022-08-27', 135, NULL, NULL);
INSERT INTO `tb_product_info` VALUES (40, NULL, '10025', 'Meizu 18s', 4999.00, 18, 2, '2022-08-27 10:59:52', '2022-08-27', 100, NULL, NULL);
INSERT INTO `tb_product_info` VALUES (41, NULL, '10026', '折叠屏华为Mate X3', 12999.00, 19, 0, '2022-08-27 11:00:52', '2022-08-27', 30, NULL, NULL);
INSERT INTO `tb_product_info` VALUES (42, NULL, '10027', 'Redmi Note 11', 2199.00, 20, 3, '2022-08-27 11:01:52', '2022-08-27', 260, NULL, NULL);
INSERT INTO `tb_product_info` VALUES (68, NULL, '15522', 'iPhone 14 Pro', 7999.00, 3, 4, '2022-08-27 10:42:52', '2022-08-27', 200, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
