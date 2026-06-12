package com.bank.account_service.domain.exception;

import org.springframework.http.HttpStatus;

public abstract class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }

    public abstract HttpStatus getStatus();
}
