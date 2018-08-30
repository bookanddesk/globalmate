drop table if exists `user`;
CREATE TABLE user  (
	ID_        	varchar(64) NOT NULL,
	CODE_     	varchar(255) NULL,
	NAME_     	varchar(255) NULL,
	NIKENAME_	varchar(255) NULL,
	PASSWORD_      	varchar(64) NULL,
	SALT_      	varchar(64) NULL,
	EMAIL_     	varchar(64) NULL,
	PHONE_     	varchar(64) NULL,
	PIC_	varchar(64) NULL,
	CREATE_TIME_     TIMESTAMP NULL,
	MODIFY_TIME_    TIMESTAMP NULL,
	ENABLE_     char(1) NULL default '1',
	ID_NUMBER_  varchar(64) NULL,
	NICE_  int,
	WHERE_    varchar(255) NULL,
  SCHOOL_   varchar(255) NULL,
	HOBBY_    varchar(4000) NULL,
	is_certified_ tinyint(1) DEFAULT 0 comment'认证状态',
	cetifiy_id_ varchar (64) null comment'认证id',
	user_credit_ varchar (64) null comment'用户信用',
	user_tag_ varchar (64) null comment'用户标签',
	resident_ varchar (64) null comment'常驻地',
	u_ext1_ varchar (64) null,
	u_ext2_ varchar (64) null,
	u_ext3_ varchar (64) null,
	PRIMARY KEY(ID_)
);
alter table `user` add column country_ varchar(64) default null comment'国家';
alter table `user` add column city_ varchar(64) default null comment'城市';
alter table `user` add column help_available_ varchar(255) default null comment'可提供的帮助';
-- 微信用户特殊字段
alter table `user` add column subscribe tinyint(1) default null comment'是否关注公众号';
alter table `user` add column openid varchar(64) default null comment'微信用户id';
alter table `user` add column sex varchar(2) default null comment'性别';
alter table `user` add column language varchar(10) default null comment'用户的语言，简体中文为zh_CN';
alter table `user` add column province varchar(64) default null comment'省份';
alter table `user` add column subscribe_scene varchar(64) default null comment'用户关注的渠道来源';


drop table if exists service;
CREATE TABLE service  (
	ID_        	varchar(64) NOT NULL,
	user_id_     	varchar(64) NOT NULL, -- //提供服务的用户id
	user_name_     	varchar(64) NOT NULL, -- //提供服务的用户name,查询服务时不需要联查用户表
	name_     varchar(255) NULL, -- //服务名称
	type_     varchar(255) NULL,  -- //服务类型
	descrition_  varchar(2000) NULL, -- //服务描述
	PRIMARY KEY(ID_)
);

drop table if exists need;
CREATE TABLE need(
	ID_        	varchar(64) NOT NULL,
	start_type_	char(1) NULL,  -- //服务发起方，0是需求方发布的，1是服务提供方发布需求方应答的
	TYPE_     	char(64) NULL,  -- //服务类型
	USER_ID_     	varchar(255) NULL, -- //需要的人
	description_  varchar(2000) NULL, -- //需求描述
	CREATE_TIME_     TIMESTAMP NULL,-- //需求提交时间
	MODIFY_TIME_    TIMESTAMP NULL, -- //需求更新时间
	END_TIME_    TIMESTAMP NULL, -- //需求结束时间
	ENABLE_     CHAR(1) null default '1',      -- //需求是否终止，新需求默认是开放状态
	EVALUATE  int,  -- //评价分，这个值加到提供人的好人值上
	RESPONDER_ varchar(255) NULL, -- //提供服务的人,
	COMMENT_  varchar(2000) NULL, -- //服务评语
	PRIMARY KEY(ID_)
);
alter table need add column user_name_ varchar (20) default null comment'需求人姓名或昵称';
alter table need add column where_ varchar (20) default null comment'地点';
alter table need add column ext1_ varchar (20) default null;
alter table need add column ext2_ varchar (200) default null;
alter table need add column ext3_ varchar (200) default null;

