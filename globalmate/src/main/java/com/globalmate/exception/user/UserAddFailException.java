package com.globalmate.exception.user;

import com.globalmate.exception.GMRuntimeException;

public class UserAddFailException extends GMRuntimeException {
    public UserAddFailException(String msg) {
        super(msg);
    }

    @Override
    protected int getCode() {
        return 0;
    }
}
