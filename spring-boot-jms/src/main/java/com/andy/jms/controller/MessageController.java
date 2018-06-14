package com.andy.jms.controller;

import com.andy.jms.rabbitmq.config.AMQPConstant;
import com.andy.jms.rabbitmq.sender.MessageSender;
import com.andy.jms.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Api("rabbiemq测试的所有接口")
@Slf4j
@RestController
public class MessageController {

    @Autowired
    private MessageSender messageSender;

    @ApiOperation("发送消息的接口")
    @GetMapping("/send/{msg}")
    public String send(@PathVariable("msg")String msg) {
        if (msg.equals("user")) {
            messageSender.queueSend(AMQPConstant.QUEUE_A, new User(1222,"james","james@126.com",2300.0,new Date()));
        } else if (msg.equals("topic")) {
            messageSender.sendTopic(AMQPConstant.TOPIC_EXCHANGE,AMQPConstant.KEY_A, "topic类型交换机发送的内容！");
        } else if (msg.equals("fanout")) {
            messageSender.sendFanout(AMQPConstant.FANOUT_EXCHANGE, "fanout类型交换机发送的内容！");
        } else if (msg.equals("direct")) {
            messageSender.sendTopic(AMQPConstant.TOPIC_EXCHANGE,AMQPConstant.KEY_A, "direct类型交换机发送的内容！");
        } else if (msg.equals("headers")) {
            messageSender.sendHeaders(AMQPConstant.HEADERS_EXCHANGE, "header类型交换机发送的内容！");
        } else {
            messageSender.queueSend(AMQPConstant.QUEUE_A, msg);
        }
        return "发送消息成功！";
    }

}