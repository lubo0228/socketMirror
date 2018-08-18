package com.code.codc;

import com.code.constant.ConstantValue;
import com.code.model.Response;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * response解码器
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
 */
public class ResponseDecoder extends ByteToMessageDecoder {
	
	/**
	 * 数据包基本长度
	 */
	public static int BASE_LENTH = 4 + 2 + 2 + 4;


	@Override
	protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
		//可读长度必须大于基本长度
		if(byteBuf.readableBytes() >= BASE_LENTH){

			//记录包头开始的index
			int beginReader = byteBuf.readerIndex();

			while(true){
				if(byteBuf.readInt() == ConstantValue.FLAG){
					break;
				}
			}

			//模块号
			short module = byteBuf.readShort();
			//命令号
			short cmd = byteBuf.readShort();
			//状态码
			int stateCode = byteBuf.readInt();
			//长度
			int length = byteBuf.readInt();

			if(byteBuf.readableBytes() < length){
				//还原读指针
				byteBuf.readerIndex(beginReader);
//				return null;
			}

			byte[] data = new byte[length];
			byteBuf.readBytes(data);
			Response response = new Response();
			response.setModule(module);
			response.setCmd(cmd);
			response.setStateCode(stateCode);
			response.setData(data);

			//继续往下传递
//			return response;
			list.add(response);
		}
		//数据包不完整，需要等待后面的包来
//		return null;
	}
}
