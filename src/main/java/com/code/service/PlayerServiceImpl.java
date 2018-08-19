package com.code.service;

import org.springframework.stereotype.Component;

/**
 * 玩家服务
 */
@Component
public class PlayerServiceImpl implements PlayerService {


    @Override
    public void register() {
        System.out.println("register");
    }

    @Override
    public void login() {
        System.out.println("login");
    }
}
