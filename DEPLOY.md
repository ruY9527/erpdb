# ERP 项目部署说明

## 快速部署

### 1. 使用 Docker Compose 部署（推荐）

```bash
# 在项目根目录执行
docker-compose up -d

# 查看服务状态
docker-compose ps

# 查看日志
docker-compose logs -f

# 停止服务
docker-compose down
```

### 2. 单独构建镜像

#### 后端构建
```bash
cd erp-boot
docker build -t erp-backend:latest .
docker run -d -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://host:3306/erpdb \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=root123 \
  erp-backend:latest
```

#### 前端构建
```bash
cd erp-ui
docker build -t erp-frontend:latest .
docker run -d -p 80:80 erp-frontend:latest
```

## 端口说明
- 前端：80 (nginx)
- 后端：8080 (Spring Boot)
- 数据库：3306 (MySQL)

## 环境变量配置

### 后端环境变量
| 变量名 | 说明 | 默认值 |
|--------|------|--------|
| SPRING_DATASOURCE_URL | 数据库连接URL | jdbc:mysql://mysql:3306/erpdb |
| SPRING_DATASOURCE_USERNAME | 数据库用户名 | root |
| SPRING_DATASOURCE_PASSWORD | 数据库密码 | root123 |
| JAVA_OPTS | JVM参数 | -Xms512m -Xmx1024m |

### 数据库环境变量
| 变量名 | 说明 | 默认值 |
|--------|------|--------|
| MYSQL_ROOT_PASSWORD | root密码 | root123 |
| MYSQL_DATABASE | 数据库名 | erpdb |
| MYSQL_USER | 用户名 | erp |
| MYSQL_PASSWORD | 用户密码 | erp123 |

## 访问地址
- 前端页面：http://localhost
- API文档：http://localhost:8080/doc.html
- 后端接口：http://localhost/api

## 注意事项
1. 首次部署需要等待 MySQL 初始化完成（约 30秒）
2. 数据库初始化脚本位于 sql/repdb_mysql8.sql
3. 前端 nginx 配置已包含 API 代理转发