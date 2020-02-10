package com.github.auth.spring.boot.domain.req;

import java.io.Serializable;

/**
 * @author czk
 * @date 2019-12-05
 */
public class PwdRequest implements Serializable {

    private static final long serialVersionUID = 9177456785017233706L;
    private Long id;

    private String oldPwd;

    private String newPwd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }
}
