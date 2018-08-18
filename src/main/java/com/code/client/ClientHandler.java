package com.code.client;

import com.code.model.Response;
import com.code.module.fuben.response.FightResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by mayn on 2017/11/20.
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
        System.out.println(msg);
        Response response = (Response)msg;
        if (response.getModule() == 1) {
            if (response.getCmd() == 1) {
                FightResponse fightRequest = new FightResponse();
                fightRequest.readFromBytes(response.getData());
                System.out.println("gold:" + fightRequest.getGold());
            }
        }
    }

}
