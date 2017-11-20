package com.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by mayn on 2017/11/20.
 *
 * NIO的通信步骤 ①创建ServerSocketChannel，为其配置非阻塞模式。
 ②绑定监听，配置TCP参数，录入backlog大小等。
 ③创建一个独立的IO线程，用于轮询多路复用器Selector。
 ④创建Selector，将之前创建的ServerSocketChannel注册到Selector上，并设置监听标识位SelectionKey.OP_ACCEPT。
 ⑤启动IO线程，在循环体中执行Selector.select()方法，轮询就绪的通道。
 ⑥当轮询到处于就绪状态的通道时，需要进行操作位判断，如果是ACCEPT状态，说明是新的客户端接入，则调用accept方法接收新的客户端。
 ⑦设置新接入客户端的一些参数，如非阻塞，并将其继续注册到Selector上，设置监听标识位等。
 ⑧如果轮询的通道标识位是READ，则进行读取，构造Buffer对象等。
 ⑨更细节的问题还有数据没发送完成继续发送的问题......
 */
public class NIOServer implements Runnable {

    private Selector selector;
    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    public NIOServer(int port){
        try {
            //1 打开多复用器
            selector = Selector.open();
            //2 打开服务器通道
            ServerSocketChannel ssc = ServerSocketChannel.open();
            //3 设置服务器通道为非阻塞方式
            ssc.configureBlocking(false);
            //4 绑定地址
            ssc.bind(new InetSocketAddress(port));
            //5 把服务器通道注册到多路复用选择器上，并监听阻塞状态
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server start, port：" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @Override
    public void run() {
        while (true) {
            try {
                //1 必须让多路复用选择器开始监听
                selector.select();
                //2 返回所有已经注册到多路复用选择器上的通道的SelectionKey
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                //3 遍历keys  
                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    keys.remove();
                    if(key.isValid()) { //如果key的状态是有效的  
                        if(key.isAcceptable()) { //如果key是阻塞状态，则调用accept()方法  
                            accept(key);
                        }
                        if(key.isReadable()) { //如果key是可读状态，则调用read()方法  
                            read(key);
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void accept(SelectionKey key) throws IOException {
        //1 获取服务器通道
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        //2 执行阻塞方法
        SocketChannel sc = ssc.accept();
        //3 设置阻塞模式为非阻塞
        sc.configureBlocking(false);
        //4 注册到多路复用选择器上，并设置读取标识
        sc.register(selector, SelectionKey.OP_READ);
    }

    private void read(SelectionKey key) throws IOException {
        //1 清空缓冲区中的旧数据
        buffer.clear();
        //2 获取之前注册的SocketChannel通道
        SocketChannel sc = (SocketChannel) key.channel();
        //3 将sc中的数据放入buffer中
        int count = sc.read(buffer);
        if(count == -1) { // == -1表示通道中没有数据
            key.channel().close();
            key.cancel();
            return;
        }
        //读取到了数据，将buffer的position复位到0
        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        //将buffer中的数据写入byte[]中
        buffer.get(bytes);
        String body = new String(bytes).trim();
        System.out.println("Server：" + body);
    }

    public static void main(String[] args) {
        new Thread(new NIOServer(8379)).start();
    }
}
