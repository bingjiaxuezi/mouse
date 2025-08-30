# Docker使用指南

## 项目概述

本项目使用Docker容器化部署，提供完整的自动化部署解决方案。系统包含以下组件：
- **MySQL数据库**（端口3306）
- **Redis缓存**（端口6379）
- **若依后端服务**（端口8092）
- **MinIO对象存储**（端口9000/9001）

## 环境要求

- **Docker Desktop**：最新版本，确保正常运行
- **PowerShell**：Windows环境下的脚本执行
- **至少4GB可用内存**
- **至少10GB可用磁盘空间**
- **Git**：用于克隆项目代码

## 快速启动

### 方式一：一键自动化部署（推荐）

**适用场景**：首次部署、完整重启

```powershell
# 1. 克隆项目
git clone https://github.com/your-username/mouse.git
cd mouse

# 2. 一键启动所有服务
.\scripts\quick-start.ps1
```

**该脚本会自动完成：**
- 检查Docker环境
- 构建后端Docker镜像
- 启动MySQL、Redis、MinIO、后端服务
- 初始化数据库
- 显示服务状态和访问地址

### 方式二：开发模式部署

**适用场景**：开发调试、代码更新

```powershell
# 一键编译部署（保持数据不丢失）
.\scripts\deploy.ps1
```

**该脚本功能：**
- 自动编译打包Spring Boot项目
- 重新构建Docker镜像
- 重启后端服务
- 保持数据库数据不丢失

### 方式三：手动Docker部署

**适用场景**：自定义配置、故障排除

```powershell
# 1. 环境检查
.\scripts\check-environment.ps1

# 2. 手动启动基础服务
docker-compose -f docker\docker-compose.yml up -d mysql redis minio

# 3. 编译并启动后端
.\scripts\deploy.ps1
```

## 脚本工具详解

| 脚本名称 | 功能描述 | 适用场景 |
|---------|---------|----------|
| `quick-start.ps1` | 一键完整部署 | 首次部署、完整重启 |
| `deploy.ps1` | 编译部署后端 | 代码更新、开发调试 |
| `check-environment.ps1` | 环境检查 | 故障排除、环境验证 |
| `stop-services.ps1` | 停止所有服务 | 系统维护、资源清理 |

## 重要注意事项

### 服务端口配置

| 服务 | 容器端口 | 主机端口 | 访问地址 |
|------|---------|---------|----------|
| MySQL | 3306 | 3306 | localhost:3306 |
| Redis | 6379 | 6379 | localhost:6379 |
| MinIO API | 9000 | 9000 | localhost:9000 |
| MinIO Console | 9001 | 9001 | localhost:9001 |
| 后端服务 | 8080 | 8092 | http://localhost:8092 |

### 数据库配置

- **数据库名**：`ry-vue`
- **用户名**：`root`
- **密码**：`password`
- **字符集**：`utf8mb4`
- **时区**：`Asia/Shanghai`

### 网络配置

- 所有容器连接到`mouse_network`网络
- 容器间通信使用服务名作为主机名
- 支持外部访问的端口已映射到主机

### 系统访问

部署成功后，可通过以下地址访问系统：

- **主系统**：http://localhost:8092
- **MinIO控制台**：http://localhost:9001
- **默认登录**：admin / admin123

## 常用命令

### 服务管理

```powershell
# 查看所有服务状态
.\scripts\check-environment.ps1

# 停止所有服务
.\scripts\stop-services.ps1

# 重新部署后端
.\scripts\deploy.ps1

# 完整重启
.\scripts\quick-start.ps1
```

### Docker原生命令

```powershell
# 查看运行中的容器
docker ps

# 查看容器日志
docker logs docker-mouse-backend-1
docker logs docker-mysql-1
docker logs docker-redis-1

# 重启特定服务
docker restart docker-mouse-backend-1
docker restart docker-mysql-1
```

### 故障排除

```powershell
# 查看详细日志
docker logs docker-mouse-backend-1 --tail 100

# 进入容器调试
docker exec -it docker-mouse-backend-1 /bin/bash

# 检查网络连接
docker network ls
docker network inspect docker_mouse_network
```

## 完整部署流程（推荐）

### 新用户首次部署

```powershell
# 1. 克隆项目
git clone https://github.com/your-username/mouse.git
cd mouse

# 2. 环境检查
.\scripts\check-environment.ps1

# 3. 一键部署
.\scripts\quick-start.ps1

# 4. 访问系统
# 浏览器打开：http://localhost:8092
# 登录账号：admin / admin123
```

### 开发者日常部署

```powershell
# 代码更新后重新部署
.\scripts\deploy.ps1

# 查看服务状态
.\scripts\check-environment.ps1
```

### 验证部署成功

```powershell
# 检查所有容器状态
docker ps

# 查看后端启动日志
docker logs docker-mouse-backend-1 --tail 50

# 测试系统访问
# 访问：http://localhost:8092
# 应该能看到登录页面
```

## 常见问题及解决方案

### 1. 端口被占用

**问题现象：** 启动失败，提示端口已被占用

**解决方案：**
```powershell
# 检查端口占用
netstat -ano | findstr :8092
netstat -ano | findstr :3306

# 停止占用进程或修改端口配置
# 或者使用脚本停止所有服务
.\scripts\stop-services.ps1
```

### 2. Docker服务未启动

**问题现象：** 提示"Cannot connect to the Docker daemon"

**解决方案：**
```powershell
# 启动Docker Desktop
# 或检查Docker服务状态
.\scripts\check-environment.ps1
```

### 3. 容器启动失败

**问题现象：** 容器无法正常启动

**解决方案：**
```powershell
# 查看详细错误日志
docker logs docker-mouse-backend-1 --tail 100

# 重新构建镜像
.\scripts\deploy.ps1

# 完整重启所有服务
.\scripts\quick-start.ps1
```

### 4. 数据库连接失败

**问题现象：** 后端无法连接数据库

**解决方案：**
```powershell
# 检查MySQL容器状态
docker logs docker-mysql-1

# 重启MySQL服务
docker restart docker-mysql-1

# 检查网络连接
docker network inspect docker_mouse_network
```

## 开发建议

### 数据持久化

项目已配置数据卷持久化：
- MySQL数据：`mysql_data`卷
- MinIO数据：`minio_data`卷
- Redis数据：`redis_data`卷

### 开发模式

```powershell
# 开发时使用deploy脚本，保持数据不丢失
.\scripts\deploy.ps1

# 查看实时日志
docker logs docker-mouse-backend-1 -f
```

### 资源清理

```powershell
# 停止所有服务
.\scripts\stop-services.ps1

# 清理未使用的Docker资源
docker system prune -f
```

## 生产环境建议

1. **安全配置**：修改默认密码和密钥
2. **资源限制**：为容器设置内存和CPU限制
3. **监控日志**：配置日志收集和监控
4. **备份策略**：定期备份数据库和文件
5. **网络安全**：使用HTTPS和防火墙

## 技术支持

如遇到问题，请按以下顺序排查：

1. 运行环境检查脚本：`.\scripts\check-environment.ps1`
2. 查看容器日志：`docker logs docker-mouse-backend-1`
3. 检查网络连接：`docker network inspect docker_mouse_network`
4. 重新部署：`.\scripts\quick-start.ps1`

---

**最后更新：** 2025年1月
**维护者：** 小鼠行为分析系统开发团队