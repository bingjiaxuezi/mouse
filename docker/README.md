# 小鼠行为监测系统 - Docker部署指南

## 📋 概述

本目录包含了小鼠行为监测系统的完整Docker化部署方案，支持一键部署整个系统栈。

## 🏗️ 架构组件

### 核心服务
- **MySQL 8.0**: 主数据库，存储业务数据
- **Redis 7**: 缓存服务，提升系统性能
- **MinIO**: 对象存储，管理视频文件和图片
- **Spring Boot**: 后端API服务
- **Vue + Nginx**: 前端Web界面
- **Python Collector**: 数据采集端（可选）

## 📁 目录结构

```
docker/
├── docker-compose.yml          # Docker Compose主配置文件
├── build-and-deploy.sh         # Linux/Mac部署脚本
├── build-and-deploy.ps1        # Windows PowerShell部署脚本
├── README.md                    # 本文档
├── backend/
│   └── Dockerfile              # Spring Boot后端镜像
├── frontend/
│   ├── Dockerfile              # Vue前端镜像
│   ├── nginx.conf              # Nginx主配置
│   └── default.conf            # Nginx站点配置
├── python-collector/
│   ├── Dockerfile              # Python采集端镜像
│   └── requirements.txt        # Python依赖
├── mysql/
│   ├── init/                   # 数据库初始化脚本
│   └── conf/                   # MySQL配置文件
└── redis/
    └── redis.conf              # Redis配置文件
```

## 🐳 Docker镜像清单

### 基础镜像
| 服务 | 基础镜像 | 版本 | 用途 |
|------|----------|------|------|
| MySQL | mysql | 8.0 | 数据库服务 |
| Redis | redis | 7-alpine | 缓存服务 |
| MinIO | minio/minio | latest | 对象存储 |

### 自定义镜像
| 服务 | 基础镜像 | 构建内容 | 端口 |
|------|----------|----------|------|
| Backend | openjdk:17-jdk-slim | Spring Boot应用 | 8080 |
| Frontend | nginx:alpine | Vue构建产物 + Nginx | 80 |
| Python Collector | python:3.9-slim | Python采集程序 | 8081 |

## 🚀 快速开始

### 前置要求
- Docker Desktop (Windows/Mac) 或 Docker Engine (Linux)
- Docker Compose v2.0+
- 至少4GB可用内存
- 至少10GB可用磁盘空间

### Windows环境部署

```powershell
# 1. 进入docker目录
cd d:\mouse\docker

# 2. 构建所有镜像
.\build-and-deploy.ps1 prod build

# 3. 部署服务
.\build-and-deploy.ps1 prod deploy

# 4. 查看服务状态
docker-compose ps

# 5. 查看日志
.\build-and-deploy.ps1 prod logs
```

### Linux/Mac环境部署

```bash
# 1. 进入docker目录
cd /path/to/mouse/docker

# 2. 给脚本执行权限
chmod +x build-and-deploy.sh

# 3. 构建所有镜像
./build-and-deploy.sh prod build

# 4. 部署服务
./build-and-deploy.sh prod deploy

# 5. 查看服务状态
docker-compose ps
```

## 🔧 配置说明

### 环境变量配置

在 `docker-compose.yml` 中可以修改以下关键配置：

```yaml
# 数据库配置
MYSQL_ROOT_PASSWORD: mouse123456
MYSQL_DATABASE: mouse_behavior
MYSQL_USER: mouse_user
MYSQL_PASSWORD: mouse_pass

# MinIO配置
MINIO_ROOT_USER: minioadmin
MINIO_ROOT_PASSWORD: minioadmin123

# Spring Boot配置
SPRING_PROFILES_ACTIVE: prod
SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/mouse_behavior
```

### 端口映射

| 服务 | 容器端口 | 主机端口 | 访问地址 |
|------|----------|----------|----------|
| Frontend | 80 | 80 | http://localhost |
| Backend | 8080 | 8080 | http://localhost:8080 |
| MySQL | 3306 | 3306 | localhost:3306 |
| Redis | 6379 | 6379 | localhost:6379 |
| MinIO | 9000/9001 | 9000/9001 | http://localhost:9001 |

