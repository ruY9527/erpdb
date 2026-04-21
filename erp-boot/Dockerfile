# ERP Backend Dockerfile
# 多阶段构建：先编译，再运行

# 阶段1: 构建阶段
FROM maven:3.8.6-openjdk-8-slim AS builder

WORKDIR /build

# 复制 pom.xml 并下载依赖（利用 Docker 缓存层）
COPY pom.xml .
RUN mvn dependency:go-offline -B

# 复制源代码并构建
COPY src ./src
RUN mvn clean package -DskipTests -B

# 阶段2: 运行阶段
FROM openjdk:8-jre-slim

WORKDIR /app

# 安装必要工具
RUN apt-get update && apt-get install -y --no-install-recommends \
    curl \
    && rm -rf /var/lib/apt/lists/*

# 从构建阶段复制 jar 文件
COPY --from=builder /build/target/erp-boot.jar app.jar

# 创建非 root 用户运行应用
RUN groupadd -r erp && useradd -r -g erp erp
USER erp

# 暴露端口
EXPOSE 8080

# 健康检查
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

# JVM 参数优化
ENV JAVA_OPTS="-Xms512m -Xmx1024m -XX:+UseG1GC -XX:MaxGCPauseMillis=200"

# 启动应用
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]