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
public class RoleSaveRequest implements Serializable {

    private static final long serialVersionUID = -4541592043368429298L;

    private Long id;

    @NotBlank
    @Length(max = 20)
    private String roleName;

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
}
