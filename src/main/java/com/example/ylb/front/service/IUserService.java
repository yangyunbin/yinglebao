package com.example.ylb.front.service;

import java.util.Map;

public interface IUserService {
    /**
     * 手机号注册
     * @param maps
     */
    void phoneRegister(Map<String, String> maps);

    /**
     * 登录操作
     * @param maps
     * @return
     */
    Map<String, Object> loginPhone(Map<String, String> maps);
}
