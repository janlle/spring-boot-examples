# spring-boot-examples
**Spring Boot 简介**

Spring Boot是由Pivotal团队提供的全新框架，其设计目的是用来简化新Spring应用的初始搭建以及开发过程。该框架使用了特定的方式来进行配置，从而使开发人员不再需要定义样板化的配置。通过这种方式，Boot致力于在蓬勃发展的快速应用开发领域（rapid application development）成为领导者。

**Spring Boot特点** 

1. 创建独立的Spring应用程序
2. 嵌入的Tomcat，无需部署WAR文件
3. 简化Maven配置
4. 自动配置Spring
5. 提供生产就绪型功能，如指标，健康检查和外部配置
6. 绝对没有代码生成和对XML没有要求配置

**Spring boot的优点**

1.spring boot 可以支持你快速的开发出 restful 风格的微服务架构

2.自动化确实方便，做微服务再合适不过了，单一jar包部署和管理都非常方便。只要系统架构设计合理，大型项目也能用，加上nginx负载均衡，轻松实现横向扩展

3.spring boot 要解决的问题, 精简配置是一方面, 另外一方面是如何方便的让spring生态圈和其他工具链整合(比如redis, email, elasticsearch)

**开始开发 Spring Boot 应用**
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.andy</groupId>
    <version>1.0.0.RELEASE</version>
    <artifactId>spring-boot-demo</artifactId>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>io.spring.platform</groupId>
                <artifactId>platform-bom</artifactId>
                <version>Cairo-SR2</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Finchley.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>

</project>

```

启动类
```
@SpringBootApplication
public class App {
    public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
```