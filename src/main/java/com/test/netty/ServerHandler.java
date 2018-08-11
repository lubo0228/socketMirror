package com.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by mayn on 2017/11/20.
 */
public class ServerHandler extends SimpleChannelInboundHandler<String> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        System.out.println("Server: " + msg);
    }

//    /**
//     * ChannelInboundHandlerAdapter
//     * @param ctx
//     * @param msg
//     * @throws Exception
//     */
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//
//        //do something msg
//        String buf = (String)msg;
////        byte[] data = new byte[buf.readableBytes()];
////        buf.readBytes(data);
////        String request = new String(data, "utf-8");
//        System.out.println("Server: " + buf);
//        //写给客户端
//        ctx.writeAndFlush(Unpooled.copiedBuffer("我是服务端的信息".getBytes()));
//        //.addListener(ChannelFutureListener.CLOSE);
//
//
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        cause.printStackTrace();
//        ctx.close();
//    }
}
