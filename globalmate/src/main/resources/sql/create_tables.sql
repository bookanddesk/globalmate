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
	ENABLE_     char(1) NULL,
	ID_NUMBER_  varchar(64) NULL,  
	NICE_  int,  
	WHERE_    varchar(255) NULL,  
    SCHOOL_   varchar(255) NULL,  
	HOBBY_    varchar(4000) NULL,  
	PRIMARY KEY(ID_)
)

CREATE TABLE service  ( 
	ID_        	varchar(64) NOT NULL,
	user_id_     	varchar(64) NOT NULL, //提供服务的用户id
	user_name_     	varchar(64) NOT NULL, //提供服务的用户name,查询服务时不需要联查用户表
	name_     varchar(255) NULL, //服务名称
	type_     varchar(255) NULL,  //服务类型
	descrition_  varchar(2000) NULL, //服务描述
	PRIMARY KEY(ID_)
)

CREATE TABLE need( 
	ID_        	varchar(64) NOT NULL,
	start_type_	char(1) NULL,  //服务发起方，0是需求方发布的，1是服务提供方发布需求方应答的
	TYPE_     	char(6) NULL,  //服务类型
	USER_ID_     	varchar(255) NULL, //需要的人
	descrition_  varchar(2000) NULL, //需求描述
	REWARD_AMOUNT_  double, //悬赏金额
	PAYWAY_     char(6) NULL,  //付款方式,
	CREATE_TIME_     TIMESTAMP NULL,//需求提交时间
	MODIFY_TIME_    TIMESTAMP NULL, //需求更新时间
	END_TIME_    TIMESTAMP NULL, //需求结束时间
	ENABLE_     CHAR（1）NULL,      //需求是否终止
	EVALUATE  int,  //评价分，这个值加到提供人的好人值上
	RESPONDER_ varchar(255) NULL, //提供服务的人,
	COMMENT_  varchar(2000) NULL, //服务评语
	PRIMARY KEY(ID_)
)

CREATE TABLE buy( 
	ID_        	varchar(64) NOT NULL,
	country_	varchar(64) NULL,  //国家
	goods_name_ varchar(255) NULL, //物品名称
	brand_	varchar(255) NULL,  //品牌
	type_  varchar(255) NULL, //型号
	descrition_  varchar(2000) NULL, //描述
	pic_  varchar(2000) NULL,  //图片
	delivery_way_     char(6) NULL,  //交货方式
	PRIMARY KEY(ID_)
)

CREATE TABLE carry( 
	ID_     varchar(64) NOT NULL,
	from_	varchar(255) NULL,  
	to_	varchar(255) NULL,  
	arrive_	TIMESTAMP  NULL,  
	goods_name_ varchar(255) NULL, //物品名称
	brand_	varchar(255) NULL,  //品牌
	type_  varchar(255) NULL, //型号
	descrition_  varchar(2000) NULL, //描述
	pic_  varchar(2000) NULL,  //图片
	delivery_way_     char(6) NULL,  //交货方式
	volume_         varchar(255) NULL,  //体积
	weight_         varchar(255) NULL,  //重量
	PRIMARY KEY(ID_)
)

CREATE TABLE accompany( 
	ID_     varchar(64) NOT NULL,
	where_	varchar(255) NULL,  
	start_time_	TIMESTAMP   NULL,  
	end_time_ TIMESTAMP  NULL,  
	descrition_  varchar(2000) NULL, //描述
	type_  varchar(255) NULL, //想玩儿什么，下拉选一种？？
	doctor_     char(1) NULL,  //是否为看病
	PRIMARY KEY(ID_)
)


CREATE TABLE clearance( 
	ID_     varchar(64) NOT NULL,
	where_	varchar(255) NULL,  //目的地国家
	time_	TIMESTAMP   NULL,  //时间
	flight_information_  varchar(255)  NULL, //航班信息
	airport varchar(255) NULL,  //通关机场
	descrition_  varchar(2000) NULL, //描述
	PRIMARY KEY(ID_)
)


CREATE TABLE learn_cooperation( 
	ID_     varchar(64) NOT NULL,
	language_ varchar(255) NULL,  //语言
	subject_  varchar(255) NULL,  //科目
	descrition_  varchar(2000) NULL, //描述
	PRIMARY KEY(ID_)
)


CREATE TABLE usergroup  ( 
	id_         	varchar(64) NOT NULL,
	enable_     	char(1) NOT NULL,
	code_       	varchar(64) NOT NULL,
	name_       	varchar(255) NOT NULL,
	descrition_     	varchar(2000) NULL,
	create_time_	TIMESTAMPNOT NULL,
	creator_    	varchar(64) NULL,
	PRIMARY KEY(id_)
)

CREATE TABLE userlink  ( 
	id_         	varchar(64) NOT NULL,
	type_       	varchar(32) NOT NULL,
	enable_     	char(1) NOT NULL,
	user_id_    	varchar(64) NOT NULL,
	target_id_  	varchar(64) NOT NULL,
	MAIN_TARGET_	char(1) NULL,
	create_time_	TIMESTAMPNOT NULL,
	authentication_ double ,//认证数目
	PRIMARY KEY(id_)
)