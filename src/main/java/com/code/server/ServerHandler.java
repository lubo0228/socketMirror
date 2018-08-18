package com.code.server;

import com.code.constant.StateCode;
import com.code.model.Request;
import com.code.model.Response;
import com.code.module.fuben.request.FightRequest;
import com.code.module.fuben.response.FightResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created by mayn on 2017/11/20.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
//        channelHandlerContext.write(1991);
//        channelHandlerContext.flush();
        System.out.println(msg);
        Request request = (Request)msg;
        if (request.getModule() == 1) {
            if (request.getCmd() == 1) {
                FightRequest fightRequest = new FightRequest();
                fightRequest.readFromBytes(request.getData());
                System.out.println("fubenId:" + fightRequest.getFubenId());
                System.out.println("count:" + fightRequest.getCount());
                //response
                Response response = new Response();
                response.setStateCode(StateCode.SUCCESS);
                response.setModule((short)1);
                response.setCmd((short)1);
                FightResponse fightResponse = new FightResponse();
                fightResponse.setGold(9999);
                response.setData(fightResponse.getBytes());
                channelHandlerContext.writeAndFlush(response);
            }
        }
    }


}
