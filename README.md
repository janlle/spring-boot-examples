# Spring Boot

**Spring Boot 简介**

Spring Boot是由Pivotal团队提供的全新框架，其设计目的是用来简化新Spring应用的初始搭建以及开发过程。该框架使用了特定的方式来进行配置，从而使开发人员不再需要定义样板化的配置。通过这种方式，Boot致力于在蓬勃发展的快速应用开发领域（rapid application development）成为领导者。

**Spring Boot特点** 

1. 创建独立的Spring应用程序
2. 嵌入的Tomcat，无需部署WAR文件
3. 简化Maven配置
4. 自动配置Spring
5. 提供生产就绪型功能，如指标，健康检查和外部配置
6. 绝对没有代码生成和对XML没有要求配置

**Spring Boot 主要目标是**

* 为所有 Spring 的开发提供一个从根本上更快的入门体验

* 开箱即用，但通过自己设置参数，即可快速摆脱这种方式。

* 提供了一些大型项目中常见的非功能性特性，如内嵌服务器、安全、指标，健康检测、外部化配置等

* 绝对没有代码生成，也无需 XML 配置。


## 应用的全局配置文件

可以在Spring Boot项目的src/main/resources目录下或者在类路径下创建一个全局的配置文件application.properties或者application.yml的文件用于修改Spring Boot项目的默认配置值，例如修改项目的默认端口，或者进入DispatcherServlet的请求地址规则等。通常，在实际开发中我们习惯使用application.properties文件作为应用的全局配置文件，一般我们放到src/main/resources目录下。例如,在src/main/resources目录下创建一个名称为application.yml的文件，配置内容如下:

```
spring:
  application:
    name: spring-boot-demo
server:
  port: 8080
  servlet:
    context-path: /demo
```


##  Starters启动器
Spring Boot为我们提供了简化项目开发的Starter启动器，例如我们在项目中使用的pom.xml文件下配置：

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

Spring Boot就会自动关联web开发相关的依赖，如Tomcat以及spring-webmvc等，进而对web开发进行支持，同时相关技术的配置也将实现自动配置,程序员即可从繁琐的配置文件中脱身而出了。除此之外，官方还提供了如下Starters：

* spring-boot-starter：这是Spring Boot的核心启动器，包含了自动配置、日志和YAML文件的支持。

* spring-boot-starter-activemq：为JMS使用Apache ActiveMQ ActiveMQ 是Apache出品，最流行的，能力强劲的开源消息总线

* spring-boot-starter-amqp：通过spring-rabbit来支持AMQP协议（Advanced Message Queuing Protocol）。

* spring-boot-starter-aop：支持面向方面的编程即AOP，包括spring-aop和AspectJ。

* spring-boot-starter-artemis：通过Apache Artemis支持JMS的API（Java Message Service API）。

* spring-boot-starter-batch：支持Spring Batch，包括HSQLDB数据库。

* spring-boot-starter-cache：支持Spring的Cache抽象。

* spring-boot-starter-cloud-connectors：支持Spring Cloud Connectors，简化了在像Cloud Foundry或Heroku这样的云平台上连接服务。

* spring-boot-starter-data-cassandra：使用Cassandra分布式数据库、Spring Data Cassandra,Apache Cassandra是一套开源分布式NoSQL数据库系统。

* spring-boot-starter-data-couchbase：使用Couchbase 文件存储数据库、Spring Data Couchbase。Spring Data是一个用于简化数据库访问，并支持云服务的开源框架。

* spring-boot-starter-data-elasticsearch：支持ElasticSearch搜索和分析引擎，包括spring-data-elasticsearch。

* spring-boot-starter-data-gemfire：支持GemFire分布式数据存储，包括spring-data-gemfire。

* spring-boot-starter-data-jpa：支持JPA（Java Persistence API），包括spring-data-jpa、spring-orm、Hibernate。

* spring-boot-starter-data-ldap：支持 Spring Data LDAP。

* spring-boot-starter-data-mongodb：支持MongoDB数据，包括spring-data-mongodb。

* spring-boot-starter-data-neo4j：使用Neo4j图形数据库、Spring Data Neo4j Neo4j是一个高性能的，NOSQL图形数据库，它将结构化数据存储在网络上而不是表中。

* spring-boot-starter-redis：支持Redis键值存储数据库，包括spring-redis。

* spring-boot-starter-data-rest：通过spring-data-rest-webmvc，支持通过REST暴露Spring Data数据仓库。

* spring-boot-starter-data-solr：支持Apache Solr搜索平台，包括spring-data-solr。

* spring-boot-starter-freemarker：支持FreeMarker模板引擎。

* spring-boot-starter-groovy-templates：支持Groovy模板引擎。

* spring-boot-starter-hateoas：通过spring-hateoas支持基于HATEOAS的RESTful Web服务。

* spring-boot-starter-integration：支持通用的spring-integration模块。

* spring-boot-starter-jdbc：支持JDBC数据库。

* spring-boot-starter-jersey：支持Jersey RESTful Web服务框架。

* spring-boot-starter-hornetq：通过HornetQ支持JMS。

* spring-boot-starter-jta-atomikos：通过Atomikos支持JTA分布式事务处理。

* spring-boot-starter-jta-bitronix：通过Bitronix支持JTA分布式事务处理。

* spring-boot-starter-mail：支持javax.mail模块。

* spring-boot-starter-mobile：支持spring-mobile。

* spring-boot-starter-mustache：支持Mustache模板引擎。

* spring-boot-starter-security：支持spring-security。

* spring-boot-starter-social-facebook：支持spring-social-facebook

* spring-boot-starter-social-linkedin：支持pring-social-linkedin

* spring-boot-starter-social-twitter：支持pring-social-twitter

* spring-boot-starter-test：支持常规的测试依赖，包括JUnit、Hamcrest、Mockito以及spring-test模块。

* spring-boot-starter-thymeleaf：支持Thymeleaf模板引擎，包括与Spring的集成。

* spring-boot-starter-velocity：支持Velocity模板引擎。

* spring-boot-starter-web：支持全栈式Web开发，包括Tomcat和spring-webmvc。

* spring-boot-starter-websocket：支持WebSocket开发。

* spring-boot-starter-ws：支持Spring Web Services。


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