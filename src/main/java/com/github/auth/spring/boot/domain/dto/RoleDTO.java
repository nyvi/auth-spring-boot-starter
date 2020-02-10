package com.github.auth.spring.boot.domain.dto;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @author czk
 * @date 2019-12-05
 */
public class RoleDTO implements Serializable {

    private static final long serialVersionUID = -76093199687246151L;

    private Long id;

    private String roleName;

    private Integer sortNo;

    private Integer enableState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Integer getEnableState() {
        return enableState;
    }

    public void setEnableState(Integer enableState) {
        this.enableState = enableState;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
