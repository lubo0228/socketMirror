package com.proto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

/**
 * Created by L-DELL on 2018/8/13.
 */
public class Test1 {
    public static void main(String[] args) throws Exception {
        //序列化
        int id = 101;
        int age = 20;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(int2Bytes(id));
        byteArrayOutputStream.write(int2Bytes(age));
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        System.out.println("idBytes   " + Arrays.toString(byteArray));
        //反序列化
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        byte[] idBytes = new byte[4];
        byteArrayInputStream.read(idBytes);
        System.out.println("id  " + bytes2int(idBytes));
        byte[] ageBytes = new byte[4];
        byteArrayInputStream.read(ageBytes);
        System.out.println("age   " + bytes2int(ageBytes));
    }

    private static byte[] int2Bytes(int i) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (i >> 3 * 8);
        bytes[1] = (byte) (i >> 2 * 8);
        bytes[2] = (byte) (i >> 1 * 8);
        bytes[3] = (byte) (i >> 0 * 8);
        return bytes;
    }

    private static int bytes2int(byte[] bytes) {
        return (bytes[0] << 3 * 8) |
                (bytes[1] << 3 * 8) |
                (bytes[2] << 2 * 8) |
                (bytes[3] << 0 * 8);
    }
}
