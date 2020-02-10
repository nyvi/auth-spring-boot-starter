package com.github.auth.spring.boot.domain.dto;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @author czk
 * @date 2019-12-05
 */
public class MenuInfoDTO implements Serializable {

    private static final long serialVersionUID = 2063075614081200231L;
    private Long id;

    private Long parentId;

    private String menuName;

    private String path;

    private String permission;

    private Integer type;

    private String icon;

    private Integer sortNo;

    private Integer enableState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
