package com.github.auth.spring.boot.control;

import com.github.auth.spring.boot.service.UserService;
import com.github.auth.spring.boot.util.Result;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author czk
 * @date 2019-11-25
 */
@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserControl {

    private final UserService userService;

    @PostMapping("saveOrUpdate")
    @PreAuthorize("@pms.hasPermission('user:add')")
    Result<Boolean> saveOrUpdate() {
        return null;
    }
}
