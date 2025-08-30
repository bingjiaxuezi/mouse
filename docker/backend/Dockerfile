# 使用本地编译的jar包
FROM openjdk:17-jdk-slim

# 第一层：安装字体库依赖解决Excel导出问题
# 使用阿里云镜像源提高下载稳定性
# 这一层很少变化，可以被Docker缓存
RUN sed -i 's/deb.debian.org/mirrors.aliyun.com/g' /etc/apt/sources.list && \
    sed -i 's/security.debian.org/mirrors.aliyun.com/g' /etc/apt/sources.list && \
    apt-get update && apt-get install -y \
    libfreetype6 \
    fontconfig \
    fonts-dejavu-core \
    && rm -rf /var/lib/apt/lists/*

# 第二层：复制应用文件
# 当应用代码变更时，只有这一层需要重新构建
COPY backend/ruoyi-admin/target/ruoyi-admin.jar app.jar
COPY backend/ruoyi-admin/src/main/resources/application-prod.yml /app/application-prod.yml

EXPOSE 8080
EXPOSE 5005

# HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
#   CMD curl -f http://localhost:8080/actuator/health || exit 1

ENV JAVA_OPTS="-Xms512m -Xmx1024m -Dspring.profiles.active=prod -Dfile.encoding=UTF-8 -Dsun.jnu.encoding=UTF-8 -Djava.awt.headless=true -Djava.awt.font.useSystemFontSettings=false -Djava.awt.fontpath= -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"

CMD sh -c "java $JAVA_OPTS -jar app.jar"