package com.leone.boot.common.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author leone
 * @since 2018-06-29
 **/
public class SimpleServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, StandardCharsets.UTF_8);
        System.out.printf("server 收到 client 发送的数据: %s", body);
        // 向客户端响应数据
        String currentTime = new Date().toString();
        System.out.printf("向客户端响应数据: %s", currentTime);
        ByteBuf res = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.write(res);
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("数据读取完毕");
        // 刷新后才将数据发送到socketChannel
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
