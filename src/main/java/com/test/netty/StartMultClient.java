package com.test.netty;

import java.util.Scanner;

public class StartMultClient {

    public static void main(String[] args) {
        MultClient multClient = new MultClient();
        multClient.init(5);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String msg = scanner.nextLine();
                multClient.nextChannel().writeAndFlush(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
