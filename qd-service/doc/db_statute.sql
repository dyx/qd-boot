
# 常用字段

ALTER TABLE table_name ADD id bigint(20) unsigned NOT NULL auto_increment COMMENT '主键';
ALTER TABLE table_name MODIFY id bigint(20) unsigned NOT NULL auto_increment COMMENT '主键';

ALTER TABLE table_name ADD create_user_id bigint unsigned DEFAULT 0 NOT NULL COMMENT '创建人id';
ALTER TABLE table_name ADD create_time datetime COMMENT '创建时间';

ALTER TABLE table_name ADD update_user_id bigint unsigned DEFAULT 0 NOT NULL COMMENT '修改人id';
ALTER TABLE table_name ADD update_time datetime COMMENT '修改时间';

ALTER TABLE table_name ADD is_deleted tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已删除';


# 字段命名
# 不要是用MySQL保留字 地址 https://dev.mysql.com/doc/mysqld-version-reference/en/keywords-5-7.html
# 如果有关键字 可使用 model_keyword 如role_name

# 字段类型
# 主键 bigint(20) unsigned
# 字典 int(10) unsigned
# 编码 varchar(32)
# 名称 varchar(30)
# url varchar(255)
# 备注 varchar(500)
# 是否 tinyint(1)