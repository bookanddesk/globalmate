package com.globalmate.exception;

public class GMIllegalArgumentException extends IllegalArgumentException {
    public GMIllegalArgumentException(String msg) {
        super(msg);
    }

    protected int getCode() {
        return 0;
    }
}
