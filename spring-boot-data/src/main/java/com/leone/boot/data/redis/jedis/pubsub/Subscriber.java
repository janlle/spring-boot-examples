package com.leone.boot.data.redis.jedis.pubsub;

import redis.clients.jedis.JedisPubSub;

/**
 * <p> 
 *
 * @author leone
 * @since 2019-01-31
 **/
public class Subscriber extends JedisPubSub {

    /**
     * 收到消息会调用
     *
     * @param channel
     * @param message
     */
    @Override
    public void onMessage(String channel, String message) {
        System.out.println(String.format("receive redis published message, channel: %s, message: %s", channel, message));
    }

    /**
     * 订阅了频道会调用
     *
     * @param channel
     * @param subscribedChannels
     */
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("subscribe redis channel success, channel: %s, subscribedChannels: %d", channel, subscribedChannels));
    }

    /**
     * 取消订阅 会调用
     *
     * @param channel
     * @param subscribedChannels
     */
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("unsubscribe redis channel, channel: %s, subscribedChannels: %d", channel, subscribedChannels));

    }


}