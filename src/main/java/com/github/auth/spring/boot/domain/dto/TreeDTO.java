package com.github.auth.spring.boot.domain.dto;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * @author czk
 * @date 2019-12-05
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeDTO implements Serializable {

    private static final long serialVersionUID = 6724494437434241950L;

    private String key;

    private String title;

    private List<TreeDTO> children;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TreeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<TreeDTO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
