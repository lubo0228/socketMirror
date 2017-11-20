package com.test.tcp;

import java.io.*;
import java.net.Socket;

/**
 * Created by mayn on 2017/11/14.
 */
public class TCPClient {

    private static int PORT = 10086;
    private static String IP = "127.0.0.1";


    public static void main(String[] args) throws Exception {
        System.out.println("客户端启动了");
        //创建Socket对象，指明需要连接的服务器的地址和端口号
        Socket socket = null;
        PrintWriter pw = null;
        BufferedReader br = null;
        try {
            //连接建立后，通过输出流想服务器端发送请求信息
            socket = new Socket(IP, PORT);
            pw = new PrintWriter(socket.getOutputStream());
            //通过输入流获取服务器响应的信息
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw.println("客户端请求了服务器....");
            pw.flush();
            String info = br.readLine();
            System.out.println("服务器说：" + info);
        } catch (Exception e) {
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
            } else {
                socket = null;
            }
        }
    }
}
