# ERP Frontend Dockerfile
# 多阶段构建：先构建，再使用 nginx 运行

# 阶段1: 构建阶段
FROM node:18-alpine AS builder

WORKDIR /build

# 复制 package.json 和 package-lock.json
COPY package*.json ./

# 安装依赖
RUN npm ci --only=production || npm install

# 复制源代码
COPY . .

# 构建生产版本
RUN npm run build

# 阶段2: 运行阶段
FROM nginx:alpine

# 安装必要工具
RUN apk add --no-cache curl

# 复制自定义 nginx 配置
COPY nginx.conf /etc/nginx/conf.d/default.conf

# 从构建阶段复制静态文件
COPY --from=builder /build/dist /usr/share/nginx/html

# 创建非 root 用户
RUN addgroup -g 101 -S nginx-app && adduser -S nginx-app -u 101 -G nginx-app
RUN chown -R nginx-app:nginx-app /usr/share/nginx/html /var/cache/nginx /var/log/nginx /etc/nginx/conf.d
USER nginx-app

# 暴露端口
EXPOSE 80

# 健康检查
HEALTHCHECK --interval=30s --timeout=10s --start-period=10s --retries=3 \
    CMD curl -f http://localhost/ || exit 1

# 启动 nginx
CMD ["nginx", "-g", "daemon off;"]