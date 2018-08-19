package com.code.service;

import com.code.annotion.SocketCommand;
import com.code.annotion.SocketModule;

/**
 * 玩家服务
 */
@SocketModule(module = 1)
public interface PlayerService {


    /**
     * 登录注册用户
     *
     * @return
     */
    @SocketCommand(cmd = 1)
    void register();


    /**
     * 登录
     *
     * @return
     */
    @SocketCommand(cmd = 2)
    void login();

}
