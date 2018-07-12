package com.globalmate.service.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XingJiajun
 * @Date 2018/7/12 10:30
 * @Description
 */
public abstract class AssistHandler<T,U,S> {

    protected AssistHandler nextHandler;

    protected ThreadLocal<Map<String, Object>> local = ThreadLocal.withInitial(() -> new HashMap<>(1));

    public void setNextHandler(AssistHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handle(T t, U u, S s);

    protected Map<String, Object> getLocal() {
        return local.get();
    }

    public void resetLocal() {
        getLocal().clear();
    }

    protected void setLocal(String key, String value) {
        getLocal().put(key, value);
    }

    public Object getLocal(String key) {
        return getLocal().get(key);
    }

    public Object removeLocal(String key) {
        return getLocal().remove(key);
    }

}
