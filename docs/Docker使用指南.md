# Docker使用指南

## 项目概述

本项目使用Docker容器化部署，包含以下组件：
- MySQL数据库（端口3307）
- 若依后端服务（端口8081）
- 前端页面（通过后端服务提供）

## 环境要求

- Docker Desktop
- 至少4GB可用内存
- 至少10GB可用磁盘空间

## 快速启动

### 1. 启动MySQL数据库

```bash
# 创建Docker网络
docker network create mouse-network

# 启动MySQL容器
docker run -d --name mouse-mysql \
  --network mouse-network \
  -p 3307:3306 \
  -e MYSQL_ROOT_PASSWORD=mouse123456 \
  -e MYSQL_DATABASE=mouse_behavior \
  -v "D:/mouse/docker/mysql/conf:/etc/mysql/conf.d" \
  -v "D:/mouse/docker/mysql/init:/docker-entrypoint-initdb.d" \
  mysql:8.0
```

### 2. 编译后端项目

#### 方式一：使用本地Java和Maven（推荐）

**前提条件：**
- 已安装Java 17或更高版本
- 项目自带Maven 3.9.4

**构建步骤：**

```bash
# 进入后端目录
cd D:\mouse\backend

# 编译项目
.\apache-maven-3.9.4\bin\mvn.cmd clean compile

# 打包项目
.\apache-maven-3.9.4\bin\mvn.cmd package -DskipTests

# 切换到Docker目录
cd D:\mouse\docker

# 启动基础服务
docker-compose up -d mysql redis minio

# 停止并删除旧的后端容器（如果存在）
docker stop mouse-backend 2>$null
docker rm mouse-backend 2>$null

# 构建并启动后端服务
docker-compose build backend --no-cache
docker-compose up backend -d

# 检查容器状态
docker logs mouse-backend --tail 20
```

**注意事项：**
1. 确保Docker中的MySQL、Redis、MinIO服务已启动
2. 数据库连接配置已修改为正确的端口（3307）
3. 验证码功能已禁用（captchaEnabled: false）

#### 方式二：使用Maven Docker镜像

```bash
# 使用Maven Docker镜像编译项目
docker run --rm \
  -v "D:/mouse/backend:/workspace" \
  -w /workspace \
  maven:3.8-openjdk-17 \
  mvn clean package -DskipTests
```

### 3. 启动后端服务

```bash
# 启动后端容器
docker run -d --name mouse-backend \
  --network mouse-network \
  -p 8081:8080 \
  -v "D:/mouse/backend/ruoyi-admin/target:/app" \
  openjdk:17-jdk-slim \
  java -jar /app/ruoyi-admin.jar
```

## 重要注意事项

### 数据库配置

- MySQL运行在**3307端口**（避免与本地MySQL冲突）
- 数据库名：`mouse_behavior`
- 用户名：`root`
- 密码：`mouse123456`
- 容器内部通信使用：`mouse-mysql:3306`

### 网络配置

- 所有容器必须连接到`mouse-network`网络
- 容器间通信使用容器名作为主机名
- 外部访问使用映射的端口

### 字体问题解决

验证码功能需要字体支持，如遇到字体错误：

```bash
# 在后端容器中安装字体
docker exec -it mouse-backend apt-get update
docker exec -it mouse-backend apt-get install -y fontconfig fonts-dejavu-core
docker restart mouse-backend
```

### 端口映射

- MySQL：`3307:3306`
- 后端服务：`8081:8080`
- 前端访问：`http://localhost:8081`

## 常用命令

### 查看容器状态

```bash
# 查看运行中的容器
docker ps

# 查看容器日志
docker logs mouse-backend
docker logs mouse-mysql
```

### 重启服务

```bash
# 重启后端服务
docker restart mouse-backend

# 重启MySQL
docker restart mouse-mysql
```

### 停止和清理

```bash
# 停止所有容器
docker stop mouse-backend mouse-mysql

# 删除容器
docker rm mouse-backend mouse-mysql

# 删除网络
docker network rm mouse-network
```

## 完整部署流程（推荐）

基于实际部署经验，以下是经过验证的完整部署流程：

### 1. 准备工作

```bash
# 确保在项目根目录
cd D:\mouse

# 检查Docker服务状态
docker --version
docker-compose --version
```

### 2. 编译后端项目

```bash
# 进入后端目录
cd D:\mouse\backend

# 使用项目自带的Maven进行清理和编译
.\apache-maven-3.9.4\bin\mvn.cmd clean compile

# 打包项目（跳过测试）
.\apache-maven-3.9.4\bin\mvn.cmd package -DskipTests
```

### 3. Docker容器部署

