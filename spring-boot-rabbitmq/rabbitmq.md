# rabbitMQ 

## 消息队列

[维基百科](https://www.wikipedia.org/)

在计算机科学中，消息队列（英语：Message queue）是一种进程间通信或同一进程的不同线程间的通信方式。消息队列提供了异步的通信协议，也就是说：消息的发送者和接收者不需要同时与消息队列互交。消息会保存在队列中，直到接收者取回它。
实现
实际上，消息队列常常保存在链表结构中。拥有权限的进程可以向消息队列中写入或读取消息。
目前，有很多消息队列有很多开源的实现，包括JBoss Messaging, JORAM, Apache ActiveMQ, Sun Open Message Queue, Apache Qpid和HTTPSQS。


## 常见的消息队列

本部分主要介绍四种常用的消息队列（ActiveMQ / RabbitMQ / RocketMQ / Kafka）的主要特性、优点、缺点

### 1.ActiveMQ 是由 Apache 出品，ActiveMQ 是一个完全支持JMS1.1 和 J2EE 1.4 规范的 JMS Provider 实现。它非常快速，支持 多种语言的客户端 和 协议，而且可以非常容易的嵌入到企业的应用环境中，并有许多高级功能。

**主要特性**

* 服从JMS规范：JMS 规范提供了良好的标准和保证，包括：同步 或 异步 的消息分发，一次和仅一次的消息分发，消息接收 和 订阅 等等。遵从 JMS 规范的好处在于，不论使用什么 JMS 实现提供者，这些基础特性都是可用的；

* 连接灵活性：ActiveMQ 提供了广泛的 连接协议，支持的协议有：HTTP/S，IP 多播，SSL，TCP，UDP 等等。对众多协议的支持让 ActiveMQ 拥有了很好的灵活性；

* 支持的协议种类多：OpenWire、STOMP、REST、XMPP、AMQP；

* 持久化插件和安全插件：ActiveMQ 提供了 多种持久化 选择。而且，ActiveMQ 的安全性也可以完全依据用户需求进行 自定义鉴权 和 授权；

* 支持的客户端语言种类多：除了 Java 之外，还有：C/C++，.NET，Perl，PHP，Python，Ruby；

* 代理集群：多个 ActiveMQ 代理 可以组成一个 集群 来提供服务；

* 异常简单的管理：ActiveMQ 是以开发者思维被设计的。所以，它并不需要专门的管理员，因为它提供了简单又使用的管理特性。有很多中方法可以 监控 ActiveMQ 不同层面的数据，包括使用在 JConsole 或者在 ActiveMQ 的 Web Console 中使用 JMX。通过处理 JMX 的告警消息，通过使用 命令行脚本，甚至可以通过监控各种类型的 日志。

**运行环境**

ActiveMQ 可以运行在 Java 语言所支持的平台之上。

**优点**

* 跨平台 (JAVA 编写与平台无关，ActiveMQ 几乎可以运行在任何的 JVM 上)；

* 可以用 JDBC：可以将 数据持久化 到数据库。虽然使用 JDBC 会降低 ActiveMQ 的性能，但是数据库一直都是开发人员最熟悉的存储介质；

* 支持 JMS 规范：支持 JMS 规范提供的 统一接口;

* 支持 自动重连 和 错误重试机制；

* 有安全机制：支持基于 shiro，jaas 等多种 安全配置机制，可以对 Queue/Topic 进行 认证和授权；

* 监控完善：拥有完善的 监控，包括 Web Console，JMX，Shell 命令行，Jolokia 的 RESTful API；

* 界面友善：提供的 Web Console 可以满足大部分情况，还有很多 第三方的组件 可以使用，比如 hawtio；



**缺点**

* 社区活跃度不及 RabbitMQ 高；

* 根据其他用户反馈，会出莫名其妙的问题，会丢失消息；

* 目前重心放到 activemq 6.0 产品 Apollo，对 5.x 的维护较少；

* 不适合用于 上千个队列 的应用场景；


### rabbitMQ

RabbitMQ 于 2007 年发布，是一个在 AMQP (高级消息队列协议)基础上完成的，可复用的企业消息系统，是当前最主流的消息中间件之一。

**主要特点**

* 可靠性：提供了多种技术可以让你在 性能 和 可靠性 之间进行 权衡。这些技术包括 持久性机制、投递确认、发布者证实 和 高可用性机制；

* 灵活的路由：消息在到达队列前是通过 交换机 进行 路由 的。RabbitMQ 为典型的路由逻辑提供了 多种内置交换机 类型。如果你有更复杂的路由需求，可以将这些交换机组合起来使用，你甚至可以实现自己的交换机类型，并且当做 RabbitMQ 的 插件 来使用；

* 消息集群：在相同局域网中的多个 RabbitMQ 服务器可以 聚合 在一起，作为一个独立的逻辑代理来使用；

* 队列高可用：队列可以在集群中的机器上 进行镜像，以确保在硬件问题下还保证 消息安全；

* 支持多种协议：支持 多种消息队列协议；

* 支持多种语言：用 Erlang 语言编写，支持只要是你能想到的 所有编程语言；

* 管理界面： RabbitMQ 有一个易用的 用户界面，使得用户可以 监控 和 管理 消息 Broker 的许多方面；

* 跟踪机制：如果 消息异常，RabbitMQ 提供消息跟踪机制，使用者可以找出发生了什么；

* 插件机制：提供了许多 插件，来从多方面进行扩展，也可以编写自己的插件。


**运行环境**

RabbitMQ 可以运行在 Erlang 语言所支持的平台之上，包括 Solaris，BSD，Linux，MacOSX，TRU64，Windows 等。


**优点**

* 由于 Erlang 语言的特性，消息队列性能较好，支持高并发；

* 健壮、稳定、易用、跨平台、支持 多种语言、文档齐全；

* 有消息 确认机制 和 持久化机制，可靠性高；

* 高度可定制的 路由；

* 管理界面 较丰富，在互联网公司也有较大规模的应用，社区活跃度高。


**缺点**

* 尽管结合 Erlang 语言本身的并发优势，性能较好，但是不利于做 二次开发和维护；

* 实现了 代理架构，意味着消息在发送到客户端之前可以在 中央节点 上排队。此特性使得 RabbitMQ 易于使用和部署，但是使得其 运行速度较慢，因为中央节点 增加了延迟，消息封装后 也比较大；

* 需要学习 比较复杂的接口和协议，学习和维护成本较高。


### rocketMQ

RocketMQ 出自阿里的开源产品，用 Java 语言开发，在设计时参考了 Kafka，并做出了自己的一些改进，消息可靠性上比 Kafka 更好。RocketMQ 在阿里内部被广泛应用在 订单，交易，充值，流计算，消息推送，日志流式处理，binglog 分发等场景。

**主要特点**

* 基于 队列模型：具有 高性能、高可靠、高实时、分布式 等特点；

* Producer、Consumer、队列 都支持 分布式；

* Producer 向一些队列轮流发送消息，队列集合 称为 Topic。Consumer 如果做 广播消费，则一个 Consumer 实例消费这个 Topic 对应的 所有队列；如果做 集群消费，则 多个 Consumer 实例 平均消费 这个 Topic 对应的队列集合；

* 能够保证 严格的消息顺序；

* 提供丰富的 消息拉取模式；

* 高效的订阅者 水平扩展能力；

* 实时 的 消息订阅机制；

* 亿级 消息堆积 能力；


**运行环境**

RocketMQ 可以运行在 Java 语言所支持的平台之上。

**优点**

单机支持 1 万以上持久化队列；

* RocketMQ 的所有消息都是 持久化的，先写入系统 PAGECACHE，然后 刷盘，可以保证 内存 与 磁盘 都有一份数据，而 访问 时，直接 从内存读取。

* 模型简单，接口易用（JMS 的接口很多场合并不太实用）；

* 性能非常好，可以允许 大量堆积消息 在 Broker 中；

* 支持 多种消费模式，包括 集群消费、广播消费等；

* 各个环节分布式扩展设计，支持 主从和高可用；

* 开发度较活跃，版本更新很快。


**缺点**

* 支持的 客户端语言不多，目前是 Java 及 C++，其中 C++ 还不成熟；

* RocketMQ 社区关注度及成熟度也不及前两者；

* 没有 Web 管理界面，提供了一个 CLI (命令行界面) 管理工具带来 查询、管理 和诊断各种问题；

* 没有在 MQ 核心里实现 JMS 等接口；


### kafka

Apache Kafka 是一个 分布式消息发布订阅 系统。它最初由 LinkedIn 公司基于独特的设计实现为一个 分布式的日志提交系统 (a distributed commit log)，之后成为 Apache 项目的一部分。Kafka 性能高效、可扩展良好 并且 可持久化。它的 分区特性，可复制 和 可容错 都是其不错的特性。

**主要特点**

* 快速持久化：可以在 O(1) 的系统开销下进行 消息持久化；

* 高吞吐：在一台普通的服务器上既可以达到 10W/s 的 吞吐速率；

* 完全的分布式系统：Broker、Producer 和 Consumer 都原生自动支持 分布式，自动实现 负载均衡；

* 支持同步和异步复制两种高可用机制；

* 支持数据批量发送和拉取；

* 零拷贝技术(zero-copy)：减少 IO 操作步骤，提高系统吞吐量；

* 数据迁移、扩容对用户透明；

* 无需停机 即可扩展机器；

* 其他特性：丰富的 消息拉取模型、高效 订阅者水平扩展、实时的 消息订阅、亿级的消息堆积能力、定期删除机制；



**运行环境**

kafka 可以运行在 Java 语言所支持的平台之上。同时还需要Scala环境

**优点**

* 客户端语言丰富：支持 Java、.Net、PHP、Ruby、Python、Go 等多种语言；

* 高性能：单机写入 TPS 约在 100 万条/秒，消息大小 10 个字节；

* 提供 完全分布式架构，并有 replica 机制，拥有较高的 可用性 和 可靠性，理论上支持 消息无限堆积；

* 支持批量操作；

* 消费者 采用 Pull 方式获取消息。消息有序，通过控制 能够保证所有消息被消费且仅被消费 一次；

* 有优秀的第三方 Kafka Web 管理界面 Kafka-Manager；

* 在日志领域比较成熟，被多家公司和多个开源项目使用。


**缺点**

* Kafka 单机超过 64 个 队列/分区 时，Load 时会发生明显的飙高现象。队列 越多，负载 越高，发送消息 响应时间变长；

* 使用 短轮询方式，实时性 取决于 轮询间隔时间；

* 消费失败 不支持重试；

* 支持 消息顺序，但是 一台代理宕机 后，就会产生 消息乱序；

* 社区更新较慢。


### 四种消息中间件对比

|对比|rabbitMq|activeMq|rocketMq|kafka|
|---|---|---|---|---|
|成熟度|成熟|成熟|比较成熟|成熟|
|所属社区/公司|Mozilla public License|Apache|Ali|Apache|
|授权方式|开源|开源|开源|开源|
|开发语言|Erlang|java|java|scala&java|
|消息批量操作|不支持|支持|支持|支持|
|消息推拉模式|多协议，pull/push均有支持|多协议，pull/push均有支持|多协议，pull/push均有支持|pull|
|吞吐量|其次（万级）|最差（万级）|最高（十万级）|次之（十万级）|
	  


## rabbitmq核心概念

### rabbitmq简介

RabbitMQ是实现了高级消息队列协议（AMQP）的开源消息代理软件（亦称面向消息的中间件）。RabbitMQ服务器是用Erlang语言编写的，而群集和故障转移是构建在开放电信平台框架上的。所有主要的编程语言均有与代理接口通讯的客户端库。


### rabbitmq核心概念

* 虚拟主机：（virtual Host）：一个虚拟主机持有一组交换机、队列和绑定。为什么需要多个虚拟主机呢？很简单， RabbitMQ 当中，用户只能在虚拟主机的粒度进行权限控制。 因此，如果需要禁止A组访问B组的交换机/队列/绑定，必须为A和B分别创建一个虚拟主机。每一个 RabbitMQ 服务器都有一个默认的虚拟主机“/”。

* 交换机：（Exchange） 用于转发消息 ，如果没有 Queue 绑定到 Exchange 的话，它会直接丢弃掉生产者发送过来的消息，rabbitMq一共有四种交换机分别是topic、direct、headers和fanout。

* 路由键：（routingKey）消息到交换机的时候，交互机会转发到对应的队列中，那么究竟转发到哪个队列，就要根据该路由键。

* 绑定：（binding）也就是交换机需要和队列相绑定，这其中如上图所示，是多对多的关系。

### rabbitmq四种交换机详解（其实默认属于direct类型）

1、 默认交换机（default exchange）实际上是一个由消息代理预先声明好的没有名字（名字为空字符串）的直连交换机（direct exchange）。它有一个特殊的属性使得它对于简单应用特别有用处：那就是每个新建队列（queue）都会自动绑定到默认交换机上，绑定的路由键（routing key）名称与队列名称相同。 

2、 Direct exchange（直连交换机），(Empty string) and amq.direct直连型交换机（direct exchange）是根据消息携带的路由键（routing key）将消息投递给对应队列的。直连交换机用来处理消息的单播路由（unicast routing）（尽管它也可以处理多播路由）下边介绍它是如何工作的：将一个队列绑定到某个交换机上，同时赋予该绑定一个路由键（routing key）当一个携带着路由键为R的消息被发送给直连交换机时，交换机会把它路由给绑定值同样为R的队列。 

3、 Fanout exchange（扇型交换机），amq.fanout扇型交换机（funout exchange）将消息路由给绑定到它身上的所有队列，而不理会绑定的路由键。如果N个队列绑定到某个扇型交换机上，当有消息发送给此扇型交换机时，交换机会将消息的拷贝分别发送给这所有的N个队列。扇型用来交换机处理消息的广播路由（broadcast routing）。 

4、 Topic exchange（主题交换机），amq.topic主题交换机（topic exchanges）通过对消息的路由键和队列到交换机的绑定模式之间的匹配，将消息路由给一个或多个队列。主题交换机经常用来实现各种分发/订阅模式及其变种。主题交换机通常用来实现消息的多播路由（multicast routing）。 

5、 Headers exchange（头交换机），amq.match (and amq.headers in RabbitMQ)有时消息的路由操作会涉及到多个属性，此时使用消息头就比用路由键更容易表达，头交换机（headers exchange）就是为此而生的。头交换机使用多个消息属性来代替路由键建立路由规则。通过判断消息头的值能否与指定的绑定相匹配来确立路由规则。

## rabbitmq安装

> 本文采用docker的方式安装rabbitmq

```
# 下载镜像
$ docker pull rabbitmq


$ docker run -d \
  --hostname localhost \
  --name rabbitmq \
  -p 15672:15672 \
  -p 5672:5672 \
  rabbitmq:3-management
  
# 停止rabbitmq
$ docker stop rabbitmq

# 启动rabbitmq
$ docker start rabbitmq

```

## springBoot 整合rabbitmq

> spring 提供了 整合rabbitmq的组件整合rabbitmq非常方便

* pom.xml

```

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>

```

* BindingConfig.java

```java
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>把队列和交换机绑定
 *
 * @author leone
 * @since 2018-10-30
 **/
@Configuration
public class BindingConfig {

    // 消息队列
    @Resource(name = RabbitMqConstant.QUEUE_B)
    private Queue queue_b;

    @Resource(name = RabbitMqConstant.QUEUE_C)
    private Queue queue_c;

    @Resource(name = RabbitMqConstant.QUEUE_D)
    private Queue queue_d;

    @Resource(name = RabbitMqConstant.QUEUE_E)
    private Queue queue_e;

    @Resource(name = RabbitMqConstant.QUEUE_F)
    private Queue queue_f;

    @Resource(name = RabbitMqConstant.QUEUE_G)
    private Queue queue_g;

    @Resource(name = RabbitMqConstant.QUEUE_H)
    private Queue queue_h;

    @Resource(name = RabbitMqConstant.QUEUE_I)
    private Queue queue_i;

    // 交换机
    @Resource
    private TopicExchange topicExchange;

    @Resource
    private FanoutExchange fanoutExchange;

    @Resource
    private HeadersExchange headersExchange;

    @Resource
    private DirectExchange directExchange;


    /**
     * 主题交换机:Topic exchange
     * <p>
     * 发送到主题交换机上的消息需要携带指定规则的routing_key，主题交换机会根据这个规则将数据路由到对应的一个或多个队列上。
     * 主题交换机的routing_key需要有一定的规则，交换机和队列的binding_key需要采用*.#.*.....的格式，每个部分用.分开，其中：
     * [*]表示一个词,[#]表示任意数量（零个或多个）单词。
     *
     * @return
     */
    @Bean
    public Binding topicBindingQueueB() {
        return BindingBuilder.bind(queue_b).to(topicExchange).with(RabbitMqConstant.ROUTING_KEY_A);
    }

    @Bean
    public Binding topicBindingQueueC() {
        return BindingBuilder.bind(queue_c).to(topicExchange).with(RabbitMqConstant.ROUTING_KEY_B);
    }


    /**
     * 直连交换机:Direct exchange
     * 需要将一个队列绑定到交换机上，要求该消息与一个特定的路由键完全匹配。这是一个完整的匹配。
     * 如果一个队列绑定到该交换机上要求路由键 “abc”，则只有被标记为“abc”的消息才被转发，不会转发abc.def，也不会转发dog.ghi，只会转发abc。
     *
     * @return
     */
    @Bean
    public Binding directBindingQueueD() {
        return BindingBuilder.bind(queue_d).to(directExchange).with(RabbitMqConstant.ROUTING_KEY_C);
    }

    @Bean
    public Binding directBindingQueueE() {
        return BindingBuilder.bind(queue_e).to(directExchange).with(RabbitMqConstant.ROUTING_KEY_D);
    }


    /**
     * 首部交换机:Headers exchange
     * 交换机绑定队列的时候需定义一个Hash的数据结构，消息发送的时候，会携带一组hash类型的数据，当Hash的内容匹配上的时候，消息就会被写入队列。
     * 绑定规则中Hash结构中要求携带一个key: "x-match", value可以是 any: 仅匹配一个，或者 all: 全部匹配。不处理routingKey
     * all: 默认值。一个传送消息的header里的键值对和交换机的header键值对全部匹配，才可以路由到对应交换机（whereAll）
     * any: 一个传送消息的header里的键值对和交换机的header键值对任意一个匹配，就可以路由到对应交换机（whereAny）
     *
     * @return
     */
    @Bean
    public Binding headersBindingQueueF() {
        Map<String, Object> map = new HashMap<>();
        map.put("header-a", "value-a");
        map.put("header-b", "value-b");
        return BindingBuilder.bind(queue_f).to(headersExchange).whereAll(map).match();
    }

    @Bean
    public Binding headersBindingQueueI() {
        Map<String, Object> map = new HashMap<>();
        map.put("header-a", "value-a");
        return BindingBuilder.bind(queue_i).to(headersExchange).whereAny(map).match();
    }


    /**
     * 扇形交换机(广播):Fanout
     * 扇形交换机是最基本的交换机类型，它所能做的事情非常简单———广播消息。扇形交换机会把能接收到的消息全部发送给绑定在自己身上的队列。
     * 因为广播不需要“思考”，所以扇形交换机处理消息的速度也是所有的交换机类型里面最快的。不处理routingKey
     *
     * @return
     */
    @Bean
    public Binding fanoutBindingQueueG() {
        return BindingBuilder.bind(queue_g).to(fanoutExchange);
    }

    @Bean
    public Binding fanoutBindingQueueH() {
        return BindingBuilder.bind(queue_h).to(fanoutExchange);
    }

}

```

* ExchangeConfig.java

```java
package com.leone.boot.rabbitmq.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitMQ交换机配置
 *
 * @author leone
 * @since 2018-05-05
 **/
@Configuration
public class ExchangeConfig {


    /**
     * 参数1 name ：交互器名
     * 参数2 durable ：是否持久化
     * 参数3 autoDelete ：当所有消费客户端连接断开后，是否自动删除队列
     * @return
     */

    // ----------------------主题交换机:Topic exchange----------------------
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(RabbitMqConstant.TOPIC_EXCHANGE, true, false);
    }

    // ----------------------扇形交换机(广播):Fanout ----------------------
    @Bean
    public FanoutExchange fanoutExchange() {
//        return (FanoutExchange) ExchangeBuilder.fanoutExchange(RabbitMqConstant.FANOUT_EXCHANGE).durable(true).build();
        return new FanoutExchange(RabbitMqConstant.FANOUT_EXCHANGE, true, false);
    }

    // ----------------------首部交换机:Headers exchange----------------------
    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange(RabbitMqConstant.HEADERS_EXCHANGE, true, false);
    }

    // ----------------------直连交换机:Direct exchange----------------------
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(RabbitMqConstant.DIRECT_EXCHANGE, true, false);
    }

}

```

* QueueConfig.java

```java

package com.leone.boot.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列配置
 *
 * @author leone
 * @since 2018-04-15
 **/
@Configuration
public class QueueConfig {


    /**
     * 参数1 name ：队列名
     * 参数2 durable ：是否持久化
     * 参数3 exclusive ：仅创建者可以使用的私有队列，断开后自动删除
     * 参数4 autoDelete : 当所有消费客户端连接断开后，是否自动删除队列
     *
     * @return
     */
    @Bean(name = "queue-a")
    public Queue queue_a() {
        return new Queue(RabbitMqConstant.QUEUE_A, true, false, false);
    }

    @Bean(name = "queue-b")
    public Queue queue_b() {
        return new Queue(RabbitMqConstant.QUEUE_B, true, false, false);
    }

    @Bean(name = "queue-c")
    public Queue queue_c() {
        return new Queue(RabbitMqConstant.QUEUE_C, true, false, false);
    }

    @Bean(name = "queue-d")
    public Queue queue_d() {
        return new Queue(RabbitMqConstant.QUEUE_D, true, false, false);
    }

    @Bean(name = "queue-e")
    public Queue queue_e() {
        return new Queue(RabbitMqConstant.QUEUE_E, true, false, false);
    }

    @Bean(name = "queue-f")
    public Queue queue_f() {
        return new Queue(RabbitMqConstant.QUEUE_F, true, false, false);
    }

    @Bean(name = "queue-g")
    public Queue queue_g() {
        return new Queue(RabbitMqConstant.QUEUE_G, true, false, false);
    }

    @Bean(name = "queue-h")
    public Queue queue_h() {
        return new Queue(RabbitMqConstant.QUEUE_H, true, false, false);
    }

    @Bean(name = "queue-i")
    public Queue queue_i() {
        return new Queue(RabbitMqConstant.QUEUE_I, true, false, false);
    }

}
```

* RabbitMqConstant.java

```java

package com.leone.boot.rabbitmq.config;

/**
 * @author leone
 * @since 2018-05-01
 **/
public interface RabbitMqConstant {

    // 消息队列配置
    String QUEUE_A = "queue-a";
    String QUEUE_B = "queue-b";
    String QUEUE_C = "queue-c";
    String QUEUE_D = "queue-d";
    String QUEUE_E = "queue-e";
    String QUEUE_F = "queue-f";
    String QUEUE_G = "queue-g";
    String QUEUE_H = "queue-h";
    String QUEUE_I = "queue-i";


    // rabbitMQ有四种类型的交换机fanout、direct、topic、headers
    String HEADERS_EXCHANGE = "headers-exchange";
    String FANOUT_EXCHANGE = "fanout-exchange";
    String DIRECT_EXCHANGE = "direct-exchange";
    String TOPIC_EXCHANGE = "topic-exchange";


    // 绑定匹配规则[*]表示一个单词,[#]表示任意数量（零个或多个）单词。
    String ROUTING_KEY_A = "route.*";
    String ROUTING_KEY_B = "route.#";
    String ROUTING_KEY_C = "route-c";
    String ROUTING_KEY_D = "route-d";
    String ROUTING_KEY_E = "route-e";

}

```

* MessageSender.java

```java
package com.leone.boot.rabbitmq.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author leone
 * @since 2018-06-05
 **/
@Component
public class MessageSender {

    @Resource
    private AmqpTemplate template;

    /**
     * 发送到默认交换机 AMQP Default
     * <p>
     * 默认交换机实际上是一个由消息代理预先声明好的没有名字（名字为空字符串）的直连交换机（direct exchange）。
     * 它有一个特殊的属性使得它对于简单应用特别有用处：那就是每个新建队列（queue）都会自动绑定到默认交换机上，绑定的路由键（routing key）名称与队列名称相同。
     *
     * @param routingKey 和队列的名称相同
     * @param message
     */
    public void send(String routingKey, Object message) {
        log.info("send default direct routingKey: {} message: {}", routingKey, message);
        template.convertAndSend(routingKey, message);
    }

    /**
     * 发送到direct类型交换机
     *
     * @param exchange
     * @param message
     */
    public void sendDirect(String exchange, String routingKey, Object message) {
        template.convertAndSend(exchange, routingKey, message);
        log.info("send: {} routingKey: {} message: {}", exchange, routingKey, message);
    }


    /**
     * 发送到topic类型的交换机
     *
     * @param exchange
     * @param routingKey
     * @param message
     */
    public void sendTopic(String exchange, String routingKey, String message) {
        template.convertAndSend(exchange, routingKey, message);
        log.info("send: {} routingKye: {} message: {}", exchange, routingKey, message);
    }

    /**
     * 发送到fanout类型的交换机
     *
     * @param exchange
     * @param message
     */
    public void sendFanout(String exchange, String message) {
        template.convertAndSend(exchange, null, message);
        log.info("send: {} message: {}", exchange, message);
    }


    /**
     * headers类型的交换机(首部模式)
     *
     * @param exchange
     * @param headers
     * @param message
     */
    public void sendHeaders(String exchange, Map<String, Object> headers, String message) {
        MessageProperties properties = new MessageProperties();
        if (!ObjectUtils.isEmpty(headers)) {
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                properties.setHeader(entry.getKey(), entry.getValue());
            }
        }
        Message data = new Message(message.getBytes(), properties);
        template.convertAndSend(exchange, "", data);
        log.info("send: {} message: {}", exchange, message);
    }

}
```

* MessageReceive.java

```java
package com.leone.boot.rabbitmq.receive;

import com.leone.boot.rabbitmq.config.RabbitMqConstant;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author leone
 * @since 2018-05-15
 **/
@Component
public class MessageReceive {


    //-------------------------普通队列模式-------------------------------
    @RabbitListener(queues = RabbitMqConstant.QUEUE_A)
    public void receiveQueueA(Message message, Channel channel) throws Exception {
        try {
            Thread.sleep(3000);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (InterruptedException e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            e.printStackTrace();
        }
        log.info("receive defaultExchange message: {} queue: {}", new String(message.getBody()), message.getMessageProperties().getConsumerQueue());
    }


    //-------------------------topic类型的交换机(主题模式)-------------------------
    @RabbitListener(queues = RabbitMqConstant.QUEUE_B)
    public void receiveQueueB(Message message, Channel channel) throws Exception {
        try {
            Thread.sleep(3000);
            // 消息确认 false 只确认当前一个消息收到，true 确认所有 consumer 获得的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (InterruptedException e) {
            // true: 如果被拒绝的消息应该重新排队，否则为false
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            e.printStackTrace();
        }
        log.info("receive: {} message: {} queue: {}", message.getMessageProperties().getReceivedExchange(), new String(message.getBody()), message.getMessageProperties().getConsumerQueue());
    }

    @RabbitListener(queues = RabbitMqConstant.QUEUE_C)
    public void receiveQueueC(Message message, Channel channel) throws Exception {
        try {
            Thread.sleep(3000);
            // 消息确认 false 只确认当前一个消息收到，true 确认所有 consumer 获得的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (InterruptedException e) {
            // true: 如果被拒绝的消息应该重新排队，否则为false
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            e.printStackTrace();
        }
        log.info("receive: {} message: {} queue: {}", message.getMessageProperties().getReceivedExchange(), new String(message.getBody()), message.getMessageProperties().getConsumerQueue());
    }


    //-------------------------headers类型的交换机(首部模式)------------------------
    @RabbitListener(queues = RabbitMqConstant.QUEUE_F)
    public void receiveQueueF(Message message, Channel channel) throws Exception {
        try {
            Thread.sleep(3000);
            // 消息确认 false 只确认当前一个消息收到，true 确认所有 consumer 获得的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (InterruptedException e) {
            // true: 如果被拒绝的消息应该重新排队，否则为false
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            e.printStackTrace();
        }
        log.info("receive: {} message: {} queue: {}", message.getMessageProperties().getReceivedExchange(), new String(message.getBody()), message.getMessageProperties().getConsumerQueue());
    }

    @RabbitListener(queues = RabbitMqConstant.QUEUE_I)
    public void receiveQueueI(Message message, Channel channel) throws Exception {
        try {
            Thread.sleep(3000);
            // 消息确认 false 只确认当前一个消息收到，true 确认所有 consumer 获得的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (InterruptedException e) {
            // true: 如果被拒绝的消息应该重新排队，否则为false
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            e.printStackTrace();
        }
        log.info("receive: {} message: {} queue: {}", message.getMessageProperties().getReceivedExchange(), new String(message.getBody()), message.getMessageProperties().getConsumerQueue());

    }


    //-------------------------direct类型的交换机(直连模式)------------------------
    @RabbitListener(queues = RabbitMqConstant.QUEUE_D)
    public void receiveDirectA(Message message, Channel channel) throws Exception {
        try {
            Thread.sleep(3000);
            // 消息确认 false 只确认当前一个消息收到，true 确认所有 consumer 获得的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (InterruptedException e) {
            // true: 如果被拒绝的消息应该重新排队，否则为false
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            e.printStackTrace();
        }
        log.info("receive: {} message: {} consumerQueue: {}", message.getMessageProperties().getReceivedExchange(), new String(message.getBody()), message.getMessageProperties().getConsumerQueue());
    }

    @RabbitListener(queues = RabbitMqConstant.QUEUE_E)
    public void receiveDirectB(Message message, Channel channel) throws Exception {
        try {
            Thread.sleep(3000);
            // 消息确认 false 只确认当前一个消息收到，true 确认所有 consumer 获得的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (InterruptedException e) {
            // true: 如果被拒绝的消息应该重新排队，否则为false
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            e.printStackTrace();
        }
        log.info("receive: {} message: {} consumerQueue: {}", message.getMessageProperties().getReceivedExchange(), new String(message.getBody()), message.getMessageProperties().getConsumerQueue());
    }


    //------------------------- fanout类型的交换机(广播模式)-----------------------
    @RabbitListener(queues = RabbitMqConstant.QUEUE_G)
    public void receiveFanoutA(Message message, Channel channel) throws Exception {
        try {
            Thread.sleep(3000);
            // 消息确认 false 只确认当前一个消息收到，true 确认所有 consumer 获得的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (InterruptedException e) {
            // true: 如果被拒绝的消息应该重新排队，否则为false
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            e.printStackTrace();
        }
        log.info("receive: {}, message: {}, consumerQueue: {}", message.getMessageProperties().getReceivedExchange(), new String(message.getBody()), message.getMessageProperties().getConsumerQueue());
    }

    @RabbitListener(queues = RabbitMqConstant.QUEUE_H)
    public void receiveFanoutB(Message message, Channel channel) throws Exception {
        try {
            Thread.sleep(3000);
            // 消息确认 false 只确认当前一个消息收到，true 确认所有 consumer 获得的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (InterruptedException e) {
            // requeue: true 如果被拒绝的消息应该重新排队，否则为false
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            e.printStackTrace();
        }
        log.info("receive: {}, message: {}, consumerQueue: {}", message.getMessageProperties().getReceivedExchange(), new String(message.getBody()), message.getMessageProperties().getConsumerQueue());
    }


}
```

* RabbitMqController.java

```java
package com.leone.boot.rabbitmq.controller;

import com.leone.boot.rabbitmq.config.RabbitMqConstant;
import com.leone.boot.rabbitmq.sender.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author leone
 * @since 2018-05-15
 **/
@Slf4j
@RestController
@RequestMapping("/api/rabbitmq")
public class RabbitMqController {

    @Autowired
    private MessageSender messageSender;

    @GetMapping("/send/{exchange}")
    public String send(@PathVariable("exchange") String exchange,
                       @RequestParam String message,
                       @RequestParam(required = false, defaultValue = "") String routingKey,
                       @RequestHeader Map<String,Object> headers) {
        switch (exchange) {
            case "topic":
                messageSender.sendTopic(RabbitMqConstant.TOPIC_EXCHANGE, routingKey, message);
                break;
            case "fanout":
                messageSender.sendFanout(RabbitMqConstant.FANOUT_EXCHANGE, message);
                break;
            case "direct":
                messageSender.sendDirect(RabbitMqConstant.DIRECT_EXCHANGE, routingKey, message);
                break;
            case "headers":
                messageSender.sendHeaders(RabbitMqConstant.HEADERS_EXCHANGE, headers, message);
                break;
            default:
                messageSender.send(routingKey, message);
                break;
        }
        return "send to " + exchange + ", routingKey: " + routingKey + ", message: " + message;
    }

}
```