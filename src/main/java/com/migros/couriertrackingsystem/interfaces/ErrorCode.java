package com.migros.couriertrackingsystem.interfaces;

public enum ErrorCode {
    COURIERNOTVALID("CTS1001"),
    TIMENOTVALID("CTS1002"),
    LATITUDENOTVALID("CTS1003"),
    LONGITUDENOTVALID("CTS1004");

    private String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String code() {
        return this.code;
    }
}