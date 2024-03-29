package com.example.air.tools;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

@Getter
public class ExceptionHandler extends HttpStatusCodeException {
    private final String message;
    private final HttpStatus status;

    public ExceptionHandler(HttpStatus status, String message) {
        super(status, message);
        this.message = message;
        this.status = status;
    }
}
