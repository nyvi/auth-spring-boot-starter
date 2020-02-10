package com.github.auth.spring.boot.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.auth.spring.boot.domain.dto.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

/**
 * @author czk
 * @date 2019-11-25
 */
public class JwtUtils {

    /**
     * 加密算法
     */
    private static final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS512;

    /**
     * 密钥
     */
    private static final Key SECRET_KEY = deserializeKey();

    /**
     * 加密
     *
     * @param userInfo 加密数据
     * @return token
     */
    public static String createJsonWebToken(UserInfo userInfo) {
        return Jwts.builder().setSubject(JSON.toJSONString(userInfo))
            // 两小时过期
            .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
            .signWith(ALGORITHM, SECRET_KEY).compact();
    }

    /**
     * 解密
     *
     * @param token 密文
     * @return 实体
     */
    public static UserInfo parseAndValidate(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        String subject = claims.getSubject();
        return JSONObject.parseObject(subject, UserInfo.class);
    }

    private static Key deserializeKey() {
        byte[] decodedKey = Base64.getDecoder().decode("seenew");
        return new SecretKeySpec(decodedKey, ALGORITHM.getJcaName());
    }

}
