package com.github.auth.spring.boot.util;

import com.github.auth.spring.boot.domain.dto.UserInfo;
import org.springframework.lang.NonNull;

/**
 * @author czk
 * @date 2019-12-03
 */
public class LoginUserHelper {

    private final static ThreadLocal<UserInfo> USER_INFO = new ThreadLocal<>();

    public static void setUser(@NonNull UserInfo user) {
        USER_INFO.set(user);
    }

    public static UserInfo getUser() {
        return LoginUserHelper.USER_INFO.get();
    }

    public static void remove() {
        USER_INFO.remove();
    }
}
