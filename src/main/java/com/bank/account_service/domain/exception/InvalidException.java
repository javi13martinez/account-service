package com.bank.account_service.domain.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class InvalidException extends ApiException {

    public InvalidException(String resource, String field, String value) {
        super(value + " es inválido para campo " + field + " de " + resource);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
