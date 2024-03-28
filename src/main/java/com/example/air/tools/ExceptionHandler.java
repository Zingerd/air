package com.example.air.tools;

import lombok.Builder;

import javax.management.OperationsException;

public class ExceptionHandler extends OperationsException {
    Integer code;
    Integer name;


    public ExceptionHandler(String name) {
        super(name);
    }
    public ExceptionHandler() {
    }
}
