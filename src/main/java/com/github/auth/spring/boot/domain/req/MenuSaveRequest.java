package com.github.auth.spring.boot.domain.req;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author czk
 * @date 2019-12-05
 */
public class MenuSaveRequest implements Serializable {

    private static final long serialVersionUID = -8148056508500393110L;
    private Long id;

    @NotNull
    private Long parentId;

    @NotBlank
    @Length(max = 20)
    private String menuName;

    @Length(max = 20)
    private String path;

    @Length(max = 50)
    private String permission;

    @Range(min = 1, max = 2)
    private Integer type;

    @Length(max = 20)
    private String icon;

    @NotNull
    private Integer sortNo;

    @Range(min = 0, max = 1)
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
}
