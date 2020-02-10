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
public class UserSaveRequest implements Serializable {

    private static final long serialVersionUID = -4123318960922793447L;

    private Long id;

    @NotBlank
    @Length(max = 20)
    private String userName;

    @NotNull
    private String phone;

    @Range(min = 0, max = 1)
    private Integer enableState;

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

    public Integer getEnableState() {
        return enableState;
    }

    public void setEnableState(Integer enableState) {
        this.enableState = enableState;
    }
}
