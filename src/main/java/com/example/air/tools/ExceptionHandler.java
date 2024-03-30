package com.example.air.tools;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;

public class ExceptionHandler extends ResponseStatusException {
    private final String message;
    private final HttpStatus status;

    public ExceptionHandler(HttpStatus status, String message) {
        super(status, message);
        this.message = message;
        this.status = status;
    }

    public static EntityNotFoundException exceptionHandlerRequest(Exception e) {
        if (e instanceof EntityNotFoundException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }
}
