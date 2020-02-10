package com.github.auth.spring.boot.domain.dto;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;

/**
 * @author czk
 * @date 2019-11-25
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 8874375197802874201L;

    private Long id;

    private String userName;

    private String phone;

    private List<String> roleList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
