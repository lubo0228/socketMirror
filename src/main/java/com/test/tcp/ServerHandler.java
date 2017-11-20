package com.test.tcp;

import java.io.*;
import java.net.Socket;

/**
 * Created by mayn on 2017/11/15.
 */
public class ServerHandler implements Runnable {

    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }



    @Override
    public void run() {
        //获取客户端ip地址和客户端名称
        String ip = socket.getInetAddress().getHostAddress();
        String name = socket.getInetAddress().getHostName();
        System.out.println("接受到来自名称为：" + name + " 和ip为：" + ip + "的连接");
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            //连接建立后，通过输入流读取客户端发送的请求信息
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //通过输出流向客户端发送响应信息
            pw = new PrintWriter(socket.getOutputStream());
            while (true) {
                String info = br.readLine();
                if (info == null)
                    break;
                System.out.println("客户端发送的消息：" + info);
                //write() println()不行  println()可以
                pw.println("服务器端响应了客户端请求....");
                pw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (pw != null) {
                try {
                    pw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            socket = null;
        }
    }
}
