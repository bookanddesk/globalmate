package com.globalmate.exception.user;

import com.globalmate.exception.GMRuntimeException;

public class UserTokenFailException extends GMRuntimeException {
    public UserTokenFailException(String msg) {
        super(msg);
    }

    @Override
    protected int getCode() {
        return 0;
    }
}
