package com.github.auth.spring.boot.domain.entity;

import java.io.Serializable;

/**
 * @author czk
 * @date 2019-11-25
 */
public class SysRoleMenuDO implements Serializable {

    private static final long serialVersionUID = -2055100561980262917L;

    private Long roleId;

    private Long menuId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
