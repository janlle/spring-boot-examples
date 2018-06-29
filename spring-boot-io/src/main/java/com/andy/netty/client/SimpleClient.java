package com.andy.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @Author: Mr.lyon
 * @CreateBy: 2018-06-29 21:10
 **/
@Slf4j
public class SimpleClient {

    private String host;

    private int port;

    public SimpleClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup eventLoopGroup = null;

        try {
            //创建Bootstrap对象用来引导启动客户端
            Bootstrap bootstrap = new Bootstrap();
            //创建EventLoopGroup对象并设置到Bootstrap中，EventLoopGroup可以理解为是一个线程池，这个线程池用来处理连接、接受数据、发送数据
            eventLoopGroup = new NioEventLoopGroup();
            //创建InetSocketAddress并设置到Bootstrap中，InetSocketAddress是指定连接的服务器地址
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress(host, port))
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) {
                    socketChannel.pipeline().addLast(new SimpleClientHandler());
                }
            });
            // • 调用Bootstrap.connect()来连接服务器
            ChannelFuture future = bootstrap.connect().sync();
            // • 最后关闭EventLoopGroup来释放资源
            future.channel().close().sync();
        } catch (Exception e) {
            log.info("erroe");
        } finally {
            eventLoopGroup.shutdownGracefully().sync();
        }


    }

    public static void main(String[] args) throws Exception {
        new SimpleClient("localhost", 9999).start();
    }


}
