package com.github.auth.spring.boot.domain.entity;

import java.io.Serializable;

/**
 * @author czk
 * @date 2019-11-25
 */
public class SysUserRoleDO implements Serializable {

    private static final long serialVersionUID = 3279960948485397777L;

    private Long userId;

    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
