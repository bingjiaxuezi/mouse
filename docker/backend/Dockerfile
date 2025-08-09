# 使用本地编译的jar包
FROM openjdk:17-jdk-slim

WORKDIR /app

# 跳过curl安装，避免网络问题
# RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# 直接复制本地编译好的jar包
COPY backend/ruoyi-admin/target/ruoyi-admin.jar app.jar
COPY backend/ruoyi-admin/src/main/resources/application-prod.yml /app/application-prod.yml

EXPOSE 8080

# HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
#   CMD curl -f http://localhost:8080/actuator/health || exit 1

ENV JAVA_OPTS="-Xms512m -Xmx1024m -Dspring.profiles.active=prod -Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8"

CMD sh -c "java $JAVA_OPTS -jar app.jar"