### 数据持久化

系统使用Docker卷进行数据持久化：

- `mysql_data`: MySQL数据文件
- `redis_data`: Redis数据文件
- `minio_data`: MinIO对象存储数据
- `backend_logs`: 后端应用日志
- `backend_uploads`: 后端上传文件
- `collector_data`: 采集端数据文件
- `collector_logs`: 采集端日志

## 🛠️ 开发环境

### 开发模式部署

```bash
# 仅启动基础服务（数据库、缓存、存储）
docker-compose up -d mysql redis minio

# 本地运行后端和前端进行开发
# 后端: mvn spring-boot:run
# 前端: npm run dev
```

### 热重载开发

```yaml
# 在docker-compose.yml中添加开发配置
backend:
  volumes:
    - ../backend/src:/app/src:ro
    - ../backend/target:/app/target
  environment:
    - SPRING_DEVTOOLS_RESTART_ENABLED=true
```

## 📊 监控和日志

### 查看服务状态

```bash
# 查看所有服务状态
docker-compose ps

# 查看特定服务状态
docker-compose ps backend

# 查看服务资源使用情况
docker stats
```

### 日志管理

```bash
# 查看所有服务日志
docker-compose logs -f

# 查看特定服务日志
docker-compose logs -f backend

# 查看最近100行日志
docker-compose logs --tail=100 backend
```

### 健康检查

所有服务都配置了健康检查：

```bash
# 查看健康状态
docker-compose ps

# 手动执行健康检查
docker exec mouse-backend curl -f http://localhost:8080/actuator/health
```

## 🔒 安全配置

### 生产环境安全建议

1. **修改默认密码**
   ```yaml
   # 修改数据库密码
   MYSQL_ROOT_PASSWORD: your-strong-password
   
   # 修改MinIO密码
   MINIO_ROOT_PASSWORD: your-strong-password
   ```

2. **网络隔离**
   ```yaml
   # 仅暴露必要端口
   ports:
     - "80:80"  # 仅暴露前端
   # 移除数据库等内部服务的端口映射
   ```

3. **SSL/TLS配置**
   ```yaml
   # 在nginx配置中启用HTTPS
   # 使用Let's Encrypt或自签名证书
   ```

## 🚨 故障排除

### 常见问题

1. **端口冲突**
   ```bash
   # 检查端口占用
   netstat -tulpn | grep :8080
   
   # 修改docker-compose.yml中的端口映射
   ports:
     - "8081:8080"  # 改为其他端口
   ```

2. **内存不足**
   ```bash
   # 检查Docker内存限制
   docker system df
   
   # 清理未使用的资源
   docker system prune -a
   ```

3. **数据库连接失败**
   ```bash
   # 检查MySQL容器状态
   docker-compose logs mysql
   
   # 重启MySQL服务
   docker-compose restart mysql
   ```

### 性能优化

1. **JVM调优**
   ```dockerfile
   # 在backend/Dockerfile中调整JVM参数
   ENV JAVA_OPTS="-Xms1g -Xmx2g -XX:+UseG1GC"
   ```

2. **数据库优化**
   ```sql
   # 在mysql/conf/my.cnf中添加配置
   [mysqld]
   innodb_buffer_pool_size = 1G
   max_connections = 200
   ```

3. **Redis优化**
   ```conf
   # 在redis/redis.conf中配置
   maxmemory 512mb
   maxmemory-policy allkeys-lru
   ```

## 📈 扩展部署

### 集群部署

```yaml
# docker-compose.cluster.yml
version: '3.8'
services:
  backend:
    deploy:
      replicas: 3
    depends_on:
      - mysql-master
      - mysql-slave
      - redis-cluster
```

### 负载均衡

```yaml
# 添加Nginx负载均衡
nginx-lb:
  image: nginx:alpine
  ports:
    - "80:80"
  volumes:
    - ./nginx/load-balancer.conf:/etc/nginx/nginx.conf
```

## 📞 技术支持

如遇到部署问题，请检查：

1. Docker版本是否满足要求
2. 系统资源是否充足
3. 网络连接是否正常
4. 防火墙设置是否正确

更多技术细节请参考项目文档：`../docs/`