package com.globalmate.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.Errors;

import java.util.stream.Collectors;

public abstract class GMRuntimeException extends RuntimeException {

    protected GMRuntimeException(String msg) {
        super(msg);
    }

    protected GMRuntimeException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    protected GMRuntimeException(Errors errors) {
        super(errors.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(",")));
    }


    /**
     * 获取错误码
     *
     * @return 报文的错误code, 错误代码 5位数字, 前三位和http status保持一致, 后两位为自定义的错误码
     */
    protected abstract int getCode();


    /**
     * 获取http的错误code
     *
     * @return http错误码
     */
    protected final int getHttpCode() {
        int code = getCode();
        if (code > 9999) {
            return code/100;
        }
        if (code > 1000) {
            return code/10;
        }
        return code;
    }


}
