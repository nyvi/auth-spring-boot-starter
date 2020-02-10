package com.github.auth.spring.boot.domain.req;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

/**
 * @author czk
 * @date 2019-12-05
 */
public class GrantAuthRequest implements Serializable {

    private static final long serialVersionUID = -6669126885039622410L;

    @NotNull
    private Long grantId;

    private Set<Long> authSet;

    public Long getGrantId() {
        return grantId;
    }

    public void setGrantId(Long grantId) {
        this.grantId = grantId;
    }

    public Set<Long> getAuthSet() {
        return authSet;
    }

    public void setAuthSet(Set<Long> authSet) {
        this.authSet = authSet;
    }
}
