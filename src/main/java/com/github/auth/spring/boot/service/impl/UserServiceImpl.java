package com.github.auth.spring.boot.service.impl;

import com.github.auth.spring.boot.dao.UserDAO;
import com.github.auth.spring.boot.domain.dto.UserDTO;
import com.github.auth.spring.boot.domain.dto.UserInfo;
import com.github.auth.spring.boot.domain.req.LoginRequest;
import com.github.auth.spring.boot.service.UserService;
import com.github.auth.spring.boot.util.JwtUtils;
import com.github.auth.spring.boot.util.LoginUserHelper;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Set;

/**
 * @author czk
 * @date 2019-11-25
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDao;

    private final StringRedisTemplate redisTemplate;

    /**
     * 加密盐值
     */
    private static final String SALT_VALUE = "seenew";

    @Override
    public String login(LoginRequest request) {

        UserDTO user = userDao.getUser(request.getAccount());

        Assert.notNull(user, "用户名不存在或者密码错误");

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Assert.isTrue(bCryptPasswordEncoder.matches(SALT_VALUE + request.getPwd(), user.getPwd()), "用户名不存在或者密码错误");

        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(user, userInfo);

        Set<String> permissionSet = userDao.listUserPermission(user.getId());

        if (!permissionSet.isEmpty()) {
            SetOperations<String, String> setOperations = redisTemplate.opsForSet();
            String[] auth = permissionSet.toArray(new String[0]);
            String key = "auth:" + user.getId();
            redisTemplate.delete(key);
            setOperations.add(key, auth);
        }

        return JwtUtils.createJsonWebToken(userInfo);
    }

    @Override
    public String refresh() {
        UserInfo userInfo = LoginUserHelper.getUser();
        return JwtUtils.createJsonWebToken(userInfo);
    }
}
