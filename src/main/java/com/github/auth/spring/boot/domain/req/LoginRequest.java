package com.github.auth.spring.boot.domain.req;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author czk
 * @date 2019-11-25
 */
public class LoginRequest implements Serializable {

    private static final long serialVersionUID = -5448757806138470856L;

    @NotEmpty(message = "账户不能为空")
    private String account;

    @NotEmpty(message = "密码不能为空")
    private String pwd;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
