/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/11/2 22:31:07                           */
/*==============================================================*/


DROP TABLE IF EXISTS book;

DROP TABLE IF EXISTS cms_article;

DROP TABLE IF EXISTS cms_article_category;

DROP TABLE IF EXISTS cms_article_tag;

DROP TABLE IF EXISTS cms_category;

DROP TABLE IF EXISTS cms_category_tag;

DROP TABLE IF EXISTS cms_comment;

DROP TABLE IF EXISTS cms_tag;

DROP TABLE IF EXISTS user;

/*==============================================================*/
/* Table: book                                                  */
/*==============================================================*/
CREATE TABLE book
(
  book_id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '编号',
  user_id INT(10) UNSIGNED NOT NULL
  COMMENT '用户编号',
  name    VARCHAR(45)      NOT NULL
  COMMENT '书名',
  PRIMARY KEY (book_id),
  KEY FK_book_1 (user_id)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 40
  DEFAULT CHARSET = utf8mb4
  COMMENT ='用户书籍表';

ALTER TABLE book
  COMMENT 'book 书';

/*==============================================================*/
/* Table: cms_article                                           */
/*==============================================================*/
CREATE TABLE cms_article
(
  article_id    INT(10) UNSIGNED    NOT NULL AUTO_INCREMENT
  COMMENT '文章编号',
  title         VARCHAR(200)        NOT NULL
  COMMENT '文章标题',
  author        VARCHAR(50)                  DEFAULT NULL
  COMMENT '文章原作者',
  fromurl       VARCHAR(300)                 DEFAULT NULL
  COMMENT '转载来源网址',
  image         VARCHAR(300)                 DEFAULT NULL
  COMMENT '封面图',
  keywords      VARCHAR(100)                 DEFAULT NULL
  COMMENT '关键字',
  description   VARCHAR(500)                 DEFAULT NULL
  COMMENT '简介',
  type          TINYINT(4)          NOT NULL DEFAULT 1
  COMMENT '类型(1:普通,2:热门...)',
  allowcomments TINYINT(4)          NOT NULL DEFAULT 1
  COMMENT '是否允许评论(0:不允许,1:允许)',
  status        TINYINT(4)          NOT NULL DEFAULT 1
  COMMENT '状态(-1:审核不通过回收站,0:刚发布未审核,1:已审核公开,2:已审核个人)',
  content       MEDIUMTEXT COMMENT '内容',
  user_id       INT(10) UNSIGNED    NOT NULL
  COMMENT '发布人id',
  up            INT(10) UNSIGNED    NOT NULL DEFAULT 0
  COMMENT '顶',
  down          INT(10) UNSIGNED    NOT NULL DEFAULT 0
  COMMENT '踩',
  readnumber    INT(10) UNSIGNED    NOT NULL DEFAULT 0
  COMMENT '阅读数量',
  ctime         BIGINT(20) UNSIGNED NOT NULL
  COMMENT '创建时间',
  orders        BIGINT(20) UNSIGNED NOT NULL
  COMMENT '排序',
  PRIMARY KEY (article_id),
  KEY cms_article_orders (orders)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='文章表';

ALTER TABLE cms_article
  COMMENT 'cms_article 文章表';

/*==============================================================*/
/* Table: cms_article_category                                  */
/*==============================================================*/
CREATE TABLE cms_article_category
(
  article_category_id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  article_id          INT(10) UNSIGNED NOT NULL,
  category_id         INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (article_category_id),
  KEY cms_article_category_article_id (article_id),
  KEY cms_article_category_category_id (category_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='文章类目表';

ALTER TABLE cms_article_category
  COMMENT 'cms_article_category 文章类目关联表';

/*==============================================================*/
/* Table: cms_article_tag                                       */
/*==============================================================*/
CREATE TABLE cms_article_tag
(
  article_tag_id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '编号',
  article_id     INT(10) UNSIGNED NOT NULL
  COMMENT '文章编号',
  tag_id         INT(10) UNSIGNED NOT NULL
  COMMENT '标签编号',
  PRIMARY KEY (article_tag_id),
  KEY cms_article_tag_article_id (article_id),
  KEY cms_article_tag_tag_id (tag_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='文章标签表';

ALTER TABLE cms_article_tag
  COMMENT 'cms_article_tag 文章标签关联表';

/*==============================================================*/
/* Table: cms_category                                          */
/*==============================================================*/
CREATE TABLE cms_category
(
  category_id INT(10) UNSIGNED     NOT NULL AUTO_INCREMENT
  COMMENT '类目编号',
  pid         INT(10) UNSIGNED COMMENT '上级编号',
  level       TINYINT(3)           NOT NULL
  COMMENT '层级',
  name        VARCHAR(20)          NOT NULL
  COMMENT '名称',
  description VARCHAR(200)                  DEFAULT NULL
  COMMENT '描述',
  icon        VARCHAR(50)                   DEFAULT NULL
  COMMENT '图标',
  type        TINYINT(3)           NOT NULL DEFAULT 1
  COMMENT '类型(1:普通,2:热门...)',
  alias       VARCHAR(20)                   DEFAULT NULL
  COMMENT '别名',
  ctime       BIGINT(20) UNSIGNED  NOT NULL
  COMMENT '创建时间',
  orders      BIGINT(255) UNSIGNED NOT NULL
  COMMENT '排序',
  PRIMARY KEY (category_id),
  KEY cms_category_orders (orders),
  KEY cms_category_pid (pid),
  KEY cms_category_alias (alias),
  KEY cms_category_level (level)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COMMENT ='类目表';

ALTER TABLE cms_category
  COMMENT 'cms_category 类目表';

/*==============================================================*/
/* Table: cms_category_tag                                      */
/*==============================================================*/
CREATE TABLE cms_category_tag
(
  category_tag_id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '编号',
  category_id     INT(10) UNSIGNED NOT NULL
  COMMENT '类目编号',
  tag_id          INT(10) UNSIGNED NOT NULL
  COMMENT '标签编号',
  PRIMARY KEY (category_tag_id),
  KEY cms_category_tag_tag_id (tag_id),
  KEY cms_category_tag_category_id (category_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT =' 分类标签表';

ALTER TABLE cms_category_tag
  COMMENT 'cms_category_tag 类目标签关联表';

/*==============================================================*/
/* Table: cms_comment                                           */
/*==============================================================*/
CREATE TABLE cms_comment
(
  comment_id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT
  COMMENT '编号',
  pid        INT(10) UNSIGNED          DEFAULT 0
  COMMENT '回复楼中楼编号回复楼中楼编号',
  article_id INT(10) UNSIGNED NOT NULL
  COMMENT '文章编号',
  user_id    INT(10) UNSIGNED NOT NULL
  COMMENT '用户编号',
  content    TEXT             NOT NULL
  COMMENT '评论内容',
  status     TINYINT(4)       NOT NULL DEFAULT 1
  COMMENT '状态(-1:审核不通过,0:未审核,1:已审核通过)',
  ip         VARCHAR(30)               DEFAULT NULL
  COMMENT '评论人ip地址',
  agent      VARCHAR(200)              DEFAULT NULL
  COMMENT '评论人终端信息',
  ctime      BIGINT(20)       NOT NULL
  COMMENT '创建时间',
  PRIMARY KEY (comment_id),
  KEY cms_comment_article_id (article_id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

ALTER TABLE cms_comment
  COMMENT 'cms_comment 评论表';

/*==============================================================*/
/* Table: cms_tag                                               */
/*==============================================================*/
CREATE TABLE cms_tag
(
  tag_id      INT(10) UNSIGNED    NOT NULL AUTO_INCREMENT
  COMMENT '标签编号',
  name        VARCHAR(20)         NOT NULL
  COMMENT '名称',
  description VARCHAR(200)                 DEFAULT NULL
  COMMENT '描述',
  icon        VARCHAR(50)                  DEFAULT NULL
  COMMENT '图标',
  type        TINYINT(4)          NOT NULL DEFAULT 1
  COMMENT '类型(1:普通,2:热门...)',
  alias       VARCHAR(20)                  DEFAULT NULL
  COMMENT '别名',
  ctime       BIGINT(20) UNSIGNED NOT NULL
  COMMENT '创建时间',
  orders      BIGINT(20) UNSIGNED NOT NULL
  COMMENT '排序',
  PRIMARY KEY (tag_id),
  KEY cms_tag_orders (orders),
  KEY cms_tag_alias (alias)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COMMENT ='标签表';

ALTER TABLE cms_tag
  COMMENT 'cms_tag 标签表';

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
CREATE TABLE user
(
  user_id  INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  username VARCHAR(32)               DEFAULT NULL,
  password VARCHAR(32)               DEFAULT NULL,
  nickname VARCHAR(32)               DEFAULT NULL,
  sex      INT(11)                   DEFAULT NULL
  COMMENT '0未知,1男,2女',
  ctime    BIGINT(20)                DEFAULT NULL,
  content  TEXT,
  PRIMARY KEY (user_id)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 56
  DEFAULT CHARSET = utf8
  COMMENT ='用户表';

ALTER TABLE user
  COMMENT 'user 用户';

ALTER TABLE book
  ADD CONSTRAINT FK_Reference_9 FOREIGN KEY (user_id)
REFERENCES user (user_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE cms_article_category
  ADD CONSTRAINT FK_Reference_7 FOREIGN KEY (category_id)
REFERENCES cms_category (category_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE cms_article_category
  ADD CONSTRAINT FK_Reference_8 FOREIGN KEY (article_id)
REFERENCES cms_article (article_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE cms_article_tag
  ADD CONSTRAINT FK_Reference_3 FOREIGN KEY (article_id)
REFERENCES cms_article (article_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE cms_article_tag
  ADD CONSTRAINT FK_Reference_4 FOREIGN KEY (tag_id)
REFERENCES cms_tag (tag_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE cms_category_tag
  ADD CONSTRAINT FK_Reference_5 FOREIGN KEY (category_id)
REFERENCES cms_category (category_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE cms_category_tag
  ADD CONSTRAINT FK_Reference_6 FOREIGN KEY (tag_id)
REFERENCES cms_tag (tag_id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

ALTER TABLE cms_comment
  ADD CONSTRAINT cms_comment_article_id FOREIGN KEY (article_id)
REFERENCES cms_article (article_id)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

ALTER TABLE cms_comment
  ADD CONSTRAINT cms_comment_pid FOREIGN KEY (pid)
REFERENCES cms_comment (comment_id)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
