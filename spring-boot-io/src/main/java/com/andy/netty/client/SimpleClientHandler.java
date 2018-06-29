package com.andy.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-06-29 21:56
 **/
@Slf4j
public class SimpleClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    //客户端连接服务器后被调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端连接服务器，开始发送数据...");
        byte[] req = "client say hello".getBytes();
        ByteBuf message = Unpooled.buffer(req.length);
        message.writeBytes(req);
    }

    //•	从服务器接收到数据后调用
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        log.info("client 读取server数据..");
        //服务端返回消息后
        ByteBuf buf = byteBuf;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        log.info("服务端数据为 :" + body);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("client exceptionCaught..");
        ctx.close();
    }
}
