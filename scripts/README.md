# 脚本使用说明

本目录包含了鼠标行为分析系统的常用脚本，用于简化开发和部署流程。

## 脚本列表

### 1. `quick-start.ps1` - 快速启动脚本 ⭐

**推荐使用**，一键启动整个后端环境。

```powershell
# 完整启动（包含环境检查、编译、启动）
.\scripts\quick-start.ps1

# 跳过编译（如果已经编译过）
.\scripts\quick-start.ps1 -SkipBuild

# 仅检查环境
.\scripts\quick-start.ps1 -CheckOnly

# 使用自定义端口
.\scripts\quick-start.ps1 -Port 8093
```

### 2. `check-environment.ps1` - 环境检查脚本

检查Docker环境、网络、容器状态和端口占用情况。

```powershell
.\scripts\check-environment.ps1
```

### 3. `init-database.ps1` - 数据库初始化脚本

导入RuoYi框架的数据库结构到MySQL。

```powershell
# 使用默认参数
.\scripts\init-database.ps1

# 自定义数据库名和密码
.\scripts\init-database.ps1 -DatabaseName "custom_db" -MySQLPassword "custom_password"
```

### 4. `start-backend.ps1` - 后端启动脚本

手动启动MySQL和RuoYi后端服务的详细步骤。

```powershell
.\scripts\start-backend.ps1
```

## 使用前提

1. **Docker Desktop** 已安装并运行
2. **PowerShell** 执行策略允许运行脚本
3. 项目已完成初始编译

## 常见问题

### Q: PowerShell提示无法执行脚本？

A: 需要设置执行策略：
```powershell
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
```

### Q: 端口被占用怎么办？

A: 使用 `quick-start.ps1 -Port <其他端口>` 或手动停止占用端口的进程。

### Q: MySQL连接失败？

A: 确保Docker网络 `docker_mouse-network` 存在，可以运行环境检查脚本确认。

## 服务访问

- **后端管理界面**: http://localhost:8092
- **MySQL数据库**: localhost:3307 (用户名: root, 密码: mouse123456)

## 开发建议

1. 首次使用建议运行 `quick-start.ps1 -CheckOnly` 检查环境
2. 日常开发可使用 `quick-start.ps1 -SkipBuild` 跳过编译
3. 遇到问题时先运行 `check-environment.ps1` 诊断