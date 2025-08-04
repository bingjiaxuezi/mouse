# 小鼠行为监测系统 - Windows PowerShell 部署脚本
# 使用方法: .\build-and-deploy.ps1 [环境] [操作]
# 环境: dev|test|prod (默认: prod)
# 操作: build|deploy|restart|stop|logs (默认: build)

param(
    [string]$Environment = "prod",
    [string]$Action = "build",
    [string]$Service = ""
)

# 配置变量
$ProjectName = "mouse-behavior"
$DockerRegistry = "your-registry.com"  # 替换为实际的Docker仓库地址
$Version = Get-Date -Format "yyyyMMdd-HHmmss"

# 日志函数
function Write-Info {
    param([string]$Message)
    Write-Host "[INFO] $Message" -ForegroundColor Blue
}

function Write-Success {
    param([string]$Message)
    Write-Host "[SUCCESS] $Message" -ForegroundColor Green
}

function Write-Warning {
    param([string]$Message)
    Write-Host "[WARNING] $Message" -ForegroundColor Yellow
}

function Write-Error {
    param([string]$Message)
    Write-Host "[ERROR] $Message" -ForegroundColor Red
}

# 检查Docker是否运行
function Test-Docker {
    try {
        docker info | Out-Null
        Write-Info "Docker服务正常"
        return $true
    }
    catch {
        Write-Error "Docker未运行，请启动Docker Desktop"
        return $false
    }
}

# 检查必要文件
function Test-RequiredFiles {
    $requiredFiles = @(
        "docker-compose.yml",
        "backend\Dockerfile",
        "frontend\Dockerfile",
        "python-collector\Dockerfile"
    )
    
    foreach ($file in $requiredFiles) {
        if (-not (Test-Path $file)) {
            Write-Error "缺少必要文件: $file"
            return $false
        }
    }
    Write-Info "所有必要文件检查通过"
    return $true
}

# 构建Spring Boot后端
function Build-Backend {
    Write-Info "开始构建Spring Boot后端..."
    
    # 检查是否存在Maven项目
    if (-not (Test-Path "..\backend\pom.xml")) {
        Write-Error "未找到Spring Boot项目，请确保后端代码在 ..\backend 目录"
        return $false
    }
    
    try {
        # Maven打包
        Push-Location "..\backend"
        Write-Info "执行Maven打包..."
        mvn clean package -DskipTests -P$Environment
        
        # 复制jar文件到Docker构建目录
        Copy-Item "target\*.jar" "..\docker\backend\" -Force
        Pop-Location
        
        # 构建Docker镜像
        Write-Info "构建后端Docker镜像..."
        docker build -t "$ProjectName-backend:$Version" -t "$ProjectName-backend:latest" .\backend
        
        Write-Success "后端构建完成"
        return $true
    }
    catch {
        Write-Error "后端构建失败: $($_.Exception.Message)"
        Pop-Location
        return $false
    }
}

# 构建Vue前端
function Build-Frontend {
    Write-Info "开始构建Vue前端..."
    
    # 检查是否存在Vue项目
    if (-not (Test-Path "..\frontend\package.json")) {
        Write-Error "未找到Vue项目，请确保前端代码在 ..\frontend 目录"
        return $false
    }
    
    try {
        # 复制前端代码到Docker构建目录
        Copy-Item "..\frontend\*" ".\frontend\" -Recurse -Force
        
        # 构建Docker镜像
        Write-Info "构建前端Docker镜像..."
        docker build -t "$ProjectName-frontend:$Version" -t "$ProjectName-frontend:latest" .\frontend
        
        # 清理临时文件
        Get-ChildItem ".\frontend" -Include "*.vue", "*.js", "*.ts", "package.json" -Recurse | Remove-Item -Force -ErrorAction SilentlyContinue
        
        Write-Success "前端构建完成"
        return $true
    }
    catch {
        Write-Error "前端构建失败: $($_.Exception.Message)"
        return $false
    }
}

