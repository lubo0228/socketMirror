package com.test.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 单客户端多连接
 */
public class MultClient {
    /**
     * 服务类
     */
    private Bootstrap bootstrap = new Bootstrap();

    /**
     * 会话
     */
    private List<Channel> channels = new ArrayList<>();

    /**
     * 引用计数
     */
    private final AtomicInteger index = new AtomicInteger();

    /**
     * 初始化
     *
     * @param count
     */
    public void init(int count) {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) {
                        socketChannel.pipeline().addLast(new StringDecoder());
                        socketChannel.pipeline().addLast(new StringEncoder());
                        socketChannel.pipeline().addLast(new ClientHandler());
                    }
                });
        for (int i = 0; i < count; i++) {
            ChannelFuture channelFuture = bootstrap.connect("192.168.2.129", 10010);
            channels.add(channelFuture.channel());
        }
    }

    /**
     * 获取会话
     *
     * @return
     */
    public Channel nextChannel() {
        return getActiveChannel(0);
    }

    private Channel getActiveChannel(int count) {
        Channel channel = channels.get(Math.abs(index.getAndIncrement() % channels.size()));
        if (!channel.isActive()) {
            //重连
            reconnect(channel);
            if (count >= channels.size()) {
                throw new RuntimeException("no can use channel");
            }
            return getActiveChannel(count + 1);
        }
        return channel;
    }

    private void reconnect(Channel channel) {
        synchronized (channel) {
            if (channels.indexOf(channel) == -1) {
                return;
            }
            ChannelFuture channelFuture = bootstrap.connect("192.168.2.129", 10010);
            channels.set(channels.indexOf(channel), channelFuture.channel());
        }
    }
}
