package com.github.auth.spring.boot.exception;

/**
 * @author czk
 * @date 2019-05-16
 */
public class AuthException extends RuntimeException {

    private static final long serialVersionUID = -6385109629952823295L;

    public AuthException() {

    }

    public AuthException(String msg) {
        super(msg);
    }
}