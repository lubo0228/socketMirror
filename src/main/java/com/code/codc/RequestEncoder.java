package com.code.codc;

import com.code.constant.ConstantValue;
import com.code.model.Request;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
/**
 * 请求编码器
 * <pre>
 * 数据包格式
 * +——----——+——-----——+——----——+——----——+——-----——+
 * | 包头          | 模块号        | 命令号      |  长度        |   数据       |
 * +——----——+——-----——+——----——+——----——+——-----——+
 * </pre>
 * 包头4字节
 * 模块号2字节short
 * 命令号2字节short
 * 长度4字节(描述数据部分字节长度)
 *
 */
public class RequestEncoder extends MessageToByteEncoder<Request> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Request request, ByteBuf byteBuf) throws Exception {
        //包头
        byteBuf.writeInt(ConstantValue.FLAG);
        //module
        byteBuf.writeShort(request.getModule());
        //cmd
        byteBuf.writeShort(request.getCmd());
        //长度
        byteBuf.writeInt(request.getDataLength());
        //data
        if(request.getData() != null){
            byteBuf.writeBytes(request.getData());
        }

    }
}
