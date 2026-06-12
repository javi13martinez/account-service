package com.bank.account_service.domain.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends ApiException {

    public ResourceNotFoundException(String resource, String field, String value) {
        super(resource + " no encontrado por " + field + ": " + value);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
