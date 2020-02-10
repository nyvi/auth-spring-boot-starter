package com.github.auth.spring.boot.domain.dto;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author czk
 * @date 2019-12-05
 */
public class UserRoleDTO implements Serializable {

    private static final long serialVersionUID = 1082798268048348554L;

    private Set<Long> checkSet;

    private List<RoleDTO> roleList;

    public Set<Long> getCheckSet() {
        return checkSet;
    }

    public void setCheckSet(Set<Long> checkSet) {
        this.checkSet = checkSet;
    }

    public List<RoleDTO> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleDTO> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
