# 数据库初始化脚本
# 用于导入RuoYi框架的数据库结构

Param(
    [string]$DatabaseName = "mouse_behavior",
    [string]$MySQLPassword = "mouse123456"
)

Write-Host "初始化数据库: $DatabaseName" -ForegroundColor Green

# 确保MySQL容器正在运行
Write-Host "检查MySQL容器状态..." -ForegroundColor Yellow
$mysqlStatus = docker ps --filter "name=mouse-mysql" --format "table {{.Names}}\t{{.Status}}"
if ($mysqlStatus -like "*Up*") {
    Write-Host "MySQL容器正在运行" -ForegroundColor Green
} else {
    Write-Host "MySQL容器未运行，请先启动MySQL" -ForegroundColor Red
    exit 1
}

# 导入RuoYi数据库结构
Write-Host "导入RuoYi数据库结构..." -ForegroundColor Green
cd backend
cmd /c "type sql\ry_20250416.sql | docker exec -i mouse-mysql mysql -u root -p$MySQLPassword $DatabaseName --default-character-set=utf8mb4"

# 验证导入结果
Write-Host "验证数据库表..." -ForegroundColor Yellow
docker exec mouse-mysql mysql -u root -p$MySQLPassword -e "USE $DatabaseName; SHOW TABLES;"

Write-Host "数据库初始化完成！" -ForegroundColor Green
cd ..