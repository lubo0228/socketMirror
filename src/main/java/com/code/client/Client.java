package com.code.client;

import com.code.codc.RequestEncoder;
import com.code.codc.ResponseDecoder;
import com.code.model.Request;
import com.code.module.fuben.request.FightRequest;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try{
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            socketChannel.pipeline().addLast(new ResponseDecoder());
                            socketChannel.pipeline().addLast(new RequestEncoder());
//                            socketChannel.pipeline().addLast(new IntegerToByteEncoder());
//                            socketChannel.pipeline().addLast(new ByteToIntegerDecoder());
                            socketChannel.pipeline().addLast(new ClientHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect("127.0.0.1", 10010).sync();
            System.out.println("client start");
//            future.channel().writeAndFlush(1111);
//            future.channel().closeFuture().sync();
            Scanner scanner = new Scanner(System.in);
            while(true) {
                System.out.println("请输入");
                int fubenId = Integer.parseInt(scanner.nextLine());
                int count = Integer.parseInt(scanner.nextLine());

                FightRequest fightRequest = new FightRequest();
                fightRequest.setFubenId(fubenId);
                fightRequest.setCount(count);

                Request request = new Request();
                request.setModule((short) 1);
                request.setCmd((short) 1);
                request.setData(fightRequest.getBytes());
                //发送请求 write没用
                future.channel().writeAndFlush(request);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
        }
    }
}
