package com.jetbovapi.domain.exception;

public class BusinessException extends Exception {
    public BusinessException(String errorMessage) {
        super(errorMessage);
    }
}
