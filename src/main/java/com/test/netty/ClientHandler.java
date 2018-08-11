package com.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by mayn on 2017/11/20.
 */
public class ClientHandler extends SimpleChannelInboundHandler<String> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        System.out.println("Client：" + msg);
    }
//    /**
//     * ChannelInboundHandlerAdapter
//     * @param ctx
//     * @param msg
//     * @throws Exception
//     */
//
//    //    @Override
////    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
////        try {
////            String str = (String) msg;
//////            byte[] data = new byte[buf.readableBytes()];
//////            buf.readBytes(data);
////            System.out.println("Client：" + str);
////        } finally {
////            ReferenceCountUtil.release(msg);
////        }
////    }
//
////    @Override
////    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
////        cause.printStackTrace();
////        ctx.close();
////    }
}