drop table if exists buy;
CREATE TABLE buy(
	ID_        	varchar(64) NOT NULL,
	need_id_    varchar(64) NOT NULL,  -- //外键，关联need表
	country_	varchar(64) NULL,  -- //国家
	goods_name_ varchar(255) NULL, -- //物品名称
	brand_	varchar(255) NULL,  -- //品牌
	type_  varchar(255) NULL, -- //型号
	title_  varchar(2000) NULL, -- //标题
	descrition_  varchar(2000) NULL, -- //描述
	pic_  varchar(2000) NULL,  -- //图片
	delivery_way_     varchar(2000) NULL,  -- //交货方式
	REWARD_AMOUNT_  double, -- //悬赏金额
	PAYWAY_     char(6) NULL,  -- //付款方式
	PRIMARY KEY(ID_),
	foreign key(need_id_)references need(ID_)
);

drop table if exists carry;
CREATE TABLE carry(
	ID_     varchar(64) NOT NULL,
	need_id_    varchar(64) NOT NULL,  -- //外键，关联need表
	from_	varchar(255) NULL,
	to_	varchar(255) NULL,
	arrive_	TIMESTAMP  NULL,
	goods_name_ varchar(255) NULL, -- //物品名称
	brand_	varchar(255) NULL,  -- //品牌
	type_  varchar(255) NULL, -- //型号
	description_  varchar(2000) NULL, -- //描述
	title_  varchar(2000) NULL, -- //标题
	pic_  varchar(2000) NULL,  -- //图片
	delivery_way_     varchar(2000) NULL,  -- //交货方式
	volume_         varchar(255) NULL,  -- //体积
	weight_         varchar(255) NULL,  -- //重量
	REWARD_AMOUNT_  double, -- //悬赏金额
	PAYWAY_     char(6) NULL,  -- //付款方式
	PRIMARY KEY(ID_),
	foreign key(need_id_)references need(ID_)
);

drop table if exists accompany;
CREATE TABLE accompany(
	ID_     varchar(64) NOT NULL,
	need_id_    varchar(64) NOT NULL,  -- //外键，关联need表
	where_	varchar(255) NULL,
	start_time_	TIMESTAMP   NULL,
	end_time_ TIMESTAMP  NULL,
	description_  varchar(2000) NULL, -- //描述
	pic_  varchar(2000) NULL,  -- //图片
	title_  varchar(2000) NULL, -- //标题
	type_  varchar(255) NULL, -- //想玩儿什么，下拉选一种？？
	doctor_     char(1) NULL,  -- //是否为看病
	REWARD_AMOUNT_  double, -- //悬赏金额
	PAYWAY_     char(6) NULL,  -- //付款方式,
	PRIMARY KEY(ID_),
	foreign key(need_id_)references need(ID_)
);

drop table if exists clearance;
CREATE TABLE clearance(
	ID_     varchar(64) NOT NULL,
	need_id_    varchar(64) NOT NULL,  -- //外键，关联need表
	where_	varchar(255) NULL,  -- //目的地国家
	time_	TIMESTAMP   NULL,  -- //时间
	flight_information_  varchar(255)  NULL, -- //航班信息
	airport varchar(255) NULL,  -- //通关机场
	description_  varchar(2000) NULL, -- //描述
	pic_  varchar(2000) NULL,  -- //图片
	title_  varchar(2000) NULL, -- //标题
	REWARD_AMOUNT_  double, -- //悬赏金额
	PAYWAY_     char(6) NULL,  -- //付款方式,
	PRIMARY KEY(ID_),
	foreign key(need_id_)references need(ID_)
)

drop table if exists learn_cooperation;
CREATE TABLE learn_cooperation(
	ID_     varchar(64) NOT NULL,
	need_id_ varchar(64) NOT NULL,  -- //外键，关联need表
	language_ varchar(255) NULL,  -- //语言
	country_ varchar(255) NULL,  -- //国家
	city_ varchar(255) NULL,  -- //城市
	school_ varchar(255) NULL,  -- //学校
	subject_  varchar(255) NULL,  -- //科目
	description_  varchar(2000) NULL, -- //描述
	pic_  varchar(2000) NULL,  -- //图片
	title_  varchar(2000) NULL, -- //标题
	REWARD_AMOUNT_  double, -- //悬赏金额
	PAYWAY_     char(6) NULL,  -- //付款方式,
	start_time_	TIMESTAMP   NULL,  -- //开始时间,
	end_time_ TIMESTAMP  NULL,   -- //结束时间,
	PRIMARY KEY(ID_),
	foreign key(need_id_)references need(ID_)
);

