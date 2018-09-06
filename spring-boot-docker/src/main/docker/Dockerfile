# 基于哪个镜像
FROM java:8
# 将本地文件夹挂载到当前容器
VOLUME /tmp
# 维护者信息
MAINTAINER leone "janlle@126.com"

RUN mkdir -p /usr/local/app

# 拷贝文件到容器
ADD target/spring-docker.jar /usr/local/app/spring-docker.jar

# 声明需要暴露的端口
EXPOSE 8761
# 配置容器启动后执行的命令
ENTRYPOINT ["java","-jar","/usr/local/app/spring-docker.jar"]