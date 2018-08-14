package com.proto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TestJavaSerialize {
    public static void main(String[] args) throws Exception {
        byte[] bytes = toBytes();
        toPlayer(bytes);
    }

    private static byte[] toBytes() throws Exception{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        JavaPlayer javaPlayer = new JavaPlayer();
        javaPlayer.setPlayerId(101);
        javaPlayer.setAge(20);
        javaPlayer.setName("peter");
        List<Integer> skills = new LinkedList<>();
        skills.add(1001);
        javaPlayer.setSkills(skills);
        objectOutputStream.writeObject(javaPlayer);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        System.out.println(Arrays.toString(bytes));
        return bytes;
    }

    private static JavaPlayer toPlayer(byte[] bytes) throws Exception {
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
        JavaPlayer javaPlayer = (JavaPlayer)objectInputStream.readObject();
        System.out.println(javaPlayer.getPlayerId());
        System.out.println(javaPlayer.getAge());
        System.out.println(javaPlayer.getName());
        System.out.println(javaPlayer.getSkills().get(0));
        return javaPlayer;
    }
}