```bash
# 切换到Docker目录
cd D:\mouse\docker

# 启动基础服务（MySQL、Redis、MinIO）
docker-compose up -d mysql redis minio

# 停止并删除旧的后端容器（如果存在）
docker stop mouse-backend
docker rm mouse-backend

# 构建并启动后端服务
docker-compose build backend --no-cache
docker-compose up backend -d

# 检查容器状态
docker logs mouse-backend
```

### 4. 验证部署

```bash
# 检查容器运行状态
docker ps

# 查看应用启动日志
docker logs mouse-backend --tail 20

# 测试接口访问
curl http://localhost:8080/captcha/captchaImage
```

## 常见部署问题及解决方案

### 1. 配置文件冲突问题

**问题描述：** 应用启动时报错 `InvalidConfigDataPropertyException`

**原因：** `application-prod.yml` 中包含 `spring.profiles.active` 或 `spring.profiles.include` 配置，与Docker环境变量冲突

**解决方案：**
```yaml
# 在 application-prod.yml 中删除以下配置
spring:
  profiles:
    active: druid  # 删除此行
    include: druid # 删除此行
```

### 2. Docker镜像构建问题

**问题描述：** Docker构建时网络连接失败

**解决方案：**
- 使用本地已有的Docker镜像
- 修改Dockerfile使用本地编译的jar包
- 避免在容器内重新编译

### 3. jar包版本不一致问题

**问题描述：** 容器中的jar包不是最新编译的版本

**解决方案：**
```bash
# 确保每次部署都重新构建镜像
docker-compose build backend --no-cache

# 检查容器内jar包时间戳
docker exec mouse-backend ls -la /app/app.jar
```

### 4. 匿名访问配置问题

**问题描述：** `@Anonymous` 注解对 `@RestController` 不生效

**解决方案：**
```java
// 修改 PermitAllUrlProperties.java
Map<String, Object> controllerBeans = applicationContext.getBeansWithAnnotation(Controller.class);
Map<String, Object> restControllerBeans = applicationContext.getBeansWithAnnotation(RestController.class);
controllerBeans.putAll(restControllerBeans);
```

### 5. Dockerfile优化建议

**推荐的Dockerfile配置：**
```dockerfile
# 使用本地编译的jar包
FROM openjdk:17-jdk-slim

WORKDIR /app

RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# 直接复制本地编译好的jar包
COPY backend/ruoyi-admin/target/ruoyi-admin.jar app.jar
COPY backend/ruoyi-admin/src/main/resources/application-prod.yml /app/application-prod.yml

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

ENV JAVA_OPTS="-Xms512m -Xmx1024m -Dspring.profiles.active=prod"

CMD sh -c "java $JAVA_OPTS -jar app.jar"
```

## 故障排除

### 1. 端口被占用

```bash
# 检查端口占用
netstat -ano | findstr :8081
netstat -ano | findstr :3307

# 修改端口映射或停止占用进程
```

### 2. 容器无法启动

```bash
# 查看详细错误信息
docker logs <container_name>

# 检查网络连接
docker network ls
docker network inspect mouse-network
```

### 3. 数据库连接失败

- 确认MySQL容器正在运行
- 检查网络配置
- 验证数据库配置文件中的连接信息

### 4. 验证码不显示

- 安装字体包（见上文字体问题解决）
- 重启后端容器

## 开发建议

### 1. 数据持久化

建议为MySQL添加数据卷：

```bash
docker run -d --name mouse-mysql \
  --network mouse-network \
  -p 3307:3306 \
  -e MYSQL_ROOT_PASSWORD=mouse123456 \
  -e MYSQL_DATABASE=mouse_behavior \
  -v mysql_data:/var/lib/mysql \
  -v "D:/mouse/docker/mysql/conf:/etc/mysql/conf.d" \
  -v "D:/mouse/docker/mysql/init:/docker-entrypoint-initdb.d" \
  mysql:8.0
```

### 2. 开发模式

开发时可以使用热重载：

```bash
# 挂载源码目录进行开发
docker run -d --name mouse-backend-dev \
  --network mouse-network \
  -p 8081:8080 \
  -v "D:/mouse/backend:/workspace" \
  -w /workspace \
  maven:3.8-openjdk-17 \
  mvn spring-boot:run
```

### 3. 资源清理

定期清理Docker资源：

```bash
# 清理未使用的镜像、容器、网络
docker system prune -f

# 清理未使用的卷
docker volume prune -f
```

## 安全建议

1. **生产环境**：修改默认密码
2. **网络隔离**：使用专用网络
3. **资源限制**：为容器设置内存和CPU限制
4. **日志管理**：配置日志轮转

## 性能优化

1. **内存分配**：为Java应用设置合适的堆内存
2. **连接池**：优化数据库连接池配置
3. **镜像优化**：使用多阶段构建减小镜像大小

---

**最后更新：** 2025年8月
**维护者：** 项目开发团队