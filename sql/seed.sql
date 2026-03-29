USE cloud_yearbook;

INSERT INTO user (id,username,password,nickname,role,status,create_time,update_time,phone,email,graduation_destination,personal_profile)
VALUES
(1,'admin','$2a$10$HraW/NJfju4JqpQZm32pt.W3rCHQ6SW4Ar0q3xY2AfG4QubvS2Jri','系统管理员','ADMIN',0,NOW(),NOW(),'13800000000','admin@school.cn','校友会','欢迎来到云同学录'),
(2,'zhangsan','$2a$10$HraW/NJfju4JqpQZm32pt.W3rCHQ6SW4Ar0q3xY2AfG4QubvS2Jri','张三','USER',0,NOW(),NOW(),'13900000000','zhangsan@school.cn','互联网公司','热爱篮球与摄影')
ON DUPLICATE KEY UPDATE nickname=VALUES(nickname);

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
