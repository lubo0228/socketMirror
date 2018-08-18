package com.code.codc;

import com.code.constant.ConstantValue;
import com.code.model.Request;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class RequestDecoder extends ByteToMessageDecoder {


    /**
     * 数据包基本长度
     */
    public static int BASE_LENTH = 4 + 2 + 2 + 4;
    
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        //可读长度必须大于基本长度
        if(byteBuf.readableBytes() >= BASE_LENTH){
            //防止socket字节流攻击
            if(byteBuf.readableBytes() > 2048){
                byteBuf.skipBytes(byteBuf.readableBytes());
            }

            //记录包头开始的index
            int beginReader;

            while(true){
                beginReader = byteBuf.readerIndex();
                byteBuf.markReaderIndex();
                if(byteBuf.readInt() == ConstantValue.FLAG){
                    break;
                }

                //未读到包头，略过一个字节
                byteBuf.resetReaderIndex();
                byteBuf.readByte();

                //长度又变得不满足
                if(byteBuf.readableBytes() < BASE_LENTH){
//                    return null;
                }
            }

            //模块号
            short module = byteBuf.readShort();
            //命令号
            short cmd = byteBuf.readShort();
            //长度
            int length = byteBuf.readInt();

            //判断请求数据包数据是否到齐
            if(byteBuf.readableBytes() < length){
                //还原读指针
                byteBuf.readerIndex(beginReader);
//                return null;
            }

            //读取data数据
            byte[] data = new byte[length];
            byteBuf.readBytes(data);
            Request request = new Request();
            request.setModule(module);
            request.setCmd(cmd);
            request.setData(data);

            //继续往下传递
//            return request;
            list.add(request);
        }
        //数据包不完整，需要等待后面的包来
//        return null;
    }

}
