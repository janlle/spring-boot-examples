package com.andy.jms.rabbitmq.sender;

import com.andy.jms.rabbitmq.config.RabbitMQConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageSender {

    @Autowired
    private AmqpTemplate template;

    //-------------------------普通队列模式-------------------------------
    public void sendQueue(String queue, Object msg) {
        log.info("发到:{},队列的消息是:{}", queue, msg);
        template.convertAndSend(queue, msg);
    }


    /**
     * direct类型的交换机(直连模式)
     * 直连交换机是一种带路由功能的交换机，一个队列会和一个交换机绑定，除此之外再绑定一个routing_key，当消息被发送的时候，需要指定一个binding_key，这个消息被送达交换机的时候，就会被这个交换机送到指定的队列里面去。同样的一个binding_key也是支持应用到多个队列中的。
     *
     * 这样当一个交换机绑定多个队列，就会被送到对应的队列去处理。
     *
     * @param exchange
     * @param msg
     */
    public void sendDirect(String exchange, Object msg) {
        template.convertAndSend(exchange, RabbitMQConstant.KEY_C, msg);
        template.convertAndSend(exchange, RabbitMQConstant.KEY_D, msg);
        log.info("发到:{}交换机,routing_key:{},发送的消息是:{}", exchange, RabbitMQConstant.KEY_C, msg);
        log.info("发到:{}交换机,routing_key:{},发送的消息是:{}", exchange, RabbitMQConstant.KEY_D, msg);
    }


    /**
     * topic类型的交换机(主题模式)
     *
     * 直连交换机的routing_key方案非常简单，如果我们希望一条消息发送给多个队列，那么这个交换机需要绑定上非常多的routing_key，假设每个交换机上都绑定一堆的routing_key连接到各个队列上。那么消息的管理就会异常地困难。
     * 所以RabbitMQ提供了一种主题交换机，发送到主题交换机上的消息需要携带指定规则的routing_key，主题交换机会根据这个规则将数据发送到对应的(多个)队列上。
     * 主题交换机的routing_key需要有一定的规则，交换机和队列的binding_key需要采用*.#.*.....的格式，每个部分用.分开，其中：
     * *表示一个单词
     * #表示任意数量（零个或多个）单词。
     *
     * @param exchange
     * @param key
     * @param msg
     */
    public void sendTopic(String exchange, String key, Object msg) {
        template.convertAndSend(exchange, RabbitMQConstant.KEY_A, msg + "a");
        template.convertAndSend(exchange, RabbitMQConstant.KEY_B, msg + "b");
        log.info("发到:{},交换机,绑定规则为{}的消息是:{}", exchange, RabbitMQConstant.KEY_A, msg);
        log.info("发到:{},交换机,绑定规则为{}的消息是:{}", exchange, RabbitMQConstant.KEY_B, msg);
    }

    /**
     * fanout类型的交换机(广播模式)
     *
     * 扇形交换机是最基本的交换机类型，它所能做的事情非常简单———广播消息。扇形交换机会把能接收到的消息全部发送给绑定在自己身上的队列。因为广播不需要“思考”，所以扇形交换机处理消息的速度也是所有的交换机类型里面最快的。
     *
     * @param exchange
     * @param msg
     */
    public void sendFanout(String exchange, Object msg) {
        template.convertAndSend(exchange, "", msg);
        log.info("发到:{},交换机,发送的消息是:{}", exchange, msg);
    }


    /**
     * headers类型的交换机(首部模式)
     *
     * 首部交换机是忽略routing_key的一种路由方式。路由器和交换机路由的规则是通过Headers信息来交换的，这个有点像HTTP的Headers。将一个交换机声明成首部交换机，绑定一个队列的时候，定义一个Hash的数据结构，消息发送的时候，会携带一组hash数据结构的信息，当Hash的内容匹配上的时候，消息就会被写入队列。
     * 绑定交换机和队列的时候，Hash结构中要求携带一个键“x-match”，这个键的Value可以是any或者all，这代表消息携带的Hash是需要全部匹配(all)，还是仅匹配一个键(any)就可以了。相比直连交换机，首部交换机的优势是匹配的规则不被限定为字符串(string)。
     *
     * @param exchange
     * @param msg
     */
    public void sendHeaders(String exchange, String msg) {
        MessageProperties properties = new MessageProperties();
        properties.setHeader("header-a", "value-a");
        properties.setHeader("header-b", "value-b");

        Message message = new Message(msg.getBytes(), properties);
        template.convertAndSend(exchange, "", message);
        log.info("发到:{},交换机,发送的消息是:{}", exchange, msg.getBytes());
    }

}