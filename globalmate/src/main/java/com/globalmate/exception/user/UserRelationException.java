package com.globalmate.exception.user;

import com.globalmate.exception.GMBusinessException;

public class UserRelationException extends GMBusinessException {
    public UserRelationException(String msg) {
        super(msg);
    }

    protected int getCode() {
        return 0;
    }
}
