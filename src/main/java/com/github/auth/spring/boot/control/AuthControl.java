package com.github.auth.spring.boot.control;

import com.github.auth.spring.boot.domain.req.LoginRequest;
import com.github.auth.spring.boot.service.UserService;
import com.github.auth.spring.boot.util.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author czk
 * @date 2019-11-25
 */
@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthControl {

    private final UserService userService;

    @PostMapping("refresh")
    Result<String> refresh() {
        return Result.success(userService.refresh());
    }

    @PostMapping("login")
    Result<String> login(@RequestBody LoginRequest request) {
        return Result.execute(request, userService::login);
    }
}
