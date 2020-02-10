package com.github.auth.spring.boot.domain.dto;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;

/**
 * @author czk
 * @date 2019-12-05
 */
public class AuthDTO implements Serializable {

    private static final long serialVersionUID = -2263682462652501021L;

    private List<Long> grantIds;

    private List<TreeDTO> menuList;

    public List<Long> getGrantIds() {
        return grantIds;
    }

    public void setGrantIds(List<Long> grantIds) {
        this.grantIds = grantIds;
    }

    public List<TreeDTO> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<TreeDTO> menuList) {
        this.menuList = menuList;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
