package com.andy.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author: lyon
 * @createBy: 2018-06-29 21:25
 **/
@Slf4j
public class SimpleServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        log.info("开始读取数据！");
        ByteBuf buf = (ByteBuf)msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        log.info("收到的数据是:{}", body);
        //向客户端响应数据
        log.info("像客户端响应数据");
        String currentTime = new Date().toString();
        ByteBuf res = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.write(res);
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("数据读取完毕");
        //刷新后才将数据发送到socketChannel
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
