# 基于哪个镜像
FROM java:8

# 将本地文件夹挂载到当前容器
VOLUME /tmp

# 维护者信息
MAINTAINER leone "exklin@gmail.com"

# 拷贝文件到容器
ADD spring-docker.jar eureka.jar

# 执行命令
RUN bash -c 'touch eureka.jar'

# 声明端口
EXPOSE 8761

# 配置容器启动后执行的命令
ENTRYPOINT ["java","-jar","-Djava.security.egd=file:/dev/./urandom","eureka.jar"]