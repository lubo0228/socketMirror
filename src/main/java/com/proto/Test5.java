package com.proto;

import java.util.Arrays;

/**
 * Created by L-DELL on 2018/8/14.
 */
public class Test5 {
    public static void main(String[] args) {
        Player player = new Player();
        player.setId(101);
        player.setName("peter");
        player.getSkills().add(1101);
        player.getGolden().setNum(99999);
        byte[] bytes = player.getBytes();
        System.out.println("bytes  " + Arrays.toString(bytes));

        Player player2 = new Player();
        player2.readFromBytes(bytes);
        System.out.println("id:" + player2.getId() + " name:" + player2.getName() + " skills:" +Arrays.toString(player2.getSkills().toArray()) + " golden:" + player2.getGolden().getNum());
    }
}