drop table if exists need_other;
CREATE TABLE need_other(
	ID_     varchar(64) NOT NULL,
	need_id_    varchar(64) NOT NULL,  -- //外键，关联need表
	description_  varchar(2000) NULL, -- //描述
	pic_  varchar(2000) NULL,  -- //图片
	title_  varchar(2000) NULL, -- //标题
	REWARD_AMOUNT_  double, -- //悬赏金额
	PAYWAY_     char(6) NULL,  -- //付款方式,
	PRIMARY KEY(ID_),
	foreign key(need_id_)references need(ID_)
);

drop table if exists usergroup;
CREATE TABLE usergroup  (
	id_         	varchar(64) NOT NULL,
	enable_     	char(1) NOT NULL,
	code_       	varchar(64) NOT NULL,
	name_       	varchar(255) NOT NULL,
	descrition_     	varchar(2000) NULL,
	create_time_	TIMESTAMP NOT NULL,
	creator_    	varchar(64) NULL,
	public_email_ varchar(64) null,
	u_group_id_ varchar(64) null comment'组织用户id',
	u_principal_id_ varchar(64) null comment'组织负责人id',
	u_principal_name_ varchar(64) null comment'组织负责人name',
	country_      varchar(64) null,
	city_ varchar(64) null,
	pic_  varchar(200) null,
	qr_code varchar(200) null,
	ext1 varchar(64) null,
	ext2 varchar (64) null,
	ext3 varchar (64) null,
	PRIMARY KEY(id_)
);

drop table if exists userlink;
CREATE TABLE userlink  (
	id_         	varchar(64) NOT NULL,
	type_       	varchar(32) NOT NULL,
	enable_     	char(1) NOT NULL,
	user_id_    	varchar(64) NOT NULL,
	target_id_  	varchar(64) NOT NULL,
	MAIN_TARGET_	char(1) NULL,
	create_time_	TIMESTAMP NOT NULL,
	authentication_ double , -- //认证数目
	PRIMARY KEY(id_)
);

