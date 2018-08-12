package com.proto;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.Arrays;

public class TestProtoBuf {

    public static void main(String[] args) throws InvalidProtocolBufferException {
        byte[] bytes = toBytes();
        toPlayer(bytes);
    }

    private static byte[] toBytes() {
        PlayerModule.PBPlayer.Builder builder = PlayerModule.PBPlayer.newBuilder();
        builder.setPlayerId(101).setAge(20).setName("peter").addSkills(1001);
        PlayerModule.PBPlayer player = builder.build();
        byte[] bytes = player.toByteArray();
        System.out.println(Arrays.toString(bytes));
        return bytes;
    }

    private static PlayerModule.PBPlayer toPlayer(byte[] bytes) throws InvalidProtocolBufferException {
        PlayerModule.PBPlayer pbPlayer = PlayerModule.PBPlayer.parseFrom(bytes);
        System.out.println(pbPlayer.getPlayerId());
        System.out.println(pbPlayer.getAge());
        System.out.println(pbPlayer.getName());
        System.out.println(pbPlayer.getSkills(0));
        return pbPlayer;
    }
}
