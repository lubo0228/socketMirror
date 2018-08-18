package com.test.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created by mayn on 2017/11/20.
 */
public class NettyClient {
    public static void main(String[] args) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try{
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline().addLast(new StringDecoder());
                            socketChannel.pipeline().addLast(new StringEncoder());
                            socketChannel.pipeline().addLast(new ClientHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect("127.0.0.1", 10011).sync();
//            future.channel().writeAndFlush(Unpooled.copiedBuffer("我是客户端的信息".getBytes()));
            future.channel().writeAndFlush("我是客户端的信息");
            future.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
        }
    }
}
