package com.code.codc;

import com.code.constant.ConstantValue;
import com.code.model.Response;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 请求编码器
 * <pre>
 * 数据包格式
 * +——----——+——-----——+——----——+——----——+——-----——+——-----——+
 * | 包头          | 模块号        | 命令号       |  状态码    |  长度          |   数据       |
 * +——----——+——-----——+——----——+——----——+——-----——+——-----——+
 * </pre>
 * 包头4字节
 * 模块号2字节short
 * 命令号2字节short
 * 长度4字节(描述数据部分字节长度)
 * 
 *
 */
public class ResponseEncoder extends MessageToByteEncoder<Response> {


	@Override
	protected void encode(ChannelHandlerContext channelHandlerContext, Response response, ByteBuf byteBuf) throws Exception {
		//包头
		byteBuf.writeInt(ConstantValue.FLAG);
		//module
		byteBuf.writeShort(response.getModule());
		//cmd
		byteBuf.writeShort(response.getCmd());
		//状态码
		byteBuf.writeInt(response.getStateCode());
		//长度
		byteBuf.writeInt(response.getDataLength());
		//data
		if(response.getData() != null){
			byteBuf.writeBytes(response.getData());
		}

//		return buffer;
	}
}
