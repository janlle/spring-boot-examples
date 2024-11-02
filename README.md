# Spring Boot

## 分布式锁

## 分布式事务

```
spring:
  application:
    name: spring-boot-demo
server:
  port: 8080
  servlet:
    context-path: /demo
```


## Spring Boot 自动配置的原理

Spring Boot在进行SpringApplication对象实例化时会加载META-INF/spring.factories文件，将该配置文件中的配置载入到Spring容器，进行自动配置。


## 开始开发 Spring Boot 应用

* 起步依赖
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

* 启动类
```
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
```

* 启动后访问localhost:8080/hello
```
@RestController
public class HelloWorldController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello,World!";
    }
}

```