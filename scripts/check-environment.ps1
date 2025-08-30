# Environment Check Script
# Check Docker environment and service status

Write-Host "=== Mouse Behavior Analysis System Environment Check ===" -ForegroundColor Cyan

# Check Docker installation
Write-Host "`n1. Checking Docker installation..." -ForegroundColor Yellow
try {
    $dockerVersion = docker --version 2>$null
    if ($dockerVersion) {
        Write-Host "OK Docker installed: $dockerVersion" -ForegroundColor Green
    } else {
        throw "Docker command failed"
    }
} catch {
    Write-Host "ERROR Docker not installed or not running" -ForegroundColor Red
    exit 1
}

# Check Docker network
Write-Host "`n2. Checking Docker network..." -ForegroundColor Yellow
try {
    $networks = docker network ls --format "table {{.Name}}{{.Driver}}" 2>$null
    if ($networks -like "*docker_mouse-network*") {
        Write-Host "OK Docker network docker_mouse-network exists" -ForegroundColor Green
    } else {
        Write-Host "WARNING Docker network docker_mouse-network does not exist" -ForegroundColor Yellow
    }
} catch {
    Write-Host "WARNING Cannot check Docker network" -ForegroundColor Yellow
}

# Check MySQL container status
Write-Host "`n3. Checking MySQL container status..." -ForegroundColor Yellow
try {
    $mysqlContainer = docker ps --filter "name=mouse-mysql" --format "table {{.Names}}{{.Status}}{{.Ports}}" 2>$null
    if ($mysqlContainer -like "*mouse-mysql*") {
        Write-Host "OK MySQL container is running" -ForegroundColor Green
        Write-Host "   $mysqlContainer" -ForegroundColor Gray
    } else {
        Write-Host "ERROR MySQL container is not running" -ForegroundColor Red
    }
} catch {
    Write-Host "ERROR Cannot check MySQL container status" -ForegroundColor Red
}

# Check port usage
Write-Host "`n4. Checking port usage..." -ForegroundColor Yellow
$ports = @(8080, 8081, 8082, 8090, 8091, 8092)
foreach ($port in $ports) {
    try {
        $portCheck = netstat -ano | findstr ":$port " 2>$null
        if ($portCheck) {
            Write-Host "WARNING Port $port is in use" -ForegroundColor Yellow
        } else {
            Write-Host "OK Port $port is available" -ForegroundColor Green
        }
    } catch {
        Write-Host "WARNING Cannot check port $port" -ForegroundColor Yellow
    }
}

# Check project files
Write-Host "`n5. Checking project files..." -ForegroundColor Yellow
$requiredFiles = @(
    "backend\ruoyi-admin\target\ruoyi-admin.jar",
    "backend\sql\ry_20250416.sql",
    "docker\docker-compose.yml"
)

foreach ($file in $requiredFiles) {
    if (Test-Path $file) {
        Write-Host "OK $file exists" -ForegroundColor Green
    } else {
        Write-Host "ERROR $file does not exist" -ForegroundColor Red
    }
}

Write-Host "`n=== Environment check completed ===" -ForegroundColor Cyan