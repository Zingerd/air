package com.example.air.tools;

public enum StatusFlightEnum {
    DELAYED("DELAYED"),
    ACTIVE("ACTIVE"),
    PENDING("PENDING"),
    COMPLETED("COMPLETED");


    private final String name;

    StatusFlightEnum(String name) {
        this.name = name;
    }

    public String getStatus() {
        return name;
    }
}
