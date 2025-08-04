# 鼠标行为分析系统快速启动脚本
# 一键启动整个后端环境

Param(
    [switch]$SkipBuild,
    [switch]$CheckOnly,
    [int]$Port = 8092
)

Write-Host "=== 鼠标行为分析系统快速启动 ===" -ForegroundColor Cyan

# 如果只是检查环境
if ($CheckOnly) {
    .\scripts\check-environment.ps1
    exit
}

# 1. 环境检查
Write-Host "\n步骤 1: 环境检查" -ForegroundColor Yellow
.\scripts\check-environment.ps1

# 2. 启动MySQL
Write-Host "\n步骤 2: 启动MySQL容器" -ForegroundColor Yellow
cd docker
docker-compose up -d mysql
cd ..

# 等待MySQL启动
Write-Host "等待MySQL启动完成..." -ForegroundColor Gray
Start-Sleep -Seconds 15

# 3. 检查数据库是否需要初始化
Write-Host "\n步骤 3: 检查数据库" -ForegroundColor Yellow
$tableCount = docker exec mouse-mysql mysql -u root -pmouse123456 -e "USE mouse_behavior; SELECT COUNT(*) as count FROM information_schema.tables WHERE table_schema = 'mouse_behavior';" --skip-column-names 2>$null
if ($tableCount -eq $null -or $tableCount -lt 10) {
    Write-Host "数据库需要初始化..." -ForegroundColor Yellow
    .\scripts\init-database.ps1
} else {
    Write-Host "数据库已存在，跳过初始化" -ForegroundColor Green
}

# 4. 编译项目（可选）
if (-not $SkipBuild) {
    Write-Host "\n步骤 4: 编译项目" -ForegroundColor Yellow
    if (-not (Test-Path "backend\ruoyi-admin\target\ruoyi-admin.jar")) {
        Write-Host "编译RuoYi项目..." -ForegroundColor Gray
        docker run --rm -v "${PWD}:/app" -w /app maven:3.8.4-openjdk-17 mvn clean package -DskipTests
    } else {
        Write-Host "JAR文件已存在，跳过编译" -ForegroundColor Green
    }
} else {
    Write-Host "\n步骤 4: 跳过编译" -ForegroundColor Yellow
}

# 5. 检查端口
Write-Host "\n步骤 5: 检查端口 $Port" -ForegroundColor Yellow
$portCheck = netstat -ano | findstr ":$Port "
if ($portCheck) {
    Write-Host "警告: 端口 $Port 已被占用，请手动停止相关进程或使用其他端口" -ForegroundColor Red
    Write-Host "使用参数 -Port <端口号> 指定其他端口" -ForegroundColor Yellow
    exit 1
}

# 6. 启动后端服务
Write-Host "\n步骤 6: 启动RuoYi后端服务" -ForegroundColor Yellow
Write-Host "服务将在 http://localhost:$Port 启动" -ForegroundColor Cyan
Write-Host "按 Ctrl+C 停止服务" -ForegroundColor Gray

docker run --rm -v "${PWD}:/app" -w /app -p ${Port}:80 --network docker_mouse-network maven:3.8.4-openjdk-17 java -jar ruoyi-admin/target/ruoyi-admin.jar