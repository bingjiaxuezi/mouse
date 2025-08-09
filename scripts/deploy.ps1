# Mouse Backend Auto Deploy Script
# Author: Trae AI Assistant
# Description: Automated build and deployment script

Write-Host "Starting automated deployment process..." -ForegroundColor Green

# Step 1: Build and package
Write-Host "Step 1: Building and packaging project..." -ForegroundColor Yellow
Set-Location "D:\mouse\backend"

Write-Host "Executing: mvn clean package -DskipTests" -ForegroundColor Cyan
.\apache-maven-3.9.4\bin\mvn.cmd clean package -DskipTests
if ($LASTEXITCODE -ne 0) {
    Write-Host "Build failed, exiting deployment process" -ForegroundColor Red
    exit 1
}
Write-Host "Build and package completed" -ForegroundColor Green

# Step 2: Verify jar file exists
Write-Host "Step 2: Verifying jar file..." -ForegroundColor Yellow
$jarPath = "D:\mouse\backend\ruoyi-admin\target\ruoyi-admin.jar"
if (-not (Test-Path $jarPath)) {
    Write-Host "Error: jar file not found: $jarPath" -ForegroundColor Red
    exit 1
}
Write-Host "Jar file verification passed: $jarPath" -ForegroundColor Green

# Step 3: Deploy services
Write-Host "Step 3: Deploying services..." -ForegroundColor Yellow
Set-Location "D:\mouse\docker"

# Stop and remove existing backend container
Write-Host "Stopping existing backend container..." -ForegroundColor Cyan
docker stop mouse-backend 2>$null
docker rm mouse-backend 2>$null

# Build new backend image
Write-Host "Building backend image..." -ForegroundColor Cyan
docker-compose build backend --no-cache
if ($LASTEXITCODE -ne 0) {
    Write-Host "Image build failed, exiting deployment process" -ForegroundColor Red
    exit 1
}

# Start backend service
Write-Host "Starting backend service..." -ForegroundColor Cyan
docker-compose up backend -d
if ($LASTEXITCODE -ne 0) {
    Write-Host "Service startup failed, exiting deployment process" -ForegroundColor Red
    exit 1
}

Write-Host "Deployment completed!" -ForegroundColor Green
Write-Host "Checking service status..." -ForegroundColor Yellow

# Wait for service to start
Start-Sleep -Seconds 5

# Check container status
$containerStatus = docker ps --filter "name=mouse-backend" --format "table {{.Names}}\t{{.Status}}"
Write-Host "Container status:" -ForegroundColor Cyan
Write-Host $containerStatus

Write-Host "Deployment process completed!" -ForegroundColor Green
Write-Host "You can check logs with: docker logs mouse-backend" -ForegroundColor Yellow