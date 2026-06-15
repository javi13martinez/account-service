package com.bank.account_service.infrastructure.entrypoint.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bank.account_service.domain.exception.ApiException;
import com.bank.account_service.domain.exception.InvalidValueException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleError(ApiException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(new ErrorResponse(
                        ex.getStatus().value(),
                        ex.getStatus().name(),
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex) {

        ApiException apiException;

        Throwable cause = ex.getCause();

        if (cause instanceof InvalidFormatException ife
                && ife.getTargetType().isEnum()) {

            apiException = new InvalidValueException(
                    ife.getPath().get(0).getFieldName(),
                    String.valueOf(ife.getValue())
            );
            return handleError(apiException);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(
                            HttpStatus.BAD_REQUEST.value(),
                            HttpStatus.BAD_REQUEST.name(),
                            ex.getMessage()
                    ));
        }
    }

    public record ErrorResponse(
            int status,
            String error,
            String message
    ) {}
}
