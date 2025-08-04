# 环境检查脚本
# 检查Docker环境和服务状态

Write-Host "=== 鼠标行为分析系统环境检查 ===" -ForegroundColor Cyan

# 检查Docker是否安装
Write-Host "\n1. 检查Docker安装状态..." -ForegroundColor Yellow
try {
    $dockerVersion = docker --version
    Write-Host "✅ Docker已安装: $dockerVersion" -ForegroundColor Green
} catch {
    Write-Host "❌ Docker未安装或未启动" -ForegroundColor Red
    exit 1
}

# 检查Docker网络
Write-Host "\n2. 检查Docker网络..." -ForegroundColor Yellow
$networks = docker network ls --format "table {{.Name}}\t{{.Driver}}"
if ($networks -like "*docker_mouse-network*") {
    Write-Host "✅ Docker网络 docker_mouse-network 存在" -ForegroundColor Green
} else {
    Write-Host "⚠️  Docker网络 docker_mouse-network 不存在" -ForegroundColor Yellow
}

# 检查MySQL容器状态
Write-Host "\n3. 检查MySQL容器状态..." -ForegroundColor Yellow
$mysqlContainer = docker ps --filter "name=mouse-mysql" --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"
if ($mysqlContainer -like "*mouse-mysql*") {
    Write-Host "✅ MySQL容器正在运行" -ForegroundColor Green
    Write-Host "   $mysqlContainer" -ForegroundColor Gray
} else {
    Write-Host "❌ MySQL容器未运行" -ForegroundColor Red
}

# 检查端口占用情况
Write-Host "\n4. 检查端口占用情况..." -ForegroundColor Yellow
$ports = @(8080, 8081, 8082, 8090, 8091, 8092)
foreach ($port in $ports) {
    $portCheck = netstat -ano | findstr ":$port "
    if ($portCheck) {
        Write-Host "⚠️  端口 $port 已被占用" -ForegroundColor Yellow
    } else {
        Write-Host "✅ 端口 $port 可用" -ForegroundColor Green
    }
}

# 检查项目文件
Write-Host "\n5. 检查项目文件..." -ForegroundColor Yellow
$requiredFiles = @(
    "backend\ruoyi-admin\target\ruoyi-admin.jar",
    "backend\sql\ry_20250416.sql",
    "docker\docker-compose.yml"
)

foreach ($file in $requiredFiles) {
    if (Test-Path $file) {
        Write-Host "✅ $file 存在" -ForegroundColor Green
    } else {
        Write-Host "❌ $file 不存在" -ForegroundColor Red
    }
}

Write-Host "\n=== 环境检查完成 ===" -ForegroundColor Cyan