drop table if exists u_Tag;
CREATE TABLE   u_Tag(
	id_         	varchar(64) NOT NULL,
	name_       	varchar(32) NOT NULL comment '标签名',
	type_    	    varchar(32) default NULL comment '标签类别',
	busiext1_  	  varchar(20) default NULL,
	PRIMARY KEY(id_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment'用户标签';

drop table if exists u_credit;
CREATE TABLE   u_credit(
	id_         	varchar(64) NOT NULL,
	grade_    	  varchar(12) not NULL comment '信用等级',
	name_       	varchar(20) default NULL comment '信用名称/别名',
	privilege_    varchar (20) default null comment '信用特权',
	busiext1_  	  varchar(20) default NULL,
	PRIMARY KEY(id_)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment'用户信用';

-- until 2018-6-11

drop table if exists T_provide;

/*==============================================================*/
/* Table: T_provide                                             */
/*==============================================================*/
create table T_provide
(
   id_                  varchar(64) not null,
   p_type               varchar(64) comment '服务类型',
   u_id                 varchar(64) comment '服务提供者id',
   u_name               varchar(32) comment '服务提供者name',
   p_location           varchar(32) comment '服务提供者者位置',
   p_resident1          varchar(32) comment '服务提供者者常驻地1',
   p_resident2          varchar(32) comment '服务提供者者常驻地2',
   p_feature            varchar(64) comment '服务特性',
   p_effective_modulus  varchar(10) comment '服务有效系数',
   p_create_time        timestamp comment '服务添加时间',
   p_modify_time        timestamp comment '服务更新时间',
   p_ext1               varchar(64) comment '扩展字段1',
   p_ext2               varchar(64) comment '扩展字段2',
   primary key (id_)
);


drop table if exists sys_match_need;

/*==============================================================*/
/* Table: sys_match_need                                        */
/*==============================================================*/
create table sys_match_need
(
   id_                  varchar(64) not null,
   need_id              varchar(64) comment '需求id',
   u_need_id            varchar(64) comment '需求人id',
   u_need_name          varchar(64) comment '需求人name',
   provider_id          varchar(64) comment '可帮助人id',
   provider_name        varchar(64) comment '可帮助人name',
   provide_id           varchar(64) comment '帮助id',
   match_info           varchar(64) comment '匹配信息',
   match_msg_count      int(10) comment '推送信息次数',
   match_accept         tinyint(1) comment '被采纳状态',
   match_create_time    timestamp comment '匹配创建时间',
   match_modify_time    timestamp comment '匹配修改时间',
   last_push_time       timestamp comment '最后推送时间',
   match_ext1           varchar(64) comment '扩展字段1',
   match_ext2           varchar(64) comment '扩展字段2',
   primary key (id_)
);


drop table if exists sys_assistance_deal;

/*==============================================================*/
/* Table: sys_assistance_deal                                   */
/*==============================================================*/
create table sys_assistance_deal
(
   id_                  varchar(64) not null,
   need_id_             varchar(64) comment '需求id_',
   u_needer_id_         varchar(64) comment '需求人id_',
   u_needer_name        varchar(32) comment '需求人name',
   provide_id_          varchar(64) comment '服务id_',
   u_provider_id_       varchar(64) comment '服务提供者人id',
   u_provider_name      varchar(32) comment '服务提供者name',
   assist_create_time   timestamp comment '帮助创建时间',
   assist_modify_time   timestamp comment '帮助更新时间',
   assist_end_time      timestamp default null comment '帮助结束时间',
   assist_status        varchar(64) comment '帮助状态',
   assist_evaluation    varchar(64) comment '帮助评价',
   assist_price         varchar(64) comment '交易代价',
   assist_ext1          varchar(64) comment '扩展字段1',
   assist_ext2          varchar(64) comment '扩展字段2',
   primary key (id_)
);


-- until 2018-6-14

drop table if exists T_need_comments;

/*==============================================================*/
/* Table: T_need_comments                                       */
/*==============================================================*/
create table T_need_comments
(
   id_                  varchar(64) not null,
   need_id              varchar(64) comment '需求id',
   u_needer_id          varchar(64) comment '留言者id',
   u_nedder_name        varchar(32) comment '留言者姓名',
   comment_create_time  timestamp comment '留言提交时间',
   comment_modify_time  timestamp comment '留言更新时间',
   topped_times         smallint(5) comment '留言被顶次数',
   stepped_times        smallint(5) comment '留言被踩次数',
   is_replied           tinyint(1) comment '留言是否有被回复',
   replied_comment_id   varchar(64) comment '被回复的留言id',
   is_reply_comment     tinyint(1) comment '是否是回复留言',
   comment_ext1         varchar(64) comment '扩展字段1',
   comment_ext2         varchar(64) comment '扩展字段2',
   primary key (id_)
);


-- until 2018-6-20


drop table if exists u_certify_info;

/*==============================================================*/
/* Table: u_certify_info  用户认证信息表                         */
/*==============================================================*/
create table u_certify_info
(
   id_                  varchar(64) not null,
   u_id                 varchar(64) comment '用户id',
   u_name               varchar(64) comment '用户name',
   certify_time         timestamp comment '认证时间',
   is_effective         tinyint(1) comment '认证是否有效',
   cetify_type          varchar(64) comment '认证方式',
   certify_photo        varchar(2000) comment '认证图片',
   modify_time          timestamp comment '更新时间',
   certify_info         varchar(64) comment '认证信息',
   cer_ext1             varchar(64) comment '扩展字段1',
   cer_ext2             varchar(64) comment '扩展字段2',
   cer_ext3             varchar(64) comment '扩展字段3',
   primary key (id_)
);

drop table if exists u_fans_relations;

/*==============================================================*/
/* Table: u_fans_relations  用户粉丝关系表                       */
/*==============================================================*/
create table u_fans_relations
(
   id_                  varchar(64) not null,
   u_id                 varchar(64) comment '用户id',
   u_name               varchar(64) comment '用户name',
   relation_type        smallint(2) comment '关系类型',
   u_related_id         varchar(64) comment '关系用户id',
   u_related_name       varchar(64) comment '关系用户name',
   create_time          timestamp comment '创建时间',
   modity_time          timestamp comment '修改时间',
   is_deleted           tinyint(1) comment '删除状态',
   rela_ext1            varchar(64) comment '扩展字段1',
   rela_ext2            varchar(64) comment '扩展字段2',
   rela_ext3            varchar(64) comment '扩展字段3',
   primary key (id_)
);

drop table if exists sys_configuration;

/*==============================================================*/
/* Table: sys_configuration   系统配置表                         */
/*==============================================================*/
create table sys_configuration
(
   id_                  char(10) not null,
   type                 smallint(10) comment '配置类型',
   code                 varchar(32) comment '配置code',
   nema                 varchar(32) comment '配置名称',
   content              varchar(64) comment '配置信息',
   create_time          timestamp comment '创建时间',
   modify_time          timestamp comment '更新时间',
   remark               varchar(64) comment '备注',
   ext1                 varchar(20) comment '扩展1',
   ext2                 varchar(20) comment '扩展2',
   primary key (id_)
);

-- util 2018-6-22


drop table if exists u_evaluation;

/*==============================================================*/
/* Table: u_evaluation                                          */
/*==============================================================*/
create table u_evaluation
(
   id                   varchar(64) not null,
   u_evaluator_id       varchar(64) comment '评论者id',
   u_evluator_name      varchar(64) comment '评论者name',
   u_targeter_id        varchar(64) comment '评论对象id',
   u_targeter_name      varchar(64) comment '评论对象name',
   need_id              varchar(64) comment '评论需求id',
   score                varchar(64) comment '评分',
   content              varchar(64) comment '评论内容',
   modify_time          timestamp DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
   create_time          timestamp comment '评论时间',
   eva_ext1             varchar(64) comment '扩展字段1', -- //评价类型，personal针对个人  platform针对平台
   eva_ext2             varchar(64) comment '扩展字段2',
   eva_ext3             varchar(64) comment '扩展字段3',
   primary key (id)
);


drop table if exists wx_msg_template;

/*==============================================================*/
/* Table: wx_msg_template                                       */
/*==============================================================*/
create table wx_msg_template
(
   id                   varchar(64),
   template_id          varchar(64) comment '消息模板id',
   app_id               varchar(64) comment '公众号app_id',
   msg_type             varchar(64) comment '消息类型',
   modify_time          timestamp DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP  comment '更新时间',
   create_time          timestamp comment '创建时间',
   ext1                 varchar(64),
   ext2                 varchar(64),
   primary key (id)
);

-- ----------------------------
-- Table structure for `location` 国家地区
-- ----------------------------
DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `CountryRegion` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,-- 国家
  `State` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,-- 省
  `City` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,-- 城市
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


-- ----------------------------
-- Table structure for `location_en` 国家地区英文
-- ----------------------------
DROP TABLE IF EXISTS `location_en`;
CREATE TABLE `location_en` (
  `id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `CountryRegion` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `State` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `City` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

CREATE TABLE `sys_school` (
`id`  varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL ,
`name`  varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL ,
`code`  varchar(24) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
`location`  varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '所在地' ,
`level`  varchar(12) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '办学层次' ,
`supervisor`  varchar(12) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '主管部门' ,
`ext1`  varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
`ext2`  varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
`ext3`  varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
`create_time`  timestamp NULL DEFAULT NULL ,
`modify_time`  timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_unicode_ci
ROW_FORMAT=DYNAMIC
;

CREATE TABLE `need_common` (
`id`  varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL ,
`need_id`  varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL ,
`type`  varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL ,
`start_time`  timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP ,
`end_time`  timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP ,
`country`  varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
`city`  varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
`reward`  varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
`title`  varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
`description`  varchar(2000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
`pic`  varchar(2000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
`ext1`  varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
`ext2`  varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
`ext3`  varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_unicode_ci
ROW_FORMAT=DYNAMIC
;

CREATE TABLE `location_cn_en` (
`id`  varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL ,
`country_cn`  varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
`country_en`  varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
`state_cn`  varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
`state_en`  varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
`city_cn`  varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
`city_en`  varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
`ext1`  varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
`ext2`  varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
`ext3`  varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_unicode_ci
ROW_FORMAT=DYNAMIC
;