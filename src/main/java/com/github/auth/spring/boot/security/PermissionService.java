package com.github.auth.spring.boot.security;

import com.github.auth.spring.boot.util.LoginUserHelper;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author czk
 * @date 2019-12-03
 */
@Component("pms")
public class PermissionService {

    private final StringRedisTemplate redisTemplate;

    public PermissionService(StringRedisTemplate redisTemplate) {this.redisTemplate = redisTemplate;}

    /**
     * 判断接口是否有权限
     *
     * @param permission 权限码
     * @return 有权限返回true, 没有返回false
     */
    public boolean hasPermission(String permission) {
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        Boolean member = setOperations.isMember(this.getKey(), permission);
        return member != null && member;
    }

    private String getKey() {
        Long userId = LoginUserHelper.getUser().getId();
        return "auth:" + userId;
    }

}
