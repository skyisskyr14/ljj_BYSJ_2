USE cloud_yearbook;

-- 为兼容已有历史库（字段长度可能与 schema.sql 不一致），用户初始化仅写入核心字段，
-- 避免在旧字段长度约束下触发 Data too long。
INSERT INTO user (id,username,password,nickname,role,status,create_time,update_time)
VALUES
(1,'admin','$2a$10$HraW/NJfju4JqpQZm32pt.W3rCHQ6SW4Ar0q3xY2AfG4QubvS2Jri',LEFT('管理员', 4),'ADMIN',0,NOW(),NOW()),
(2,'zhangsan','$2a$10$HraW/NJfju4JqpQZm32pt.W3rCHQ6SW4Ar0q3xY2AfG4QubvS2Jri',LEFT('张三', 4),'USER',0,NOW(),NOW())
ON DUPLICATE KEY UPDATE
  nickname=LEFT(VALUES(nickname), 4),
  role=VALUES(role),
  status=VALUES(status),
  update_time=NOW();

INSERT INTO `class` (id,class_name,grade,description,creator_id,create_time,update_time)
VALUES (1,'计算机科学1班','2020级','软件工程方向班级',1,NOW(),NOW())
ON DUPLICATE KEY UPDATE class_name=VALUES(class_name);

INSERT INTO user_class (user_id,class_id,join_time,identity)
VALUES (1,1,NOW(),'管理员'),(2,1,NOW(),'学生')
ON DUPLICATE KEY UPDATE identity=VALUES(identity);

INSERT INTO tag (id,tag_name,type,create_time)
VALUES (1,'篮球','兴趣',NOW()),(2,'前端开发','技能',NOW())
ON DUPLICATE KEY UPDATE tag_name=VALUES(tag_name);

INSERT INTO user_tag (user_id,tag_id,create_time)
VALUES (2,1,NOW()),(2,2,NOW())
ON DUPLICATE KEY UPDATE tag_id=VALUES(tag_id);

INSERT INTO message (content,sender_id,parent_id,status,like_count,create_time,update_time)
VALUES ('毕业快乐，常联系！',2,0,0,3,NOW(),NOW()),('欢迎大家完善个人档案。',1,0,0,1,NOW(),NOW());

INSERT INTO notification (user_id,title,content,type,is_read,related_id,create_time)
VALUES (2,'欢迎加入','欢迎加入云同学录','SYSTEM',0,NULL,NOW());
