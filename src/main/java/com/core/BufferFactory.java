package com.core;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteOrder;
/**
 * buff工厂
 *
 */
public class BufferFactory {
	
	public static ByteOrder BYTE_ORDER = ByteOrder.BIG_ENDIAN;

	/**
	 * 获取一个buffer
	 * 
	 * @return
	 */
	public static ByteBuf getBuffer() {
		ByteBuf dynamicBuffer = Unpooled.buffer();
		return dynamicBuffer;
	}

	/**
	 * 将数据写入buffer
	 * @param bytes
	 * @return
	 */
	public static ByteBuf getBuffer(byte[] bytes) {
		ByteBuf copiedBuffer = Unpooled.copiedBuffer(bytes);
		return copiedBuffer;
	}

}
