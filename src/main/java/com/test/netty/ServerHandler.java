package com.test.netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * Created by mayn on 2017/11/20.
 */
public class ServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        System.out.println("Server: " + msg);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if (idleStateEvent.state() == IdleState.ALL_IDLE) {
                ChannelFuture channelFuture = ctx.channel().writeAndFlush("time out");
                channelFuture.addListener((ChannelFutureListener) channelFuture1 -> channelFuture1.channel().close());
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }


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
