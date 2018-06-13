package com.globalmate.exception;

public class GMIllegalArgumentException extends GMRuntimeException {
    public GMIllegalArgumentException(String msg) {
        super(msg);
    }

    @Override
    protected int getCode() {
        return 0;
    }
}
