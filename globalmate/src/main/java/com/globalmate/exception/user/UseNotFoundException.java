package com.globalmate.exception.user;

import com.globalmate.exception.GMRuntimeException;

public class UseNotFoundException extends GMRuntimeException {
    public UseNotFoundException(String msg) {
        super(msg);
    }

    @Override
    protected int getCode() {
        return 0;
    }
}
