package com.test.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by mayn on 2017/11/14.
 */
public class TCPServer {

    private static int PORT = 10086;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        System.out.println("服务端启动了");
        try {
            //创建ServerSocket对象，绑定监听端口
            serverSocket = new ServerSocket(PORT);
                   /* //通过accept()方法监听客户端请求
                    Socket socket = serverSocket.accept();
                    //启动一个线程来处理客户端请求
                    new Thread(new ServerHandler(socket)).start();*/
            //线程池
            Socket socket = null;
            HandlerExecutorPool pool = new HandlerExecutorPool(50, 1000);
            while (true) {
                socket = serverSocket.accept();
                pool.execute(new ServerHandler(socket));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            serverSocket = null;
        }


    }

}
