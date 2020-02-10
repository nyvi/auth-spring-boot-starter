package com.github.auth.spring.boot.service;

import com.github.auth.spring.boot.domain.req.LoginRequest;

/**
 * @author czk
 * @date 2019-11-25
 */
public interface UserService {

    /**
     * 登陆
     * 登陆成功缓存UserDTO对象
     *
     * @param request 登陆信息
     * @return token
     */
    String login(LoginRequest request);

    /**
     * 刷新token
     *
     * @return 新token
     */
    String refresh();

}
