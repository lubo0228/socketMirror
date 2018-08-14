package com.proto;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Arrays;

/**
 * Created by L-DELL on 2018/8/13.
 */
public class Test3 {
    public static void main(String[] args) {
        //序列化
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeInt(101);
        byteBuf.writeDouble(80.1);
        byte[] bytes = new byte[byteBuf.writerIndex()];
        byteBuf.readBytes(bytes);
        System.out.println("bytes   " + Arrays.toString(bytes));
        //反序列化
        ByteBuf buffer2 = Unpooled.wrappedBuffer(bytes);
        System.out.println("id  " + buffer2.readInt());
        System.out.println("age  " + buffer2.readDouble());
    }
}
