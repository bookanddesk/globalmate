package com.globalmate.exception.user;

import com.globalmate.exception.GMBusinessException;

public class UserCheckFailException extends GMBusinessException {
    public UserCheckFailException(String msg) {
        super(msg);
    }
}
