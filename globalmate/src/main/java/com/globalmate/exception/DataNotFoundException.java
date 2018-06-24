package com.globalmate.exception;

public class DataNotFoundException extends GMRuntimeException {
    public DataNotFoundException(String msg) {
        super(msg);
    }

    @Override
    protected int getCode() {
        return 0;
    }
}
