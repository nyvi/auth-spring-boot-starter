package com.github.auth.spring.boot.config;

import com.github.auth.spring.boot.filter.JwtAuthTokenFilter;
import com.github.auth.spring.boot.security.JwtAuthExceptionPoint;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;
import java.util.Set;

/**
 * @author czk
 * @date 2019-12-03
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private JwtAuthExceptionPoint jwtAuthExceptionPoint;

    @Value("ignore.url:/")
    private String[] ignoreUrl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/auth/login").permitAll()
            .anyRequest().authenticated().and()
            // custom token authorize exception handler
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthExceptionPoint).and()
            // since we use jwt, session is not necessary
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            // since we use jwt, csrf is not necessary
            .csrf().disable();
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // disable cache
        http.headers().cacheControl();
    }

    @Override
    public void configure(WebSecurity web) {
        Set<String> ignoreSet = Sets.newHashSet(
            "/**/*.css", "/**/*.js", "/webjars/**", "/index.html", "/**/*.png", "/auth/login",
            "/swagger-ui.html", "/swagger-resources", "/swagger-resources/configuration/ui",
            "/swagger-resources/configuration/security", "/swagger-resources", "/v2/api-docs",
            "/"
        );
        Collections.addAll(ignoreSet, ignoreUrl);
        String[] ignore = new String[ignoreSet.size()];
        ignoreSet.toArray(ignore);
        web.ignoring().antMatchers(ignore).antMatchers(HttpMethod.OPTIONS);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
