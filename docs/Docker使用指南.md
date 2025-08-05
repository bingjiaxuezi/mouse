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