# 构建Python采集端
function Build-PythonCollector {
    Write-Info "开始构建Python数据采集端..."
    
    # 检查是否存在Python项目
    if (-not (Test-Path "..\python-collector\requirements.txt")) {
        Write-Warning "未找到Python采集端项目，跳过构建"
        return $true
    }
    
    try {
        # 复制Python代码到Docker构建目录
        Copy-Item "..\python-collector\*" ".\python-collector\" -Recurse -Force
        
        # 构建Docker镜像
        Write-Info "构建Python采集端Docker镜像..."
        docker build -t "$ProjectName-python-collector:$Version" -t "$ProjectName-python-collector:latest" .\python-collector
        
        Write-Success "Python采集端构建完成"
        return $true
    }
    catch {
        Write-Error "Python采集端构建失败: $($_.Exception.Message)"
        return $false
    }
}

# 推送镜像到仓库
function Push-Images {
    if ($Environment -eq "prod") {
        Write-Info "推送镜像到Docker仓库..."
        
        try {
            # 标记镜像
            docker tag "$ProjectName-backend:latest" "$DockerRegistry/$ProjectName-backend:$Version"
            docker tag "$ProjectName-frontend:latest" "$DockerRegistry/$ProjectName-frontend:$Version"
            docker tag "$ProjectName-python-collector:latest" "$DockerRegistry/$ProjectName-python-collector:$Version"
            
            # 推送镜像
            docker push "$DockerRegistry/$ProjectName-backend:$Version"
            docker push "$DockerRegistry/$ProjectName-frontend:$Version"
            docker push "$DockerRegistry/$ProjectName-python-collector:$Version"
            
            Write-Success "镜像推送完成"
        }
        catch {
            Write-Error "镜像推送失败: $($_.Exception.Message)"
        }
    }
}

# 部署服务
function Deploy-Services {
    Write-Info "开始部署服务..."
    
    try {
        # 设置环境变量
        $env:COMPOSE_PROJECT_NAME = "$ProjectName-$Environment"
        $env:IMAGE_VERSION = $Version
        
        # 停止现有服务
        docker-compose -f docker-compose.yml down
        
        # 启动服务
        docker-compose -f docker-compose.yml up -d
        
        # 等待服务启动
        Write-Info "等待服务启动..."
        Start-Sleep -Seconds 30
        
        # 检查服务状态
        Test-Services
        
        Write-Success "服务部署完成"
    }
    catch {
        Write-Error "服务部署失败: $($_.Exception.Message)"
    }
}

# 检查服务状态
function Test-Services {
    Write-Info "检查服务状态..."
    
    $services = @("mysql", "redis", "minio", "backend", "frontend")
    
    foreach ($service in $services) {
        $status = docker-compose ps $service
        if ($status -match "Up") {
            Write-Success "$service 服务运行正常"
        }
        else {
            Write-Error "$service 服务启动失败"
        }
    }
}

# 显示日志
function Show-Logs {
    if ($Service) {
        docker-compose logs -f $Service
    }
    else {
        docker-compose logs -f
    }
}

# 停止服务
function Stop-Services {
    Write-Info "停止所有服务..."
    docker-compose down
    Write-Success "服务已停止"
}

# 重启服务
function Restart-Services {
    Write-Info "重启服务..."
    docker-compose restart
    Start-Sleep -Seconds 10
    Test-Services
    Write-Success "服务重启完成"
}

# 清理资源
function Clear-Resources {
    Write-Info "清理未使用的Docker资源..."
    docker system prune -f
    docker volume prune -f
    Write-Success "清理完成"
}

# 主函数
function Main {
    Write-Info "开始执行 $Action 操作，环境: $Environment"
    
    if (-not (Test-Docker)) {
        exit 1
    }
    
    if (-not (Test-RequiredFiles)) {
        exit 1
    }
    
    switch ($Action.ToLower()) {
        "build" {
            $success = (Build-Backend) -and (Build-Frontend) -and (Build-PythonCollector)
            if ($success) {
                Push-Images
            }
        }
        "deploy" {
            Deploy-Services
        }
        "restart" {
            Restart-Services
        }
        "stop" {
            Stop-Services
        }
        "logs" {
            Show-Logs
        }
        "cleanup" {
            Clear-Resources
        }
        default {
            Write-Error "未知操作: $Action"
            Write-Host "支持的操作: build|deploy|restart|stop|logs|cleanup"
            exit 1
        }
    }
    
    Write-Success "操作完成！"
}

# 执行主函数
Main