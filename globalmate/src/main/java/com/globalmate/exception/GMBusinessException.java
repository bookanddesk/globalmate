package com.globalmate.exception;

public abstract class GMBusinessException extends Exception {

    protected GMBusinessException(String msg) {
        super(msg);
    }

    protected GMBusinessException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

}
