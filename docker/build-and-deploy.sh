#!/bin/bash

# 小鼠行为监测系统 - 生产环境打包部署脚本
# 使用方法: ./build-and-deploy.sh [环境] [操作]
# 环境: dev|test|prod (默认: prod)
# 操作: build|deploy|restart|stop|logs (默认: build)

set -e

# 配置变量
ENV=${1:-prod}
ACTION=${2:-build}
PROJECT_NAME="mouse-behavior"
DOCKER_REGISTRY="your-registry.com"  # 替换为实际的Docker仓库地址
VERSION=$(date +"%Y%m%d-%H%M%S")

# 颜色输出
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

log_info() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

log_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

log_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

log_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# 检查Docker是否运行
check_docker() {
    if ! docker info > /dev/null 2>&1; then
        log_error "Docker未运行，请启动Docker服务"
        exit 1
    fi
    log_info "Docker服务正常"
}

# 检查必要文件
check_files() {
    local required_files=(
        "docker-compose.yml"
        "backend/Dockerfile"
        "frontend/Dockerfile"
        "python-collector/Dockerfile"
    )
    
    for file in "${required_files[@]}"; do
        if [[ ! -f "$file" ]]; then
            log_error "缺少必要文件: $file"
            exit 1
        fi
    done
    log_info "所有必要文件检查通过"
}

# 构建Spring Boot后端
build_backend() {
    log_info "开始构建Spring Boot后端..."
    
    # 检查是否存在Maven项目
    if [[ ! -f "../backend/pom.xml" ]]; then
        log_error "未找到Spring Boot项目，请确保后端代码在 ../backend 目录"
        return 1
    fi
    
    # Maven打包
    cd ../backend
    log_info "执行Maven打包..."
    mvn clean package -DskipTests -P${ENV}
    
    # 复制jar文件到Docker构建目录
    cp target/*.jar ../docker/backend/
    cd ../docker
    
    # 构建Docker镜像
    log_info "构建后端Docker镜像..."
    docker build -t ${PROJECT_NAME}-backend:${VERSION} -t ${PROJECT_NAME}-backend:latest ./backend
    
    log_success "后端构建完成"
}

# 构建Vue前端
build_frontend() {
    log_info "开始构建Vue前端..."
    
    # 检查是否存在Vue项目
    if [[ ! -f "../frontend/package.json" ]]; then
        log_error "未找到Vue项目，请确保前端代码在 ../frontend 目录"
        return 1
    fi
    
    # 复制前端代码到Docker构建目录
    cp -r ../frontend/* ./frontend/
    
    # 构建Docker镜像
    log_info "构建前端Docker镜像..."
    docker build -t ${PROJECT_NAME}-frontend:${VERSION} -t ${PROJECT_NAME}-frontend:latest ./frontend
    
    # 清理临时文件
    find ./frontend -name "*.vue" -o -name "*.js" -o -name "*.ts" -o -name "*.json" | head -10 | xargs rm -f
    
    log_success "前端构建完成"
}

# 构建Python采集端
build_python_collector() {
    log_info "开始构建Python数据采集端..."
    
    # 检查是否存在Python项目
    if [[ ! -f "../python-collector/requirements.txt" ]]; then
        log_warning "未找到Python采集端项目，跳过构建"
        return 0
    fi
    
    # 复制Python代码到Docker构建目录
    cp -r ../python-collector/* ./python-collector/
    
    # 构建Docker镜像
    log_info "构建Python采集端Docker镜像..."
    docker build -t ${PROJECT_NAME}-python-collector:${VERSION} -t ${PROJECT_NAME}-python-collector:latest ./python-collector
    
    log_success "Python采集端构建完成"
}

# 推送镜像到仓库
push_images() {
    if [[ "$ENV" == "prod" ]]; then
        log_info "推送镜像到Docker仓库..."
        
        # 标记镜像
        docker tag ${PROJECT_NAME}-backend:latest ${DOCKER_REGISTRY}/${PROJECT_NAME}-backend:${VERSION}
        docker tag ${PROJECT_NAME}-frontend:latest ${DOCKER_REGISTRY}/${PROJECT_NAME}-frontend:${VERSION}
        docker tag ${PROJECT_NAME}-python-collector:latest ${DOCKER_REGISTRY}/${PROJECT_NAME}-python-collector:${VERSION}
        
        # 推送镜像
        docker push ${DOCKER_REGISTRY}/${PROJECT_NAME}-backend:${VERSION}
        docker push ${DOCKER_REGISTRY}/${PROJECT_NAME}-frontend:${VERSION}
        docker push ${DOCKER_REGISTRY}/${PROJECT_NAME}-python-collector:${VERSION}
        
        log_success "镜像推送完成"
    fi
}

# 部署服务
deploy_services() {
    log_info "开始部署服务..."
    
    # 设置环境变量
    export COMPOSE_PROJECT_NAME=${PROJECT_NAME}-${ENV}
    export IMAGE_VERSION=${VERSION}
    
    # 停止现有服务
    docker-compose -f docker-compose.yml down
    
    # 启动服务
    docker-compose -f docker-compose.yml up -d
    
    # 等待服务启动
    log_info "等待服务启动..."
    sleep 30
    
    # 检查服务状态
    check_services
    
    log_success "服务部署完成"
}

# 检查服务状态
check_services() {
    log_info "检查服务状态..."
    
    local services=("mysql" "redis" "minio" "backend" "frontend")
    
    for service in "${services[@]}"; do
        if docker-compose ps | grep -q "${service}.*Up"; then
            log_success "$service 服务运行正常"
        else
            log_error "$service 服务启动失败"
        fi
    done
}

# 显示日志
show_logs() {
    local service=${3:-""}
    if [[ -n "$service" ]]; then
        docker-compose logs -f "$service"
    else
        docker-compose logs -f
    fi
}

# 停止服务
stop_services() {
    log_info "停止所有服务..."
    docker-compose down
    log_success "服务已停止"
}

# 重启服务
restart_services() {
    log_info "重启服务..."
    docker-compose restart
    sleep 10
    check_services
    log_success "服务重启完成"
}

# 清理资源
cleanup() {
    log_info "清理未使用的Docker资源..."
    docker system prune -f
    docker volume prune -f
    log_success "清理完成"
}

# 主函数
main() {
    log_info "开始执行 $ACTION 操作，环境: $ENV"
    
    check_docker
    check_files
    
    case $ACTION in
        "build")
            build_backend
            build_frontend
            build_python_collector
            push_images
            ;;
        "deploy")
            deploy_services
            ;;
        "restart")
            restart_services
            ;;
        "stop")
            stop_services
            ;;
        "logs")
            show_logs
            ;;
        "cleanup")
            cleanup
            ;;
        *)
            log_error "未知操作: $ACTION"
            echo "支持的操作: build|deploy|restart|stop|logs|cleanup"
            exit 1
            ;;
    esac
    
    log_success "操作完成！"
}

# 执行主函数
main "$@"