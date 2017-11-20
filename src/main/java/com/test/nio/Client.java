package com.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by mayn on 2017/11/20.
 */
public class Client {

    private static final String str = "你好啊！我是客户端。";

    public static void main(String[] args) {
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8379);
        SocketChannel sc = null;
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            //打开通道
            sc = SocketChannel.open();
            //建立连接
            sc.connect(address);
            byte[] bytes = str.getBytes();
            //把输入的数据放入buffer缓冲区
            buffer.put(bytes);
            //复位操作
            buffer.flip();
            //将buffer的数据写入通道
            sc.write(buffer);
            //清空缓冲区中的数据
            buffer.clear();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(sc != null) {
                try {
                    sc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
