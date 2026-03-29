# 基于 SpringBoot + Vue 的云同学录系统

一个可运行、可展示、可答辩的前后端分离毕业设计项目，围绕“班级/校友信息管理 + 留言互动 + 管理治理”构建。

## 技术栈
- 前端：Vue3 + Vite + TypeScript + Pinia + Vue Router + Element Plus
- 后端：Spring Boot 3 + Spring Security + JWT + MyBatis-Plus
- 数据库：MySQL 8
- 缓存：Redis
- 构建：Maven

## 项目结构
```
.
├── backend                 # Spring Boot 后端
├── frontend                # Vue3 前端
├── sql
│   ├── schema.sql          # 建表脚本
│   └── seed.sql            # 初始化数据
└── README.md
```

## 默认账号
- 管理员：`admin / Admin@123`
- 普通用户：`zhangsan / Admin@123`

## 本地运行
### 1) 准备环境
- JDK 17
- Maven 3.9+
- Node.js 20+
- MySQL 8
- Redis 6+

### 2) 初始化数据库
```bash
mysql -uroot -proot < sql/schema.sql
mysql -uroot -proot < sql/seed.sql
```

### 3) 启动后端
```bash
cd backend
mvn spring-boot:run
```
后端默认地址：`http://localhost:8080`

### 4) 启动前端
```bash
cd frontend
npm install
npm run dev
```
前端默认地址：`http://localhost:5173`

## 主要功能清单
- 用户注册/登录/退出
- JWT 鉴权，普通用户与管理员角色控制
- 个人中心资料查看/编辑、头像上传
- 同学录列表：搜索、分页、详情页
- 留言墙：发布、分页、删除（管理员/本人）
- 后台管理：数据看板、用户状态管理、班级管理
- Redis 缓存高频同学录查询
- 操作日志（登录/注册）入库
- 统一返回体与全局异常处理

## 关键接口（示例）
- 认证：`POST /api/auth/login`、`POST /api/auth/register`
- 用户：`GET /api/user/me`、`PUT /api/user/me`、`POST /api/file/avatar`
- 同学录：`POST /api/directory/search`、`GET /api/directory/{id}`
- 留言墙：`POST /api/messages`、`GET /api/messages`、`DELETE /api/messages/{id}`
- 元数据：`GET /api/meta/classes|tags`，管理员 `POST/DELETE`
- 管理后台：`GET /api/admin/dashboard`、`GET /api/admin/users`、`PUT /api/admin/users/{id}/status`

## 答辩建议亮点
- 认证授权链路：JWT + Spring Security + 角色控制
- 业务主线：从登录、完善档案、检索同学到留言互动
- 工程能力：前后端分离、统一响应、异常处理、缓存、日志、SQL 初始化脚本
