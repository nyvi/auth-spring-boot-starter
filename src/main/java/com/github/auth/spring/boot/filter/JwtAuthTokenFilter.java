package com.github.auth.spring.boot.filter;

import com.github.auth.spring.boot.domain.dto.UserInfo;
import com.github.auth.spring.boot.util.JwtUtils;
import com.github.auth.spring.boot.util.LoginUserHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author czk
 * @date 2019-12-03
 */
@Component
public class JwtAuthTokenFilter extends OncePerRequestFilter {

    private static final String TOKEN_PREFIX = "Bearer ";

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String AUTHORIZATION_TOKEN = "access_token";

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
        @NonNull FilterChain chain) throws IOException, ServletException {

        String authToken = resolveToken(request);

        if (StringUtils.isNotBlank(authToken)) {

            UserInfo userInfo = JwtUtils.parseAndValidate(authToken);

            if (Objects.nonNull(userInfo) && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = parseToken(userInfo);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                // 设置当前线程全局登陆信息
                LoginUserHelper.setUser(userInfo);
                // 存入日志中
                MDC.put("userName", userInfo.getUserName());
            }

        }

        try {
            chain.doFilter(request, response);
        } finally {
            // 移除登陆信息
            LoginUserHelper.remove();
            // 移除MDC
            MDC.clear();
        }
    }

    /**
     * Get token from header
     */
    private String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.startsWith(bearerToken, TOKEN_PREFIX)) {
            return StringUtils.removeStart(bearerToken, TOKEN_PREFIX);
        }

        String jwt = request.getParameter(AUTHORIZATION_TOKEN);
        if (StringUtils.isNotBlank(jwt)) {
            return jwt;
        }

        return null;
    }

    private UserDetails parseToken(UserInfo userInfo) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (!CollectionUtils.isEmpty(userInfo.getRoleList())) {
            userInfo.getRoleList().forEach(r -> authorities.add(new SimpleGrantedAuthority(r)));
        }
        return new User(userInfo.getId().toString(), "", authorities);
    }
}
