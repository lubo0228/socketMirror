package com.proto;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by L-DELL on 2018/8/13.
 */
public class Test2 {

    public static void main(String[] args) {
        //序列化
        int id = 101;
        int age = 20;
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        byteBuffer.putInt(id);
        byteBuffer.putInt(age);
        byte[] bytes = byteBuffer.array();
        System.out.println("bytes   " + Arrays.toString(bytes));
        //反序列化
        ByteBuffer byteBuffer2 = ByteBuffer.wrap(bytes);
        System.out.println("id  " + byteBuffer2.getInt());
        System.out.println("age  " + byteBuffer2.getInt());

    }
}
