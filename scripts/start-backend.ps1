# RuoYi后端服务启动脚本
# 用于启动MySQL数据库和RuoYi后端应用

# 启动MySQL容器
Write-Host "启动MySQL容器..." -ForegroundColor Green
cd docker
docker-compose up -d mysql

# 等待MySQL启动完成
Write-Host "等待MySQL启动完成..." -ForegroundColor Yellow
Start-Sleep -Seconds 10

# 返回项目根目录
cd ..

# 编译项目（如果需要）
Write-Host "编译RuoYi项目..." -ForegroundColor Green
docker run --rm -v "${PWD}:/app" -w /app maven:3.8.4-openjdk-17 mvn clean package -DskipTests

# 启动RuoYi后端服务
Write-Host "启动RuoYi后端服务..." -ForegroundColor Green
Write-Host "服务将在 http://localhost:8092 启动" -ForegroundColor Cyan
docker run --rm -v "${PWD}:/app" -w /app -p 8092:80 --network docker_mouse-network maven:3.8.4-openjdk-17 java -jar ruoyi-admin/target/ruoyi-admin.jar