/*
 Navicat Premium Data Transfer

 Source Server         : mysql-latest
 Source Server Type    : MySQL
 Source Server Version : 80400
 Source Host           : localhost:3306
 Source Schema         : fe-blog-system

 Target Server Type    : MySQL
 Target Server Version : 80400
 File Encoding         : 65001

 Date: 21/06/2024 13:21:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
                         `blog_id` int NOT NULL AUTO_INCREMENT COMMENT '博客主键',
                         `title` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '标题',
                         `content` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '内容',
                         `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '描述',
                         `type` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创造类型（原创，转载，二创）',
                         `deleted` int NOT NULL DEFAULT 0 COMMENT '逻辑删除字段 0-未删除 1-删除',
                         `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                         `status` int NULL DEFAULT NULL COMMENT '状态(1,草稿，2已发布，3已过时（已删除）)',
                         `user_id` int NULL DEFAULT NULL COMMENT '作者id',
                         `field_id` int NULL DEFAULT NULL COMMENT '领域id',
                         `author` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '作者名字',
                         PRIMARY KEY (`blog_id`) USING BTREE,
                         UNIQUE INDEX `index_id`(`blog_id` ASC) USING BTREE,
                         INDEX `fieldID`(`field_id` ASC) USING BTREE,
                         INDEX `USER_ID`(`user_id` ASC) USING BTREE,
                         CONSTRAINT `FIELD_ID` FOREIGN KEY (`field_id`) REFERENCES `field` (`field_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                         CONSTRAINT `USER_ID` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES (62, 'FE个人博客系统介绍', '<h1 id=\"h1-fe-blog-system\"><a name=\"fe-blog-system\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>fe-blog-system</h1><h4 id=\"h4-u4ECBu7ECD\"><a name=\"介绍\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>介绍</h4><p>FE个人博客管理系统，后端结合MyBatis框架，前端结合Layui的一个JavaWeb项目</p>\n<h3 id=\"h3-u8F6Fu4EF6u67B6u6784\"><a name=\"软件架构\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>软件架构</h3><p>MVC三层架构</p>\n<h3 id=\"h3-u524Du7AEFu6280u672Fu6808\"><a name=\"前端技术栈\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>前端技术栈</h3><p>HTML、CSS、JavaScript、sass、Less、Layui</p>\n<h3 id=\"h3-u540Eu7AEFu6280u672Fu6808\"><a name=\"后端技术栈\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>后端技术栈</h3><p>Java8、Maven、Tomcat、MyBatis、MySQL8、git/gitee、Lombok、Junit、fastjson</p>\n<h3 id=\"h3-u73AFu5883u914Du7F6E\"><a name=\"环境配置\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>环境配置</h3><p>需要本地有Java8的JDK环境和maven3.5及以上的版本的环境，Tomcat7、8、9均可使用</p>\n<h3 id=\"h3-u4F7Fu7528u8BF4u660E\"><a name=\"使用说明\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>使用说明</h3><p>克隆或下载项目到本地后，配置Tomcat和数据库和数据库的连接源即可使用本博客系统。</p>\n<p>本地数据库运行需要新建一个库，然后在其中执行本项目中的.sql的数据库脚本文件让数据表中存在在本地中</p>\n<p>数据库的连接源配置在resource目录下的database.properties中</p>\n￥#fe-blog-system\n####介绍\nFE个人博客管理系统，后端结合MyBatis框架，前端结合Layui的一个JavaWeb项目\n\n###软件架构\nMVC三层架构\n\n###前端技术栈\n\nHTML、CSS、JavaScript、sass、Less、Layui\n\n\n###后端技术栈\n\nJava8、Maven、Tomcat、MyBatis、MySQL8、git/gitee、Lombok、Junit、fastjson\n\n\n###环境配置\n\n需要本地有Java8的JDK环境和maven3.5及以上的版本的环境，Tomcat7、8、9均可使用\n\n###使用说明\n\n克隆或下载项目到本地后，配置Tomcat和数据库和数据库的连接源即可使用本博客系统。\n\n本地数据库运行需要新建一个库，然后在其中执行本项目中的.sql的数据库脚本文件让数据表中存在在本地中\n\n数据库的连接源配置在resource目录下的database.properties中\n\n\n', 'FE个人博客管理系统，后端结合MyBatis框架，前端结合Layui的一个JavaWeb项目\r\n', '原创', 0, '2024-06-21 10:41:51', '2024-06-21 10:41:51', 2, 1, 1, 'admin');
INSERT INTO `blog` VALUES (63, 'CSS实现毛玻璃效果', '<h2 id=\"h2-u6BDBu73BBu7483u6548u679Cu5236u4F5Cu603Bu7ED3\"><a name=\"毛玻璃效果制作总结\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>毛玻璃效果制作总结</h2><h4 id=\"h4-1-\"><a name=\"1.效果展示\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>1.效果展示</h4><p><img src=\"images/media/glass00.jpg\" alt=\"\"></p>\n<h4 id=\"h4-2-\"><a name=\"2.网页结构\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>2.网页结构</h4><pre><code class=\"lang-ht\"> &lt;div class=&quot;banner&quot;&gt;\n        &lt;div class=&quot;drop-shadow&quot;&gt;\n            &lt;div class=&quot;glass&quot;&gt;&lt;/div&gt;\n            &lt;span&gt;毛玻璃效果GLASS&lt;/span&gt;\n            &lt;input type=&quot;file&quot;&gt;\n        &lt;/div&gt;\n    &lt;/div&gt;\n</code></pre>\n<p>banner,drop-shadow,glass为大小相同的div<br><img src=\"images/media/glass01.jpg\" alt=\"\"></p>\n<p>banner层用来添加总的背景，drop-shadow用来添加毛玻璃的阴影滤镜，glass用来做毛玻璃</p>\n<h4 id=\"h4-3-\"><a name=\"3.原理与制作\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>3.原理与制作</h4><p>为banner于glass添加相同的背景图片，glass区域要保证于与banner区域的背景图相等，需要先将两张背景图叠在一起然后在进行裁切，所以glass与banner等大且具有相同的背景图，之后使用clip-path将glass进行裁切，然后为glass设置模糊滤镜，这样就形成了一个毛玻璃效果</p>\n<pre><code class=\"lang-css\">*{\n        margin: 0;\n        padding: 0;\n        box-sizing: border-box;\n    }\n    html,body{\n        width: 100vw;\n        height: 100vh;\n\n    }\n    .banner{\n        width: 100vw;\n        height: 100vh;\n        background: url(./02.jpg);\n        background-position: center;\n        background-size: cover;\n        display: flex;\n        justify-content: center;\n        align-items: center;\n\n    }\n\n\n    .glass{\n        width: 100%;\n        height: 100%;\n        background: url(./02.jpg);\n        background-size: cover;\n        background-position:center;\n        clip-path: inset(200px 200px);\n        filter: blur(20px);\n        display: flex;\n        justify-content: center;\n        align-items: center; \n\n    } \n    span{\n        position: absolute;\n        color: white;\n        font-size: 40px;\n        letter-spacing: 0.75em;\n        padding-left: 0.375em;\n    }\n</code></pre>\n<p>最后为drop-shadow添加drop-shadow滤镜为毛玻璃添加阴影效果</p>\n<pre><code class=\"lang-css\">  .drop-shadow{\n        height: 100%;\n        width: 100%;\n        filter:  drop-shadow(0px 20px 10px rgba(0, 0, 0, 0.5));  \n        display: flex;\n        justify-content: center;\n        align-items: center;\n    }\n</code></pre>\n<h4 id=\"h4-4-\"><a name=\"4.总结\" class=\"reference-link\"></a><span class=\"header-link octicon octicon-link\"></span>4.总结</h4><p>之前看到别人制作的毛玻璃效果非常漂亮，因此自己也想学习一下，毛玻璃效果中采用了clip-path裁切与filter滤镜，对我来说还比较陌生，没想到还可以使用滤镜添加阴影效果，以上就是毛玻璃效果的一种实现思路了</p>\n￥## 毛玻璃效果制作总结\n\n#### 1.效果展示\n![](images/media/glass00.jpg)\n\n#### 2.网页结构\n\n``` ht\n <div class=\"banner\">\n        <div class=\"drop-shadow\">\n            <div class=\"glass\"></div>\n            <span>毛玻璃效果GLASS</span>\n            <input type=\"file\">\n        </div>\n    </div>\n```\n\nbanner,drop-shadow,glass为大小相同的div\n![](images/media/glass01.jpg)\n\nbanner层用来添加总的背景，drop-shadow用来添加毛玻璃的阴影滤镜，glass用来做毛玻璃\n\n#### 3.原理与制作\n\n为banner于glass添加相同的背景图片，glass区域要保证于与banner区域的背景图相等，需要先将两张背景图叠在一起然后在进行裁切，所以glass与banner等大且具有相同的背景图，之后使用clip-path将glass进行裁切，然后为glass设置模糊滤镜，这样就形成了一个毛玻璃效果\n\n``` css\n*{\n        margin: 0;\n        padding: 0;\n        box-sizing: border-box;\n    }\n    html,body{\n        width: 100vw;\n        height: 100vh;\n       \n    }\n    .banner{\n        width: 100vw;\n        height: 100vh;\n        background: url(./02.jpg);\n        background-position: center;\n        background-size: cover;\n        display: flex;\n        justify-content: center;\n        align-items: center;\n        \n    }\n\n\n    .glass{\n        width: 100%;\n        height: 100%;\n        background: url(./02.jpg);\n        background-size: cover;\n        background-position:center;\n        clip-path: inset(200px 200px);\n        filter: blur(20px);\n        display: flex;\n        justify-content: center;\n        align-items: center; \n        \n    } \n    span{\n        position: absolute;\n        color: white;\n        font-size: 40px;\n        letter-spacing: 0.75em;\n        padding-left: 0.375em;\n    }\n```\n\n最后为drop-shadow添加drop-shadow滤镜为毛玻璃添加阴影效果\n\n```  css\n  .drop-shadow{\n        height: 100%;\n        width: 100%;\n        filter:  drop-shadow(0px 20px 10px rgba(0, 0, 0, 0.5));  \n        display: flex;\n        justify-content: center;\n        align-items: center;\n    }\n```\n\n#### 4.总结\n\n之前看到别人制作的毛玻璃效果非常漂亮，因此自己也想学习一下，毛玻璃效果中采用了clip-path裁切与filter滤镜，对我来说还比较陌生，没想到还可以使用滤镜添加阴影效果，以上就是毛玻璃效果的一种实现思路了\n', '之前看到别人制作的毛玻璃效果非常漂亮，因此自己也想学习一下，毛玻璃效果中采用了clip-path裁切与filter滤镜，对我来说还比较陌生，没想到还可以使用滤镜添加阴影效果！', '原创', 0, '2024-06-21 10:47:58', '2024-06-21 10:47:58', 2, 1, 60, 'admin');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
                            `comment_id` int NOT NULL AUTO_INCREMENT COMMENT '评论主键',
                            `blog_id` int NOT NULL COMMENT '博客id',
                            `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论内容',
                            `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
                            `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '评论更新时间',
                            `commentator` int NOT NULL COMMENT '游客id（随机生成）',
                            PRIMARY KEY (`comment_id`) USING BTREE,
                            INDEX `BLOG_ID`(`blog_id` ASC) USING BTREE,
                            CONSTRAINT `BLOG_ID` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`blog_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for field
-- ----------------------------
DROP TABLE IF EXISTS `field`;
CREATE TABLE `field`  (
                          `field_id` int NOT NULL AUTO_INCREMENT,
                          `name` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                          PRIMARY KEY (`field_id`) USING BTREE,
                          UNIQUE INDEX `field_field_id_uindex`(`field_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of field
-- ----------------------------
INSERT INTO `field` VALUES (1, '其它');
INSERT INTO `field` VALUES (4, 'javaSE');
INSERT INTO `field` VALUES (60, '前端');
INSERT INTO `field` VALUES (61, '后端');

-- ----------------------------
-- Table structure for homepage
-- ----------------------------
DROP TABLE IF EXISTS `homepage`;
CREATE TABLE `homepage`  (
                             `homepageid` int NOT NULL,
                             `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '描述',
                             `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '首页标题',
                             `welcome` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '欢迎语',
                             `banner` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '封面图片地址',
                             `announcement` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
                             PRIMARY KEY (`homepageid`) USING BTREE,
                             UNIQUE INDEX `homepage_homepageid_uindex`(`homepageid` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of homepage
-- ----------------------------
INSERT INTO `homepage` VALUES (1, 'FE全称Fancy.we，是博主的呢称，本博客完成于今年6月', 'weFancy‘s Blog', '欢迎光临！', 'default-banner.jpg', '大家好，本博客是由weFancy（wlf）开发的可定制化个人博客系统，java大作业，还有很多未知的BUG');

-- ----------------------------
-- Table structure for media
-- ----------------------------
DROP TABLE IF EXISTS `media`;
CREATE TABLE `media`  (
                          `image` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                          `music` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                          `media_id` int NOT NULL AUTO_INCREMENT,
                          PRIMARY KEY (`media_id`) USING BTREE,
                          UNIQUE INDEX `MEDIA_ID`(`media_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 120 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of media
-- ----------------------------
INSERT INTO `media` VALUES ('default-banner.jpg', NULL, 64);
INSERT INTO `media` VALUES ('fm1.jpg', NULL, 103);
INSERT INTO `media` VALUES ('fm2.jpg', NULL, 104);
INSERT INTO `media` VALUES ('fm3.jpg', NULL, 105);
INSERT INTO `media` VALUES ('fm4.jpg', NULL, 106);
INSERT INTO `media` VALUES ('fm5.jpg', NULL, 107);
INSERT INTO `media` VALUES ('fm6.jpg', NULL, 108);
INSERT INTO `media` VALUES ('fm7.jpg', NULL, 109);
INSERT INTO `media` VALUES ('fm8.jpg', NULL, 110);
INSERT INTO `media` VALUES ('fm9.jpg', NULL, 111);
INSERT INTO `media` VALUES ('fm10.jpg', NULL, 112);
INSERT INTO `media` VALUES ('fm11.jpg', NULL, 113);
INSERT INTO `media` VALUES ('bg.jpg', NULL, 114);
INSERT INTO `media` VALUES ('dztx.gif', NULL, 116);
INSERT INTO `media` VALUES ('傲娇.jpg', NULL, 117);
INSERT INTO `media` VALUES ('glass00.jpg', NULL, 118);
INSERT INTO `media` VALUES ('glass01.jpg', NULL, 119);

-- ----------------------------
-- Table structure for media_relation
-- ----------------------------
DROP TABLE IF EXISTS `media_relation`;
CREATE TABLE `media_relation`  (
                                   `blog_id` int NOT NULL,
                                   `media_id` int NOT NULL,
                                   UNIQUE INDEX `MEDIA_ID`(`blog_id` ASC) USING BTREE,
                                   INDEX `id4`(`media_id` ASC) USING BTREE,
                                   CONSTRAINT `id3` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`blog_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                   CONSTRAINT `id4` FOREIGN KEY (`media_id`) REFERENCES `media` (`media_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of media_relation
-- ----------------------------

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
                        `tag_id` int NOT NULL AUTO_INCREMENT COMMENT '标签id',
                        `name` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '标签名',
                        PRIMARY KEY (`tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES (1, 'javascript');
INSERT INTO `tag` VALUES (5, '后端');
INSERT INTO `tag` VALUES (24, 'css');
INSERT INTO `tag` VALUES (25, 'html');
INSERT INTO `tag` VALUES (26, '没用的文章');

-- ----------------------------
-- Table structure for tag_relation
-- ----------------------------
DROP TABLE IF EXISTS `tag_relation`;
CREATE TABLE `tag_relation`  (
                                 `blog_id` int NOT NULL,
                                 `tag_id` int NOT NULL,
                                 `deleted` int NOT NULL DEFAULT 0,
                                 INDEX `id1`(`blog_id` ASC) USING BTREE,
                                 INDEX `id2`(`tag_id` ASC) USING BTREE,
                                 CONSTRAINT `id1` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`blog_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                 CONSTRAINT `id2` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`tag_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag_relation
-- ----------------------------
INSERT INTO `tag_relation` VALUES (62, 26, 0);
INSERT INTO `tag_relation` VALUES (63, 24, 0);
INSERT INTO `tag_relation` VALUES (63, 25, 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
                         `user_id` int NOT NULL AUTO_INCREMENT,
                         `account` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '账户',
                         `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '密码',
                         `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '邮箱',
                         `nick` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '昵称',
                         `profile` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '简介',
                         `avatar` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '头像地址',
                         `deleted` int NOT NULL DEFAULT 0 COMMENT '逻辑删除字段 逻辑删除字段 0-未删除 1-删除',
                         `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                         `sex` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '性别',
                         `birthday` timestamp NULL DEFAULT NULL COMMENT '生日',
                         PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123456', NULL, 'weFancy', '生命不息，编码不止！', NULL, 0, '2024-06-19 19:19:05', '2024-06-21 10:25:29', '女', '2004-07-03 00:00:00');

SET FOREIGN_KEY_CHECKS = 1;
